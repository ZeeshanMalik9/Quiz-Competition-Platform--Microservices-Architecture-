#!/bin/bash

# This script sets up the project environment for the Microservices Architecture Analysis project.

# Update package lists
echo "Updating package lists..."
sudo apt-get update

# Install necessary dependencies
echo "Installing dependencies..."
sudo apt-get install -y openjdk-11-jdk maven docker-compose

# Verify installations
echo "Verifying installations..."
java -version
mvn -version
docker --version
docker-compose --version

# Build each microservice
echo "Building microservices..."
cd api-gateway/src && mvn clean install
cd ../../eureka-server/src && mvn clean install
cd ../question-service/src && mvn clean install
cd ../quiz-service/src && mvn clean install

# Setup complete
echo "Setup complete! You can now run the services using Docker Compose."