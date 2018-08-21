public class Map <Key, Value> {

    private Key[] keys;
    private Value[] values;

    private int count=0;

    public Map(int length){
        if(length <= 0){
            throw new IllegalArgumentException("Must not be less than 1");
        }
        keys = (Key[]) new Object[length];
        values = (Value[]) new Object[length];
    }

    public Value get(Key key){
        if(key == null){
            for(int i = 0; i < count; i++){
                if(keys[i] == null){
                    return values[i];
                }
            }
        } else {
            for(int i=0; i < count; i++){
                if(key.equals(keys[i])){
                    return values[i];
                }
            }
        }
        throw new IllegalArgumentException("Not in map");
    }

    private boolean isEqual(Key leftKey, Key rightKey){
        if(leftKey==null){
            return rightKey==null;
        } else if(rightKey==null){
            return false;
        } else {
            return leftKey.equals(rightKey);
        }
    }

    public boolean isIn(Key key){
        if(key == null) {
            for(int i = 0; i < count; i++){
                if (keys[i] == null) {
                    return true;
                }
            }
        } else {
            for(int i = 0; i < count; i++){
                if(key.equals(keys[i])){
                    return true;
                }
            }
        }


        return false;
    }

    public void put(Key key, Value value){
        if(where(key) != -1){
            values[where(key)] = value;
        } else if(count == keys.length){
            throw new IllegalStateException("Map is full");
        } else{
            keys[count] = key;
            values[count] = value;
            count++;
            return;
        }
    }

    private int where(Key key){
        if(key == null) {
            for(int i = 0; i < count; i++){
                if (keys[i] == null) {
                    return i;
                }
            }
        } else {
            for(int i = 0; i < count; i++){
                if(key.equals(keys[i])){
                    return i;
                }
            }
        }
        return -1;
    }



}
