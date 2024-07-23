import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class ArrayListModifier implements Callable<ArrayList<Integer>>{
    private ArrayList<Integer> listToDouble;
    public ArrayListModifier(ArrayList<Integer> listToDouble){
        this.listToDouble = listToDouble;
    }
    @Override
    public ArrayList<Integer> call(){
        ArrayList<Integer> doubledArrayList = new ArrayList<>();
        for (int j=0; j<listToDouble.size(); j++){
            doubledArrayList.add(listToDouble.get(j) * 2);
        }
        return doubledArrayList;
    }
}

public class CallableMultithreading {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int[] arr = {8, -1, 3, 0, 10, -7, -1};
        ArrayList<Integer> listToDouble = new ArrayList<>();
        for (int j=0; j<arr.length; j++){
            listToDouble.add(arr[j]);
        }
        
        ArrayListModifier arrayListModifier =  new ArrayListModifier(listToDouble);
        ExecutorService es = Executors.newFixedThreadPool(10);
        Future<ArrayList<Integer>> doubledArrayList = es.submit(arrayListModifier);
        System.out.println(doubledArrayList.get());
    }
}
