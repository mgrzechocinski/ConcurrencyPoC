package net.grzechocinski.concurrentpoc;

import net.grzechocinski.concurrentpoc.decoder.*;
import net.grzechocinski.concurrentpoc.encoder.SingleThreadedEncoder;

/**
 * @author Mateusz Grzechociński <mateusz.grzechocinski@polidea.com>
 */
public class Main {

    public static void main(String[] args) throws Exception {

        SingleThreadedEncoder encoder = new SingleThreadedEncoder();
        ParallelDecoder parallelDecoder = new ParallelDecoder(encoder);

        parallelDecoder.decodeBy4Decoders();

        parallelDecoder.awaitUntilAllFinish();
        encoder.shutdown();
    }
}
