package com.cdac.todo.controllers;

import com.cdac.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cdac.todo.Entity.Todo;

import java.util.Optional;

@Controller
public class TodoController {

	@Autowired
	TodoRepository todoRepository;
	
	@GetMapping
	public String index() {
		return "index";
	}
	
	@GetMapping("/todos")
	public String todos(Model model) {
		model.addAttribute("todos", todoRepository.findAll());
		return "todos";
	}
	
	@PostMapping("/todoNew")
	public String add(@RequestParam String todoItem, @RequestParam String status, Model model) {
		Todo todo = new Todo();
		todo.setTodoItem(todoItem);
		todo.setCompleted(status);
		todoRepository.save(todo);
		model.addAttribute("todos", todoRepository.findAll());
		return "redirect:/todos";
	}
	
	@PostMapping("/todoDelete/{id}")
	public String delete(@PathVariable long id, Model model) {
		todoRepository.deleteById(id);
		model.addAttribute("todos", todoRepository.findAll());
		return "redirect:/todos";
	}
	
	@PostMapping("/todoUpdate/{id}")
	public String update(@PathVariable long id, Model model) {
		Optional<Todo> optionalTodo = todoRepository.findById(id);
		if (optionalTodo.isPresent()) {
			Todo todo = optionalTodo.get();
			// Proceed with updating the 'completed' status
			if ("Yes".equals(todo.getCompleted())) {
				todo.setCompleted("No");
			} else {
				todo.setCompleted("Yes");
			}
			todoRepository.save(todo);
		} else {
			model.addAttribute("error", "Todo not found");
			return "errorPage"; // Custom error page
		}
		return "redirect:/todos";
	}

}
