import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    @Test
    public void test1() {
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads1 = new ArrayDequeSolution<>();
        String message = "\n";

        for (int i = 0; i < 1000; i += 1) {
            int numberBetweenZeroAndNine = StdRandom.uniform(16);

            if (numberBetweenZeroAndNine < 4) {
                sad1.addLast(i);
                ads1.addLast(i);
                message = message + "addLast(" + i + ")\n";
            } else if (numberBetweenZeroAndNine <= 4 && numberBetweenZeroAndNine < 8) {
                sad1.addFirst(i);
                ads1.addFirst(i);
                message = message + "addFirst(" + i + ")\n";
            } else if (numberBetweenZeroAndNine <= 8 && numberBetweenZeroAndNine < 12) {
                if (!sad1.isEmpty() && !ads1.isEmpty()) {
                    Integer sadFirst = sad1.removeFirst();
                    Integer adsFirst = ads1.removeFirst();
                    message = message + "removeFirst(): " + sadFirst + "\n";
                    assertEquals(message, sadFirst, adsFirst);
                }
            } else {
                if (!sad1.isEmpty() && !ads1.isEmpty()) {
                    Integer sadLast = sad1.removeLast();
                    Integer adsLast = ads1.removeLast();
                    message = message + "removeLast(): " + sadLast + "\n";
                    assertEquals(message, sadLast, adsLast);
                }
            }
        }
    }
}