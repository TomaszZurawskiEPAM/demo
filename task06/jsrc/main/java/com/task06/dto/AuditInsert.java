package com.task06.dto;



import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.util.Map;


@DynamoDbBean
public class AuditInsert
{
	private String id;
	private String itemKey;
	private String modificationTime;
	private Map<String,Object> newValue;

	@DynamoDbPartitionKey()
	public String getId()
	{
		return id;
	}

	public String getItemKey()
	{
		return itemKey;
	}

	public String getModificationTime()
	{
		return modificationTime;
	}


	public Map<String, Object> getNewValue()
	{
		return newValue;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public void setItemKey(String itemKey)
	{
		this.itemKey = itemKey;
	}

	public void setModificationTime(String modificationTime)
	{
		this.modificationTime = modificationTime;
	}

	public void setNewValue(Map<String, Object> newValue)
	{
		this.newValue = newValue;
	}

	@Override public String toString()
	{
		return "AuditInsert{" + "id='" + id + '\'' + ", itemKey='" + itemKey + '\'' + ", modificationTime='" + modificationTime + '\'' + ", newValue=" + newValue + '}';
	}
}
