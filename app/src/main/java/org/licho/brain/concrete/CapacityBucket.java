package org.licho.brain.concrete;

/**
 *
 */
public class CapacityBucket<T> {
    private final long capacity;
    private final long bucket;
//    private final T[][] gparam_0;


    public CapacityBucket(long capacity, long bucket) {
        if (capacity < 0L) {
            throw new IllegalArgumentException("Capacity must be >= 0 capacity");
        }
        if (bucket <= 0L) {
            throw new IllegalArgumentException("Bucket size must be > 0 bucketSize");
        }
        this.capacity = capacity;
        this.bucket = bucket;
        // TODO: 2022/1/14
    }


    public CapacityBucket(CapacityBucket<T> source) {
        this(source.capacity, source.bucket);
        this.method_0(source);
    }

    public void method_0(CapacityBucket<T> source) {

    }

    public long Length() {
        return this.capacity;
    }
}
