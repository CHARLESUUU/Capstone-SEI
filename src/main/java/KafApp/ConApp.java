package KafApp;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.*;
import java.util.Collections;
import java.util.Properties;

public class ConApp{
	private final static String TOPIC= "test";
	//final local host
	private final static String BOOTSTRAP_SERVERS= "localhost:9092";
	public static ArrayList<String> msg= new ArrayList<String>();
	public static Consumer<Long,String> createConsumer(){
		final Properties props=new Properties();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,BOOTSTRAP_SERVERS);
		//change
		props.put(ConsumerConfig.GROUP_ID_CONFIG,"KafkaSEI");
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,LongDeserializer.class.getName());
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());
		final Consumer<Long, String> consumer = new KafkaConsumer<>(props);
		consumer.subscribe(Collections.singletonList(TOPIC));
		return consumer;
	}
	static void runConsumer() throws InterruptedException{
		final Consumer<Long,String> consumer=createConsumer();
		final int giveUp=0;
		int noRecordsCount=0;
		while(true){
			final ConsumerRecords<Long,String> consumerRecords=consumer.poll(1000);
			if(consumerRecords.count()==0){
			noRecordsCount++;
			if(noRecordsCount>giveUp)break;
			else continue;
			}
			consumerRecords.forEach(record -> {
            System.out.printf("%s\n", record.value());
			msg.append(record.value());
            });
			consumer.commitAsync();
		}
		consumer.close();
		System.out.println("Done");
	}
	public static void main(String... args) throws Exception {
        runConsumer();
		for (String a:msg){
			System.out.println(a+" ");
  }
	
}
}

	


