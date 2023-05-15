package com.example.mydemobasic;

public class TodoUnAuthenException extends RuntimeException{

	public TodoUnAuthenException()  {
		super("Token Ivalid");
	}

}
