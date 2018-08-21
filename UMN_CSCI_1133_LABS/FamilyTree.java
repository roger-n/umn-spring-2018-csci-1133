import org.omg.SendingContext.RunTime;

public class FamilyTree {

    private class Node{
        String name;
        Node father;
        Node mother;
        private Node(String name, Node father, Node mother){
            this.name = name;
            this.father = father;
            this.mother = mother;
        }
    }

    Node ego;

    public FamilyTree(String ego){
        this.ego = new Node(ego, null, null);
    }

    private Node find(String name){
        return find(name, ego);
    }

    private Node find(String name, Node root){
        if(root != null){
            if(root.name.equals(name)){
                return root;
            }

            if(find(name, root.father) != null) {
                return find(name, root.father);
            }
            if(find(name, root.mother) != null){
                return find(name, root.mother);
            }
            return null;

        } else {
            return null;
        }
    }

    public void addParents(String ego, String father, String mother){
        Node child = find(ego);
        if(child == null){
            throw new IllegalArgumentException("No such child");
        }
        child.father = new Node(father, null, null);
        child.mother = new Node(mother, null, null);
    }

    public boolean isDescendant(String ego, String ancestor){
        Node anc = find(ancestor);
        Node eg = find(ego);
        if(anc == null || eg == null){
            return false;
        }
        return isDescendant(eg, anc);
    }

    public boolean isDescendant(Node root, Node ancestor){
        return find(ancestor.name, root) != null;
    }
}
