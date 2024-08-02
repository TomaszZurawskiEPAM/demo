package com.task05.dto;

import java.util.Map;

public class Event
{
	public String id;
	public int principalId;
	public String createdAt;
	public Map<String,String> body;

	public Event(String id, int principalId, String createdAt, Map<String,String> body)
	{
		this.id = id;
		this.principalId = principalId;
		this.createdAt = createdAt;
		this.body = body;
	}
}
