package setCircularList;

public class Set {
    // класс нода
    private static class Node{
        int number;
        Node next;

        //конструктор
        public Node(int a, Node n){
            number = a;
            next = n;
        }
    }

    private Node tail; // хвост

    // обычный конструктор
    public Set(){
        tail = null;
    }


    //копирующий конструктор
    public Set(Set a){
        // создаём хвост
        tail = new Node(a.tail.number, a.tail.next);


        Node tempA = a.tail.next;
        Node tempSelf = tail;

        // идём двумя указателями
        while (tempA != a.tail){
            tempSelf.next = new Node(tempA.number, tempA.next);
            tempA = tempA.next;
            tempSelf = tempSelf.next;
        }
        tempSelf.next = tail;
    }

    public void insert(int a){
        // елси список пустой вставляем в хвост и закольцовываем
        if (tail == null){
            tail = new Node(a, null);
            tail.next = tail;
            return;
        }

        // если 1 элемент
        if (tail.next == tail){
            if (a == tail.number)
                return;
            if (a > tail.number){
                tail.next = new Node(tail.number, tail);
                tail.number = a;
                return;
            }

            tail.next = new Node(a, tail);
            return;
        }


        //Если элемент больше хвоста
        if (a > tail.number){
            Node temp = searchPrev(tail, tail.number);

            temp.next = new Node(tail.number, tail);

            tail.number = a;
            return;
        }

        Node temp = searchPlaceForInsert(tail.next, a);

        if (temp == null) {
            tail.next = new Node(a, tail.next);
            return;
        }

        if (temp.next.number == a){
            return;
        }

        Node temporary = temp.next;
        temp.next = new Node(a, temporary);
    }

    public void delete(int a){
        /*
        если пустое множество - ретерн
        если нужно удалить элемент в хвосте и в множестве один элемент - tail = null
        в общем случае ищем предыдущий и перекижываем ссылки
         */
        if (tail == null) return;

        if (tail == tail.next){
            tail = null;
            return;
        }

        Set.Node temp = searchPrev(tail, a);

        if (temp != null) {
            tail = temp;
            tail.next = tail.next.next;
        }
    }

    public void assign(Set a){
        // если хвост а == null, то tail = null -> return

        // хвосту прислоить новое значение хвоста а
        // в темп сохранить значение головы а
        // циклом while переприсвоить из темп в темпА
        if (a.tail == null) {
            tail = null;
            return;
        }

        tail = new Set.Node(a.tail.number, null);
        Set.Node tempA = tail;

        Set.Node temp = a.tail.next;

        while (temp != a.tail){
            tempA.next = new Set.Node(temp.number, null);
            tempA = tempA.next;
            temp = temp.next;
        }

        tempA.next = tail;
    }

    //
    public int min(){
        int min = Integer.MAX_VALUE;
        if (tail == null) throw new myException("set is empty");

        Node temp = tail.next;
        while (temp != tail){
            if (temp.number < min){
                min = temp.number;
            }
            temp = temp.next;
        }

        return Math.min(min, tail.number);
    }

    public int max(){
        int max = Integer.MIN_VALUE;
        if (tail == null) throw new myException("set is empty");

        Node temp = tail.next;
        while (temp != tail){
            if (temp.number > max){
                max = temp.number;
            }
            temp = temp.next;
        }

        return Math.max(max, tail.number);
    }

    public void print(){
        if (tail == null) return;
        Node q = tail.next;
        while (q != tail && q != null){
            System.out.print(q.number + " ");
            q = q.next;
        }

        System.out.print(tail.number + " ");
        System.out.println();
    }

    public Set union(Set a) {
        if (this == a)
            return null;
        Set set1 = this;
        Node head1 = set1.tail.next;
        Node head2 = a.tail.next;
        Set resultSet = new Set();
        resultSet.tail = new Node(Math.max(set1.tail.number, a.tail.number), null);
        resultSet.tail.next = resultSet.tail;
        Node temp = resultSet.tail;


        while (head1 != set1.tail && head2 != a.tail) {
            if (head1.number < head2.number) {
                temp.next = new Node(head1.number, resultSet.tail);
                head1 = head1.next;
            } else {
                temp.next = new Node(head2.number, resultSet.tail);
                if (head1.number == head2.number) head1 = head1.next;
                head2 = head2.next;
            }
            temp = temp.next;
        }

        if (head1 == set1.tail){
            while (head2 != a.tail){
                if (head1.number < head2.number) {
                    temp.next = new Node(head1.number, resultSet.tail);
                    head1 = head1.next;
                    temp = temp.next;
                    break;
                } else {
                    temp.next = new Node(head2.number, resultSet.tail);
                    if (head1.number == head2.number) head1 = head1.next;
                    head2 = head2.next;
                    temp = temp.next;
                }
            }
            while (head2 != a.tail){
                temp.next = new Node(head2.number, resultSet.tail);
                head2 = head2.next;
                temp = temp.next;
            }
        }
        else {
            // вставляем голову последнюю
            while (head1 != set1.tail){
                if (head1.number < head2.number) {
                    temp.next = new Node(head1.number, resultSet.tail);
                    head1 = head1.next;
                    temp = temp.next;
                } else {
                    temp.next = new Node(head2.number, resultSet.tail);
                    if (head1.number == head2.number) head1 = head1.next;
                    head2 = head2.next;
                    temp = temp.next;
                    break;
                }
            }
            while (head1 != set1.tail) {
                temp.next = new Node(head1.number, resultSet.tail);
                head1 = head1.next;
                temp = temp.next;
            }

        }
        if (head1 == set1.tail && head2 == a.tail) {
            temp.next = new Node(Math.min(head2.number, head1.number), resultSet.tail);
        }

        //.tail = new Node(Math.max(head2.number, head1.number), resultSet.tail.next);

        return resultSet;
    }

