package com.zdpx.cctpp.concrete;

import lombok.Data;

/**
 *
 */
@Data
public class BaseNumber<T> {
    public T count;
    public T minimum;
    public T maximum;
    public T average;
}
