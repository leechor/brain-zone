package org.licho.brain.concrete;

import org.licho.brain.concrete.cont.EngineResources;
import org.licho.brain.simioEnums.QueueRanking;
import org.licho.brain.utils.simu.system.IDisposable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 */
public class QueueList<T> implements Iterable<T> {

    private QueueItemCache<T> queueItemCache;
    private BaseQueueGridItemTrace<T> baseQueueGridItemTrace;
    private int length;
    private QueueRanking queueRanking;
    private Queue<T> behindQueue;
    private Queue<T> aheadQueue;
    private T cl;

    public QueueList(QueueItemCache<T> queueItemCache) {
        this.queueItemCache = queueItemCache;
    }

    public QueueList(BaseQueueGridItemTrace<T> queueItemCache) {
        this.baseQueueGridItemTrace = queueItemCache;
    }

    public int Length() {
        return this.length;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    public BaseQueueGridItemTrace<T> getBaseQueueGridItemTrace() {
        return this.baseQueueGridItemTrace;
    }

    @SuppressWarnings("unchecked")
    QueueItemCache<T> getQueueItemCache() {
        if (this.queueItemCache == null) {
            // TODO: 2021/12/1 a type may wrong.
            this.queueItemCache = (QueueItemCache<T>) this.baseQueueGridItemTrace.getAbsBaseRunSpace()
                    .getMayApplication().getCache(this.cl.getClass());
        }
        return this.queueItemCache;
    }

    public void changeQueueRanking(QueueRanking queueRanking) {
        this.queueRanking = queueRanking;
        while (this.behindQueue != null) {
            Queue<T> queueItem = this.clearBehind();
            QueueItemCache<T> itemCache = this.getQueueItemCache();
            if (itemCache != null) {
                itemCache.resetBehindQueue(queueItem);
            }
        }
    }

    public Queue<T> getQueue(IntelligentObjectRunSpace intelligentObjectRunSpace) {
        // maybe wrong
        if (this.behindQueue == null) {
            return null;
        }

        var queue = this.getQueueList();
        while (queue.hasNext()) {
            var q = queue.next();
            if (q.getAssociatedObjectRunSpace() == intelligentObjectRunSpace) {
                return q;
            }
        }
        return null;
    }


    public Queue<T> getQueue(T queueObject) {
        // maybe wrong
        if (this.behindQueue == null) {
            return null;
        }

        var queue = this.getQueueList();
        while (queue.hasNext()) {
            var q = queue.next();
            if (q == queueObject) {
                return q;
            }
        }
        return null;
    }

    public Queue<T> ItemAtIndex(int index) {
        if (index < 0 || index > this.length - 1) {
            return null;
        }
        Queue<T> queue;
        if (index > this.length / 2) {
            queue = this.aheadQueue;
            for (int i = this.length - 1; i > index; i--) {
                queue = queue.getAheadQueue();
            }
        } else {
            queue = this.behindQueue;
            for (int j = 0; j < index; j++) {
                queue = queue.getBehindQueue();
            }
        }
        return queue;
    }

    public Queue<T> method_9(double param0) {
        if (this.behindQueue == null) {
            return null;
        }
        double num = 1.0;
        switch (this.queueRanking) {
            case FirstInFirstOut:
                return this.aheadQueue;
            case LastInFirstOut:
                return null;
            case LargestValueFirst:
                num = -1.0;
                break;
        }
        for (Queue<T> queue = this.aheadQueue; queue != null; queue = queue.getAheadQueue()) {
            if ((param0 - queue.method_1()) * num >= 0.0) {
                return queue;
            }
        }
        return null;
    }

    public void insertByValue(Queue<T> queueItem, double value) {
        this.length++;
        queueItem.updateQueueList(this, value);
        if (this.behindQueue == null) {
            this.behindQueue = queueItem;
            this.aheadQueue = queueItem;
            return;
        }
        Queue<T> queue = this.method_9(queueItem.method_1());
        if (queue != null) {
            queueItem.setBehindQueue(queue.getBehindQueue());
            queueItem.setAheadQueue(queue);
            queue.setBehindQueue(queueItem);
            if (queueItem.getBehindQueue() != null) {
                queueItem.getBehindQueue().setAheadQueue(queueItem);
            }
            if (queue == this.aheadQueue) {
                this.aheadQueue = queueItem;
                return;
            }
        } else {
            queueItem.setBehindQueue(this.behindQueue);
            if (this.behindQueue != null) {
                this.behindQueue.setAheadQueue(queueItem);
            }
            this.behindQueue = queueItem;
        }
    }

    public void insertByIndex(Queue<T> queueItem, int index, double value) {
        if (index == 0) {
            if (this.behindQueue == null) {
                this.behindQueue = queueItem;
                this.aheadQueue = queueItem;
            } else {
                queueItem.setBehindQueue(this.behindQueue);
                this.behindQueue.setAheadQueue(queueItem);
                this.behindQueue = queueItem;
            }
            this.length++;
            queueItem.updateQueueList(this, value);
            return;
        }
        this.method_12(queueItem, this.ItemAtIndex(index - 1), value);
    }

    public void method_12(Queue<T> queueItem, Queue<T> predQueueItem, double param2) {
        if (predQueueItem == null) {
            throw new RuntimeException(EngineResources.Error_SystemException_InvalidQueueInsertion);
        }
        queueItem.setBehindQueue(predQueueItem.getBehindQueue());
        if (queueItem.getBehindQueue() != null) {
            queueItem.getBehindQueue().setAheadQueue(queueItem);
        }
        queueItem.setAheadQueue(predQueueItem);
        predQueueItem.setBehindQueue(queueItem);
        if (this.aheadQueue == predQueueItem) {
            this.aheadQueue = queueItem;
        }
        this.length++;
        queueItem.updateQueueList(this, param2);
    }

    public void method_13(Queue<T> queueItem, Queue<T> succQueueItem, double value) {
        if (succQueueItem == null) {
            throw new RuntimeException(EngineResources.Error_SystemException_InvalidQueueInsertion);
        }
        queueItem.setAheadQueue(succQueueItem.getAheadQueue());
        if (queueItem.getAheadQueue() != null) {
            queueItem.getAheadQueue().setBehindQueue(queueItem);
        }
        queueItem.setBehindQueue(succQueueItem);
        succQueueItem.setAheadQueue(queueItem);
        if (this.behindQueue == succQueueItem) {
            this.behindQueue = queueItem;
        }
        this.length++;
        queueItem.updateQueueList(this, value);
    }

    public QueueList<T>.EnumeratorUtil getQueueList() {
        return new EnumeratorUtil(this);
    }

    private Queue<T> clearBehind() {
        if (this.behindQueue == null) {
            return null;
        }
        Queue<T> q = this.behindQueue;
        this.behindQueue = this.behindQueue.getBehindQueue();

        if (this.behindQueue != null) {
            this.behindQueue.setAheadQueue(null);
        } else {
            this.aheadQueue = null;
        }
        this.length--;
        q.clear();
        return q;
    }

    public Queue<T> method_15() {
        if (this.aheadQueue == null) {
            return null;
        }
        Queue<T> queue = this.aheadQueue;
        this.aheadQueue = this.aheadQueue.getAheadQueue();
        if (this.aheadQueue != null) {
            this.aheadQueue.setBehindQueue(null);
        } else {
            this.behindQueue = null;
        }
        this.length--;
        queue.clear();
        return queue;
    }

    public void method_16(Queue<T> queueItem) {
        if (queueItem == this.behindQueue) {
            this.clearBehind();
            return;
        }
        if (queueItem == this.aheadQueue) {
            this.method_15();
            return;
        }
        queueItem.getAheadQueue().setBehindQueue(queueItem.getBehindQueue());
        queueItem.getBehindQueue().setAheadQueue(queueItem.getAheadQueue());
        this.length--;
        queueItem.clear();
    }

    public boolean method_17() {
        if (this.queueRanking == QueueRanking.LargestValueFirst || this.queueRanking == QueueRanking.SmallestValueFirst) {
            List<Queue<T>> array = new ArrayList<>();
            for (int i = 0; i < this.Length(); i++) {
                array.add(this.clearBehind());
            }

            array.forEach(t -> this.insertByValue(t, t.getValue()));

            var tmp = this.getQueueList();
            int i = 0;
            while (tmp.hasNext()) {
                var current = tmp.next();
                if (current != array.get(i)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public Queue<T> getBehindQueue() {
        return this.behindQueue;
    }

    public Queue<T> getAheadQueue() {
        return this.aheadQueue;
    }


    public QueueRanking getQueueRanking() {
        return this.queueRanking;
    }

    public class EnumeratorUtil implements Iterator<Queue<T>>, IDisposable {
        private QueueList<T> queueList;
        private Queue<T> current;

        public EnumeratorUtil(QueueList<T> queueList) {
            this.queueList = queueList;
            this.current = null;
        }

        @Override
        public boolean hasNext() {
            if (this.current == this.queueList.aheadQueue) {
                return false;
            } else {
                return true;
            }
        }

        @Override
        public Queue<T> next() {
            if (this.current == null) {
                this.current = this.queueList.behindQueue;
            } else {
                this.current = this.current.getBehindQueue();
            }
            return this.current;
        }

        @Override
        public void Dispose() {

        }
    }
}
