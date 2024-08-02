package com.task05.dto;

import org.json.JSONObject;

import java.util.Map;

public record Request(int principalId, String content)
{
	public static Request fromJson(String jsonString){
		JSONObject json = new JSONObject(jsonString);
		int principalId = json.optInt("principalId",-1);
		String content = json.optString("content", null);
		return new Request(principalId,content);
	}

}
