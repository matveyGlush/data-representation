package setPartiallyOrdered;

public class SetElement extends Trail {
    private final int key;
    private int count;

    public SetElement(SetElement i, Trail n, int k, int c) {
        super(i, n);
        key = k;
        count = c;
    }

    public SetElement(int k, int id) {
        super(null, null);
        key = k;
        count = 0;
    }

    public void increment(){
        count++;
    }

    public void decrement(){
        count--;
    }

    public int getCount() {
        return count;
    }

    public int getKey() {
        return key;
    }
}
