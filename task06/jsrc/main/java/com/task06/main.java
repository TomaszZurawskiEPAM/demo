package com.task06;

import com.amazonaws.services.lambda.runtime.ClientContext;
import com.amazonaws.services.lambda.runtime.CognitoIdentity;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent;

public class main
{

	public static void main(String[] args)
	{
		AuditProducer auditProducer = new AuditProducer();

		auditProducer.handleRequest(new DynamodbEvent(), new Context()
		{

			@Override public String getAwsRequestId()
			{
				return null;
			}

			@Override public String getLogGroupName()
			{
				return null;
			}

			@Override public String getLogStreamName()
			{
				return null;
			}

			@Override public String getFunctionName()
			{
				return null;
			}

			@Override public String getFunctionVersion()
			{
				return null;
			}

			@Override public String getInvokedFunctionArn()
			{
				return null;
			}

			@Override public CognitoIdentity getIdentity()
			{
				return null;
			}

			@Override public ClientContext getClientContext()
			{
				return null;
			}

			@Override public int getRemainingTimeInMillis()
			{
				return 0;
			}

			@Override public int getMemoryLimitInMB()
			{
				return 0;
			}

			@Override public LambdaLogger getLogger()
			{
				return null;
			}
		});
	}
}
