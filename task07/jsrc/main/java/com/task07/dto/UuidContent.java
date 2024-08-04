package com.task07.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UuidContent
{
private List<String> uuids;

public UuidContent(){
	uuids = new ArrayList<>();
	for (int i = 0; i <10 ; i++)
	{
		uuids.add(UUID.randomUUID().toString());
	}
}

	@Override public String toString()
	{
		StringBuilder stringBuilder = new StringBuilder("{\"ids\": [");
		for (String uuid:uuids
			 )
		{
			stringBuilder.append("\"").append(uuid).append("\"").append(",");
		}
		stringBuilder.deleteCharAt(stringBuilder.length()-1);
		stringBuilder.append("]}");
		return stringBuilder.toString();
	}
}
