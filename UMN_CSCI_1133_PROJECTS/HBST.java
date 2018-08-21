public class HBST<Key, Value> {

    private class ValueNode{
        Key key;
        Value value;
        ValueNode next;
        private ValueNode(Key key, Value value, ValueNode next){
            this.key = key;
            this.value = value;
            this.next = next;
        }

        private boolean hasNext(){
            return this.next != null;
        }
    }

    private class TreeNode{
        Key key;
        ValueNode value;
        TreeNode left;
        TreeNode right;
        private TreeNode(Key key, ValueNode value, TreeNode left, TreeNode right){
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    TreeNode head;
    private int height;

    public HBST(){
        head = new TreeNode(null,null, null, null);
    }

    public Value get(Key key){
        TreeNode current = head;
        while(current != null){
            if(hash(key) > hash(current.key)){
                current = current.right;
            } else if(hash(key) < hash(current.key)) {
                current = current.left;
            } else {
                ValueNode currentNode = current.value;
                while (currentNode != null) {
                    if(currentNode.key == key){
                        return currentNode.value;
                    }
                    currentNode = currentNode.next;
                }
            }
        }
        throw new IllegalArgumentException("No such key");
    }

    private int hash(Key key) {
        if (key == null) {
            return -1;
        } else {
            return Math.abs(key.hashCode());
        }
    }

    public int height(){
        return height;
    }

    public void put(Key key, Value value){
        int newHeight = 0;
        TreeNode current = head;
        while(current != null){
            if(hash(key) > hash(current.key)){
                newHeight++;
                if(current.right == null){
                    current.right = new TreeNode(key, new ValueNode(key, value, null), null, null);
                    if(newHeight > height){
                        height = newHeight;
                    }
                    return;
                } else {
                    current = current.right;
                }
            } else if (hash(key) < hash(current.key)){
                newHeight++;
                if(current.left == null){
                    current.left = new TreeNode(key, new ValueNode(key, value, null), null, null);
                    if(newHeight > height){
                        height = newHeight;
                    }
                    return;
                } else {
                    current = current.left;
                }
            } else {
                ValueNode currentNode = current.value;
                while(currentNode.hasNext()){
                    currentNode = currentNode.next;
                }
                currentNode.next = new ValueNode(key, value, null);
                return;
            }
        }
    }
}

class HBSTifier {
    private final static String[] keys =
            {"abstract", "assert", "boolean", "break",
                    "byte", "case", "catch", "char",
                    "class", "const", "continue", "default",
                    "do", "double", "else", "extends",
                    "false", "final", "finally", "float",
                    "for", "goto", "if", "implements",
                    "import", "instanceof", "int", "interface",
                    "long", "native", "new", "null",
                    "package", "private", "protected", "public",
                    "return", "short", "static", "super",
                    "switch", "synchronized", "this", "throw",
                    "throws", "transient", "true", "try",
                    "var", "void", "volatile", "while"};

    private final static Integer[] keys1 =
            {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

    public static void main(String[] args) {

        HBST<String, Integer> hbst = new HBST<String, Integer>();

        for (int index = 0; index < keys.length; index += 1) {
            hbst.put(keys[index], index);
        }

        System.out.println(hbst.height());

        for (int index = 0; index < keys.length; index += 1) {
            System.out.format("%02d %s", hbst.get(keys[index]), keys[index]);
            System.out.println();
        }

        HBST<Integer, Integer> hbst1 = new HBST<Integer, Integer>();
        for (int i = 0; i < keys1.length; i++){
            hbst1.put(keys1[i], i);
        }

        System.out.println(hbst1.height());

        for (int i = 0; i < keys1.length; i++){
            System.out.format("%02d %d", hbst1.get(keys1[i]), keys1[i]);
            System.out.println();
        }

    }
}