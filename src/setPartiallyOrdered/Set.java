package setPartiallyOrdered;

public class Set {
    private SetElement head;
    private int length = 0;

    public Set(){
        head = null;
    }

    public class Trail {
        private SetElement id;
        private Trail next;

        public Trail(SetElement i, Trail n){
            id = i;
            next = n;
        }

        public Trail getNext() {
            return next;
        }

        public SetElement getId() {
            return id;
        }

        public void setId(SetElement id) {
            this.id = id;
        }

        public void setNext(Trail next) {
            this.next = next;
        }
    }

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

    public boolean init(Pair[] pair){
        if (pair.length == 0 || pair[0].key == pair[0].dep)
            return false;

        head = new SetElement(null,null, pair[0].key, 0);
        head.setId(new SetElement(null, null, pair[0].dep, 0));
        head.setNext(new Trail(head.getId(),null));
        head.getId().increment();
        length++;

        for (int i = 1; i < pair.length; i++) {
            if (pair[i].key == pair[i].dep){
                return false;
            }

            SetElement temp1 = search(pair[i].key);
            if (temp1.getKey() != pair[i].key) {
                temp1.setId(new SetElement(null, null, pair[i].key, 0));
                temp1 = temp1.getId();
                length++;
            }

            SetElement temp2 = search(pair[i].dep);
            if (temp2.getKey() != pair[i].dep){
                temp2.setId(new SetElement(null, null, pair[i].dep, 0));
                temp2 = temp2.getId();
                length++;
            }

            temp2.increment();

            Trail tempTrail = temp1.getNext();
            temp1.setNext(new Trail(temp2, tempTrail));
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
            if (q.getCount() == 0) {
                SetElement removedElement = removeElementWithZeroCount(q, q2);
                checkSum++;
                if (newSet.head != null) {
                    assert lastInNewSet != null;
                    SetElement temp = lastInNewSet.getId();
                    lastInNewSet.setId(removedElement);
                    lastInNewSet.getId().setId(temp);
                    lastInNewSet = lastInNewSet.getId();
                } else {
                    newSet.head = removedElement;
                    newSet.head.setId(null);
                    lastInNewSet = newSet.head;
                }
                q = head;
                q2 = null;
                continue;
            }
            q2 = q;
            q = q.getId();
        }

        if (newSet.head == null || checkSum != length) return false;

        head = newSet.head;
        return true;
    }

    private SetElement removeElementWithZeroCount(SetElement q, SetElement q2) {
        Trail tempTrail = q.getNext();
        while (tempTrail != null) {
            tempTrail.getId().decrement();
            tempTrail = tempTrail.getNext();
        }
        q.setNext(null);

        if (q == head) {
            head = head.getId();
        } else {
            assert q2 != null;
            q2.setId(q.getId());
        }

        return q;
    }

    public void print(){
        SetElement temp = head;
        while (temp != null){
            System.out.print(temp.getKey());
            System.out.print(" ");
            temp = temp.getId();
        }
    }

    private SetElement search(int p){
        SetElement q = head;
        SetElement q2 = null;

        while (q != null){
            if (p == q.getKey()) {
                return q;
            }
            q2 = q;
            q = q.getId();
        }
        return q2;
    }
}
