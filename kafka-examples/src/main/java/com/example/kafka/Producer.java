package com.example.kafka;


import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class Producer {

    public static void main(String[] args) {
        // create properties object for the producer
        Properties prop = new Properties();
        prop.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.20.53.127:9092");
        prop.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        prop.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());


        // create the producer
        final KafkaProducer<String, String> producer = new KafkaProducer<String, String>(prop);



        // create producer record
        ProducerRecord<String, String> record = new ProducerRecord<>("sample-topic", "Key1", "kafka is working baby");


        // send data - Asynchronous
        producer.send(record);

        // flush and close producer
        producer.flush();
        producer.close();
    }
}
