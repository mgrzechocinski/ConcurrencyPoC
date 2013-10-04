package net.grzechocinski.concurrentpoc.decoder;

import net.grzechocinski.concurrentpoc.util.FramesReadyTrigger;
import net.grzechocinski.concurrentpoc.util.Log;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * @author Mateusz Grzechociński <mateusz.grzechocinski@polidea.com>
 */
public abstract class AbstractWorker implements Runnable {

    private CountDownLatch countDownLatch;
    private CyclicBarrier cyclicBarrier;
    protected FramesReadyTrigger barrierAction;

    private int currentIndex = 0;

    public AbstractWorker(CountDownLatch countDownLatch, CyclicBarrier cyclicBarrier, FramesReadyTrigger barrierAction) {
        this.countDownLatch = countDownLatch;
        this.cyclicBarrier = cyclicBarrier;
        this.barrierAction = barrierAction;
    }

    @Override
    public void run(){
        try {
            Thread.currentThread().setName(this.getClass().getSimpleName());
            countDownLatch.countDown();
            countDownLatch.await();

            int[] values = specifyValues();
            while (currentIndex < values.length){
                int i = values[currentIndex++];
                onResultReady(i);
                cyclicBarrier.await();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected abstract void onResultReady(int i);

    protected abstract int[] specifyValues();

}

