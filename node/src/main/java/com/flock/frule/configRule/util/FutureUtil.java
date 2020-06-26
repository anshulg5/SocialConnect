package com.flock.frule.configRule.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

public class FutureUtil {
    private static final Logger log = LoggerFactory.getLogger(FutureUtil.class);

    public static CompletionStage<Void> error(Throwable t) {
        CompletableFuture<Void> cs = new CompletableFuture<>();
        cs.completeExceptionally(t);
        return cs;
    }

    public static <T> CompletionStage<T> async(Callable<T> task, Executor es) {
        return CompletableFuture.completedFuture(null)
                .thenApplyAsync(__ -> {
                    try {
                        return task.call();
                    } catch (Exception e) {
                        log.debug(e.getMessage());
                        throw new CompletionException(e);
                    }
                }, es);
    }
}
