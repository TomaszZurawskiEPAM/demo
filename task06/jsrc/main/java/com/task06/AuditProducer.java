package com.task06;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent;
import com.amazonaws.services.lambda.runtime.events.models.dynamodb.Record;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.syndicate.deployment.annotations.events.DynamoDbTriggerEventSource;
import com.syndicate.deployment.annotations.lambda.LambdaHandler;
import com.syndicate.deployment.annotations.resources.DependsOn;
import com.syndicate.deployment.model.ResourceType;
import com.syndicate.deployment.model.RetentionSetting;
import com.task06.dto.AuditModify;
import com.task06.dto.Response;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import com.task06.dto.AuditInsert;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@LambdaHandler(lambdaName = "audit_producer",
	roleName = "audit_producer-role",
	logsExpiration = RetentionSetting.SYNDICATE_ALIASES_SPECIFIED
)
@DynamoDbTriggerEventSource(targetTable="Configuration",
batchSize=1)
@DependsOn(resourceType = ResourceType.DYNAMODB_TABLE
				,name="Audit")
public class AuditProducer implements RequestHandler<DynamodbEvent, Map<String, Object>> {
	static final DynamoDbClient standardClient = DynamoDbClient.builder()
					.region(Region.EU_CENTRAL_1)
					.build();

	// Use the configured standard client with the enhanced client.
	static final DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.builder()
					.dynamoDbClient(standardClient)
					.build();
	//static final DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.create();
	static final DynamoDbTable<AuditInsert> insertTable = enhancedClient.table("cmtr-975a2528-Audit", TableSchema.fromBean(
					AuditInsert.class));

	static final DynamoDbTable<AuditModify> modifyTable = enhancedClient.table("cmtr-975a2528-Audit", TableSchema.fromBean(
					AuditModify.class));

	public Map<String, Object> handleRequest(DynamodbEvent dynamoEvent, Context context)
	{
		try
		{
			//System.out.println(customerTable.toString());
			System.out.println(dynamoEvent.toString());
context.getLogger().log(dynamoEvent.toString());
			context.getLogger().log("teste \n test");
			context.getLogger().log(new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(dynamoEvent));

			ObjectMapper objectMapper = new ObjectMapper();
			//System.out.println(new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(request));

			//Request requestDto = objectMapper.readValue(
			//				new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(request), Request.class);

			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
			String isoDateTime = now.format(formatter);
Record record = dynamoEvent.getRecords().get(0);
			if (record.getEventName().equals("INSERT")){
				AuditInsert auditDto = new AuditInsert() ;
				auditDto.setId(UUID.randomUUID().toString());
				auditDto.setModificationTime(isoDateTime);
				auditDto.setItemKey(record.getDynamodb().getKeys().get("key").toString());
				auditDto.setNewValue(Map.of(auditDto.getItemKey(),Integer.valueOf(record.getDynamodb().getNewImage().get("value").toString())));

				//customerTable.createTable();
				insertTable.putItem(auditDto);
				System.out.println(auditDto);

				Response response = new Response(201, auditDto);
			}

			if(record.getEventName().equals("MODIFY")){
				AuditModify auditDto = new AuditModify() ;
				auditDto.setId(UUID.randomUUID().toString());
				auditDto.setModificationTime(isoDateTime);
				auditDto.setItemKey(record.getDynamodb().getKeys().get("key").toString());
				auditDto.setNewValue(Integer.valueOf(record.getDynamodb().getNewImage().get("value").toString()));
				auditDto.setOldValue(Integer.valueOf(record.getDynamodb().getOldImage().get("value").toString()));

				//customerTable.createTable();
				modifyTable.putItem(auditDto);
				System.out.println(auditDto);

				Response response = new Response(201, new AuditInsert());
			}




			Response response = new Response(201, new AuditInsert());

			return new ObjectMapper().readValue(new ObjectMapper().writeValueAsString(response), HashMap.class);
		}
		catch (JsonProcessingException e)
		{
			throw new RuntimeException(e);
		}

	}
}
