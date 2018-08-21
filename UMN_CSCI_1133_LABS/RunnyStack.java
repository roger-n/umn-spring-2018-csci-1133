public class RunnyStack <Base> {

    private class Run {
        private Base object;
        private Run next;
        private int length;

        private Run(Base value, Run next) {
            this.object = value;
            this.next = next;
            this.length = 1;
        }

    }

    private Run top;
    private int numRuns;
    private int totalElements;

    public RunnyStack() {
        top = null;
        numRuns = 0;
    }

    public int depth() {
        return totalElements;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public Base peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Empty Stack");
        }
        return top.object;
    }

    public void pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Empty Stack");
        }
        top.length--;
        totalElements--;
        if (top.length == 0) {
            top = top.next;
            numRuns--;
        }
    }

    private boolean equals(Object object, Run top){
        if (object == null && top.object == null){
            return true;
        } else if (object != null && top.object != null) {
            return object.equals(top.object);
        } else {
            return false;
        }
    }

    public void push(Base object) {
        if(isEmpty()){
            top = new Run(object, top);
            numRuns++;
            totalElements++;
        } else {
            if (equals(object, top)){
                top.length++;
                totalElements++;
            } else {
                top = new Run(object, top);
                totalElements++;
                numRuns++;
            }
        }
    }

    public int runs(){
        return numRuns;
    }
}