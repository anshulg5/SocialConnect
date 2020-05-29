package com.flock.frule.util;

import com.sun.istack.internal.Nullable;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClients;
import org.eclipse.jetty.http.HttpMethod;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionException;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

public class HttpClientWrapper {

    private static final String CHARSET = "UTF-8";
    private final org.apache.http.client.HttpClient delegate;

    public HttpClientWrapper() {
        this.delegate = HttpClients.createDefault();
    }

    public CompletionStage<Void> execute(HttpMethod method, String url, @Nullable Object data) {
        switch (method){
            case GET:
                return exec(() -> delegate.execute(new HttpGet(url)));
            case POST:
                HttpPost req = new HttpPost(url);
                BasicHttpEntity entity = new BasicHttpEntity();
                entity.setContent(new ByteArrayInputStream(Serializer.getJsonBytes(data, CHARSET)));
                entity.setContentType(ContentType.APPLICATION_JSON.withCharset(CHARSET).toString());
                req.setEntity(entity);
                return exec(() -> delegate.execute(req));
            default:
                return FutureUtil.error(new UnsupportedOperationException());
        }
    }

    private <T> CompletionStage<Void> exec(Callable<T> call) {
        return FutureUtil.async(call, Runnable::run)
                .thenAccept(resp -> {

                });
    }

    private <T,U> CompletionStage<U> getResponse(Callable<T> call) {
        return FutureUtil.async(call, Runnable::run)
                .thenApply(resp -> {
                    try {
                        String responseContent = new BufferedReader(new InputStreamReader(((HttpResponse)resp)
                                .getEntity().getContent())).lines()
                                .collect(Collectors.joining("\n"));
                        return (U)responseContent;
                    } catch (IOException e) {
                        throw new CompletionException(e);
                    }

                });
    }
}
