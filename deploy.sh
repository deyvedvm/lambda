#!/bin/bash

# Define variables
FUNCTION_NAME="HelloWorldLambda"
HANDLER_NAME="dev.deyve.Hello::handleRequest"
ROLE_ARN="arn:aws:iam::452503617370:role/HelloWorldLambdaRole"
JAR_FILE_PATH="target/lambda-1.0.0.jar"

# Create or update Lambda function
# Check if the function already exists
if aws lambda get-function --function-name $FUNCTION_NAME 2>&1 --profile deyvedev | grep -q 'ResourceNotFoundException'
then
    echo "Function does not exist, creating..."
    aws lambda create-function --function-name $FUNCTION_NAME \
                               --runtime java21 \
                               --role $ROLE_ARN \
                               --handler $HANDLER_NAME \
                               --zip-file fileb://$JAR_FILE_PATH \
                               --timeout 15 \
                               --memory-size 512 \
                               --profile deyvedev
else
    echo "Function exists, updating..."
    aws lambda update-function-code --function-name $FUNCTION_NAME \
                                    --zip-file fileb://$JAR_FILE_PATH \
                                    --profile deyvedev
fi