package com.example.springbootserver.todos.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springbootserver.todos.model.Todo;
import com.example.springbootserver.todos.model.TodoRepository;
import com.example.springbootserver.core.auth.session.MyUserDetails;
import com.example.springbootserver.core.exception.Exception400;
import com.example.springbootserver.core.exception.Exception403;
import com.example.springbootserver.core.exception.Exception500;
import com.example.springbootserver.todos.dto.TodoReq;
import com.example.springbootserver.todos.dto.TodoReq.TodoSave;

import lombok.extern.slf4j.Slf4j;

@Transactional(readOnly = true)
@Service
@Slf4j
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Transactional
    public List<Todo> findByUserId(MyUserDetails userDetails) {
        return todoRepository.findByUserId(userDetails.getUser().getId())
                .orElseThrow(() -> new Exception400(null, "조회 데이터가 없습니다."));
    }

    @Transactional
    public Todo findbyId(final Long id, MyUserDetails userDetails) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new Exception400(null, "조회 데이터가 없습니다."));
        if (todo.getUserId() != userDetails.getUser().getId()) {
            throw new Exception403("권한 필요");
        }
        return todo;

    }

    @Transactional
    public Todo save(final TodoReq.TodoSave todoSave, MyUserDetails userDetails) {
        todoSave.setUserId(userDetails.getUser().getId());
        try {
            Todo todo = TodoSave.toEntity(todoSave);
            todoRepository.save(todo);
            log.info("Todo 저장 완료, Id : " + todo.getId());
            return todo;
        } catch (Exception e) {
            throw new Exception500("저장에 실패했습니다.");
        }
    }

    @Transactional
    public Todo update(final TodoReq.TodoUpdate todoUpdate, MyUserDetails userDetails) {
        Todo todo = todoRepository.findById(todoUpdate.getId())
                .orElseThrow(() -> new Exception400(null, "조회 데이터가 없습니다."));
        if (todo.getUserId() != userDetails.getUser().getId()) {
            throw new Exception403("권한 필요");
        }
        todoUpdate.setUserId(userDetails.getUser().getId());
        try {
            todo = TodoReq.TodoUpdate.toEntity(todoUpdate);
            todoRepository.save(todo);
            log.info("Todo 수정 완료, Id : " + todo.getId());
            return todo;
        } catch (Exception e) {
            throw new Exception500("수정에 실패했습니다.");
        }
    }

    @Transactional
    public void delete(Long id, MyUserDetails userDetails) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new Exception400(null, "조회 데이터가 없습니다."));
        if (todo.getUserId() != userDetails.getUser().getId()) {
            throw new Exception403("권한 필요");
        }
        try {
            todoRepository.delete(todo);
            log.info("Todo delete 완료");
        } catch (Exception e) {
            log.error("Todo 삭제 실패, id " + id);
            throw new Exception500("삭제에 실패했습니다.");
        }
    }

    // @Transactional
    // public List<Todo> findAll() {
    // try {
    // List<Todo> result = todoRepository.findAll();

    // if (ObjectUtils.isEmpty(result)) {
    // result = new ArrayList<>();
    // }
    // return result;
    // } catch (Exception500 e) {
    // throw new Exception500("조회에 실패했습니다.");
    // }
    // }

}
