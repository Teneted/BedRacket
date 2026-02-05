package org.teneted.bedracket.event;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Improved multithreading manager
 * Provides asynchronous execution, scheduled tasks and other features with better performance and security
 */
public final class Multithreading {

    // Core thread pool configuration parameters
    private static final int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors();
    private static final int MAX_POOL_SIZE = Math.max(CORE_POOL_SIZE * 4, 64);
    private static final long KEEP_ALIVE_TIME = 30L;
    private static final long DEFAULT_QUEUE_CAPACITY = 2048;

    // Thread factories and rejection policies
    private static final ThreadFactory ASYNC_THREAD_FACTORY = new NamedThreadFactory("MinecartAsync");
    private static final ThreadFactory SCHEDULED_THREAD_FACTORY = new NamedThreadFactory("MinecartScheduled");
    private static final RejectedExecutionHandler DEFAULT_REJECT_HANDLER = new ThreadPoolExecutor.CallerRunsPolicy();

    // Asynchronous execution thread pool - using bounded queue to prevent memory overflow
    private static final ThreadPoolExecutor ASYNC_EXECUTOR = new ThreadPoolExecutor(
            CORE_POOL_SIZE,
            MAX_POOL_SIZE,
            KEEP_ALIVE_TIME,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>((int) DEFAULT_QUEUE_CAPACITY),
            ASYNC_THREAD_FACTORY,
            DEFAULT_REJECT_HANDLER
    );

    // Scheduled task thread pool
    private static final ScheduledThreadPoolExecutor SCHEDULED_EXECUTOR = new ScheduledThreadPoolExecutor(
            Math.max(CORE_POOL_SIZE * 2, 16),
            SCHEDULED_THREAD_FACTORY,
            DEFAULT_REJECT_HANDLER
    );

    static {
        // Set thread pool pre-start strategy for better performance
        ASYNC_EXECUTOR.prestartAllCoreThreads();
        SCHEDULED_EXECUTOR.setRemoveOnCancelPolicy(true); // Remove immediately from queue when task cancelled
    }

    /**
     * Execute periodic scheduled tasks
     */
    public static ScheduledFuture<?> scheduleAtFixedRate(Runnable command,
                                                         long initialDelay,
                                                         long period,
                                                         TimeUnit unit) {
        validateNotNull(command, "Runnable cannot be null");
        return SCHEDULED_EXECUTOR.scheduleAtFixedRate(command, initialDelay, period, unit);
    }

    /**
     * Execute single delayed task
     */
    public static ScheduledFuture<?> schedule(Runnable command, long delay, TimeUnit unit) {
        validateNotNull(command, "Runnable cannot be null");
        return SCHEDULED_EXECUTOR.schedule(command, delay, unit);
    }

    /**
     * Execute task asynchronously
     */
    public static Future<?> runAsync(Runnable runnable) {
        validateNotNull(runnable, "Runnable cannot be null");
        return ASYNC_EXECUTOR.submit(runnable);
    }

    /**
     * Execute task asynchronously with return value
     */
    public static <T> Future<T> runAsync(Callable<T> task) {
        validateNotNull(task, "Callable cannot be null");
        return ASYNC_EXECUTOR.submit(task);
    }

    /**
     * Get current active thread count
     */
    public static int getActiveThreadCount() {
        return ASYNC_EXECUTOR.getActiveCount();
    }

    /**
     * Get completed task count
     */
    public static long getCompletedTaskCount() {
        return ASYNC_EXECUTOR.getCompletedTaskCount();
    }

    /**
     * Get total task count
     */
    public static long getTotalTaskCount() {
        return ASYNC_EXECUTOR.getTaskCount();
    }

    /**
     * Get task count in queue
     */
    public static int getQueueSize() {
        return ASYNC_EXECUTOR.getQueue().size();
    }

    /**
     * Gracefully shut down all thread pools
     * Wait for submitted tasks to complete, force shutdown after timeout
     */
    public static void shutdownGracefully(long timeout, TimeUnit unit) throws InterruptedException {
        // First stop accepting new tasks
        ASYNC_EXECUTOR.shutdown();
        SCHEDULED_EXECUTOR.shutdown();

        // Wait for existing tasks to complete
        if (!ASYNC_EXECUTOR.awaitTermination(timeout / 2, unit)) {
            ASYNC_EXECUTOR.shutdownNow(); // Force interrupt
        }

        if (!SCHEDULED_EXECUTOR.awaitTermination(timeout / 2, unit)) {
            SCHEDULED_EXECUTOR.shutdownNow();
        }
    }

    /**
     * Immediately shut down all thread pools
     */
    public static void shutdownNow() {
        ASYNC_EXECUTOR.shutdownNow();
        SCHEDULED_EXECUTOR.shutdownNow();
    }

    /**
     * Check if shutdown has been initiated
     */
    public static boolean isShutdown() {
        return ASYNC_EXECUTOR.isShutdown() && SCHEDULED_EXECUTOR.isShutdown();
    }

    /**
     * Validate that parameter is not null
     */
    private static void validateNotNull(Object obj, String message) {
        if (obj == null) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Custom named thread factory
     */
    private static class NamedThreadFactory implements ThreadFactory {
        private final String prefix;
        private final AtomicLong threadNumber = new AtomicLong(0);
        private final ThreadGroup group;

        public NamedThreadFactory(String prefix) {
            // Since getSecurityManager() is deprecated in Java 17+, use a safe approach
            // ThreadGroup can be null, in which case it uses the system's default thread group
            this.group = Thread.currentThread().getThreadGroup();
            this.prefix = prefix;
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    String.format("%s-%d", prefix, threadNumber.incrementAndGet()),
                    0);

            // Set default priority and non-daemon thread state
            if (t.isDaemon()) t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY) t.setPriority(Thread.NORM_PRIORITY);

            return t;
        }
    }
}