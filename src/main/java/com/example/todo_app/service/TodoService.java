package com.example.todo_app.service;

import com.example.todo_app.todo.model.Todo;
import com.example.todo_app.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public Todo createTodo(Todo todo) {
        todo.setCreatedAt(LocalDateTime.now());
        todo.setUpdatedAt(LocalDateTime.now());
        return todoRepository.save(todo);
    }

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Optional<Todo> getTodoById(Long id) {
        return todoRepository.findById(id);
    }

    public Todo updateTodo(Long id, Todo todoDetails) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found"));

        todo.setTitle(todoDetails.getTitle());
        todo.setDescription(todoDetails.getDescription());
        todo.setCompleted(todoDetails.isCompleted());
        todo.setUpdatedAt(LocalDateTime.now());

        return todoRepository.save(todo);
    }

    public void deleteTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found"));
        todoRepository.delete(todo);
    }

    public List<Todo> searchTodos(String searchTerm) {
        return todoRepository.findByTitleContainingOrDescriptionContaining(searchTerm, searchTerm);
    }
}
