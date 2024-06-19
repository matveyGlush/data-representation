package setPartiallyOrdered;

public class Main {
    public static void main(String[] args) {

        Set s = new Set();

        boolean ifInitialized = s.init(new Pair[] { // классы и вывод
                new Pair(6, 123),
                new Pair(1, 2),
                new Pair(4, 6),
                new Pair(2, 10),
                new Pair(4, 8),
                new Pair(6, 3),
                new Pair(1, 3),
                new Pair(3, 5),
                new Pair(5, 8),
                new Pair(7, 5),
                new Pair(7, 9),
                new Pair(9, 4),
                new Pair(9, 10),
                new Pair(5, 10),
                // if you want exception
//                new Set.Pair(2, 1),
        });

        if (ifInitialized) {
            s.print();
            if (s.sort()) {
                System.out.println();
                s.print();
            } else System.out.println("sorting error");
        } else {
            System.out.println("initialization error");
        }
    }

}