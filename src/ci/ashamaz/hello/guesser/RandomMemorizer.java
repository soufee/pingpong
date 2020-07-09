package ci.ashamaz.hello.guesser;

public class RandomMemorizer {
    public boolean isGuessed = false;
    private int number;

    public RandomMemorizer() {
        number = (int) (Math.random() * 1000);
    }

    public boolean checkNumber(int n) {
        if (n == number) {
            isGuessed = true;
            System.out.println("Да! Это число "+n);
        }
        return n == number;
    }
}
