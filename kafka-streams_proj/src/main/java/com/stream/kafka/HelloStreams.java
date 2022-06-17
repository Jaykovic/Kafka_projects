package com.stream.kafka;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;

import java.util.Properties;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class HelloStreams {
    //private static final Logger logger = LogManager.getLogger(Producer.class);

    public static void main(String[] args) {
        // kafka stream properties
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG,AppConfigs.applicationID);
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,AppConfigs.bootstrapServers);
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.Integer().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        // Open a stream to a source topic
        StreamsBuilder streamsBuilder = new StreamsBuilder();
        KStream<Integer,String> kStream = streamsBuilder.stream(AppConfigs.topicName);

        // Process the stream
        kStream.foreach((k,v)-> System.out.println("Key= " + k + " Value= " + v));
        //kStream.foreach((k,v)-> System.out.println("Key= " + k + " Value= " + v));

        // Create Topology
        Topology topology = streamsBuilder.build();

        // Start kafka stream
        KafkaStreams streams = new KafkaStreams(topology,props);
        //logger.info("Starting stream...");
        streams.start();

        //Shutdown stream
        Runtime.getRuntime().addShutdownHook(new Thread(() ->{
            //logger.info("Shutting down stream");
            streams.close();
        }));

    }
}
