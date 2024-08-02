package com.task05.dto;

import org.json.JSONObject;

import java.util.Map;

public class Request
{
	int principalId;
	String content;

	public static Request fromJson(String jsonString){
		JSONObject json = new JSONObject(jsonString);
		int principalId = json.optInt("principalId",-1);
		String content = json.optString("content", null);
		return new Request(principalId,content);
	}

	public Request(int principalId, String content){
		this.principalId=principalId;
		this.content=content;
	}

	public int getPrincipalId()
	{
		return principalId;
	}

	public String getContent()
	{
		return content;
	}
}
