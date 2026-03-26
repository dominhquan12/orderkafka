package com.develop.orderkafka.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
public class KafkaConsumerConfig {

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory(
            ConsumerFactory<String, Object> consumerFactory) {

        ConcurrentKafkaListenerContainerFactory<String, Object> factory =
                new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(consumerFactory);

        // 🔥 retry 3 lần, mỗi lần cách nhau 2s
        factory.setCommonErrorHandler(
                new DefaultErrorHandler(
                        (record, exception) -> {
                            System.out.println("💀 Message failed after retries: " + record.value());
                        },
                        new FixedBackOff(2000L, 3)
                )
        );

        return factory;
    }
}