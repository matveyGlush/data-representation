package dictionaryClosedHashing;

public class dictionary {

    private static class element {
        char[] name;

        // конструктор по имени
        public element(char[] new_name) {
            name = new char[new_name.length];
            for (int i = 0; i < new_name.length; i++){
                name[i] = new_name[i];
            }
        }

        // проверка на удалённый элемент
        public boolean isDeleted(){
            return name[0] == '\0';
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

        //печать имени
        private void print(){
            if (name == null)
                return;
            if (name[0] == '\0')
                return;

            for (int i = 0; i < name.length; i++){
                if (name[i] != '\0')
                    System.out.print(name[i]);
                else {
                    System.out.println();
                    return;
                }
            }
        }
    }

    // данные класса
    private final element[] array;
    private final static int SIZE = 15;

    // конструктор
    public dictionary(int a){
        array = new element[a];
    }

    private int hashCode(char[] name) {
        int sum = 0;

        // находим сумму символов
        for (int i = 0; i < name.length; i++){
            sum += name[i];
        }
        return sum;
    }

    // считаем хэш
    private int hashFunc(char[] name) {
        // возвращаем остаток от деления на длину списка
        return hashCode(name) % array.length;
    }

    // считаем хэш
    private int hashFunc(int hash, int q) {
        // возвращаем остаток от деления на длину списка
        return (hash + q) % array.length;
    }

    // метод вставки
    public void insert(char[] name) {
        // считаем хэш
        int hash = hashFunc(name);
        int place = hash;
        int start = place;

        int counter = 0;
        int deleted= -1;
        int empty= -1;

        // проверяем первый элемент, самый вероятный
        if (array[place] == null) {
            array[place] = new element(name);
            return;
        }
        else if (array[place].equals(name))
            return;

        place = hashFunc(hash, ++counter);

        // идём до себя или до Null
        while (place != start ){
            // проверяем на NULL
            if (array[place] == null) {
                empty = place;
                break;
            }

            // проверяем на совпадение
            if (array[place].equals(name))
                return;

            // если нашли первый удалённый, то запоминаем
            if (deleted == -1 && array[place].isDeleted()){
                deleted = place;
            }

            place = hashFunc(hash, ++counter);
        }

        // вставляем в первый удалённый
        if (deleted != -1){
            array[deleted] = new element(name);
        } else array[empty] = new element(name);
    }

    public void insert(String name) {
        insert(convertStringToCharArray(name));
    }

    // метод поиска
    private int search(char []name) {
        int hash = hashFunc(name);
        int place = hash;
        int start = place;
        int counter = 0;

        // проверяем первый элемент, самый вероятный
        if (array[place] == null)
            return -1;
        else if (array[place].equals(name))
            return place;

        place = hashFunc(hash, ++counter);

        // проверяем остальное
        while (array[place] != null || place != start){
            if (array[place].equals(name)) {
                return place;
            }
            place = hashFunc(hash, ++counter);
        }

        return -1;
    }

    // метод удаления элемента
    public void delete(char[] name) {
        int temp = search(name);
        if (temp != -1){
            array[temp].name[0] = '\0';
        }
    }

    public void delete(String name) {
        delete(convertStringToCharArray(name));
    }

    // метод проверки на членство
    public boolean member(char[] name) {
        return search(name) != -1;
    }

    // зануление множества
    public void makeNull() {
        for (int i = 0; i < array.length; i++){
            array[i] = null;
        }
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

    // вывод
    public void print() {
        for (int i = 0; i < array.length; i++){
            if (array[i] != null) {
                array[i].print();
            }
        }
    }

}
