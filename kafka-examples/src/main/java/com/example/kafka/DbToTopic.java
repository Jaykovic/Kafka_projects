package com.example.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;



public class DbToTopic
{
    private static KafkaProducer<String, String> producer;
    private static final String topic= "Trial";
    public void initialize()
    {
        Properties prop = new Properties();
        prop.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.52.233:9092");
        prop.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        prop.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        //172.29.77.69

        producer = new KafkaProducer<String, String>(prop);
        //final KafkaProducer<String, String> producer = new KafkaProducer<String, String>(prop);

    }
    public void MySQLConn() throws Exception
    {
        String msg = null;
        String myDriver = "com.mysql.jdbc.Driver";
        String myUrl = "jdbc:mysql://localhost:3306/salesdb";
        Class.forName(myDriver);
        Connection conn = DriverManager.getConnection(myUrl, "root", "Fintrak@123");

        String query = "SELECT * FROM products";

        Statement st = conn.createStatement();

        ResultSet rs = st.executeQuery(query);

        while (rs.next())
        {
           int id = rs.getInt("id");
            String productName = rs.getString("product_name");
            Float price = rs.getFloat("price");

            msg = id+","+productName+","+price;
            //System.out.println(rs.getString("price"));

            //KeyedMesaage<Integer, String>keyedMsg = new KeyedMesaage<Integer, String>(topic, msg);
            ProducerRecord<String, String> record = new ProducerRecord<>("Trial", "key", msg);
            producer.send(record);
           //producer.send(keyedMsg);

        }
        conn.close();
    }
    public static void main(String[] args) throws Exception
    {
        DbToTopic DbToTopic = new DbToTopic();

        DbToTopic.initialize();

        DbToTopic.MySQLConn();


        producer.close();
    }
}