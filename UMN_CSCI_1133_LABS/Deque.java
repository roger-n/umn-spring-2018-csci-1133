public class Deque <Base> {
    private class Node{

        private Base object;
        private Node left;
        private Node right;

        public Node(Base object, Node left, Node right) {
            this.object = object;
            this.left = left;
            this.right = right;
        }
    }

    private Node head;

    public Deque(){
        head = new Node(null, null, null);
        head.left = head;
        head.right = head;
    }

    public void enqueueFront(Base object){
        head.right = new Node(object, head, head.right);
        head.right.right.left = head.right;
    }

    public void enqueueRear (Base object){
        head.left = new Node(object, head.left, head);
        head.left.left.right = head.left;
    }

    public Base dequeueFront(){
        if(isEmpty()) {
            throw new IllegalStateException("Empty Deque");
        }
        Base returnObject = head.right.object;
        head.right = head.right.right;
        head.right.left = head;
        return returnObject;
    }

    public Base dequeueRear(){
        if(isEmpty()){
            throw new IllegalStateException("Empty Deque");
        }
        Base returnObject = head.left.object;
        head.left = head.left.left;
        head.left.right = head;
        return returnObject;
    }

    public boolean isEmpty(){
        return head.right == head;
    }
}
