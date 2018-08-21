class PriorityQueue<Base> {
    private class Node {
        private Base object;
        private int rank;
        private Node left;
        private Node right;

        private Node(Base object, int rank) {
            this.object = object;
            this.rank = rank;
            left = null;
            right = null;
        }
    }

    private Node root;  //  Root node of the BST.

    public PriorityQueue(){
        root = new Node(null, 997);
    }

    public Base dequeue(){
        if (isEmpty()){
            throw new IllegalStateException("Empty Queue");
        } else {
            Node removeNode = root;
            Node aboveRemoveNode = root;
            while(removeNode.left != null){
                aboveRemoveNode = removeNode;
                removeNode = removeNode.left;
            }
            aboveRemoveNode.left = removeNode.right;
            return removeNode.object;
        }
    }

    public void enqueue(Base object, int rank){
        if(rank < 0){
            throw new IllegalArgumentException("Rank must be <= 0");
        } else {
            Node current = root;
            while (current != null) {
                if (rank <= current.rank){
                    if(current.left == null){
                        current.left = new Node(object, rank);
                        return;
                    } else {
                        current = current.left;
                    }
                } else {
                    if(current.right == null){
                        current.right = new Node(object, rank);
                        return;
                    } else {
                        current = current.right;
                    }
                }
            }
        }
    }

    public boolean isEmpty(){
        return root.left == null;
    }
}