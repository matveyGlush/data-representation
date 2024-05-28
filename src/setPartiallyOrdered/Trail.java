package setPartiallyOrdered;

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
