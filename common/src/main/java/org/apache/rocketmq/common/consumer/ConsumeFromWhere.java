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
package org.apache.rocketmq.common.consumer;

/**
 * Consumer从哪里开始消费
 */
public enum ConsumeFromWhere {

    /**
     * 从队列当前最大偏移量开始消费
     *
     * 第一次启动从队列最后位置消费，后续再启动接着上次消费的进度开始消费
     */
    CONSUME_FROM_LAST_OFFSET,

    @Deprecated
    CONSUME_FROM_LAST_OFFSET_AND_FROM_MIN_WHEN_BOOT_FIRST,
    @Deprecated
    CONSUME_FROM_MIN_OFFSET,
    @Deprecated
    CONSUME_FROM_MAX_OFFSET,

    /**
     * 第一次启动从队列初始位置消费，后续再启动接着上次消费的进度开始消费
     *
     * 即：从队列当前最小偏移量开始消费
     */
    CONSUME_FROM_FIRST_OFFSET,

    /**
     * 第一次启动从指定时间点位置消费，后续再启动接着上次消费的进度开始消费
     *
     * 从消费者启动时间戳开始消费
     */
    CONSUME_FROM_TIMESTAMP,

    /**
     * 注意：
     *  以上所说的第一次启动是指从来没有消费过的消费者，如果该消费者消费过，那么会在broker端记录该消费者的消费位置，
     *  如果该消费者挂了再启动，那么自动从上次消费的进度开始
     */
}
