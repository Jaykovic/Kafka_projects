package com.example.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Properties;



public class Producer2
{
    private static KafkaProducer<String, String> producer;
    private static final String topic= "income123";
    public void initialize()
    {
        Properties prop = new Properties();
        prop.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.19.36.43:9092,172.19.36.43:9093,172.19.36.43:9094");
        prop.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        prop.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        //172.29.77.69

        producer = new KafkaProducer<String, String>(prop);
        //final KafkaProducer<String, String> producer = new KafkaProducer<String, String>(prop);

    }
    public void MySQLConn() throws Exception
    {
        String msg = null;
        String myDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String myUrl = "jdbc:sqlserver://localhost:1433;instance=OBEKPA-JAMES\\MSSQLSERVER01;databaseName=fintrakVolumereport;trustServerCertificate=true;";
        Class.forName(myDriver);
        Connection conn = DriverManager.getConnection(myUrl, "jco", "fintrak10000000");

        String query = "SELECT * FROM income_volume_analysis";

        Statement st = conn.createStatement();

        ResultSet rs = st.executeQuery(query);

        while (rs.next())
        {
            String superCaption = rs.getString("super_caption");
            String caption = rs.getString("caption");
            Float actualVol = rs.getFloat("actualVol");
            String productCode = rs.getString("productcode");
            String category = rs.getString("category");
            String misCode = rs.getString("mis_code");
            Date runDate = rs.getDate("rundate");


            msg = superCaption+","+caption+","+actualVol+","+productCode+","+category+","+misCode+","+runDate;
            //System.out.println(rs.getFloat("actualVol"));


            ProducerRecord<String, String> record = new ProducerRecord<>("income123", "key", msg);
            producer.send(record);
            //producer.send(keyedMsg);

        }
        conn.close();
    }
    public static void main(String[] args) throws Exception
    {
        Producer2 producer2 = new Producer2();

        producer2.initialize();

        producer2.MySQLConn();


        producer.close();
    }
}