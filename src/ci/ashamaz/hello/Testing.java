package ci.ashamaz.hello;

/*
* Известная задачка на собеседованиях: Сделать пинг-понг между двумя потоками
* Famous java interview task - Ping-Pong between two threads
* */

public class Testing {
    private final static Object lock = new Object();

    public static void main(String[] args) {

        PingPong ping = new PingPong("ping");
        PingPong pong = new PingPong("pong");
        ping.start();
        pong.start();
    }
    static class PingPong extends Thread {
        private String mess;

        public PingPong(String mess) {
            this.mess = mess;
        }

        @Override
        public void run() {
            for (int i = 1; i < 111; i++) {
                synchronized (lock) {
                    System.out.println(i + " " + mess);
                    lock.notifyAll();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {

                    }
                }
            }
        }
    }
}

