package setManyToMany;

public class Main {
    public static void main(String[] args) {
        ManyToMany m = new ManyToMany();
        m.putStudentOnCourse("Bob", 2);
        m.putStudentOnCourse("Bob", 1);
        m.putStudentOnCourse("Alice", 1);
        m.putStudentOnCourse("John", 1);
        m.putStudentOnCourse("John", 2);
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
    }
}