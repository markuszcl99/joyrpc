package com.markus.joyrpc.read.service.impl;

import com.markus.joyrpc.read.service.EchoService;

/**
 * @Author: zhangchenglong06
 * @Date: 2024/4/1
 * @Description:
 */
public class DefaultEchoService implements EchoService {
  @Override
  public String echo(String message) {
    return "EchoService#echo print [" + message + "]";
  }
}
