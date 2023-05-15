package com.example.mydemobasic;

public class TodoNotFoundException extends RuntimeException {

	public TodoNotFoundException() {
		// TODO Auto-generated constructor stub
	}
	
	public TodoNotFoundException(Long id) {
		super("Could not find todo " + id);
	}

}
