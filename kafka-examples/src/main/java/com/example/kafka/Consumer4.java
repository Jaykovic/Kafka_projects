package com.example.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

public class Consumer4 {


    public static void main(String[] args) {


        Properties properties=new Properties();
        properties.put("bootstrap.servers", "172.19.36.43:9092,172.19.36.43:9093,172.19.36.43:9094");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("group.id","test123");

        KafkaConsumer< String, String> consumer=new KafkaConsumer<String, String>(properties);


        ArrayList<String> topics=new ArrayList<String>();
        topics.add("income123");

        consumer.subscribe(topics); // You can subscribe to any number of topics.

        try {

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;instance=OBEKPA-JAMES\\MSSQLSERVER01;databaseName=TestDb;trustServerCertificate=true;","jco","fintrak10000000");
            Statement stmt = con.createStatement();
            while(true){

                ConsumerRecords<String, String> records = consumer.poll(1000);

                for(ConsumerRecord<String, String> record : records){

                    System.out.println(record.value());
                    String Message = record.value().toString();

                    //String content = new String(records.iterator().next().value());
                    String col1 = new String(Message.split(",")[0]);
                    String col2 = new String(Message.split(",")[1]);
                    String col3 = new String(Message.split(",")[2]);
                    String col4 = new String(Message.split(",")[3]);
                    String col5 = new String(Message.split(",")[4]);
                    String col6 = new String(Message.split(",")[5]);
                    String col7 = new String(Message.split(",")[6]);

                    String data = "INSERT INTO income_volume_analysi_replica VALUES('" + col1 + "', '" + col2 + "','" + col3 + "','" + col4 + "','" + col5 + "','" + col6 + "','" + col7 + "')";
                    stmt.executeUpdate(data);
                }
            }

        } catch (Exception e) {
            System.out.println("Inside exception loop : ");
            e.printStackTrace();
        }finally{
            consumer.close();
        }

    }

}