package com.task06.dto;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

@DynamoDbBean
public class NewValue
{
	String key;
	Integer value;

	public String getKey()
	{
		return key;
	}

	public void setKey(String key)
	{
		this.key = key;
	}

	public Integer getValue()
	{
		return value;
	}

	public void setValue(Integer value)
	{
		this.value = value;
	}

	@Override public String toString()
	{
		return "NewValue{" + "key='" + key + '\'' + ", value=" + value + '}';
	}
}
