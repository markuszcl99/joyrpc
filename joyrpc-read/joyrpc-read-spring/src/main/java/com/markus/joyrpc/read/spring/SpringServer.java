package com.markus.joyrpc.read.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/4/7
 * @Description:
 */
public class SpringServer {

  private static final Logger logger = LoggerFactory.getLogger(SpringServer.class);

  public static void main(String[] args) throws IOException {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/joyrpc-provider.xml");
    logger.info("服务端启动完成！");
    System.in.read();
  }
}
