package setIntArray;

public class Set {
    // Внутренний класс для представления позиции в массиве
    public static class position {
        int index; // индекс в массиве
        int pos;   // позиция внутри элемента массива (бит)

        // Конструктор позиции
        public position(int i, int p) {
            index = i;
            pos = p;
        }
    }

    // Константа для определения старшего бита
    private int[] array; // массив для хранения множества
    private int zeroPosition; // позиция нуля
    private int start, end; // границы множества

    // Метод для установки бита в 1
    public int SetBit(int reg, int bit) {
        reg |= (1 << bit); // устанавливаем 1 в позиции bit
        return reg;
    }

    // Метод для установки бита в 0
    public int ClearBit(int reg, int bit) {
        reg &= (~(1 << bit)); // устанавливаем 0 в позиции bit
        return reg;
    }

    // Метод для проверки, установлен ли бит в 1
    public boolean BitIsSet(int reg, int bit) {
        return ((reg & (1 << bit)) != 0); // проверяем, установлен ли бит в 1
    }

    // Конструктор множества с указанием границ
    public Set(int from, int to) {
        if ((from == 0 && to == 0) || (from > to))
            return; // проверяем корректность границ
        start = from;
        end = to;

        // Определяем размеры массива
        if (start < 0) {
            int negativeLen = start >= -31 ? -1 : start / 32 - 1;
            int positiveLen = end <= 31 ? 0 : end / 32;
            array = new int[Math.abs(negativeLen) + positiveLen + 1];
            zeroPosition = -negativeLen;
            return;
        }

        zeroPosition = 0;
        position p1 = findInArray(start);
        position p2 = findInArray(end);
        array = new int[p2.index - p1.index + 1];
        zeroPosition = -p1.index;
    }

    // Копирующий конструктор
    public Set(Set a) {
        start = a.start;
        end = a.end;
        zeroPosition = a.zeroPosition;
        array = new int[a.array.length];
        for (int i = 0; i < array.length; i++) {
            array[i] = a.array[i]; // копируем элементы массива
        }
    }

