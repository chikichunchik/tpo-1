package lab.dashes;

public class OutputController {
    private static int syncCount=0;

    public synchronized void printNonBlocking(String ch) {
        System.out.println(ch);
    }

    public synchronized void printBlocking(String ch) {
        System.out.println(ch);
        this.notifyAll();
        try {
            this.wait(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        OutputController outputController = new OutputController();
        DashDealer horizontal = new DashDealer("-", ch -> outputController.printNonBlocking(ch));
        DashDealer vertical = new DashDealer("|", ch -> outputController.printNonBlocking(ch));

        DashDealer horizontal_fixed = new DashDealer( "-", ch -> outputController.printBlocking(ch));
        DashDealer vertical_fixed = new DashDealer("|", ch -> outputController.printBlocking(ch));

        Thread t1 = new Thread(horizontal);
        Thread t2 = new Thread(vertical);

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
