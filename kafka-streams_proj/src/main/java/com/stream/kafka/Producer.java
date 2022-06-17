package com.stream.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Producer {
    private static final Logger logger = LogManager.getLogManager().getLogger("");

    public static void main(String[] args) {
        logger.info("Creating kafka Producer...");
        Properties props = new Properties();
        //props.put(ProducerConfig.CLIENT_ID_CONFIG,AppConfigs.applicationID);
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,AppConfigs.bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<Integer,String> producer = new KafkaProducer<Integer,String>(props);

        logger.info("Start sending messages ...");
        for(int i = 0; i< 1000; i++) {
            producer.send(new ProducerRecord<>(AppConfigs.topicName,i,"simple Message"+i));
        }
        logger.info("Finished sending messages, closing Producer");
        producer.close();

    }
}
