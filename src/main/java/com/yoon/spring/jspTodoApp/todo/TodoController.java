package com.yoon.spring.jspTodoApp.todo;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
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
		List<Todo> todos = todoService.findByUserName("YOON");
		model.addAttribute("todos", todos);
		return "listTodos";
	}
	
	@RequestMapping(value = "add-todo", method = RequestMethod.GET)
	public String showNewTodoPage() {

		return "todo";
	}

	@RequestMapping(value = "add-todo", method = RequestMethod.POST)
	public String addNewTodo() {

		//return to URL not JSP name and operate listAllTodos Controller Method
		return "redirect:list-todos";
	}	
	
	
}
