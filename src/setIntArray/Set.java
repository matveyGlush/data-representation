package setIntArray;

public class Set {
    public static class position{
        int index;
        int pos;

        public position(int i, int p){
            index = i;
            pos = p;
        }
    }
    private static final int leftBit = 0b10000000000000000000000000000000;
    private int[] array; // массив интов
    private int zeroPosition; // позиция нуля
    private int start, end; // границы


    public int SetBit(int reg, int bit) {
        reg |= (1<<bit); //поставить 1 в bit
        return reg;
    }

    public int ClearBit(int reg, int bit) {
        reg &= (~(1<<bit)); // поставить 0 в bit
        return reg;
    }

    public int InvBit(int reg, int bit) { 
        reg ^= (1<<bit); //инвертировать бит
        return reg;
    }
    
    public boolean BitIsSet(int reg, int bit) {
        return ((reg & (1<<bit)) != 0); //проверить, стоит ли 1 в бите
    }

    public boolean BitIsClear(int reg, int bit) {
        return ((reg & (1<<bit)) == 0); //проверить, стоит ли 0 в бите
    }

    // конструктор от границ
    public Set(int from, int to) {
        // проверяем правильность ввода
        if ((from == 0 && to == 0) || (from > to)) 
            return;
        start = from;
        end = to;

        if (start < 0){
            int negativeLen = start >= -31? -1 : start/32 - 1;
            int positiveLen = end <= 31 ? 0 : end/32;
            array = new int[Math.abs(negativeLen) + positiveLen + 1];
            zeroPosition = -negativeLen;
            return;
        }

        zeroPosition = 0;
        position p1 = findInArray(start);
        position p2 = findInArray(end);

        array = new int[p2.index - p1.index +  1];
        zeroPosition = -p1.index;
    }

    public Set(Set a){
        // копирующий конструктор
        start = a.start;
        end = a.end;
        zeroPosition = a.zeroPosition;
        array = new int[a.array.length];
        for (int i = 0; i < array.length; i ++){
            array[i] = a.array[i];
        }
    }

    public void print(){
       // System.out.println("Zero at: " + zeroPosition);
       // System.out.println("Start: " + start + " , End: " + end);
        /*for (int i = 0; i < array.length; i++) {
            if (array[i] == 0) 
                System.out.print("0" + " ");
            else 
                System.out.print(String.format("%32s", Integer.toBinaryString(array[i])).replaceAll(" ", "0") + " ");
            
        }*/
        //System.out.println();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < 32; j++)
                if(BitIsSet(array[i], j)) {
                    System.out.print((-zeroPosition + i) * 32 + j + " ");
                }
            
        }
    
