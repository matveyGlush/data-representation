package dictionaryClosedHashing;

public class Main {
    public static void main(String[] args) {
        System.out.println("CLOSED");
        dictionary d = new dictionary(50);
//        dictionary d2 = new dictionary(50);

        d.insert("matvey");
        d.insert("yatvem");
        d.print();

//        d2.insert("matvey");
//        d2.insert("sasha");
//        d2.print();

        d.delete("matvey");
        d.delete("yatvem");

        d.insert("yatvem");
        d.insert("matvey");

//        d2.delete("matvey");
//        d2.delete("sasha");

        d.print();
//        d2.print();
        System.out.println();
    }
}
