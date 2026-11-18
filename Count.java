import java.util.ArrayList;

public class Count {
    ArrayList<CountHelper> countHelpers;

    private class CountHelper{
        int pathLength;
        int amount;

        public CountHelper(int data){
            pathLength = data;
            amount = 1;
        }
    }

    public Count(){
        countHelpers = new ArrayList<CountHelper>();
    }

    public void add(int data){
        boolean added = false;
        for(int i = 0; i<countHelpers.size();i++){
            if(countHelpers.get(i).pathLength==data){
                countHelpers.get(i).amount +=1;
                added = true;
            }
        }

        if(!added){
            countHelpers.add(new CountHelper(data));
        }
    }

    public int getMinLength(){
        int minIndex = Integer.MAX_VALUE;
        for(int i = 0; i<countHelpers.size();i++){
            if(minIndex > countHelpers.get(i).amount){
                minIndex = i;
            }
        }
        return countHelpers.get(minIndex).amount;
    }
    public void printPathLengths(){
        for(int i = 0; i<countHelpers.size(); i++){
            System.out.println("There are "+countHelpers.get(i).amount+" with a path length of "+countHelpers.get(i).pathLength);
        }
    }
}
