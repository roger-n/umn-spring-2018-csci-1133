public class Zillion{
    private int[] counter;
    public Zillion(int size){
        counter = new int[size];
    }

    public void increment(){
        boolean done = false;
        int position = counter.length-1;
        while(!done){
            if(counter[position] != 9){
                counter[position]++;
                done = true;
            } else {
                if(position == 0){
                    counter[0] = 0;
                    done = true;
                } else{
                    counter[position] = 0;
                    position--;
                }
            }
        }
        return;
    }

    public String toString(){
        String counterStr = "";
        for(int i = 0; i < counter.length; i++){
            counterStr += counter[i];
        }
        return counterStr;
    }
}

