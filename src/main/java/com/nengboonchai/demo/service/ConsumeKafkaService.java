package com.nengboonchai.demo.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;


// https://www.stackchief.com/blog/%40KafkaListener%20%7C%20Spring%20Boot%20Example#google_vignette
@Component
public class ConsumeKafkaService {
    // https://www.baeldung.com/spring-kafka
    // @KafkaListener(topics = "topic1, topic2", groupId = "foo")
    @KafkaListener(topics = "quickstart-events", groupId = "foo")
    public void listenGroupFoo(String message) {
        System.out.println("xxxxxxxxxxxxxxxxxReceived Message in group foo: " + message);
    }

    @KafkaListener(topics = "topicName")
    public void listenWithHeaders(
            @Payload String message,
            @Header(KafkaHeaders.RECEIVED_PARTITION) int partition) {
        System.out.println(
                "Received Message: " + message
                        + "from partition: " + partition);
    }
//
//    @KafkaListener(
//            topicPartitions = @TopicPartition(topic = "topicName",
//                    partitionOffsets = {
//                            @PartitionOffset(partition = "0", initialOffset = "0"),
//                            @PartitionOffset(partition = "3", initialOffset = "0")}),
//            containerFactory = "partitionsKafkaListenerContainerFactory")
//    public void listenToPartition(
//            @Payload String message,
//            @Header(KafkaHeaders.RECEIVED_PARTITION) int partition) {
//        System.out.println(
//                "Received Message: " + message
//                        + "from partition: " + partition);
//    }
}
