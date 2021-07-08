# TXDemo

#Kafka:

Download Kafka (latest version), unzip, and open config/server.properties

Add these lines:

listeners=PLAINTEXT://localhost:9092
auto.create.topics.enable=false

#Open terminal and position into bin folder:

#Start Zookeeper:
./zookeeper-server-start.sh ../config/zookeeper.properties

#Start server:
./kafka-server-start.sh ../config/server.properties

#Create topic:
./kafka-topics.sh --create --topic INPUT-TOPIC -zookeeper localhost:2181 --replication-factor 1 --partitions 1

#Loading test data:
gunzip -c stream.gz | ./kafka/bin/kafka-console-producer.sh --broker-list localhost:9092 --topic INPUT-TOPIC
