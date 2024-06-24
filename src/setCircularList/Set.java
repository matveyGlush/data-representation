package setCircularList;

public class Set {
    // Внутренний класс Node, представляющий узел в циклическом списке
    private static class Node {
        int number; // значение узла
        Node next;  // ссылка на следующий узел

        // Конструктор узла
        public Node(int a, Node n) {
            number = a;
            next = n;
        }
    }

    private Node tail; // Ссылка на последний узел в циклическом списке

    // Конструктор, копирующий другой Set
    public Set(Set a) {
        copy(a);
    }

    // Конструктор с двумя параметрами (не используется)
    public Set(int a, int b) {
        tail = null;
    }

    // Конструктор без параметров, создающий пустой Set
    public Set() {
        tail = null;
    }

    // Метод для присвоения значений другого Set текущему Set
    public void assign(Set a) {
        if (this != a) // Проверка на самоприсвоение
            copy(a);
    }

    // Вспомогательный метод для копирования Set
    private void copy(Set a) {
        tail = new Node(a.tail.number, a.tail.next); // Создание нового хвоста

        Node tempA = a.tail.next; // Временная переменная для обхода копируемого Set
        Node tempSelf = tail; // Временная переменная для обхода текущего Set

        // Цикл копирования всех узлов, кроме хвоста
        while (tempA != a.tail) {
            tempSelf.next = new Node(tempA.number, tempA.next);
            tempA = tempA.next;
            tempSelf = tempSelf.next;
        }
        tempSelf.next = tail; // Закольцовка списка
    }

    // Метод для вставки элемента в Set
    public void insert(int a) {
        if (tail == null) {
            tail = new Node(a, null); // Если список пуст, вставляем первый элемент
            tail.next = tail; // Закольцовываем
            return;
        }

        if (tail.next == tail) { // Если в списке один элемент
            if (a == tail.number)
                return; // Элемент уже существует, ничего не делаем
            if (a > tail.number) {
                tail.next = new Node(tail.number, tail); // Вставляем в конец
                tail.number = a; // Обновляем хвост
                return;
            }
            tail.next = new Node(a, tail); // Вставляем в начало
            return;
        }

        if (a > tail.number) { // Вставка в хвост
            Node temp = searchPrev(tail.number);
            temp.next = new Node(tail.number, tail);
            tail.number = a;
            return;
        }

        if (a < tail.next.number) { // Вставка в голову
            Node temp = new Node(a, tail.next);
            tail.next = temp;
            return;
        }

        // Общий случай вставки
        Node temp = searchPrev(a);
        if (temp == null) {
            tail.next = new Node(a, tail.next);
            return;
        }
        if (temp.next.number == a) {
            return; // Элемент уже существует, ничего не делаем
        }
        Node temporary = temp.next;
        temp.next = new Node(a, temporary);
    }

    // Метод для удаления элемента из Set
    public void delete(int a) {
        if (tail == null)
            return; // Пустой список, ничего не делаем
        if (a == tail.number) { // Удаление хвоста
            if (tail == tail.next) {
                tail = null; // Один элемент в списке
                return;
            }
            Node temp = searchPrev(a);
            temp.next = tail.next; // Перекидываем ссылки
            tail = temp; // Обновляем хвост
        }
        Node temp = searchPrev(a); // Поиск предыдущего элемента
        if (temp != null) {
            temp.next = temp.next.next; // Перекидываем ссылки
        }
    }

    // Метод для получения минимального элемента
    public int min() {
        return tail.next.number;
    }

    // Метод для получения максимального элемента
    public int max() {
        return tail.number;
    }

    // Метод для печати элементов Set
    public void print() {
        if (tail == null) return; // Пустой список, ничего не делаем
        Node q = tail.next;
        while (q != tail) {
            System.out.print(q.number + " ");
            q = q.next;
        }
        System.out.print(tail.number + " ");
        System.out.println();
    }

    // Метод для объединения двух Set
    public Set union(Set a) {
        return merge_Set(a);
    }

    // Метод для слияния двух Set
    public Set merge(Set a) {
        return merge_Set(a);
    }

