package net.grzechocinski.concurrentpoc.encoder;

import net.grzechocinski.concurrentpoc.util.FrameSync;
import net.grzechocinski.concurrentpoc.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author Mateusz Grzechociński <mateusz.grzechocinski@polidea.com>
 */
public class SingleThreadedEncoder implements Runnable {

    public LinkedBlockingQueue<FrameSync> queue = new LinkedBlockingQueue<FrameSync>();
    private final ExecutorService executorService;

    public SingleThreadedEncoder() {
        executorService = Executors.newSingleThreadExecutor();
        executorService.submit(this);
    }

    public void onNewFrames(FrameSync frameSync) {
        try {
            queue.put(frameSync);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted() && !executorService.isShutdown()) {
            try {
                FrameSync take = queue.take();
                Log.i("Encoding: " + take.frame1 + "/" + take.frame2 + "/" + take.frame3 + "/" + take.frame4);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void shutdown() throws InterruptedException {
        executorService.shutdown();
    }
}
