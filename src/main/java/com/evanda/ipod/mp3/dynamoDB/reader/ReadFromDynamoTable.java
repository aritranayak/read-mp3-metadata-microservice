package com.evanda.ipod.mp3.dynamoDB.reader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.evanda.ipod.aws.utilities.CreateScanCondition;
import com.evanda.ipod.aws.utilities.DynamoDBConnector;
import com.evanda.ipod.aws.utilities.QueryScanDynamoDBTable;
import com.evanda.ipod.model.MusicInfo;
import com.evanda.ipod.model.Response;

/**
 * This Lambda is executed by a API call from the AWS API Gateway. This
 * represents the "get-all-unprocessed-uploads" into S3. The caller is expected
 * to get as _response an array of JSON, containing all Metadata information
 * about the MP3 file.
 * 
 * <TODO> Retrieve records by username </TODO>
 * 
 * @author Aritra Nayak
 *
 */
public class ReadFromDynamoTable implements RequestHandler<Map<String, Object>, List<Response>> {

	private final String FAILURE = "FAILURE";

	private final String SUCCESS = "SUCCESS";

	private final String TRYING = "Trying to connect to DynamoDB ..................";

	private final String TABLE_NAME = "MusicInfo";

	LambdaLogger vLogger = null;

	private final String COLUMN_NAME_TO_QUERY_FROM = "processed";

	public List<Response> handleRequest(Map<String, Object> arg0, Context pContext) {

		/**
		 * Stores logger retrieved from AWS Context object
		 */
		vLogger = pContext.getLogger();

		// first connect to the DynamoDB database
		String rMessage = TRYING;
		vLogger.log(rMessage);

		// now condition is that the item should be unprocessed
		CreateScanCondition<Integer> _filterCondition = (_co, _notProcessed) -> {

			return new Condition().withComparisonOperator(_co.toString())
					.withAttributeValueList(new AttributeValue().withN(_notProcessed.toString()));

		};

		// here, we are implementing the functional interface to prepare the
		// query
		QueryScanDynamoDBTable<MusicInfo> _queryMusicInfoTable = (_tableName, _filterConditionObject) -> {
			HashMap<String, Condition> _scanFilter = new HashMap<String, Condition>();
			_scanFilter.put(COLUMN_NAME_TO_QUERY_FROM, _filterConditionObject);
			ScanRequest _scanRequest = new ScanRequest(_tableName).withScanFilter(_scanFilter);
			DynamoDBConnector _dynamoDBConnector = () -> {
				AmazonDynamoDBClient _amazonDynamoDBClient = new AmazonDynamoDBClient(
						new EnvironmentVariableCredentialsProvider());
				Region iRegion = Regions.getCurrentRegion();
				if (iRegion == null)
					iRegion = Region.getRegion(Regions.US_WEST_2);
				_amazonDynamoDBClient.setRegion(iRegion);
				return _amazonDynamoDBClient;
			};

			try {
				AmazonDynamoDBClient _amazonDynamoDBClient = _dynamoDBConnector.createConnectionToDynamoDB();
				ScanResult _scanResult = _amazonDynamoDBClient.scan(_scanRequest);
				DynamoDBMapper _dynamoDBMapper = new DynamoDBMapper(_amazonDynamoDBClient);
				return _dynamoDBMapper.marshallIntoObjects(MusicInfo.class, _scanResult.getItems());
			} catch (Exception e) {
				vLogger.log(e.getMessage());
				e.printStackTrace();
				return null;
			}

		};

		// now read from the table
		List<MusicInfo> _listOfUnprocessedItems = _queryMusicInfoTable.getQueryScanResults(TABLE_NAME,
				(Condition) _filterCondition.returnCondition(ComparisonOperator.EQ, Integer.valueOf(0)));

		List<Response> __responseList = null;
		if (null != _listOfUnprocessedItems){
			
			__responseList = _listOfUnprocessedItems.stream()
							.map(_musicInfo -> {
								Response _response = new Response();
								_response.setId(_musicInfo.getUuid());
								_response.setAlbumName(_musicInfo.getAlbum());
								_response.setArtist(_musicInfo.getArtist());
								String _fileNameToShow = _musicInfo.getFileName();
								if(null != _fileNameToShow){
									String[] _splitString = _fileNameToShow.split("/");
									int length = _splitString.length;
									_fileNameToShow = _splitString[length - 1];
								}
								_response.setFileName(_fileNameToShow);
								_response.setTitleName(_musicInfo.getTitle());
								return _response;
							})
							.collect(Collectors.toList());
			vLogger.log(SUCCESS);
		}else{
			vLogger.log("No items left for processing");
		}
			
		return __responseList;
	}

}
