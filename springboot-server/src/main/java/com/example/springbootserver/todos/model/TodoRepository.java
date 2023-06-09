package com.example.springbootserver.todos.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long>{

    Optional<List<Todo>> findByUserId(Long userId);

    Optional<Todo> findById(Long id);

    Todo save(Todo todo);

    void deleteById(Long id);
}
