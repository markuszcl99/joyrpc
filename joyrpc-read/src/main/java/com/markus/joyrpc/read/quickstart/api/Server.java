package com.markus.joyrpc.read.quickstart.api;

import com.markus.joyrpc.read.service.EchoService;
import com.markus.joyrpc.read.service.impl.DefaultEchoService;
import io.joyrpc.config.ProviderConfig;
import io.joyrpc.config.RegistryConfig;
import io.joyrpc.config.ServerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/4/1
 * @Description:
 */
public class Server {
  private static final Logger logger = LoggerFactory.getLogger(Server.class);

  public static void main(String[] args) throws IOException {
    // 1. 创建 service 类
    EchoService echoService = new DefaultEchoService();

    // 2. 创建注册中心
    RegistryConfig registryConfig = new RegistryConfig();
    //  Provider端支持多注册中心同时注册,注册中心插件化，默认提供memory注册中心实现，zk、etcd注册中心实现，使用方可自行扩展。
    registryConfig.setRegistry("memory");

    // 3. 创建服务端启动配置
    ProviderConfig<EchoService> providerConfig = new ProviderConfig<>();
    providerConfig.setRegistry(registryConfig);
    providerConfig.setServerConfig(new ServerConfig());
    providerConfig.setInterfaceClazz(EchoService.class.getName());
    providerConfig.setRef(echoService);
    providerConfig.setAlias("joyrpc-api-demo");

    providerConfig.exportAndOpen().whenComplete((v, t) -> {
      if (t != null) {
        logger.error(t.getMessage(), t);
        System.exit(1);
      }
    });
    System.in.read();
  }
}
