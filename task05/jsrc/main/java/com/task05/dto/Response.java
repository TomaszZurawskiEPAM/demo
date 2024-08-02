package com.task05.dto;

public class Response
{
	public int statusCode;
	public Event event;

	public Response(int statusCode, Event event)
	{
		this.statusCode = statusCode;
		this.event = event;
	}
}
