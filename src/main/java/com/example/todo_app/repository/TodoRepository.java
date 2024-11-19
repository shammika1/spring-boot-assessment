package com.example.todo_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.example.todo_app.todo.model.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByTitleContainingOrDescriptionContaining(String title, String description);
}
