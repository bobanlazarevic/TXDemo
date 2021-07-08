package com.blazarevic.demo.main;

import com.blazarevic.demo.topology.CustomAppTopology;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Properties;

public class Main_Loading {
    private static final Logger logger = LogManager.getLogger(Main_Loading.class);

    public static void main(String[] args) {
        BasicConfigurator.configure();

        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, AppConfigs.applicationLoadingID);
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.bootstrapServers);
        props.put(StreamsConfig.STATE_DIR_CONFIG, AppConfigs.stateStoreName);

        StreamsBuilder streamsBuilder = new StreamsBuilder();
        CustomAppTopology.withLoadingBuilder(streamsBuilder);

        logger.info("Starting Stream...");
        KafkaStreams streams = new KafkaStreams(streamsBuilder.build(), props);
        streams.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("Stopping Streams...");
            streams.close();
        }));
    }

}
