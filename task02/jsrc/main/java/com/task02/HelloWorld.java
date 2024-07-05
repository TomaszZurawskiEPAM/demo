package com.task02;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.syndicate.deployment.annotations.lambda.LambdaHandler;
import com.syndicate.deployment.annotations.lambda.LambdaLayer;
import com.syndicate.deployment.annotations.lambda.LambdaUrlConfig;
import com.syndicate.deployment.model.Architecture;
import com.syndicate.deployment.model.ArtifactExtension;
import com.syndicate.deployment.model.DeploymentRuntime;
import com.syndicate.deployment.model.RetentionSetting;
import com.syndicate.deployment.model.lambda.url.AuthType;
import com.syndicate.deployment.model.lambda.url.InvokeMode;

import java.util.Map;

@LambdaHandler(lambdaName = "hello_world",
				roleName = "hello_world-role",
				isPublishVersion = false,
				logsExpiration = RetentionSetting.SYNDICATE_ALIASES_SPECIFIED
)
@LambdaUrlConfig(
				authType = AuthType.NONE,
				invokeMode = InvokeMode.BUFFERED
)
public class HelloWorld implements RequestHandler<APIGatewayV2HTTPEvent, APIGatewayV2HTTPResponse>
{

	@Override public APIGatewayV2HTTPResponse handleRequest(APIGatewayV2HTTPEvent requestEvent, Context context)
	{
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		if(requestEvent.getRequestContext().getHttp().getMethod().equals("GET") && requestEvent.getRequestContext().getHttp().getPath().equals("/hello"))
			return APIGatewayV2HTTPResponse.builder()
							.withStatusCode(200)
							.withHeaders(Map.of("Content-Type", "application/json"))
							.withBody(gson.toJson(new Body(200, "Hello from Lambda")))
							.build();
		else
			return APIGatewayV2HTTPResponse.builder()
							.withStatusCode(400)
							.withHeaders(Map.of("Content-Type", "application/json"))
							.withBody(gson.toJson(new Body(400,"Bad request syntax or unsupported method. Request path: "+requestEvent.getRequestContext().getHttp().getPath()+". HTTP method: "+requestEvent.getRequestContext().getHttp().getMethod())))
							.build();
	}

	private class Body{

		int statusCode;
		String message;

		public Body(int statusCode, String message)
		{
			this.statusCode = statusCode;
			this.message = message;
		}
	}

	/*public Map<String, Object> handleRequest(Object request, Context context) {
		System.out.println("Hello from lambda");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("message", "Hello from Lambda");
		resultMap.put("statusCode", 200);
		return resultMap;
	}*/
}