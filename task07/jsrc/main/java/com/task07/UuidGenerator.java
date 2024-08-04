package com.task07;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.syndicate.deployment.annotations.events.RuleEventSource;
import com.syndicate.deployment.annotations.lambda.LambdaHandler;
import com.syndicate.deployment.annotations.resources.DependsOn;
import com.syndicate.deployment.model.ResourceType;
import com.syndicate.deployment.model.RetentionSetting;
import com.task07.dto.UuidContent;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3ClientBuilder;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@LambdaHandler(lambdaName = "uuid_generator", roleName = "uuid_generator-role", logsExpiration = RetentionSetting.SYNDICATE_ALIASES_SPECIFIED) @RuleEventSource(targetRule = "uuid_trigger") @DependsOn(resourceType = ResourceType.CLOUDWATCH_RULE, name = "uuid_trigger") public class UuidGenerator
				implements RequestHandler<Object, Map<String, Object>>
{

	final static S3Client s3Client = S3Client.builder().region(Region.CA_CENTRAL_1).build();

	public Map<String, Object> handleRequest(Object request, Context context)
	{
		String bucketName = "cmtr-975a2528-uid-storage-test";

		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
		String isoDateTime = now.format(formatter);
		String key = isoDateTime.toString();
		UuidContent content = new UuidContent();

		PutObjectRequest putObjectRequest = PutObjectRequest.builder().bucket(bucketName).key(key).build();

		PutObjectResponse response = s3Client.putObject(putObjectRequest, RequestBody.fromBytes(content.toString().getBytes()));

		if (response != null)
		{
			System.out.println("File uploaded successfully!");
		}
		else
		{
			System.out.println("File upload failed!");
		}

		System.out.println("Hello from lambda");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("statusCode", 200);
		resultMap.put("body", "Hello from Lambda");
		return resultMap;
	}
}
