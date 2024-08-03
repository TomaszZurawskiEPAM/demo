package com.task05;

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
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@LambdaHandler(lambdaName = "api_handler", roleName = "api_handler-role", isPublishVersion = false, logsExpiration = RetentionSetting.SYNDICATE_ALIASES_SPECIFIED) public class ApiHandler
				implements RequestHandler<Object, Map<String, Object>>
{
	static final DynamoDbClient standardClient = DynamoDbClient.builder()
					.region(Region.EU_CENTRAL_1)
					.build();

	// Use the configured standard client with the enhanced client.
	static final DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.builder()
					.dynamoDbClient(standardClient)
					.build();
	//static final DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.create();
	static final DynamoDbTable<Event> customerTable = enhancedClient.table("cmtr-975a2528-Events-test", TableSchema.fromBean(Event.class));

	public Map<String, Object> handleRequest(Object request, Context context)
	{
		try
		{
			System.out.println(customerTable.toString());
			System.out.println(request.toString());
			ObjectMapper objectMapper = new ObjectMapper();
			System.out.println(new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(request));

			Request requestDto = objectMapper.readValue(
							new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(request), Request.class);

			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
			String isoDateTime = now.format(formatter);

			Event eventDto = new Event();
			eventDto.setId(UUID.randomUUID().toString());
			eventDto.setCreatedAt(isoDateTime);
			eventDto.setPrincipalId(requestDto.getPrincipalId());
			eventDto.setBody(requestDto.getContent());

//customerTable.createTable();
			customerTable.putItem(eventDto);
			System.out.println(eventDto);

			Response response = new Response(201, eventDto);

			return new ObjectMapper().readValue(new ObjectMapper().writeValueAsString(response), HashMap.class);
		}
		catch (JsonProcessingException e)
		{
			throw new RuntimeException(e);
		}

	}
}
