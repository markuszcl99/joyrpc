package com.markus.joyrpc.read.client.service.impl;

import com.markus.joyrpc.read.client.service.EchoService;

/**
 * @author: markus
 * @date: 2024/4/6 3:20 PM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class DefaultEchoService implements EchoService {
    @Override
    public String echo(String message) {
        return "DefaultEchoService echo (" + message + ")";
    }
}
