package com.task06.dto;

public class Response
{
	public int statusCode;
	public AuditInsert audit;

	public Response(int statusCode, AuditInsert audit)
	{
		this.statusCode = statusCode;
		this.audit = audit;
	}
}
