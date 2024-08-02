package com.task05.dto;

public class Response
{
	int statusCode;
	Event event;

	public Response(int statusCode, Event event)
	{
		this.statusCode = statusCode;
		this.event = event;
	}
}
