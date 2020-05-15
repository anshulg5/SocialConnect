package com.flock.frule.util;

import java.util.concurrent.*;
import java.util.function.Supplier;

public class FutureUtil {

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
                        throw new CompletionException(e);
                    }
                }, es);
    }
}
