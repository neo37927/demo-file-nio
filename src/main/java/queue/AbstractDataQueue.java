package queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.TaskExecutor;

import javax.annotation.Resource;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by xiaolin  on 2017/8/8.
 */
public abstract class AbstractDataQueue<T> implements DataQueue<T> {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Resource(name = "syncTaskUserDataCustomerExecutor")
    protected TaskExecutor syncTaskCustomerExecutor;

    public void put(T t, BlockingQueue<T> bq) {
        Boolean isSuccess = Boolean.FALSE;
        Integer count = 2;
        while (!isSuccess) {
            if (--count <= 0) {return;}
            try {
//                bq.put(t);
                isSuccess = bq.offer(t, 1, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                logger.error("添加Object加到队列被中断,异常{}。", e);
                return;
            }
        }
    }

    public T take(BlockingQueue<T> bq) {
        while (true) {
            try {
                return bq.poll(1, TimeUnit.MILLISECONDS);
            } catch (NullPointerException e) {
                if (bq.size() == 0) {
                    return null;
                }
            } catch (InterruptedException e) {
                logger.error("获取Object加到队列被中断,异常{}。", e);
                return null;
            }
        }
    }

    /*public void clearParseQueue(BlockingQueue<T> bq) {
        bq.clear();
        isSuccess = Boolean.FALSE;
    }*/
}
