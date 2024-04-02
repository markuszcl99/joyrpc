package com.markus.joyrpc.read.quickstart.api;

import com.markus.joyrpc.read.service.EchoService;
import io.joyrpc.config.ConsumerConfig;
import io.joyrpc.config.RegistryConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/4/1
 * @Description:
 */
public class Client {

  private static Logger logger = LoggerFactory.getLogger(Client.class);

  public static void main(String[] args) {
    ConsumerConfig<EchoService> consumerConfig = new ConsumerConfig<>(); //consumer设置
    consumerConfig.setInterfaceClazz(EchoService.class.getName());
    consumerConfig.setAlias("joyrpc-api-demo");
    //consumerConfig.setRegistry(new RegistryConfig("broadcast"));
    consumerConfig.setRegistry(new RegistryConfig("fix", "grpc://127.0.0.1:22000"));
    consumerConfig.setTimeout(1000000);
    try {
      CompletableFuture<EchoService> future = consumerConfig.refer();
      EchoService service = future.get();

      String echo = service.echo("hello"); //发起服务调用
      logger.info("Get msg: {} ", echo);

      System.in.read();
    } catch (Throwable e) {
      logger.error(e.getMessage(), e);
    }
  }
}
