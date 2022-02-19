package com.chaytech.rapid.common.helper;

import org.asynchttpclient.*;

import java.util.concurrent.CompletableFuture;

/**
 * 异步的http辅助类
 *
 * @author chency
 * @email chaytech@163.com
 * @date 2022/02/19
 */
public class AsyncHttpHelper {

    private AsyncHttpHelper() {

    }

    private static final class AsyncHttpHelperInstance {
        private static final AsyncHttpHelper INSTANCE = new AsyncHttpHelper();
    }

    public static AsyncHttpHelper getInstance() {
        return AsyncHttpHelperInstance.INSTANCE;
    }


    private AsyncHttpClient asyncHttpClient;

    public void initialized(AsyncHttpClient asyncHttpClient) {
        this.asyncHttpClient = asyncHttpClient;
    }

    public CompletableFuture<Response> executeRequest(Request request) {
        ListenableFuture<Response> future = asyncHttpClient.executeRequest(request);
        return future.toCompletableFuture();
    }

    public <T> CompletableFuture<T> executeRequest(Request request, AsyncHandler<T> handler) {
        ListenableFuture<T> future = asyncHttpClient.executeRequest(request, handler);
        return future.toCompletableFuture();
    }
}
