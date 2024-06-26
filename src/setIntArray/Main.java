package setIntArray;

public class Main {
    public static void main(String[] args) {
        Set set = new Set(0, 10);
        set.insert(1);
        set.insert(4);
        set.insert(7);

        Set set1 = new Set(-10, 20);
        set1.insert(2);
        set1.insert(6);
        set1.insert(7);

        Set set2 = new Set(10, 100);
        set2.insert(10);
        set2.insert(40);
        set2.insert(70);

        System.out.print("union (set, set1): ");
        set.union(set1).print();
        System.out.print("diff (set, set1): ");
        set.difference(set1).print();
        System.out.print("intersection (set, set1): ");
        set.intersection(set1).print();
        System.out.print("merge (set, set2): ");
        if (!set.equal(set2)) {
            set.merge(set2).print();
        }
        System.out.print("min (set): ");
        System.out.println(set.min());
        System.out.print("max (set): ");
        System.out.println(set.max());
        System.out.print("find (7): ");
        Set S = set.find(set1, 7);
        if (S == set)
            System.out.println("in A");
        else if (S == set1)
            System.out.println("in B");
        else
            System.out.println("No such element");

        System.out.println();
    }

}