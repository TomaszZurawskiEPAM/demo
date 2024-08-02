package com.task05.dto;

public class Event
{
	String id;
	int principalId;
	String createdAt;
	String body;

	public Event(String id, int principalId, String createdAt, String body)
	{
		this.id = id;
		this.principalId = principalId;
		this.createdAt = createdAt;
		this.body = body;
	}
}
