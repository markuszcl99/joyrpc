package com.markus.joyrpc.read.api.server;

import com.markus.joyrpc.read.client.service.EchoService;
import io.joyrpc.config.ConsumerConfig;
import io.joyrpc.config.RegistryConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author: markus
 * @date: 2024/4/6 11:15 PM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class Client {
    private static final Logger logger = LoggerFactory.getLogger(Client.class);

    public static void main(String[] args) throws IOException {
        ConsumerConfig<EchoService> consumerConfig = new ConsumerConfig<>();
        consumerConfig.setInterfaceClazz(EchoService.class.getName());
        consumerConfig.setAlias("joyrpc-read-demo");
//        consumerConfig.setRegistry(new RegistryConfig("broadcast"));
        consumerConfig.setRegistry(new RegistryConfig("fix", "grpc://127.0.0.1:22000"));
        consumerConfig.setTimeout(1000000);
        try {
            CompletableFuture<EchoService> future = consumerConfig.refer();
            EchoService echoService = future.get();
            String echo = echoService.echo("Hello, JoyRpc!");
            logger.info("Get msg from server : {}", echo);
            System.in.read();
        } catch (InterruptedException | ExecutionException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
