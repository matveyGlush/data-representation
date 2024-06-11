import dictionaryClosedHashing.dictionary;
import setIntArray.Set;
import setManyToMany.ManyToMany;

public class Main {
    public static void main(String[] args) {
        arr();
        list();

//        closed();
//        opened();

//        partiallyOrdered();

//        manyToMany();
    }

    public static void arr() {
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

    public static void list() {
        setCircularList.Set set = new setCircularList.Set(0, 10);
        set.insert(1);
        set.insert(4);
        set.insert(7);

        setCircularList.Set set1 = new setCircularList.Set(-10, 20);
        set1.insert(2);
        set1.insert(6);
        set1.insert(7);

        setCircularList.Set set2 = new setCircularList.Set(10, 100);
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
        setCircularList.Set S = set.find(set1, 7);
        if (S == set)
            System.out.println("in A");
        else if (S == set1)
            System.out.println("in B");
        else
            System.out.println("No such element");

        System.out.println();
    }

    public static void closed() {
        System.out.println("CLOSED");
        dictionary d = new dictionary(50);
        dictionary d2 = new dictionary(50);

        d.insert("matvey");
        d.insert("sasha");
        d.print();

//        d2.insert("matvey");
//        d2.insert("sasha");
//        d2.print();

        d.delete("matvey");
        d.delete("sasha");

        d.insert("matvey");
        d.insert("sasha");

//        d2.delete("matvey");
//        d2.delete("sasha");

        d.print();
//        d2.print();
        System.out.println();
    }
    public static void opened() {
        System.out.println("OPENED");
        dictionaryOpenHashing.dictionary d = new dictionaryOpenHashing.dictionary(50);
        dictionaryOpenHashing.dictionary d2 = new dictionaryOpenHashing.dictionary(50);

        d.insert("matvey");
        d.insert("sasha");
        d.print();

//        d2.insert("matvey");
//        d2.insert("sasha");
//        d2.print();

        d.delete("matvey");
        d.delete("sasha");

        d.insert("matvey");
        d.insert("sasha");

//        d2.delete("matvey");
//        d2.delete("sasha");

        d.print();
//        d2.print();
    }

    public static void partiallyOrdered() {
        setPartiallyOrdered.Set s = new setPartiallyOrdered.Set();

        boolean ifInitialized = s.init(new setPartiallyOrdered.Pair[] { // классы и вывод
                new setPartiallyOrdered.Pair(1, 2),
                new setPartiallyOrdered.Pair(2, 4),
                new setPartiallyOrdered.Pair(4, 6),
                new setPartiallyOrdered.Pair(2, 10),
                new setPartiallyOrdered.Pair(4, 8),
                new setPartiallyOrdered.Pair(6, 3),
                new setPartiallyOrdered.Pair(1, 3),
                new setPartiallyOrdered.Pair(3, 5),
                new setPartiallyOrdered.Pair(5, 8),
                new setPartiallyOrdered.Pair(7, 5),
                new setPartiallyOrdered.Pair(7, 9),
                new setPartiallyOrdered.Pair(9, 4),
                new setPartiallyOrdered.Pair(9, 10),


//                new setPartiallyOrdered.Set.Pair(6, 123),
//                new setPartiallyOrdered.Set.Pair(1, 2),
//                new setPartiallyOrdered.Set.Pair(4, 6),
//                new setPartiallyOrdered.Set.Pair(2, 10),
//                new setPartiallyOrdered.Set.Pair(4, 8),
//                new setPartiallyOrdered.Set.Pair(6, 3),
//                new setPartiallyOrdered.Set.Pair(1, 3),
//                new setPartiallyOrdered.Set.Pair(3, 5),
//                new setPartiallyOrdered.Set.Pair(5, 8),
//                new setPartiallyOrdered.Set.Pair(7, 5),
//                new setPartiallyOrdered.Set.Pair(7, 9),
//                new setPartiallyOrdered.Set.Pair(9, 4),
//                new setPartiallyOrdered.Set.Pair(9, 10),
//                new setPartiallyOrdered.Set.Pair(5, 10),
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

    public static void manyToMany() {
        ManyToMany m = new ManyToMany();
        m.putStudentOnCourse("Bob",2);
        m.putStudentOnCourse("Bob",1);
        m.putStudentOnCourse("Alice",1);
        m.putStudentOnCourse("John",1);
        m.putStudentOnCourse("John",2);
//        m.removeStudentFromCourse(1, "Alice");
//        m.removeStudentFromCourse(1,"Bob");
//        m.removeStudentFromCourse(2,"Bob");

        System.out.print("Bob's list of courses: ");
        m.listOfCourses("Bob");
        System.out.println();
        System.out.print("Alice's list of courses: ");
        m.listOfCourses("Alice");
        System.out.println();
        System.out.print("John's list of courses: ");
        m.listOfCourses("John");
        System.out.println();
        System.out.print("All students from 1 course: ");
        m.listOfStudents(1);
        System.out.println();
        System.out.print("All students from 2 course: ");
        m.listOfStudents(2);
        System.out.println();


        m.removeStudentEverywhere("John");
        m.listOfStudents(1);
        System.out.println();

//        m.removeCourseEverywhere(1);
//        System.out.print("All students from 1 course: ");
//        m.listOfStudents(1);
//        System.out.println();
//
//        System.out.println();
//        System.out.print("Bob's list of courses: ");
//        m.listOfCourses("Bob");
//        System.out.print("Alice's list of courses: ");
//        m.listOfCourses("Alice");
//        System.out.println();
//        System.out.print("John's list of courses: ");
//        m.listOfCourses("John");
    }
}

