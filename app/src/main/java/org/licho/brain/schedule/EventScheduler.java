package org.licho.brain.schedule;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 事件任务处理
 */
public class EventScheduler {

    private final BlockingQueue<Runnable> taskQueue = new PriorityBlockingQueue<>();

    private static final ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
            .setNameFormat("event-scheduler-%d").build();

    ExecutorService executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
            Runtime.getRuntime().availableProcessors() * 2,
            3,
            TimeUnit.SECONDS,
            taskQueue,
            namedThreadFactory,
            new ThreadPoolExecutor.CallerRunsPolicy()
    );


}