    public Set intersection(Set a){
        if (a == null || tail == null)
            throw new myException("set is empty");
        if (a.tail.next.number > tail.number || tail.next.number > a.tail.number)
            return null;
        if (a == this)
            return this;


        Node head1 = tail.next;
        Node head2 = a.tail.next;
        Set resultSet = new Set();
        resultSet.tail =  new Node(Math.min(tail.number, a.tail.number), null);
        resultSet.tail.next = resultSet.tail;
        Node temp = resultSet.tail;

        while (head1 != tail && head2 != a.tail) {
            if (head1.number < head2.number) {
                head1 = head1.next;
            } else {
                if (head1.number == head2.number) {
                    temp.next = new Node(head1.number, resultSet.tail);
                    temp = temp.next;
                    head1 = head1.next;
                }
                head2 = head2.next;
            }
        }

        //
        if (head2 == a.tail) {
            while (head1 != tail && head1.number < a.tail.number){
                head1 = head1.next;
            }

            if (head1.number == a.tail.number ) {
                return resultSet;
            }
        }
        if (head1 == tail) {
            while (head2 != a.tail && head2.number < tail.number){
                head2 = head2.next;
            }

            if (head2.number == tail.number ) {
                return resultSet;
            }
        }


        Node prev = resultSet.searchPrev(resultSet.tail, resultSet.tail.number);
        prev.next = resultSet.tail.next;
        resultSet.tail = prev;


        return resultSet;
    }

    public Set difference(Set a) {
        if (a == null || tail == null)
            throw new myException("set is empty");
        if (a == this)
            return new Set();;
        if (a.tail.next.number > tail.number || tail.next.number > a.tail.number)
            return this;

        Node head1 = tail.next;
        Node head2 = a.tail.next;
        Set resultSet = new Set();
        resultSet.tail =  new Node(tail.number, null);
        resultSet.tail.next = resultSet.tail;
        Node temp = resultSet.tail;

        while (head1 != tail && head2 != a.tail) {
            if (head1.number < head2.number) {
                temp.next = new Node(head1.number, resultSet.tail);
                head1 = head1.next;
                temp = temp.next;
            } else {
                if (head1.number == head2.number)
                    head1 = head1.next;
                head2 = head2.next;
            }
        }

        //
        if (head1 != tail) {
            while (head1 != tail && head1.number < a.tail.number){
                temp.next = new Node(head1.number, resultSet.tail);
                head1 = head1.next;
                temp = temp.next;
            }

            if (head1 != tail ) {
                if (head1.number == a.tail.number)
                    head1 = head1.next;
                while (head1 != tail){
                    temp.next = new Node(head1.number, resultSet.tail);
                    head1 = head1.next;
                    temp = temp.next;
                }
                return resultSet;
            }
        }

        if (head1.number == a.tail.number) {
            Node prev = resultSet.searchPrev(resultSet.tail, resultSet.tail.number);
            prev.next = resultSet.tail.next;
            resultSet.tail = prev;
        }

        return resultSet;
    }


    private Node searchPrev(Node pos, int n){
        //создаем два указателя - один запаздывающий
        //если не запаздывающий равен искомому элементу - возрващаем запаздывающий
        //в цикле сравниваем элементы
        //если в конце цикла не запаздывающий равен нашей искомой позиции - возвращаем запаздывающий
        Node temp = pos;
        Node temp2 = pos.next;
        if (temp2.number == n)
            return temp;

        while (temp2 != pos){
            if (temp2.number == n)
                return temp;

            temp = temp2;
            temp2 = temp2.next;
        }

        if (temp2.number == n){
            return temp;
        }
        return null;
    }

    private Node searchPlaceForInsert(Node pos, int n) {
        /*
        если искомая позиция уже есть в нашей позиции - возвращаем ее
        если нет - проходим по циклу с запаздывающим указателем
         */
        if (pos.number == n) return pos;
        Node temp = pos;
        Node temp2 = pos.next;
        while (temp2 != pos) {
            if (temp2.number >= n)
                return temp;

            temp = temp2;
            temp2 = temp2.next;
        }
        return null;
    }

    private boolean member(Set set, int num) {
        Node temp = set.tail.next;
        while (temp != tail) {
            if (temp.number == num) {
                return true;
            }
            temp = temp.next;
        }
        return temp.number == num;
    }

    public static Set find(Set[] sets, int x) {
        for (Set set : sets) {
            if (set.member(set, x)) {
                return set;
            }
        }
        return null; // Если элемент не найден ни в одном множестве
    }
}
