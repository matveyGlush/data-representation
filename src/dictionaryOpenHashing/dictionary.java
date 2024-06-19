package dictionaryOpenHashing;

public class dictionary {
    private static class element{
        char[] name;
        element next;

        // конструктор
        public element(){
            name = null;
            next = null;
        }

        // конструктор
        public element(char[] n, element nxt){
            next = nxt;
            name = new char[10];
            copy(n);
        }

        // копирующий конструктор
        public element(char[] n){
            name = new char[10];
            copy(n);
        }

        // копирование строк
        public void copy(char[] from){
            for (int i = 0; i < from.length; i++){
                name[i] = from[i];
            }
        }

         // сравнение имён
         private boolean equals(char[] b){
            int i;
            for (i = 0; name[i] != '\0' &&  b[i] != '\0'; i++){
                if (name[i] != b[i])
                    return false;
                
            }
            if (name[i] != b[i])
                return false;
            return true;
        }

        // печать
        public void printName(){
            if (name == null) 
                return;
            for (int i = 0; i < name.length; i++){
                if (name[i] != '\0'){
                    System.out.print(name[i]);
                }
                else {
                    System.out.println();
                    return;
                }
            }
            
        }
    }

    // данные класса
    private final element[] array;
    private final int SIZE = 10;

    // конструктор
    public dictionary(int a){
        array = new element[a / SIZE + 1];
    }

    // метод вставки
    public void insert(char[] name) {
        int place = hashFunc(name);

        // проверяем первый элемент
        if (array[place] == null) {
            array[place] = new element(name);
            return;
        }

        if (array[place].equals(name)) {
            return;
        }

        // проверяем есть ли такой элемент
        if (searchPrev(name, place) == null) {
            array[place] = new element(name, array[place]);
        }
    }

    public void insert(String name) {
        insert(convertStringToCharArray(name));
    }

    // удаление
    public void delete(char[] name) {
        // считаем хэш
        int place = hashFunc(name);

        if (array[place] == null) 
            return;

        // проверяем первый элемент
        if (array[place].equals(name)){
            // если 1 элемент
            if (array[place].next == null){
                array[place] = null;
            }
            // если несколько
            else {
                array[place] = array[place].next;
            }
            return;
        }

        // ищем предидущий и сдвигаем
        element temp = searchPrev(name, place);
        if (temp != null){
            temp.next = temp.next.next;
        }

    }

    public void delete(String name) {
        delete(convertStringToCharArray(name));
    }

    // проверяем наличие
    public boolean member(char[] name) {
        int place = hashFunc(name);

        if (array[place].equals(name))
            return true;

        return (searchPrev(name, place) != null);
    }

    // зануление списка
    public void makeNull(){
        for (int i = 0; i < array.length; i++){
            array[i] = null;
        }
    }

    // печать
    public void print(){
        for (int i = 0; i < array.length; i++){
            element q = array[i];
            while (q != null){
                q.printName();
                q = q.next;
            }
        }
    }

    private int hash(char[] name){
        int sum = 0;
        for (int i = 0; i < name.length; i++){
            sum += name[i];
        }
        return sum;
    }

    // считаем хэш
    private int hashFunc(char[] name){
        return hash(name) % array.length;
    }

    private char[] convertStringToCharArray(String str){
        char[] name = new char[SIZE];
        copyCharArrays(str.toCharArray(), name);
        return name;
    }

    private void copyCharArrays(char[] from, char[] to){
        for (int i = 0; i < from.length; i++){
            to[i] = from[i];
        }
    }

    // поиск предшественника
    private element searchPrev(char[] name, int place){
        element q = array[place];
        element q2 = null;
        while (q != null){
            if (q.equals(name)) {
                return q2;
            }
            q2 = q;
            q = q.next;
        }

        return null;
    }
}