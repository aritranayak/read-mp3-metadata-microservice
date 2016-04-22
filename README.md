Read DynamoDB Micro-service (a project of 'My Ipod Customizer Application')

This project is continuation of the 's3-upload-listener' project. So, once the user has uploaded the music files they would like to edit,
the metadata information should be displayed in a presentable manner.This project will retrieve all items that show the metadata of a recently uploaded MP3 file. It is built using AWS Java SDK and leverages AWS Lambda, as micro-services.
Note: The idea is to use an API Gateway to call the micro-service and get the required information as response. The API Gateway call is likely to come from a website, as REST web-service call.

For the bigger picture, the intention is to be able to update the various metadata of a MP3 file, in a user friendly manner.
This is particularly useful for people having trouble to sort their music files via iTunes. Using the complete set of micro-services and web application associated with the project,
anyone would be able to edit their mp3 file's metadata.

Getting Started

Clone this project and you will be able to start working on it. To run the application as-is, you need to have an AWS account.

Pre-requisities

 1. See the Pre-requisities section for 'https://github.com/aritranayak/s3-upload-listener' 

Development

	Building

To build 'read-music-file-metadata-microservice', you will need:

JDK 8 - Oracle or OpenJDK
AWS SDK - latest
maven - Version 3 recommended
After installing these tools simply run 'mvn package shade:shade' and find the jar in the target folder.

Other Useful maven lifecycles:

clean - remove binaries, docs and temporary build files
compile - compile the library
test - run all unit tests
package - package compiled code into a jar

Deployment

	Get the 'dynamoDB.reader-0.0.1-SNAPSHOT-shaded.jar' and upload it into a Lambda function.
	Give an appropriate name to the Lambda function (like 'ReadFromTableMicroService').
	Create an API using AWS API Gateway and add a POST method, linking it to this newly created Lambda Function
	

Built With

    Maven

Contributing

I would love to automate the deployment part, using CloudFormation perhaps? If anyone can provide me a guide, I will be much obliged.

If you have added a feature or fixed a bug in 'read-music-file-metadata-microservice' please submit a pull request as follows:

Fork the project
Write the code for your feature or bug fix
Please don't auto-format the code or make wholesale whitespace changes as it makes seeing what has changed more difficult
Add tests! This is important so the code you've added doesn't get unintentionally broken in the future
Make sure the existing tests pass
Commit and do not mess with version or history
Submit a pull request
Thanks for sharing!


Authors

    Aritra Nayak - Initial work

License

This project is licensed under the MIT License - see the LICENSE.md file for details

Acknowledgments

    mpatric for his mp3agic project
