package com.yoon.spring.jspTodoApp.todo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Integer> {

	//difine Method to findByUserName
	public List<Todo> findByUsername(String username);
	
}
