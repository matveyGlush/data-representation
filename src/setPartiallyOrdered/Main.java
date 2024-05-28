package setPartiallyOrdered;

public class Main {
    public static void main(String[] args) {
        Set s = new Set();

        // не двумерный массив !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        boolean ifInitialized = s.init(new int [][] {
                {1,2},
                {2,4},
                {4,6},
                {2,10},
                {4,8},
                {6,3},
                {1,3},
                {3,5},
                {5,8},
                {7,5},
                {7,9},
                {9,4},
                {9,10},
        });

        s.print();

        s.sort();
        System.out.println();
        s.print();
    }
}
