package net.grzechocinski.concurrentpoc.decoder.frames;

import net.grzechocinski.concurrentpoc.decoder.AbstractWorker;
import net.grzechocinski.concurrentpoc.util.FramesReadyTrigger;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
* @author Mateusz Grzechociński <mateusz.grzechocinski@polidea.com>
*/
public class Decoder21To30 extends AbstractWorker {

    public Decoder21To30(CountDownLatch countDownLatch, CyclicBarrier cyclicBarrier, FramesReadyTrigger barrierAction) {
        super(countDownLatch, cyclicBarrier, barrierAction);
    }

    @Override
    protected void onResultReady(int result) {
        barrierAction.setFrame3(result);
    }

    @Override
    protected int[] specifyValues() {
        return new int[]{21, 22, 23, 24, 25, 26, 27, 28, 29, 30};
    }
}
