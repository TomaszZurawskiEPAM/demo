package com.task06.dto;



import com.amazonaws.services.lambda.runtime.events.models.dynamodb.AttributeValue;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.util.Map;


@DynamoDbBean
public class AuditInsert
{
	private String id;
	private String itemKey;
	private String modificationTime;
	private NewValue newValue;

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

	public NewValue getNewValue()
	{
		return newValue;
	}

	public void setNewValue(NewValue newValue)
	{
		this.newValue = newValue;
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


	@Override public String toString()
	{
		return "AuditInsert{" + "id='" + id + '\'' + ", itemKey='" + itemKey + '\'' + ", modificationTime='" + modificationTime + '\'' + ", newValue=" + newValue + '}';
	}
}
