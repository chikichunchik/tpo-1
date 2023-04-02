package lab.counter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Counter {
    private int counter;
    private Lock lock = new ReentrantLock();

    public void incrementSimple() {
        counter++;
    }

    public void decrementSimple() {
        counter--;
    }

    public synchronized void incrementSynchMethod() {
        counter++;
    }

    public synchronized void decrementSynchMethod() {
        counter--;
    }

    public void incrementLock() {
        lock.lock();
        counter++;
        lock.unlock();
    }

    public void decrementLock() {
        lock.lock();
        counter--;
        lock.unlock();
    }

    public void incrementSynchBlock() {
        synchronized (Counter.class) {
            counter++;
        }
    }

    public void decrementSynchBlock() {
        synchronized (Counter.class) {
            counter--;
        }
    }

    public static void main(String[] args) {
        final Counter counter = new Counter();

        Thread testSimpleDec = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                counter.decrementSimple();
            }
        });
        Thread testSimpleInc = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                counter.incrementSimple();
            }
        });

        Thread testSynchMethDec = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                counter.decrementSynchMethod();
            }
        });
        Thread testSynchMethInc = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                counter.incrementSynchMethod();
            }
        });

        Thread testSynchBlockDec = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                counter.decrementSynchBlock();
            }
        });
        Thread testSynchBlockInc = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                counter.incrementSynchBlock();
            }
        });

        Thread testLockDec = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                counter.decrementLock();
            }
        });
        Thread testLockInc = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                counter.incrementLock();
            }
        });

        testSimpleDec.start();
        testSimpleInc.start();

        try {
            testSimpleDec.join();
            testSimpleInc.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Simple: " + counter.counter);

        counter.counter=0;

        testSynchBlockInc.start();
        testSynchBlockDec.start();

        try {
            testSynchBlockInc.join();
            testSynchBlockDec.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("SynchBlock: " + counter.counter);

        counter.counter=0;

        testLockDec.start();
        testLockInc.start();

        try {
            testLockDec.join();
            testLockInc.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Lock: " +  counter.counter);

        counter.counter=0;

        testSynchMethDec.start();
        testSynchMethInc.start();

        try {
            testSynchMethDec.join();
            testSynchMethInc.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("SynchMeth: " + counter.counter);
    }
}
