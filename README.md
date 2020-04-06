# AnalyticsEngine
This project is for the AnalyticsEngine

- Pre-requisites for running the software.
- Java JDK 8.0 or later
- Kafka 2.4.0 or later
- Maven 3.x or later

### Steps for running installing and running Kafka
- Download Kafka from the link https://kafka.apache.org/downloads
- Create a folder Kafka in one of your drive such C:\Kafka
- Extract the downlaoded Kafka archive into C:\kafka_2.12-2.4.1
- Open Command Prompt
- Go Kafka folder such as cd C:\kafka_2.12-2.4.1\
- Start Zookeeper by ./bin/window/zookeeper-server-start.bat ./config/zookeeper.properties
- The above command should start Zookeeper in the current window
- Open a new Command Prompt
- Go Kafka folder such as cd C:\kafka_2.12-2.4.1\
- Start Kafka Server by running ./bin/windows/kafka-server-start.bat ./config/server.properties
- Open a new Command Prompt
- Go Kafka folder such as cd C:\kafka_2.12-2.4.1\
- Create a new topic by running the below command
- ./bin/windows/kafka-topics.bat --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic ANALYTICS_INGEST

### Steps for running Analytics Engine
- Clone or download the Analytics Engine
- go the root project AnalyticsEngine\AnalyticsIngester\src\main\resources\application.properties
- Set the client_id and client_secret values for com.heapcode.anlaytics.rep.client_id and com.heapcode.anlaytics.rep.client_secret respectively
- go the root project AnalyticsEngine
- run mvn clean install
- once the build is completed successfully you will have 2 jar files they are
- IngesterApp which is located at AnalyticsEngine\AnalyticsIngester\target\AnalyticsIngester-1.0.jar
- ProcessorrApp which is located at AnalyticsEngine\AnalyticsProcessor\target\AnalyticsProcessor-1.0.jar
- Open two command prompts and go the above two locations respectively
- run java -jar AnalyticsIngester-1.0.jar in first terminal
- run java -jar AnalyticsProcessor-1.0.jar in second terminal

#### Once the above steps are completed you should be able to see Ingester producing Events to Kafka Message Bus and ProcessApp consuming and printing metris in logs.
