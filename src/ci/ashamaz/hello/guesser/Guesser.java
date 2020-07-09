package ci.ashamaz.hello.guesser;

public class Guesser extends Thread {
    private RandomMemorizer memorizer;
    private Strategy strategy;
    private String objName;

    public Guesser(RandomMemorizer memorizer, Strategy strategy, String objName) {
        this.memorizer = memorizer;
        this.strategy = strategy;
        this.objName = objName;
    }

    public String getObjName() {
        return objName;
    }

    @Override
    public void run() {
        while (!memorizer.isGuessed) {
            synchronized (memorizer) {
                int n = strategy.getNum();
                System.out.println(objName + " предполагает что загадано число "+n);
                boolean res = memorizer.checkNumber(n);
                if (res) System.out.println("Я победил! Ура!! " + objName);
                memorizer.notifyAll();
                try {
                    memorizer.wait(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
