package com.task05;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.syndicate.deployment.annotations.lambda.LambdaHandler;
import com.syndicate.deployment.model.RetentionSetting;
import com.task05.dto.Event;
import com.task05.dto.Request;
import com.task05.dto.Response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@LambdaHandler(lambdaName = "api_handler",
	roleName = "api_handler-role",
	isPublishVersion = false,
	logsExpiration = RetentionSetting.SYNDICATE_ALIASES_SPECIFIED
)
public class ApiHandler implements RequestHandler<Object, Map<String, Object>> {

AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.DEFAULT_REGION).build();
	public Map<String, Object> handleRequest(Object request, Context context)
	{
		try
		{
			System.out.println("Hello from lambda");
			ObjectMapper objectMapper = new ObjectMapper();


			Request requestDto = objectMapper.readValue(new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(request), Request.class);

		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
		String isoDateTime = now.format(formatter);

		Event eventDto = new Event(UUID.randomUUID().toString(), requestDto.getPrincipalId(), isoDateTime, requestDto.getContent());

		var map = objectMapper.writer().withDefaultPrettyPrinter().writeValueAsString(eventDto);
		var result = new ObjectMapper().readValue(map, HashMap.class);
		amazonDynamoDB.putItem("Events", result);

		Response response = new Response(201, eventDto);

			return new ObjectMapper().readValue(new ObjectMapper().writeValueAsString(response), HashMap.class);
	}
		catch (JsonProcessingException e)
		{
			throw new RuntimeException(e);
		}

	}
}
