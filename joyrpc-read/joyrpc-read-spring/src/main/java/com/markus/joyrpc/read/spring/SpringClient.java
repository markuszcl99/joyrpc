package com.markus.joyrpc.read.spring;

import com.markus.joyrpc.read.client.service.EchoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/4/7
 * @Description:
 */
public class SpringClient {
  private static final Logger logger = LoggerFactory.getLogger(SpringServer.class);

  public static void main(String[] args) throws IOException, InterruptedException {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/joyrpc-consumer.xml");
    logger.info("客户端启动完成！");
    EchoService echoService = context.getBean("echoService", EchoService.class);
    while (true) {
      System.out.println(echoService.echo("Hello,Spring Joyrpc!"));
      Thread.sleep(1000L);
    }
  }
}