    // Метод для пересечения двух Set
// Метод для пересечения двух множеств
    public Set intersection(Set a) {
        // Проверка на пустые множества
        if (a == null || tail == null)
            throw new myException("set is empty");

        // Если множества не пересекаются, возвращаем null
        if (a.tail.next.number > tail.number || tail.next.number > a.tail.number)
            return null;

        // Если множества идентичны, возвращаем текущее множество
        if (a == this)
            return this;

        // Инициализация переменных для обхода списков
        Node head1 = tail.next;
        Node head2 = a.tail.next;

        // Создаем новый результат множества
        Set resultSet = new Set();
        resultSet.tail = new Node(Math.min(tail.number, a.tail.number), null);
        resultSet.tail.next = resultSet.tail;
        Node temp = resultSet.tail;

        // Поиск общих элементов
        while (head1 != tail && head2 != a.tail) {
            if (head1.number < head2.number) {
                head1 = head1.next;
            } else {
                if (head1.number == head2.number) {
                    // Добавляем общий элемент в результат множества
                    temp.next = new Node(head1.number, resultSet.tail);
                    temp = temp.next;
                    head1 = head1.next;
                }
                head2 = head2.next;
            }
        }

        // Проверка хвостов на общие элементы
        if (head2 == a.tail) {
            while (head1 != tail && head1.number < a.tail.number) {
                head1 = head1.next;
            }
            if (head1.number == a.tail.number) {
                return resultSet;
            }
        }
        if (head1 == tail) {
            while (head2 != a.tail && head2.number < tail.number) {
                head2 = head2.next;
            }
            if (head2.number == tail.number) {
                return resultSet;
            }
        }

        // Обновляем хвост результирующего множества
        Node prev = resultSet.searchPrev(resultSet.tail.number);
        prev.next = resultSet.tail.next;
        resultSet.tail = prev;

        return resultSet;
    }

    // Метод для нахождения разницы между двумя множествами
    public Set difference(Set a) {
        // Проверка на пустые множества
        if (a == null || tail == null)
            throw new myException("set is empty");

        // Если множества идентичны, возвращаем пустое множество
        if (a == this)
            return new Set();

        // Если множества не пересекаются, возвращаем текущее множество
        if (a.tail.next.number > tail.number || tail.next.number > a.tail.number)
            return this;

        // Инициализация переменных для обхода списков
        Node head1 = tail.next;
        Node head2 = a.tail.next;

        // Создаем новый результат множества
        Set resultSet = new Set();
        resultSet.tail = new Node(tail.number, null);
        resultSet.tail.next = resultSet.tail;
        Node temp = resultSet.tail;

        // Исключение общих элементов
        while (head1 != tail && head2 != a.tail) {
            if (head1.number < head2.number) {
                // Добавляем элемент, который есть только в текущем множестве
                temp.next = new Node(head1.number, resultSet.tail);
                head1 = head1.next;
                temp = temp.next;
            } else {
                if (head1.number == head2.number)
                    head1 = head1.next; // Пропускаем общие элементы
                head2 = head2.next;
            }
        }

        // Обрабатываем оставшиеся элементы текущего множества
        if (head1 != tail) {
            while (head1 != tail && head1.number < a.tail.number) {
                temp.next = new Node(head1.number, resultSet.tail);
                head1 = head1.next;
                temp = temp.next;
            }
            if (head1 != tail) {
                if (head1.number == a.tail.number)
                    head1 = head1.next;
                while (head1 != tail) {
                    temp.next = new Node(head1.number, resultSet.tail);
                    head1 = head1.next;
                    temp = temp.next;
                }
                return resultSet;
            }
        }

        // Если последний элемент равен элементу из множества a, обновляем хвост
        if (head1.number == a.tail.number) {
            Node prev = resultSet.searchPrev(resultSet.tail.number);
            prev.next = resultSet.tail.next;
            resultSet.tail = prev;
        }

        return resultSet;
    }


    // Вспомогательный метод для поиска предыдущего узла
    private Node searchPrev(int n) {
        Node temp = tail.next; // Первый элемент
        Node temp2 = temp.next; // Следующий элемент

        if (temp.number >= n)
            return tail;

        while (temp2 != tail) {
            if (temp2.number >= n)
                return temp;
            temp = temp2;
            temp2 = temp2.next;
        }

        if (temp2.number >= n) {
            return temp;
        }
        return null;
    }

    // Вспомогательный метод для проверки наличия элемента в Set
    private boolean member(int num) {
        Node N = searchPrev(num).next;
        return searchPrev(num).next.number == num;
    }

    // Метод для поиска элемента в двух множествах
    public Set find(Set a, int n) {
        if (this.member(n))
            return this;
        if (a.member(n))
            return a;
        return null;
    }

    // Метод для проверки равенства двух Set
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

    // Вспомогательный метод для объединения двух Set
    private Set merge_Set(Set a) {
        if (this == a)
            return new Set(a);

        Set resultSet = new Set();
        Node head1 = this.tail.next;
        Node head2 = a.tail.next;

        resultSet.tail = new Node(Math.max(this.tail.number, a.tail.number), null);
        resultSet.tail.next = resultSet.tail;
        Node temp = resultSet.tail;

        // Объединение всех элементов из двух Set
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

    // Метод для очистки Set
    public void makeNull() {
        tail = null;
    }
}
