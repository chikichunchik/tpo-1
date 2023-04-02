package lab.billiards.first;

import java.util.Optional;

public class BallThread extends Thread {
    private Ball b;
    private BallCanvas ballCanvas;

    public BallThread(Ball ball, BallCanvas ballCanvas) {
        b = ball;
        this.ballCanvas = ballCanvas;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i < 10000; i++) {
                b.move();
                Thread.sleep(5);
            }
        } catch (InterruptedException ex) {
            System.out.println("Exception in thread " + Thread.currentThread().getName());
        }
    }
}
