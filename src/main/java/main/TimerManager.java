package src.main.java.main;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TimerManager {
    private final int THREAD_POOL_SIZE = 1;
    private final int FREQUENCY = 10;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(THREAD_POOL_SIZE);

    public void scheduleTask(Runnable task) {
        scheduler.scheduleAtFixedRate(task, 0, FREQUENCY, TimeUnit.MILLISECONDS);
    }

}
