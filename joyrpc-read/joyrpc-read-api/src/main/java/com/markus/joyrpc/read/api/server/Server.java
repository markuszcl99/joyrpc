package com.markus.joyrpc.read.api.server;

import com.markus.joyrpc.read.client.service.EchoService;
import com.markus.joyrpc.read.client.service.impl.DefaultEchoService;
import io.joyrpc.config.ProviderConfig;
import io.joyrpc.config.RegistryConfig;
import io.joyrpc.config.ServerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author: markus
 * @date: 2024/4/6 3:22 PM
 * @Description: 服务端
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class Server {

    private final static Logger logger = LoggerFactory.getLogger(Server.class);

    public static void main(String[] args) throws IOException {
        EchoService echoService = new DefaultEchoService();

        ProviderConfig<EchoService> providerConfig = new ProviderConfig<>();
        providerConfig.setRegistry(new RegistryConfig("memory"));
        providerConfig.setRef(echoService);
        providerConfig.setInterfaceClazz(EchoService.class.getName());
        providerConfig.setAlias("joyrpc-read-demo");
        providerConfig.setServerConfig(new ServerConfig());

        providerConfig.exportAndOpen().whenComplete((v, t) -> {
            if (t != null) {
                logger.error(t.getMessage(), t);
                System.exit(1);
            }
        });


        providerConfig.setParameter("shutdownTimeout", 15000);
        System.in.read();
    }
}
