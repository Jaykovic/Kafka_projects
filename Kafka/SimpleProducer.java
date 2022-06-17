import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Properties;
import java.io.FileInputStream;
import kafka.producer.ProducerConfig;
import kafka.producer.ProducerKeyedMessage;

public class SimpleProducer
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

    public void publishMessage(object[] args) throws Exception
    {
        String fl = args[0].toString();
            File file = new File(fl);
        FileInputStream fstream = new FileInputStream(file);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        String msg = null;

        while ((msg = br.readLine()) != null) {
            if(msg.contains("info"))
            {
                System.out.println(msg);
                KeyedMessage<Integer, String> keyedMsg = new KeyedMessage<Integer, String>(topic, msg);
                producer.send(keyedMsg);
            }
            else
            {
                producer.send("no message to be displayed");
            }

        }
        br.close();
    }
    public static void main(String[] args) throws Exception{
        SimpleProducer kafkaProducer = new SimpleProducer();
        kafkaProducer.initialize();
        kafkaProducer.publishMessage(args);
        producer.close();
    }
}
