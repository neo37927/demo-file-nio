package queue;

import java.util.concurrent.BlockingQueue;

/**
 * Created by xiaolin  on 2017/8/8.
 */
public interface DataQueue<T> {
    void put(T t, BlockingQueue<T> bq);

    T take(BlockingQueue<T> bq);

    void initializeParseQueue(Integer multiThreadAmount, String multiThreadNameGroup);

    void clearParseQueue(BlockingQueue<T> bq);

    void clearParseQueue();
}
