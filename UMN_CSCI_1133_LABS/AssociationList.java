public class AssociationList<Key, Value> {


    private class Node{

        private Key key;
        private Value value;
        private Node next;

        private Node(Key key, Value value, Node next){
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private Node head;

    public AssociationList(){
        head = new Node(null, null, null);
    }

    public void delete(Key key){
        Node previous = head;
        Node current = head.next;
        while(current != null){
            if(isEqual(key, current.key)){
                previous.next = current.next;
                return;
            }
            previous = previous.next;
            current = current.next;
        }
        return;
    }

    public Value get(Key key){
        Node current = head.next;
        while(current != null){
            if(isEqual(key, current.key)){
                return current.value;
            }
            current = current.next;
        }
        throw new IllegalArgumentException("Key does not exist");
    }

    public boolean isEqual(Key leftKey, Key rightKey){
        if(leftKey == null && rightKey == null){
            return true;
        } else if(leftKey == null && rightKey != null){
            return false;
        } else if(leftKey != null && rightKey == null){
            return false;
        } else {
            return leftKey.equals(rightKey);
        }
    }

    public boolean isIn(Key key){
        Node current = head.next;
        while(current != null){
            if(isEqual(key, current.key)){
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public void put(Key key, Value value){
        if(isIn(key)){
            Node current = head.next;
            while(current != null){
                if(isEqual(key, current.key)){
                    current.value = value;
                }
                current = current.next;
            }
        }
        head.next = new Node (key, value, head.next);
    }
}
