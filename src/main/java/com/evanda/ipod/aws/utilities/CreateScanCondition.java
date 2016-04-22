package com.evanda.ipod.aws.utilities;

import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;

/**
 * Returns a Condition object based on the ComparisonOperator & value to compare
 * 
 * @author ANAYAK
 * @param <I>
 *
 */
@FunctionalInterface
public interface CreateScanCondition<I> {

	/**
	 * Creates the condition. Implemented by the lambda function
	 * 
	 * @param comparisonOperator
	 * @param valueToCompare
	 * @return
	 */
	public Condition returnCondition(ComparisonOperator comparisonOperator, I valueToCompare);
	
}
