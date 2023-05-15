package com.example.mydemobasic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {

	private List<Todo> todos = new ArrayList<>();
	private final AtomicLong counter = new AtomicLong();

	public TodoController() {

//		todos.add(new Todo(2, "cat"));
//		todos.add(new Todo(3, "tus"));
	}

//	//GET
//	@GetMapping("/say")
//	public String say() {
//		return "Pattaya";
//	}

	// .../todo
	@GetMapping("/todo")
	public List<Todo> getTodos() {
		return todos;
	}

	// .../todo/1,2,3
	@GetMapping("/todo/{id}")
	public Todo getTodosById(@PathVariable long id) {
		return todos.stream().filter(result -> result.getId() == id).findFirst()
				.orElseThrow(() -> new TodoNotFoundException(id));
	}

	// .../todo/search?name = pattaya
	@GetMapping("/todo/search")
	public String getTodosByName(@RequestParam String name) {
		return "search : " + name;
	}

	// ..post
	// ...todo
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/todo")
	public void addTodo(@RequestBody Todo todo) {
		todos.add(new Todo(counter.getAndIncrement(), todo.getName()));

	}

	// ..put,update
	// ...todo/12534
	@PutMapping("/todo/{id}")
	public void editTodo(@RequestBody Todo todo, @PathVariable long id) {
		todos.stream().filter(result -> result.getId() == id).findFirst().ifPresentOrElse(result -> {
			result.setName(todo.getName());
		}, () -> {
		});
		// todo
	}

	// ..delete,
	// ...todo/12534
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/todo/{id}")
	public void deleteTodo(@PathVariable long id) {
		todos.stream().filter(result -> result.getId() == id).findFirst().ifPresentOrElse(result -> {
			todos.remove(result);
		}, () -> {
			throw new TodoNotFoundException(id);
		});

	}

}
