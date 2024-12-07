package com.cdac.todo;

import java.util.Arrays;
import java.util.Collection;

import com.cdac.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cdac.todo.Entity.Todo;

@SpringBootApplication
public class SpringBootTodoApplication implements CommandLineRunner {

	@Autowired
	public TodoRepository todoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootTodoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Collection<Todo> todos = Arrays.asList(new Todo("Read code", "Yes"), new Todo("Learn to sing", "No"), new Todo("Go for a Run", "No"), new Todo("Prepare Dinner", "Yes"));
		todos.forEach(todoRepository::save);

	}                  
}
