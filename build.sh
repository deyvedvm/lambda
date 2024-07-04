#!/bin/bash

# Define project base directory
BASEDIR=$(dirname "$0")

# Navigate to the project base directory
cd "$BASEDIR" || exit

# Clean and package the Java project using Maven
mvn clean package

# Check if the build was successful
if [ $? -eq 0 ]; then
    echo "Build successful."
else
    echo "Build failed."
    exit 1
fi

# Optional: Copy the generated .jar file to a specific directory
# cp target/lambda-1.0-SNAPSHOT.jar /path/to/destination

echo "Build script execution completed."





