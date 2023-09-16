package com.scaler.taskmanager_spring_java.controller;

import com.scaler.taskmanager_spring_java.dtos.CreateNoteResponseDTO;
import com.scaler.taskmanager_spring_java.dtos.CreateNoteDTO;
import com.scaler.taskmanager_spring_java.entities.NoteEntity;
import com.scaler.taskmanager_spring_java.service.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks/{taskId}/notes")
public class NotesController {
    private final NoteService notesService;
    public NotesController(NoteService notesService) {
        this.notesService = notesService;
    }
    @GetMapping("")
    public ResponseEntity<List<NoteEntity>> getNotes(@PathVariable("taskId") Integer taskId) {
        var notes = notesService.getNotesForTask(taskId);
        if (notes == null) {
            return null;
        }
        return ResponseEntity.ok(notes);
    }

    @PostMapping("")
    public ResponseEntity<CreateNoteResponseDTO> addNote(
            @PathVariable("taskId") Integer taskId,
            @RequestBody CreateNoteDTO body
    ) {
        var note = notesService.addNoteForTask(taskId, body.getTitle(), body.getBody());
        return ResponseEntity.ok(new CreateNoteResponseDTO(taskId, note));
    }
}
