package com.nengboonchai.demo.kafka;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class ProducerKafaService {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessageNoWait(String topicName,String key , String msg) {
        kafkaTemplate.send(topicName, key, msg);
        System.out.println("========================"+msg);
    }

    public void sendMessageWithWaiting(String topicName,String message) {
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, message);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("Sent message=[" + message +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                System.out.println("Unable to send message=[" +
                        message + "] due to : " + ex.getMessage());
            }
        });
    }
}
