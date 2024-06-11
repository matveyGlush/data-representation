package setPartiallyOrdered;

public class Set {
    private SetElement head;
    private int length = 0;

    public Set(){
        head = null;
    }

    private class Trail {
        public SetElement id;
        public Trail next;

        public Trail(SetElement i, Trail n){
            id = i;
            next = n;
        }
    }

    private class SetElement extends Trail {
        public final int key;
        public int count;

        public SetElement(SetElement i, Trail n, int k, int c) {
            super(i, n);
            key = k;
            count = c;
        }

        public void increment(){
            count++;
        }

        public void decrement(){
            count--;
        }
    }

    public boolean init(Pair[] pair){
        if (pair.length == 0 || pair[0].key == pair[0].dep)
            return false;

        head = new SetElement(null,null, pair[0].key, 0);
        head.id = new SetElement(null, null, pair[0].dep, 0);
        head.next = new Trail(head.id,null);
        head.id.increment();
        length++;

        for (int i = 1; i < pair.length; i++) {
            if (pair[i].key == pair[i].dep){
                return false;
            }

            SetElement temp1 = search(pair[i].key);
            if (temp1.key != pair[i].key) {
                temp1.id = new SetElement(null, null, pair[i].key, 0);
                temp1 = temp1.id;
                length++;
            }

            SetElement temp2 = search(pair[i].dep);
            if (temp2.key != pair[i].dep){
                temp2.id = new SetElement(null, null, pair[i].dep, 0);
                temp2 = temp2.id;
                length++;
            }

            temp2.increment();

            Trail tempTrail = temp1.next;
            temp1.next = new Trail(temp2, tempTrail);
        }
        length++;

        return true;
    }

    public boolean sort() {
        int checkSum = 0;
        Set newSet = new Set();
        SetElement q = head;
        SetElement q2 = null;
        SetElement lastInNewSet = null;

        while (q != null) {
            if (q.count == 0) {
                SetElement removedElement = removeElementWithZeroCount(q, q2);
                checkSum++;
                if (newSet.head != null) {
                    assert lastInNewSet != null;
                    SetElement temp = lastInNewSet.id;
                    lastInNewSet.id = removedElement;
                    lastInNewSet.id.id = temp;
                    lastInNewSet = lastInNewSet.id;
                } else {
                    newSet.head = removedElement;
                    newSet.head.id = null;
                    lastInNewSet = newSet.head;
                }
                q = head;
                q2 = null;
                continue;
            }
            q2 = q;
            q = q.id;
        }

        if (newSet.head == null || checkSum != length) return false;

        head = newSet.head;
        return true;
    }

    private SetElement removeElementWithZeroCount(SetElement q, SetElement q2) {
        Trail tempTrail = q.next;
        while (tempTrail != null) {
            tempTrail.id.decrement();
            tempTrail = tempTrail.next;
        }
        q.next = null;

        if (q == head) {
            head = head.id;
        } else {
            assert q2 != null;
            q2.id = q.id;
        }

        return q;
    }

    public void print(){
        SetElement temp = head;
        while (temp != null){
            System.out.print(temp.key);
            System.out.print(" ");
            temp = temp.id;
        }
    }

    private SetElement search(int p){
        SetElement q = head;
        SetElement q2 = null;

        while (q != null){
            if (p == q.key) {
                return q;
            }
            q2 = q;
            q = q.id;
        }
        return q2;
    }
}
