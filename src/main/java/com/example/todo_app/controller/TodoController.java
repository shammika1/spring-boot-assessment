package com.example.todo_app.controller;

import com.example.todo_app.todo.model.Todo;
import com.example.todo_app.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @PostMapping
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo) {
        return ResponseEntity.ok(todoService.createTodo(todo));
    }

    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodos() {
        return ResponseEntity.ok(todoService.getAllTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Long id) {
        return todoService.getTodoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @RequestBody Todo todo) {
        return ResponseEntity.ok(todoService.updateTodo(id, todo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        String successMessage = "Todo item with ID " + id + " has been deleted successfully.";
        return ResponseEntity.ok(successMessage);
    }


    @GetMapping("/search")
    public ResponseEntity<List<Todo>> searchTodos(@RequestParam String query) {
        return ResponseEntity.ok(todoService.searchTodos(query));
    }
}
