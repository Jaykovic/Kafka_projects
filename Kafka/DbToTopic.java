

import java.sql.*;
import java.util.Properties;


public class DbToTopic
{
    private static Producer<Integer, String> producer;
    private static final string topic= "Trial";
    public void initialize()
    {
        Properties producerProps = new Properties();
        producerProps.put("metadata.broker.list","localhost:9092");
        producerProps.put("serializer.class", "kafka.serializer.StringEncoder");
        producerProps.put("request.required.acks", "1");
        ProducerConfig producerConfig = new ProducerConfig(producerProps);
        producer = new Producer<Integer, String>(producerConfig);

    }
    public void MySQLConn() throws Exception
    {
        String msg = null;
        String myDriver = "com.mysql.jdbc.Driver";
        String myUrl = "jdbc:mysql://localhost/salesdb";
        Class.forName(myDriver);
        Connection conn = DriverManager.getConnection(myUrl, "root", "Fintrak@123");

        String query = "SELECT * FROM products";

        Statement st = conn.createStatement();

        ResultSet rs = st.executeQuery(query);

        while (rs.next())
        {
            int id = rs.getInt("id");
            String productName = rs.getString("product_name");
            Float price = rs.getFLoat("price");

            msg = id+","+productName+","+price;

            KeyedMesaage<Integer, String>keyedMsg = new KeyedMesaage<Integer, String>(topic, msg);

            producer.send(keyedMsg);
        }
        conn.close();
    }
}