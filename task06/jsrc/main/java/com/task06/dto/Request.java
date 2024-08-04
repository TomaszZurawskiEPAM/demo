package com.task06.dto;

import java.util.Map;

public class Request
{
	String key;
	int value;

public Request(){}

	public Request(String key, int value){
		this.key =key;
		this.value=value;
	}

	public String getKey()
	{
		return key;
	}

	public int getValue()
	{
		return value;
	}
}
