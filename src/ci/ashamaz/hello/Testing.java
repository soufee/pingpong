package ci.ashamaz.hello;

/*
* Известная задачка на собеседованиях: Сделать пинг-понг между двумя потоками
* Famous java interview task - Ping-Pong between two threads
* */

public class Testing {
    public static void main(String[] args) {
        final Object object = new Object();

        PingPong ping = new PingPong("ping", object);
        PingPong pong = new PingPong("pong", object);
        ping.start();
        pong.start();
    }

}

class PingPong extends Thread {
    private String text;
    private int counter = 10;
    private final Object lock;

    public PingPong(String text, Object lock) {
        this.text = text;
        this.lock = lock;
    }

    @Override
    public void run() {
        while (counter>0) {
            synchronized (lock) {
                System.out.println(counter + ": " + text);
                counter--;
            }
            try {
                Thread.sleep(100);
            } catch (Exception e) {
            }
        }
    }
}
