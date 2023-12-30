package com.yoon.spring.jspTodoApp.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

//@Controller
@SessionAttributes("name")
public class TodoController {

	private TodoService todoService;
	
	
	
	public TodoController(TodoService todoService) {
		super();
		this.todoService = todoService;
	}



	//return list-todos.jsp
	@RequestMapping("list-todos")
	public String listAllTodos(ModelMap model) {
		String username = getLoggedinUsername(model);
		List<Todo> todos = todoService.findByUsername(username);
		model.addAttribute("todos", todos);
		return "listTodos";
	}


	//extracted Method- get username directly from SpringSecurity
	private String getLoggedinUsername(ModelMap model) {
		Authentication authentication = 
				SecurityContextHolder.getContext().getAuthentication();
		
		return authentication.getName();

	}
	
	@RequestMapping(value = "add-todo", method = RequestMethod.GET)
	public String showNewTodoPage(ModelMap model) {
		String username = getLoggedinUsername(model);
		//default form Todo value
		Todo todo = new Todo(0, username, "", LocalDate.now().plusYears(1), false);
		model.put("todo", todo);
		return "todo";
	}

	@RequestMapping(value = "add-todo", method = RequestMethod.POST)
	public String addNewTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		if (result.hasErrors()) {
			return "todo";
		}
		//use TodoService for addNewTodo
		//"name"-> Session
		String username = getLoggedinUsername(model);
		todoService.addTodo(username, todo.getDescription(), todo.getTargetDate(), false);
		//return to URL not JSP name and operate listAllTodos Controller Method
		return "redirect:list-todos";
	}	
	
	@RequestMapping("todo-delete")
	public String deleteTodo(@RequestParam int id) {
		todoService.deleteByUserId(id);
		return "redirect:list-todos";
	}
	
	@RequestMapping(value = "todo-update",method = RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
		// i want find specific Todo Object to use todoService and it put new generate Todo Object
		Todo todo = todoService.findById(id);
		model.addAttribute("todo", todo);
		return "todo";
	}

	@RequestMapping(value = "todo-update", method = RequestMethod.POST)
	public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		if (result.hasErrors()) {
			return "todo";
		}
		//use TodoService for updateTodo
		//"name"-> Session
		String username = getLoggedinUsername(model);
		todo.setUsername(username);
		todoService.updateTodo(todo);
		
		//return to URL not JSP name and operate listAllTodos Controller Method
		return "redirect:list-todos";
	}	

	
	
}
