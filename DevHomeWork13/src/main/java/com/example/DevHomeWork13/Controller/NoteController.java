package com.example.DevHomeWork13.Controller;

import com.example.DevHomeWork13.entity.Note;
import com.example.DevHomeWork13.service.NoteService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/note")
public class NoteController {

    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }



    @GetMapping("/list")
    public String listNotes(Model model) {
        List<Note> noteList = noteService.getAllNotes();
        model.addAttribute("notes", noteList);
        model.addAttribute("newNote", new Note());

        return "list";
    }

    @GetMapping("/edit")
    public String editNoteForm(@RequestParam long id, Model model) {
        Optional<Note> optionalNote = noteService.getNoteById(id);
        if (optionalNote.isEmpty()) {
            throw new IllegalArgumentException("Note with id " + id + " not found");
        }
        Note note = optionalNote.get();
        model.addAttribute("note", note);
        return "edit";
    }

    @PostMapping("/edit")
    public String editNoteSubmit(@ModelAttribute Note editedNote) {
        noteService.saveNote(editedNote);
        return "redirect:/note/list";
    }
    @GetMapping("/delete")
    public String deleteNoteForm(@RequestParam long id, Model model) {
        Optional<Note> optionalNote = noteService.getNoteById(id);
        if (optionalNote.isEmpty()) {
            throw new IllegalArgumentException("Note with id " + id + " not found");
        }
        Note note = optionalNote.get();
        model.addAttribute("note", note);
        return "delete";
    }

    @PostMapping("/delete")
    public String deleteNote(@RequestParam long id) {
        noteService.deleteNoteById(id);
        return "redirect:/note/list";
    }

    @GetMapping("/add")
    public String addNoteForm(Model model) {
        model.addAttribute("newNote", new Note());
        return "add";
    }

    @PostMapping("/add")
    public String addNoteSubmit(@ModelAttribute Note newNote) {
        noteService.saveNote(newNote);
        return "redirect:/note/list";
    }
}
