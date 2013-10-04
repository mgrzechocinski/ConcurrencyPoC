package net.grzechocinski.concurrentpoc.util;

import net.grzechocinski.concurrentpoc.encoder.SingleThreadedEncoder;

/**
 * @author Mateusz Grzechociński <mateusz.grzechocinski@polidea.com>
 */
public class FramesReadyTrigger implements Runnable {

    private int frame1;
    private int frame2;
    private int frame3;
    private int frame4;

    private SingleThreadedEncoder encoder;

    public FramesReadyTrigger(SingleThreadedEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public void run() {
        encoder.onNewFrames(new FrameSync(frame1, frame2, frame3, frame4));
    }

    public void setFrame1(int frame1) {
        this.frame1 = frame1;
    }

    public void setFrame2(int frame2) {
        this.frame2 = frame2;
    }

    public void setFrame3(int frame3) {
        this.frame3 = frame3;
    }

    public void setFrame4(int frame4) {
        this.frame4 = frame4;
    }
}
