package com.evanda.ipod.aws.utilities;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

/**
 * Java 8 lambda function will make use of this interface to connect to a DynamoDB database
 * 
 * 
 * @author anayak
 *
 */
@FunctionalInterface
public interface DynamoDBConnector {

	public AmazonDynamoDBClient createConnectionToDynamoDB();
	
}
