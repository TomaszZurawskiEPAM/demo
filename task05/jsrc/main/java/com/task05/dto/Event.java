package com.task05.dto;



import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.util.Map;


@DynamoDbBean
public class Event
{
	public String id;
	public int principalId;
	public String createdAt;
	public Map<String,String> body;

	public Event(String id, int principalId, String createdAt/*, Map<String,String> body*/)
	{
		this.id = id;
		this.principalId = principalId;
		this.createdAt = createdAt;
		/*this.body = body;*/
	}

	@DynamoDbPartitionKey()
	public String getId()
	{
		return id;
	}

	public int getPrincipalId()
	{
		return principalId;
	}

	public String getCreatedAt()
	{
		return createdAt;
	}


	public Map<String, String> getBody()
	{
		return body;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public void setPrincipalId(int principalId)
	{
		this.principalId = principalId;
	}

	public void setCreatedAt(String createdAt)
	{
		this.createdAt = createdAt;
	}

	public void setBody(Map<String, String> body)
	{
		this.body = body;
	}

	@Override public String toString()
	{
		return "Event{" + "id='" + id + '\'' + ", principalId=" + principalId + ", createdAt='" + createdAt + '\'' + ", body=" + body + '}';
	}
}
