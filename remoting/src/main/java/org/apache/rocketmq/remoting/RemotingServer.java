/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.rocketmq.remoting;

import io.netty.channel.Channel;
import org.apache.rocketmq.remoting.common.Pair;
import org.apache.rocketmq.remoting.exception.RemotingSendRequestException;
import org.apache.rocketmq.remoting.exception.RemotingTimeoutException;
import org.apache.rocketmq.remoting.exception.RemotingTooMuchRequestException;
import org.apache.rocketmq.remoting.netty.NettyRequestProcessor;
import org.apache.rocketmq.remoting.protocol.RemotingCommand;

import java.util.concurrent.ExecutorService;

/**
 * 远程通信，Server接口
 */
public interface RemotingServer extends RemotingService {

    /**
     * 注册请求处理器，ExecutorService必须要对应一个队列大小有限制的阻塞队列，防止OOM
     */
    void registerProcessor(final int requestCode, final NettyRequestProcessor processor, final ExecutorService executor);

    /**
     * 注册默认的处理器
     *
     * @param processor
     * @param executor
     */
    void registerDefaultProcessor(final NettyRequestProcessor processor, final ExecutorService executor);


    /**
     * 服务器绑定的本地端口
     */
    int localListenPort();

    /**
     * 根据请求code来获取不同的处理Pair
     *
     * @param requestCode
     * @return
     */
    Pair<NettyRequestProcessor, ExecutorService> getProcessorPair(final int requestCode);

    /**
     * 同RemotingClient端一样,同步通信,有返回RemotingCommand
     */
    RemotingCommand invokeSync(final Channel channel, final RemotingCommand request, final long timeoutMillis)
            throws InterruptedException, RemotingSendRequestException, RemotingTimeoutException;

    /**
     * 同RemotingClient端一样,异步通信,无返回RemotingCommand
     */
    void invokeAsync(final Channel channel, final RemotingCommand request, final long timeoutMillis, final InvokeCallback invokeCallback)
            throws InterruptedException, RemotingTooMuchRequestException, RemotingTimeoutException, RemotingSendRequestException;

    /**
     * 同RemotingClient端一样，单向通信，诸如心跳包
     */
    void invokeOneway(final Channel channel, final RemotingCommand request, final long timeoutMillis)
            throws InterruptedException, RemotingTooMuchRequestException, RemotingTimeoutException, RemotingSendRequestException;

}
