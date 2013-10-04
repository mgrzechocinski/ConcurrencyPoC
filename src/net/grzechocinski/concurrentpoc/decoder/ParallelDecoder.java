package net.grzechocinski.concurrentpoc.decoder;

import net.grzechocinski.concurrentpoc.decoder.frames.Decoder11To20;
import net.grzechocinski.concurrentpoc.decoder.frames.Decoder1To10;
import net.grzechocinski.concurrentpoc.decoder.frames.Decoder21To30;
import net.grzechocinski.concurrentpoc.decoder.frames.Decoder31To40;
import net.grzechocinski.concurrentpoc.encoder.SingleThreadedEncoder;
import net.grzechocinski.concurrentpoc.util.FramesReadyTrigger;

import java.util.concurrent.*;

/**
 * @author Mateusz Grzechociński <mateusz.grzechocinski@polidea.com>
 */
public class ParallelDecoder{

    private static final int COUNT = 4;

    private final ExecutorService decoders;
    private final CyclicBarrier cyclicBarrier;
    private final CountDownLatch countDownLatch;
    private final FramesReadyTrigger barrierAction;

    public ParallelDecoder(SingleThreadedEncoder encoder) {
        this.barrierAction = new FramesReadyTrigger(encoder);
        this.cyclicBarrier = new CyclicBarrier(COUNT, barrierAction);
        this.decoders = Executors.newFixedThreadPool(COUNT);
        this.countDownLatch = new CountDownLatch(COUNT);
    }

    public void decodeBy4Decoders() {
        decoders.submit(new Decoder1To10(countDownLatch, cyclicBarrier, barrierAction));
        decoders.submit(new Decoder11To20(countDownLatch, cyclicBarrier, barrierAction));
        decoders.submit(new Decoder21To30(countDownLatch, cyclicBarrier, barrierAction));
        decoders.submit(new Decoder31To40(countDownLatch, cyclicBarrier, barrierAction));
    }

    public void awaitUntilAllFinish() throws InterruptedException {
        decoders.shutdown();
        decoders.awaitTermination(5, TimeUnit.SECONDS);
    }
}
