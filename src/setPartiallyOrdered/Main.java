package setPartiallyOrdered;

public class Main {
    public static void main(String[] args) {
        Set s = new Set();

        boolean ifInitialized = s.init(new Set.Pair[] {
                new Set.Pair(1, 2),
                new Set.Pair(2, 4),
                new Set.Pair(4, 6),
                new Set.Pair(2, 10),
                new Set.Pair(4, 8),
                new Set.Pair(6, 3),
                new Set.Pair(1, 3),
                new Set.Pair(3, 5),
                new Set.Pair(5, 8),
                new Set.Pair(7, 5),
                new Set.Pair(7, 9),
                new Set.Pair(9, 4),
                new Set.Pair(9, 10),
                new Set.Pair(5, 10),
        });

        s.print();
        s.sort();
        System.out.println();
        s.print();
    }
}
