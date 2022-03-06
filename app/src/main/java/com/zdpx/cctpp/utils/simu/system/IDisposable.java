package com.zdpx.cctpp.utils.simu.system;

/**
 *
 */
public interface IDisposable extends AutoCloseable {
    default void close() {
        Dispose();
    }

    void Dispose();
}