        System.out.println();
    }

    public void insert(int q){
        if (q < start || q > end) return;
        position p = findInArray(q);
        array[p.index] = SetBit(array[p.index], p.pos);
        //array[p.index] |= leftBit >>> p.pos;
    }

    public void delete(int q){
        if (q < start || q > end) return;
        position p = findInArray(q);

       
        array[p.index] = ClearBit(array[p.index], p.pos);
    }

    public void assign(Set a){
        start = a.start;
        end = a.end;
        zeroPosition = a.zeroPosition;

        array = new int[a.array.length];
        for (int i = 0; i < array.length; i ++){
            array[i] = a.array[i];
        }
    }

    public int min(){
        for (int i = 0; i < array.length; i++) {
            if (array[i] != 0){
                int mask;
                for (int j = 0; j < 32; j++){
                    mask = leftBit >> j;
                    if(BitIsSet(array[i], j)){
                        return ((-zeroPosition + i) * 32 + j);
                    }
                }

            }
        }
        throw new myException("The set is empty");
    }

    public int max(){
        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i] != 0) {
                int mask;
                int maskCounter = 0;
                for (int j = 31; j >= 0; j--) {
                    mask =  1 << maskCounter;
                    if(BitIsSet(array[i], j)){
                        return ((-zeroPosition + i) * 32 + j);
                    }
                    maskCounter++;
                }
            }
        }
        throw new myException("The set is empty");
    }

    public boolean equal(Set a){
        if (a == null) throw new myException("");
        if (a == this) return true;

        if (end < a.start || a.end < start)
            return false;

        // проверка левой части на наличие нулей
        // мы должны найти набор, который имеет более низкое начало
        Set leftSet = start < a.start? this : a;
        Set secondSet = start < a.start? a : this;
        int intersectionStart = leftSet.findInArray(secondSet.start).index;
        for (int i = 0; i < intersectionStart; i++){
            if (leftSet.array[i] != 0)
                return false;
        }

        // проходим пересечение
        int secondSetIntersectionStart = 0;
        int intersectionEnd = secondSet.findInArray(Math.min(end, a.end)).index;
        for (int i = intersectionStart; i <= intersectionEnd; i++){
            if (leftSet.array[i] != secondSet.array[secondSetIntersectionStart])
                return false;
            secondSetIntersectionStart++;
        }

        //проверка правой части массива
        secondSet = end < a.end? a : this;
        leftSet = end < a.end? this : a;
        intersectionEnd = secondSet.findInArray(leftSet.end).index;
        for (int i = intersectionEnd + 1; i < secondSet.findInArray(end).index; i++){
            if (secondSet.array[i] != 0)
                return false;
        }

        return true;
    }

    public void makeNull(){
        for (int i = 0; i < array.length; i++){
            array[i] = 0;
        }
    }

    public boolean member(int q){
        if(isEmpty()) return false;
        if (q < start || q > end) return false;
        position p = findInArray(q);
        return BitIsSet(array[p.index], p.pos);
    }

    public Set find(Set a, int x){
        if(!(isEmpty() || (x < start || x > end))){
            position p = findInArray(x);
            if (BitIsSet(array[p.index], p.pos)) {
                return this;
            }
        }

        if(!(a.isEmpty() || (x < a.start || x > a.end))){
            position p = a.findInArray(x);
            
            if (a.BitIsSet(array[p.index], p.pos)) {
                return a;
            }
        }

        return null;
    }

    public Set merge(Set a){
        if (a == this) return new Set(a);
        return mergeSets(a);
    }

    public Set union(Set a){
        if (a == this) return new Set(a);
        return mergeSets(a);
    }

    public Set intersection(Set a){
        if (a == this) 
            return new Set(a);
        if (a.end < start || a.start > end) 
            return null;

        int intersectionStart = Math.max(a.start, start);
        int intersectionEnd = Math.min(a.end, end);

        Set c = new Set(intersectionStart, intersectionEnd);

        int firstNew = c.findInArray(c.start).index;
        int lastNew = c.findInArray(c.end).index;

        int firstSetStart = findInArray(intersectionStart).index;
        int secondSetStart = a.findInArray(intersectionStart).index;


        for (int i = firstNew; i <= lastNew ; i++){
            c.array[i] = array[firstSetStart] & a.array[secondSetStart];
            firstSetStart++;
            secondSetStart++;
        }

        return c;
    }

    public Set difference(Set a){
        if (a == this) return new Set(start, end);

        Set newSet = new Set(this);

        //если нет пересечения
        if(end < a.start || a.end < start){
            return newSet;
        }

        int secondSetStart;
        if (start <= a.start)
            secondSetStart = a.findInArray(a.start).index;
        else
            secondSetStart = a.findInArray(start).index;

        int intersectionEnd;
        if (end <= a.end)
            intersectionEnd = findInArray(end).index;
        else
            intersectionEnd = findInArray(a.end).index;

        for (int i = 0; i <= intersectionEnd && secondSetStart < a.array.length; i++){
            newSet.array[i] = array[i] & ~(a.array[secondSetStart]);
            secondSetStart++;
        }

        return newSet;
    }

    public position findInArray(int q){
        //если старт в 0
        if (start == 0)
            return new position(q / 32, q % 32);

        //если старт меньше нуля
        if (start < 0) {
            if (q < 0)
                return new position(zeroPosition - Math.abs(q / 32) - 1, q % 32);
            else return new position(zeroPosition + q / 32, q % 32);
        }

        //если старт > 0
        return new position(q / 32 + zeroPosition, q%32);
    }

    private boolean isEmpty(){
        for (int i = 0; i < array.length; i++){
            if (array[i] != 0) return false;
        }
        return true;
    }

    private Set mergeSets(Set a) {
        int newStart = Math.min(a.start, start);
        int newEnd = Math.max(a.end, end);

        Set n = new Set(newStart, newEnd);

        int thisStartIndex = n.findInArray(start).index;
        int aStartIndex = n.findInArray(a.start).index;

        for (int i = 0, nIndex = thisStartIndex; i < array.length; i++, nIndex++) {
            n.array[nIndex] |= array[i];
        }

        for (int i = 0, nIndex = aStartIndex; i < a.array.length; i++, nIndex++) {
            n.array[nIndex] |= a.array[i];
        }

        return n;
    }

//    private Set mergeSets(Set a){
//        Set n = new Set(Math.min(a.start, start), Math.max(a.end, end));
//
//        int counter = 0;
//        int nStart = n.findInArray(start).index;
//        int nEnd = n.findInArray(end).index;
//        for (int i = nStart; i < nEnd; i++){
//            n.array[i] |= array[counter];
//            counter++;
//        }
//
//        counter = 0;
//        int aStart = n.findInArray(a.start).index;
//        int aEnd = n.findInArray(a.end).index;
//        for (int i = aStart; i <= aEnd; i++){
//            n.array[i] |= a.array[counter];
//            counter++;
//        }
//
//        return n;
//    }
}
