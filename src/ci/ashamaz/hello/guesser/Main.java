package ci.ashamaz.hello.guesser;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Strategy incremental = new Strategy() {
            int i = 0;

            @Override
            public int getNum() {
                return ++i;
            }
        };
        Strategy randomizer = () -> (int) (Math.random() * 1000);

        Strategy cleverRandomizer = new Strategy() {
            List<Integer> list = new ArrayList<>();

            @Override
            public int getNum() {
                return getUniqueRandom();
            }

            public int getUniqueRandom() {
                int n = (int) (Math.random() * 1000);
                if (!list.contains(n)) return n;
                else return getUniqueRandom();
            }
        };

        RandomMemorizer memorizer = new RandomMemorizer();
        Guesser incr = new Guesser(memorizer, incremental, "incrementer");
        Guesser rand = new Guesser(memorizer, randomizer, "randomizer");
        Guesser rand2 = new Guesser(memorizer, randomizer, "2randomizer");
        Guesser clever = new Guesser(memorizer, cleverRandomizer, "clever randomizer");
        Guesser clever2 = new Guesser(memorizer, cleverRandomizer, "2clever randomizer");
        incr.start();
        rand.start();
        clever.start();
        rand2.start();
        clever2.start();
    }


}
