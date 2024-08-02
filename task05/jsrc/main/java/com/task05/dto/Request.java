package com.task05.dto;

import org.json.JSONObject;

import java.util.Map;

public class Request
{
	int principalId;
	Map<String, String> content;

public Request(){}

	public Request(int principalId, Map<String, String> content){
		this.principalId=principalId;
		this.content=content;
	}

	public int getPrincipalId()
	{
		return principalId;
	}

	public Map<String, String> getContent()
	{
		return content;
	}
}
