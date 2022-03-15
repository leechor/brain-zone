package org.licho.brain.concrete;

/**
 *
 */
public class QueueItemCache<T> extends Cache {
    private Queue<T> queue;

    public Queue<T> getQueue(T queueObject) {
        Queue<T> queue;
        if (this.queue != null) {
            queue = this.queue;
            this.queue = queue.getBehindQueue();
            queue.setAheadQueue(null);
            queue.setBehindQueue(null);
        } else {
            queue = new Queue<T>();
        }
        queue.setCurrentEntry(queueObject);
        return queue;
    }

    public void reset(Queue<T> queueItem) {
        queueItem.init();
        queueItem.setBehindQueue(this.queue);
        this.queue = queueItem;
    }


    public void resetBehindQueue(Queue<T> queueItem) {
        queueItem.init();
        queueItem.setBehindQueue(this.queue);
        this.queue = queueItem;
    }
}
