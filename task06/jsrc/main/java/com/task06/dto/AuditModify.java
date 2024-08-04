package com.task06.dto;



import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.util.Map;

@DynamoDbBean
public class AuditModify
{
	private String id;
	private String itemKey;
	private String modificationTime;
	private String updatedAttribute;
	private Integer oldValue;
	private Integer newValue;

	public String getUpdatedAttribute()
	{
		return updatedAttribute;
	}

	public void setUpdatedAttribute(String updatedAttribute)
	{
		this.updatedAttribute = updatedAttribute;
	}

	public Integer getOldValue()
	{
		return oldValue;
	}

	public void setOldValue(Integer oldValue)
	{
		this.oldValue = oldValue;
	}

	public Integer getNewValue()
	{
		return newValue;
	}

	public void setNewValue(Integer newValue)
	{
		this.newValue = newValue;
	}

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
		return "AuditModify{" + "id='" + id + '\'' + ", itemKey='" + itemKey + '\'' + ", modificationTime='" + modificationTime + '\'' + ", updatedAttribute='" + updatedAttribute + '\'' + ", oldValue=" + oldValue + ", newValue=" + newValue + '}';
	}
}
