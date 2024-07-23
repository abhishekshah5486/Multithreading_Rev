import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class ListSorter implements Callable<ArrayList<Integer>> {
    private ArrayList<Integer> listToSort;
    public ListSorter(ArrayList<Integer> listToSort){
        this.listToSort = listToSort;
    }
    @Override
    public ArrayList<Integer> call() throws Exception {
        if (listToSort.size() <= 1) return listToSort;
        int mid = listToSort.size()/2;
        ArrayList<Integer> leftHalfToSort = getSubarray(listToSort, 0, mid);
        ArrayList<Integer> rightHalfToSort = getSubarray(listToSort, mid, listToSort.size());

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        ListSorter leftSorter = new ListSorter(leftHalfToSort);
        ListSorter rightSorter = new ListSorter(rightHalfToSort);
        Future<ArrayList<Integer>> leftSorterFuture = executorService.submit(leftSorter);
        Future<ArrayList<Integer>> rightSorterFuture = executorService.submit(rightSorter);

        ArrayList<Integer> leftSortedList = leftSorterFuture.get();
        ArrayList<Integer> rightSortedList = rightSorterFuture.get();

        return mergeSortedList(leftSortedList, rightSortedList);
    }
    private ArrayList<Integer> getSubarray(ArrayList<Integer> list, int s, int e){
        ArrayList<Integer> subList = new ArrayList<>();
        for (int j=s; j<e; j++){
            subList.add(list.get(j));
        }
        return subList;
    }
    private ArrayList<Integer> mergeSortedList(ArrayList<Integer> leftSortedList, ArrayList<Integer> rightSortedList){
        int j = 0;
        int k = 0;

        ArrayList<Integer> mergedList = new ArrayList<>();
        while (j < leftSortedList.size() && k < rightSortedList.size()){
            if (leftSortedList.get(j) >= rightSortedList.get(k)){
                mergedList.add(rightSortedList.get(k));
                k++;
            }
            else{
                mergedList.add(leftSortedList.get(j));
                j++;
            }
        }

        if (j != leftSortedList.size()){
            while (j < leftSortedList.size()){
                mergedList.add(leftSortedList.get(j));
                j++;
            }
        }

        if (k != rightSortedList.size()){
            while (k < rightSortedList.size()){
                mergedList.add(rightSortedList.get(k));
                k++;
            }
        }
        return mergedList;
    }
}

public class MergeSortMultithreading {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int[] arr = {-3, 4, 10, -8, 0, -5, 40, 12, -45, -2, -2, -1, 7, 9};
        ArrayList<Integer> listToSort = new ArrayList<>();
        for (int j=0; j<arr.length; j++){
            listToSort.add(arr[j]);
        }
        
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        ListSorter listSorter = new ListSorter(listToSort);
        Future<ArrayList<Integer>> sortedList = executorService.submit(listSorter);
        System.out.println(sortedList.get());
        executorService.shutdown();
    }
}
