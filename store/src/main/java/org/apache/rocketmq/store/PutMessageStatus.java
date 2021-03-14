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
package org.apache.rocketmq.store;

/**
 * 消息发送存储状态
 */
public enum PutMessageStatus {
    //正常状态
    PUT_OK,

    //同步刷盘 且 需要落盘应答的场景,flush超时
    FLUSH_DISK_TIMEOUT,

    //同步刷盘止之后master需要同步到slave 且 需要落盘应答的场景， slave的flush超时,参考CommitLog.handleHA()
    FLUSH_SLAVE_TIMEOUT,

    //同步刷盘止之后master需要同步到slave 且 需要落盘应答的场景， 没有可用的slave,参考CommitLog.handleHA()
    SLAVE_NOT_AVAILABLE,

    //服务不可用
    SERVICE_NOT_AVAILABLE,

    //创建 mapedFile 失败
    CREATE_MAPEDFILE_FAILED,

    //消息不合法，主要是消息体过大，或者topic长度过长
    MESSAGE_ILLEGAL,

    //消息的 properties 大小超过了Short.MAX_VALUE
    PROPERTIES_SIZE_EXCEEDED,

    //最近一段时间内写消息操作还没有完成，则为忙
    OS_PAGECACHE_BUSY,

    //未知异常
    UNKNOWN_ERROR,
}