    // Метод для печати множества
    public void print() {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < 32; j++)
                if (BitIsSet(array[i], j)) {
                    System.out.print((-zeroPosition + i) * 32 + j + " "); // выводим элементы множества
                }
        }
        System.out.println();
    }

    // Метод для вставки элемента в множество
    public void insert(int q) {
        if (q < start || q > end) return; // проверяем границы
        position p = findInArray(q);
        array[p.index] = SetBit(array[p.index], p.pos); // вставляем элемент
    }

    // Метод для удаления элемента из множества
    public void delete(int q) {
        if (q < start || q > end) return; // проверяем границы
        position p = findInArray(q);
        array[p.index] = ClearBit(array[p.index], p.pos); // удаляем элемент
    }

    // Метод для присваивания значения другому множеству
    public void assign(Set a) {
        if (this == a) return; // проверка на самоприсвоение
        start = a.start;
        end = a.end;
        zeroPosition = a.zeroPosition;
        array = new int[a.array.length];
        for (int i = 0; i < array.length; i++) {
            array[i] = a.array[i]; // копируем элементы массива
        }
    }

    // Метод для нахождения минимального элемента в множестве
    public int min() {
        for (int i = 0; i < array.length; i++) {
            if (array[i] != 0) { // ищем первый непустой элемент массива
                for (int j = 0; j < 32; j++) {
                    if (BitIsSet(array[i], j)) {
                        return ((-zeroPosition + i) * 32 + j); // возвращаем минимальный элемент
                    }
                }
            }
        }
        throw new myException("The set is empty");
    }

    // Метод для нахождения максимального элемента в множестве
    public int max() {
        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i] != 0) { // ищем последний непустой элемент массива
                for (int j = 31; j >= 0; j--) {
                    if (BitIsSet(array[i], j)) {
                        return ((-zeroPosition + i) * 32 + j); // возвращаем максимальный элемент
                    }
                }
            }
        }
        throw new myException("The set is empty");
    }

    // Метод для проверки равенства двух множеств
    public boolean equal(Set a) {
        if (a == null) throw new myException("");
        if (a == this) return true; // проверка на самоприсвоение

        if (end < a.start || a.end < start)
            return false; // проверка на пересечение границ

        // Проверка левой части на наличие нулей
        Set leftSet = start < a.start ? this : a;
        Set secondSet = start < a.start ? a : this;
        int intersectionStart = leftSet.findInArray(secondSet.start).index;
        for (int i = 0; i < intersectionStart; i++) {
            if (leftSet.array[i] != 0)
                return false;
        }

        // Проверка пересечения
        int secondSetIntersectionStart = 0;
        int intersectionEnd = secondSet.findInArray(Math.min(end, a.end)).index;
        for (int i = intersectionStart; i <= intersectionEnd; i++) {
            if (leftSet.array[i] != secondSet.array[secondSetIntersectionStart])
                return false;
            secondSetIntersectionStart++;
        }

        // Проверка правой части на наличие нулей
        secondSet = end < a.end ? a : this;
        leftSet = end < a.end ? this : a;
        intersectionEnd = secondSet.findInArray(leftSet.end).index;
        for (int i = intersectionEnd + 1; i < secondSet.findInArray(end).index; i++) {
            if (secondSet.array[i] != 0)
                return false;
        }

        return true;
    }

    // Метод для очистки множества
    public void makeNull() {
        for (int i = 0; i < array.length; i++) {
            array[i] = 0; // обнуляем элементы массива
        }
    }

    // Метод для проверки, является ли элемент членом множества
    public boolean member(int q) {
        if (isEmpty()) return false; // проверка на пустое множество
        if (q < start || q > end) return false; // проверка границ
        position p = findInArray(q);
        return BitIsSet(array[p.index], p.pos); // проверка, установлен ли бит
    }

    // Метод для нахождения элемента в двух множествах
    public Set find(Set a, int x) {
        if (!(isEmpty() || (x < start || x > end))) {
            position p = findInArray(x);
            if (BitIsSet(array[p.index], p.pos)) {
                return this;
            }
        }

        if (!(a.isEmpty() || (x < a.start || x > a.end))) {
            position p = a.findInArray(x);
            if (a.BitIsSet(array[p.index], p.pos)) {
                return a;
            }
        }

        return null;
    }

    // Метод для объединения двух множеств
    public Set merge(Set a) {
        if (a == this) return new Set(a); // проверка на самоприсвоение
        return mergeSets(a);
    }

    // Метод для объединения (объединение синоним merge)
    public Set union(Set a) {
        if (a == this) return new Set(a); // проверка на самоприсвоение
        return mergeSets(a);
    }

    // Метод для пересечения двух множеств
    public Set intersection(Set a) {
        // Если переданное множество совпадает с текущим, создаем его копию
        if (a == this)
            return new Set(a);

        // Если множества не пересекаются, возвращаем null
        if (a.end < start || a.start > end)
            return null;

        // Определяем начало и конец пересечения
        int intersectionStart = Math.max(a.start, start);
        int intersectionEnd = Math.min(a.end, end);

        // Создаем новое множество для пересечения с заданными границами
        Set c = new Set(intersectionStart, intersectionEnd);

        // Находим индексы для начала и конца пересечения в новом множестве
        int firstNew = c.findInArray(c.start).index;
        int lastNew = c.findInArray(c.end).index;

        // Находим индексы начала пересечения в текущем и переданном множествах
        int firstSetStart = findInArray(intersectionStart).index;
        int secondSetStart = a.findInArray(intersectionStart).index;

        // Выполняем пересечение множеств
        for (int i = firstNew; i <= lastNew; i++) {
            // Пересекаем элементы текущего множества и переданного множества
            c.array[i] = array[firstSetStart] & a.array[secondSetStart];
            firstSetStart++;
            secondSetStart++;
        }

        // Возвращаем новое множество, содержащее пересечение
        return c;
    }

    // Метод для разности двух множеств
    public Set difference(Set a) {
        // Если переданное множество совпадает с текущим, создаем новое пустое множество
        if (a == this) return new Set(start, end);

        // Создаем копию текущего множества
        Set newSet = new Set(this);

        // Если множества не пересекаются, возвращаем копию текущего множества
        if (end < a.start || a.end < start) {
            return newSet;
        }

        // Определяем индекс начала пересечения в переданном множестве
        int secondSetStart;
        if (start <= a.start)
            secondSetStart = a.findInArray(a.start).index;
        else
            secondSetStart = a.findInArray(start).index;

        // Определяем индекс конца пересечения
        int intersectionEnd;
        if (end <= a.end)
            intersectionEnd = findInArray(end).index;
        else
            intersectionEnd = findInArray(a.end).index;

        // Выполняем разность множеств
        for (int i = 0; i <= intersectionEnd && secondSetStart < a.array.length; i++) {
            // Удаляем элементы переданного множества из текущего множества
            newSet.array[i] = array[i] & ~(a.array[secondSetStart]);
            secondSetStart++;
        }

        // Возвращаем новое множество, содержащее разность
        return newSet;
    }

    // Метод для нахождения позиции элемента в массиве
    public position findInArray(int q) {
        // Если стартовая позиция равна 0
        if (start == 0)
            return new position(q / 32, q % 32);

        // Если стартовая позиция меньше нуля
        if (start < 0) {
            if (q < 0)
                return new position(zeroPosition - Math.abs(q / 32) - 1, q % 32);
            else return new position(zeroPosition + q / 32, q % 32);
        }

        // Если стартовая позиция больше 0
        return new position(q / 32 + zeroPosition, q % 32);
    }

    // Метод для проверки, является ли множество пустым
    private boolean isEmpty() {
        for (int i = 0; i < array.length; i++) {
            if (array[i] != 0) return false; // проверка на пустоту массива
        }
        return true;
    }

    // Метод для объединения массивов
    private Set mergeSets(Set a) {
        int newStart = Math.min(a.start, start);
        int newEnd = Math.max(a.end, end);

        Set n = new Set(newStart, newEnd);

        int thisStartIndex = n.findInArray(start).index;
        int aStartIndex = n.findInArray(a.start).index;

        // Объединение текущего множества
        for (int i = 0, nIndex = thisStartIndex; i < array.length; i++, nIndex++) {
            n.array[nIndex] |= array[i];
        }

        // Объединение переданного множества
        for (int i = 0, nIndex = aStartIndex; i < a.array.length; i++, nIndex++) {
            n.array[nIndex] |= a.array[i];
        }

        return n;
    }
}
