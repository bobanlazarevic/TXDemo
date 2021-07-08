# TXDemo

= Kafka:

- Download Kafka (latest version), unzip, and open config/server.properties

= Add these lines:

- listeners=PLAINTEXT://localhost:9092
- auto.create.topics.enable=false

= Open terminal and position into bin folder:

= Start Zookeeper:
- ./zookeeper-server-start.sh ../config/zookeeper.properties

= Start server:
- ./kafka-server-start.sh ../config/server.properties

= Create topic:
- ./kafka-topics.sh --create --topic INPUT-TOPIC -zookeeper localhost:2181 --replication-factor 1 --partitions 1

= Loading test data:
- gunzip -c stream.gz | ./kafka/bin/kafka-console-producer.sh --broker-list localhost:9092 --topic INPUT-TOPIC

= Dowload source code

- Run Main_Loading.java to print all entries to the console
- Run Main_Counting.java to count all entries based on UIDs

= Useful commands
- ./kafka-topics.sh --zookeeper localhost:2181 --list
- ./kafka-consumer-groups.sh --bootstrap-server localhost:9092 --all-groups -describe

= Update (APP-ID) and (TOPIC-NAME) to reflect real names
- ./kafka-consumer-groups.sh --bootstrap-server localhost:9092 --group (APP-ID) --reset-offsets --to-earliest --topic (TOPIC-NAME) -execute

= Metrics
- https://github.com/yahoo/cmak
