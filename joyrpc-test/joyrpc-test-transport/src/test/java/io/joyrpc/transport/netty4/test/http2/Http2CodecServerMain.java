package io.joyrpc.transport.netty4.test.http2;

/*-
 * #%L
 * joyrpc
 * %%
 * Copyright (C) 2019 joyrpc.io
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import io.joyrpc.Plugin;
import io.joyrpc.extension.URL;
import io.joyrpc.transport.Server;
import io.joyrpc.transport.channel.ChannelChain;

/**
 * @date: 2019/2/18
 */
public class Http2CodecServerMain {
    public static void main(String[] orgs) throws InterruptedException {
        URL url = URL.valueOf("http2://127.0.0.1:22000");
        Server server = Plugin.ENDPOINT_FACTORY.get().createServer(url);
        server.setCodec(new MockHttp2Codec());
        server.setChannelHandlerChain(
                new ChannelChain()
                        .addLast(new MockHttp2ChannelHandler())
        );
        server.open();

        synchronized (Http2CodecServerMain.class) {
            while (true) {
                try {
                    Http2CodecServerMain.class.wait();
                } catch (InterruptedException e) {
                }
            }
        }
    }
}
