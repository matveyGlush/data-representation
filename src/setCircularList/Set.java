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

    public Set(Set a){
        // создаём хвост
        copy(a);
    }


    // обычный конструктор
    public Set(int a, int b){
        tail = null;
    }

    
    // обычный конструктор
    public Set(){
        tail = null;
    }

    public void assign(Set a){
        // создаём хвост
        copy(a);
    }

    private void copy (Set a){
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
            Node temp = searchPrev(tail.number);
            
            temp.next = new Node(tail.number, tail);

            tail.number = a;
            return;
        }

        //Если элемент меньще головы
        if (a < tail.next.number){
            Node temp = new Node(a, tail.next);

            tail.next = temp;
            return;
        }

        // общий случай
        Node temp = searchPrev(a);

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
        если нужно удалить элемент в хвосте и в множестве НЕ один элемент - ссылки перекинуть
        если нужно удалить элемент в хвосте и в множестве один элемент - tail = null
        */
        
        if (tail == null) 
            return;
        if (a == tail.number) {
            if (tail == tail.next){
                tail = null;
                return;
            }
            Node temp = searchPrev(a);
            temp.next = tail.next;
            tail = temp;
        }
        
        Node temp = searchPrev(a);
        
        if (temp != null) {
            temp.next = temp.next.next;
        }
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
        return merge_Set(a);
    }

    public Set merge(Set a) {
        return merge_Set(a);
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
        
        
        Node prev = resultSet.searchPrev(resultSet.tail.number);
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
            Node prev = resultSet.searchPrev(resultSet.tail.number);
            prev.next = resultSet.tail.next;
            resultSet.tail = prev;
        }

        return resultSet;
    }


    private Node searchPrev(int n){
        
        //создаем два указателя - один запаздывающий
        Node temp = tail.next;
        Node temp2 = temp.next;

        if (temp.number >= n)
            return tail;
        
        //в цикле сравниваем элементы
        while (temp2 != tail){
            if (temp2.number >= n)
                return temp;
            
            temp = temp2;
            temp2 = temp2.next;
        }
            
        //если в конце цикла не запаздывающий равен нашей искомой позиции - возвращаем запаздывающий
        if (temp2.number >= n){
            return temp;
        }
        return null;
    }

    private boolean member(int num) {
        Node N = searchPrev(num).next;
        return searchPrev(num).next.number == num;
    }

    public Set find(Set a, int n) {
        if (this.member(n))
            return this;
        if (a.member(n))
            return a;
        return null;
    }

    public boolean equal(Set S) {
        if (S == this)
            return true;
        if (S.tail.number != tail.number)
            return false;
        Node head1 = tail.next;
        Node head2 = S.tail.next;

        while (head1 != tail && head2 != S.tail) {
            if (head1.number != head2.number)
                return false;
            head1 = head1.next;
            head2 = head2.next;
        }
        if (head1 != tail || head2 != S.tail)
            return false;
        return true;
    }

    private Set merge_Set(Set a) {
        if (this == a)
            return new Set(a);

        Set resultSet = new Set();
        Node head1 = this.tail.next;
        Node head2 = a.tail.next;

        resultSet.tail = new Node(Math.max(this.tail.number, a.tail.number), null);
        resultSet.tail.next = resultSet.tail;
        Node temp = resultSet.tail;

        while (head1 != this.tail && head2 != a.tail) {
            if (head1.number < head2.number) {
                temp.next = new Node(head1.number, resultSet.tail);
                head1 = head1.next;
            } else if (head1.number > head2.number) {
                temp.next = new Node(head2.number, resultSet.tail);
                head2 = head2.next;
            } else {
                temp.next = new Node(head1.number, resultSet.tail);
                head1 = head1.next;
                head2 = head2.next;
            }
            temp = temp.next;
        }

        while (head1 != this.tail) {
            temp.next = new Node(head1.number, resultSet.tail);
            head1 = head1.next;
            temp = temp.next;
        }

        while (head2 != a.tail) {
            temp.next = new Node(head2.number, resultSet.tail);
            head2 = head2.next;
            temp = temp.next;
        }

        if (head1.number != resultSet.tail.number) {
            temp.next = new Node(head1.number, resultSet.tail);
            temp = temp.next;
        }
        if (head2.number != resultSet.tail.number) {
            temp.next = new Node(head2.number, resultSet.tail);
        }

        return resultSet;
    }

    public void makeNull() {
        tail = null;
    }
}
