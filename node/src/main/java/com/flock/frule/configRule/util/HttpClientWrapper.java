package com.flock.frule.configRule.util;

import com.flock.frule.util.Serializer;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClients;
import org.eclipse.jetty.http.HttpMethod;

import javax.annotation.Nullable;
import java.io.ByteArrayInputStream;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionStage;

public class HttpClientWrapper {

    private static final String CHARSET = "UTF-8";
    private final org.apache.http.client.HttpClient delegate;

    public HttpClientWrapper() {
        this.delegate = HttpClients.createDefault();
    }

    public <T> CompletionStage<T> execute(HttpMethod method, String url, @Nullable Object data) {
        switch (method){
            case GET:
                return (CompletionStage<T>) exec(() -> delegate.execute(new HttpGet(url)));
            case POST:
                HttpPost req = new HttpPost(url);
                BasicHttpEntity entity = new BasicHttpEntity();
                entity.setContent(new ByteArrayInputStream(Serializer.getJsonBytes(data, CHARSET)));
                entity.setContentType(ContentType.APPLICATION_JSON.withCharset(CHARSET).toString());
                req.setEntity(entity);
                return (CompletionStage<T>) exec(() -> delegate.execute(req));
            default:
                return (CompletionStage<T>) FutureUtil.error(new UnsupportedOperationException());
        }
    }

    private <T> CompletionStage<T> exec(Callable<T> call) {
        return FutureUtil.async(call, Runnable::run);
    }
}
