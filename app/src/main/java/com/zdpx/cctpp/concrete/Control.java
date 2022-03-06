package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.IFunction.Action;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.function.Supplier;

/**
 *
 */
public class Control {
    private CompletableFuture future;

    public Future BeginInvoke(Runnable action) {
        return CompletableFuture.runAsync(action);
    }
}
