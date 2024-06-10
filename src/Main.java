import dictionaryClosedHashing.dictionary;
import setIntArray.Set;
import setManyToMany.ManyToMany;

public class Main {
    public static void main(String[] args) {
//        arr();
//        list();

//        closed();
//        open();

        partiallyOrdered();

//        manyToMany();
    }

    public static void arr() {
        Set stest = new Set(-1, 1);
        stest.insert(-1);
        stest.insert(1);
        stest.print();
        System.out.println();

        Set s11 = new Set(-300, 200);
        s11.insert(-210);
        Set s22 = new Set(-15, 0);
        s22.insert(0);

        s11.print();
        System.out.println();

        s22.print();
        System.out.println();

        System.out.println("s33 = s11.merge(s22)");
        Set s33 = s11.merge(s22);
        s33.print();
        System.out.println();

        Set s44 = s33.difference(s11);
        System.out.println("s44 = s33.difference(s11)");
        s44.print();
        System.out.println(s44.equal(s22));
        System.out.println();


        Set s1 = new Set(100, 300);
        s1.insert(105);
        s1.insert(106);
        s1.insert(107);
        s1.insert(109);
        System.out.println("s1");
        s1.print();
        System.out.println();

        Set s2 = new Set(50, 500);
        s2.insert(106);

        Set s4 = new Set(90, 1000);
        s4.insert(101);
        System.out.println("s4.equal(s2)");
        System.out.println(s4.equal(s2));
        System.out.println();

        Set s3 = s1.difference(s2);
        System.out.println("s1.difference(s2)");
        s3.print();
        System.out.println();
    }

    public static void list() {
        setCircularList.Set s = new setCircularList.Set();
        setCircularList.Set s1 = new setCircularList.Set();

        // 👍👍👍👍👍
        s1.insert(-250);
        s1.insert(-200);
        s1.insert(2);
        s1.insert(22);
        s1.insert(-1);
        s1.insert(-123);
        s1.insert(124);
        s1.print();

        s.insert(2);
        s.insert(-250);
        s.insert(-1);
        s.insert(0);
        s.insert(-123);
        s.insert(-124);
        s.insert(124);
        s.insert(455);
        s.insert(123213);
        s.print();
        System.out.println();

        s.delete(-124);
        s.print();
        System.out.println();

        System.out.println("s.intersection(s1);");
        setCircularList.Set i = s.intersection(s1);
        i.print();
        System.out.println();

        System.out.println("s.union(s1);");
        setCircularList.Set u = s.union(s1);
        u.print();
        System.out.println();
    }

    public static void closed() {
        System.out.println("CLOSE");
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
    public static void open() {
        System.out.println("OPEN");
        dictionaryOpenHashing.dictionary d = new dictionaryOpenHashing.dictionary(50);
        dictionaryOpenHashing.dictionary d2 = new dictionaryOpenHashing.dictionary(50);

        d.insert("matvey");
        d.insert("sasha");
        d.print();

        d2.insert("matvey");
        d2.insert("sasha");
        d2.print();

        d.delete("matvey");
        d.delete("sasha");


        d.insert("matvey");
        d.insert("sasha");

        d2.delete("matvey");
        d2.delete("sasha");

        d.print();
        d2.print();
        System.out.println();
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

