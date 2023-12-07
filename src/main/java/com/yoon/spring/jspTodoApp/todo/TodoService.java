package com.yoon.spring.jspTodoApp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TodoService {

	private static List<Todo> todos = new ArrayList<>();

	//count initialize 0
	private static int todosCount = 0;
	
	static {
		todos.add(new Todo(++todosCount, "YOON", "Learn JSP",
				LocalDate.now().plusYears(1), false));
		todos.add(new Todo(++todosCount, "YOON", "Learn NetWork",
				LocalDate.now().plusYears(2), false));
		todos.add(new Todo(++todosCount, "YOON", "Learn Security",
				LocalDate.now().plusYears(3), false));

	}
	
	//use return method Because todos is private
	public List<Todo> findByUserName(String username){
		return todos;
	}
	
	//add new Todo logic
	public void addTodo(String username, String description, LocalDate targetDate, boolean done) {
		Todo todo = new Todo(++todosCount, username, description, targetDate, done);
		todos.add(todo);
	}
	
	
}
