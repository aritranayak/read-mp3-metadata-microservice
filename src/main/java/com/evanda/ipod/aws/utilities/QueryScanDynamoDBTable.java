package com.evanda.ipod.aws.utilities;

import java.util.List;

import com.amazonaws.services.dynamodbv2.model.Condition;

/**
 * Functional interface to be implemented by the Lambda to query a  table based on a given condition
 * 
 * 
 * @author ANAYAK
 *
 */
@FunctionalInterface
public interface QueryScanDynamoDBTable<T> {

	public List<T> getQueryScanResults(String tableName, Condition filter);
}
