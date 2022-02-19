package com.chaytech.rapid.core.config;

import com.chaytech.rapid.common.constants.BasicConst;
import com.chaytech.rapid.common.enums.WaitStrategyEnum;
import com.chaytech.rapid.common.utils.NetUtil;
import lombok.Data;

/**
 * 网关配置类
 *
 * @author chency
 * @email chaytech@163.com
 * @date 2022/01/05
 */
@Data
public class RapidConfig {

    /**
     * 默认端口号
     */
    public static final int DEFAULT_PORT = 8888;
    /**
     * 默认注册中心地址
     */
    public static final String DEFAULT_REGISTER_ADDRESS = "http://127.0.0.1:2379";
    /**
     * 默认注册中心地址
     */
    public static final String DEFAULT_NAME_SPACE = "rapid-dev";
    /**
     * 默认报文最大长度
     */
    public static final int DEFAULT_MAX_CONTENT_LENGTH = 64 * 1024 * 1024;
    /**
     * 默认缓冲区大小
     */
    public static final int DEFAULT_BUFFER_SIZE = 16 * 1024;
    /**
     * 默认连接超时时间
     */
    public static final int DEFAULT_CONNECT_TIMEOUT = 30 * 1000;
    /**
     * 默认最大重试次数
     */
    public static final int DEFAULT_HTTP_MAX_REQUEST_RETRY = 2;
    /**
     * 默认最大连接数
     */
    public static final int DEFAULT_HTTP_MAX_CONNECTIONS = 10000;
    /**
     * 默认每个地址支持的最大连接数
     */
    public static final int DEFAULT_HTTP_PER_HOST_MAX_CONNECTIONS = 8000;
    /**
     * 默认空闲超时时间
     */
    public static final int DEFAULT_IDLE_TIMEOUT = 60 * 1000;


    private int port; // 网关的默认端口
    private String serverId; // 网关服务唯一ID：ip:port
    private String registerAddress; // 网关的注册中心地址
    private String nameSpace; // 网关的命名空间：dev test prod
    private int processThread; // 网关服务器的CPU核数映射的线程数
    private int eventLoopGroupBossNum; // Netty的Boss线程数
    private int eventLoopGroupWorkNum; // Netty的Work线程数
    private boolean useEPoll; // 是否开启EPOLL
    private boolean nettyAllocator; // 是否开启Netty内存分配机制
    private int maxContentLength; // http body报文最大长度
    private int dubboConnections; // dubbo开启连接数数量
    private boolean whenComplete; // 设置响应模式, 默认是单异步模式
    private String bufferType; // 网关队列配置：缓冲模式；
    private int bufferSize; // 网关队列：内存队列大小
    private String waitStrategy; // 网关队列：阻塞/等待策略
    private int httpConnectTimeout; // 连接超时时间
    private int httpRequestTimeout; // 请求超时时间
    private int httpMaxRequestRetry; // 客户端请求重试次数
    private int httpMaxConnections; // 客户端请求最大连接数
    private int httpConnectionsPerHost; // 客户端每个地址支持的最大连接数
    private int httpPooledConnectionIdleTimeout; // 客户端空闲连接超时时间, 默认60秒

    public RapidConfig() {
        port = DEFAULT_PORT;
        serverId = NetUtil.getLocalIp() + BasicConst.COLON_SEPARATOR + port;
        registerAddress = DEFAULT_REGISTER_ADDRESS;
        nameSpace = DEFAULT_NAME_SPACE;
        processThread = Runtime.getRuntime().availableProcessors();
        eventLoopGroupBossNum = 1;
        eventLoopGroupWorkNum = processThread;
        useEPoll = Boolean.TRUE.booleanValue();
        nettyAllocator = Boolean.TRUE.booleanValue();
        maxContentLength = DEFAULT_MAX_CONTENT_LENGTH;
        dubboConnections = processThread;
        whenComplete = Boolean.TRUE.booleanValue();
        bufferSize = DEFAULT_BUFFER_SIZE;
        waitStrategy = WaitStrategyEnum.BLOCKING.getValue();
        httpConnectTimeout = DEFAULT_CONNECT_TIMEOUT;
        httpRequestTimeout = DEFAULT_CONNECT_TIMEOUT;
        httpMaxRequestRetry = DEFAULT_HTTP_MAX_REQUEST_RETRY;
        httpMaxConnections = DEFAULT_HTTP_MAX_CONNECTIONS;
        httpConnectionsPerHost = DEFAULT_HTTP_PER_HOST_MAX_CONNECTIONS;
        httpPooledConnectionIdleTimeout = DEFAULT_IDLE_TIMEOUT;
    }
}
