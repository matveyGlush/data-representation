import dictionaryClosedHashing.dictionary;
import setIntArray.Set;

public class Main {
    public static void main(String[] args) {
        arr();
//        list();

//        closed();
//        open();
    }

    public static void arr() {
        Set s11 = new Set(-1, 1);
        s11.insert(-1);
        s11.insert(1);
        s11.print();
//        Set s11 = new Set(-300, 200);
//        s11.insert(-210);
//        Set s22 = new Set(-15, 0);
//        s22.insert(0);
//
//        s11.print();
//        System.out.println();
//
//        s22.print();
//        System.out.println();
//
//        System.out.println("s33 = s11.merge(s22)");
//        Set s33 = s11.merge(s22);
//        s33.print();
//        System.out.println();
//
//        Set s44 = s33.difference(s11);
//        System.out.println("s44 = s33.difference(s11)");
//        s44.print();
//        System.out.println(s44.equal(s22));
//        System.out.println();
//
//
//        Set s1 = new Set(100, 300);
//        s1.insert(105);
//        s1.insert(106);
//        s1.insert(107);
//        s1.insert(109);
//        System.out.println("s1");
//        s1.print();
//        System.out.println();
//
//        Set s2 = new Set(50, 500);
//        s2.insert(106);
//
//        Set s4 = new Set(90, 1000);
//        s4.insert(101);
//        System.out.println("s4.equal(s2)");
//        System.out.println(s4.equal(s2));
//        System.out.println();
//
//        Set s3 = s1.difference(s2);
//        System.out.println("s1.difference(s2)");
//        s3.print();
//        System.out.println();
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
        s.insert(23);
        s.insert(123213);
        s.print();
        System.out.println();

        System.out.println("s.intersection(s1);");
        setCircularList.Set i = s.intersection(s1);
        i.print();
        System.out.println();

//        System.out.println("s.union(s1);");
//        setCircularList.Set u = s.union(s1);
//        u.print();
//        System.out.println();
    }

    public static void closed() {
        System.out.println("CLOSE");
        dictionary d = new dictionary(50);
        d.insert("matvey");
        d.insert("ymatve");
        d.print();

        d.delete("ymatve");
        d.delete("matvey");
        d.print();
        System.out.println();

        d.insert("ymatve");
        d.insert("matvey");
        d.print();
        System.out.println();
    }
    public static void open() {
        System.out.println("OPEN");
        dictionaryOpenHashing.dictionary d = new dictionaryOpenHashing.dictionary(50);
        d.insert("matvey");
        d.insert("ymatve");
        d.insert("ymatve111");

        d.print();
        System.out.println();

        d.delete("matvey");
        d.delete("ymatve");
        d.insert("matvey");
        d.print();
        System.out.println();
    }
}

