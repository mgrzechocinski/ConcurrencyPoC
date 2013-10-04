package net.grzechocinski.concurrentpoc.decoder.frames;

import net.grzechocinski.concurrentpoc.decoder.AbstractWorker;
import net.grzechocinski.concurrentpoc.util.FramesReadyTrigger;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
* @author Mateusz Grzechociński <mateusz.grzechocinski@polidea.com>
*/
public class Decoder11To20 extends AbstractWorker {

    public Decoder11To20(CountDownLatch countDownLatch, CyclicBarrier cyclicBarrier, FramesReadyTrigger barrierAction) {
        super(countDownLatch, cyclicBarrier, barrierAction);
    }

    @Override
    protected void onResultReady(int result) {
        barrierAction.setFrame2(result);
    }


    @Override
    protected int[] specifyValues() {
        return new int[]{11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
    }
}
