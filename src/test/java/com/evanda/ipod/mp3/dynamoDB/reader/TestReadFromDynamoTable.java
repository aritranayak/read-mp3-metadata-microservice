package com.evanda.ipod.mp3.dynamoDB.reader;

import junit.framework.TestCase;

/**
 * @author ANAYAK
 *
 */
public class TestReadFromDynamoTable extends TestCase {

	public void testSuccessfulRead(){
		assertEquals("SUCCESS", new ReadFromDynamoTable().handleRequest(null, null));
	}
	
}
