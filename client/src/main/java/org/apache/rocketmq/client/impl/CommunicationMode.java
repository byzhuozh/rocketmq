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
package org.apache.rocketmq.client.impl;

public enum CommunicationMode {
    //客户端提交消息到broker后会等待返回结果，相对来说是最常用的方式
    SYNC,       // 可靠同步发送

    //调用发送接口时会注册一个callback类，发送线程继续其它业务逻辑，producer在收到broker结果后回调。比较适合不想发送结果影响正常业务逻辑的情况
    ASYNC,     // 可靠异步发送

    //Producer提交消息后，无论broker是否正常接收消息都不关心。适合于追求高吞吐、能容忍消息丢失的场景，比如日志收集
    ONEWAY,   // 单向发送，不保证一定成功发送
}
