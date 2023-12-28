package com.yoon.spring.jspTodoApp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public class TodoService {

	private static List<Todo> todos = new ArrayList<>();

	//count initialize 0
	private static int todosCount = 0;
	
	static {
		todos.add(new Todo(++todosCount, "YOON", "Learn JSP Now!",
				LocalDate.now().plusYears(1), false));
		todos.add(new Todo(++todosCount, "YOON", "Learn NetWork Now!",
				LocalDate.now().plusYears(2), false));
		todos.add(new Todo(++todosCount, "YOON", "Learn Security Now!",
				LocalDate.now().plusYears(3), false));

	}
	
	//use return method Because todos is private
	public List<Todo> findByUsername(String username){
		
		Predicate<? super Todo> predicate = 
				todo-> todo.getUsername().equalsIgnoreCase(username);
		
		return todos.stream().filter(predicate).toList();
	}
	
	//add new Todo logic
	public void addTodo(String username, String description, LocalDate targetDate, boolean done) {
		Todo todo = new Todo(++todosCount, username, description, targetDate, done);
		todos.add(todo);
	}
	
	public void deleteByUserId(int id){
		//Todo --- todo   // todo.getid()==id
		Predicate<? super Todo> predicate = todo-> todo.getId()==id;
		todos.removeIf(predicate );
		
	}

	public Todo findById(int id) {
		// TODO Auto-generated method stub
		Predicate<? super Todo> predicate = todo-> todo.getId()==id;
		Todo todo = todos.stream().filter(predicate).findFirst().get();
		return todo;
	}

	public void updateTodo(@Valid Todo todo) {
		// TODO Auto-generated method stub
		deleteByUserId(todo.getId());
		todos.add(todo);
	}


	
}
