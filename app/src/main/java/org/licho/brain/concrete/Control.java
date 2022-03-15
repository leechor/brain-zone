package org.licho.brain.concrete;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 *
 */
public class Control {
    private CompletableFuture future;

    public Future BeginInvoke(Runnable action) {
        return CompletableFuture.runAsync(action);
    }
}
