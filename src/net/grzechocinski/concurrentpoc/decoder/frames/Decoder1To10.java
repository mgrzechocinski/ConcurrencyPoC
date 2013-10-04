package net.grzechocinski.concurrentpoc.decoder.frames;

import net.grzechocinski.concurrentpoc.decoder.AbstractWorker;
import net.grzechocinski.concurrentpoc.util.FramesReadyTrigger;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
* @author Mateusz Grzechociński <mateusz.grzechocinski@polidea.com>
*/
public class Decoder1To10 extends AbstractWorker {

    public Decoder1To10(CountDownLatch countDownLatch, CyclicBarrier cyclicBarrier, FramesReadyTrigger barrierAction) {
        super(countDownLatch, cyclicBarrier, barrierAction);
    }

    @Override
    protected void onResultReady(int result) {
       barrierAction.setFrame1(result);
    }

    @Override
    protected int[] specifyValues() {
        return new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    }
}
