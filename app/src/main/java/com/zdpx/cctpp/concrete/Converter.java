package com.zdpx.cctpp.concrete;

import java.util.function.Function;

/**
 *
 */
@FunctionalInterface
public interface Converter<T, R> extends Function<T, R> {
}
