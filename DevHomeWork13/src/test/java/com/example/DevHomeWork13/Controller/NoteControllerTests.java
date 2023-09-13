package com.example.DevHomeWork13.Controller;

import static org.junit.jupiter.api.Assertions.*;

import com.example.DevHomeWork13.entity.Note;
import com.example.DevHomeWork13.service.NoteService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest
@AutoConfigureMockMvc
public class NoteControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NoteService noteService;

    @Test
    public void testAddNote() throws Exception {
        Note noteToSave = new Note("Test Title", "Test Content");
        Note savedNote = new Note("Test Title", "Test Content");
        savedNote.setId(1L);

        Mockito.when(noteService.saveNote(Mockito.any(Note.class))).thenReturn(savedNote);

        mockMvc.perform(MockMvcRequestBuilders.post("/note/add")
                        .param("title", "Test Title")
                        .param("content", "Test Content"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/note/list"));

        Mockito.verify(noteService, Mockito.times(1)).saveNote(Mockito.any(Note.class));
    }
}