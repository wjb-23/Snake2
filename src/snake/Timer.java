/**
 * Authors: Jason Shi, James Bradley, Sidney Langford
 * COMP 127-04
 * Referred to: https://stackoverflow.com/questions/20959805/adding-a-delay-without-thread-sleep-and-a-while-loop-doing-nothing

 */
package snake;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Timer {
    private static final ScheduledExecutorService scheduledThreadPoolExecutor = Executors.newScheduledThreadPool(10);
    /**
     * Pauses code without interrupting the thread for smooth animation.
     * @param ms
     */
    public static void doPause(int ms) {
        try {
            scheduledThreadPoolExecutor.schedule(() -> {
            }, ms, TimeUnit.MILLISECONDS).get();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public String toString() {
        return "Timer []";
    }
}
