package net.grzechocinski.concurrentpoc.decoder.frames;

import net.grzechocinski.concurrentpoc.decoder.AbstractWorker;
import net.grzechocinski.concurrentpoc.util.FramesReadyTrigger;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
* @author Mateusz Grzechociński <mateusz.grzechocinski@polidea.com>
*/
public class Decoder31To40 extends AbstractWorker {

    public Decoder31To40(CountDownLatch countDownLatch, CyclicBarrier cyclicBarrier, FramesReadyTrigger barrierAction) {
        super(countDownLatch, cyclicBarrier, barrierAction);
    }

    @Override
    protected void onResultReady(int i) {
        barrierAction.setFrame4(i);
    }

    @Override
    protected int[] specifyValues() {
        return new int[]{31, 32, 33, 34, 35, 36, 37, 38, 39, 40};
    }
}
