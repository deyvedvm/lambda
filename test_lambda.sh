#!/bin/bash

# Define variables
AWS_REGION="us-east-1"
LAMBDA_FUNCTION_NAME="HelloWorldLambda"
# Sample JSON input, adjust as necessary
INPUT_JSON='{"name": "Deyve"}'
AWS_PROFILE="deyvedev"  # Replace with your AWS CLI profile name

# Invoke Lambda function and capture the response in a file
response_file=$(mktemp)
aws lambda invoke \
  --function-name $LAMBDA_FUNCTION_NAME \
  --region $AWS_REGION \
  --payload "$(echo $INPUT_JSON)" \
  --profile $AWS_PROFILE \
  --cli-binary-format raw-in-base64-out \
  "$response_file"

# Check if the invocation was successful
if [ $? -eq 0 ]; then
    echo "Invocation successful. Response:"
    cat "$response_file"
else
    echo "Invocation failed."
fi

# Clean up
rm "$response_file"