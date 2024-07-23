// class NumberPrinter extends Thread{
//     private int n;
//     public NumberPrinter(int n){
//         this.n = n;
//     }

//     @Override
//     public void run(){
//         System.out.println(n + " " + Thread.currentThread().getName());
//     }
// }

// class SingleNumberPrinter extends Thread{
//     private int num;
//     public SingleNumberPrinter(int num){
//         this.num = num;
//     }

//     @Override
//     public void run(){
//         System.out.println(num + " " + Thread.currentThread().getName());
//     }
// }

// class Student extends Thread{
//     @Override
//     public void run(){

//     }
// }

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class SingleNumberPrinterV2 implements Runnable{
    private int num;

    public SingleNumberPrinterV2(int num){
        this.num = num;
    }
    @Override
    public void run() {
        System.out.println(num + " " + Thread.currentThread().getName());
    }
    
}
public class PrintNumbersMultithreading {
    public static void main(String[] args) {
        // for (int j=1; j<=10; j++){
        //     NumberPrinter numberPrinter = new NumberPrinter(j);
        //     numberPrinter.start();
        // }

        // for (int j=1; j<=100; j++){
        //     SingleNumberPrinter singleNumberPrinter = new SingleNumberPrinter(j);
        //     singleNumberPrinter.start();
        // }

        // for (int j=1; j<=100; j++){
        //     SingleNumberPrinterV2 singleNumberPrinter = new SingleNumberPrinterV2(j);
        //     Thread thread = new Thread(singleNumberPrinter);
        //     thread.start();
        // }

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int j=1; j<=100; j++){
            SingleNumberPrinterV2 singleNumberPrinterV2 = new SingleNumberPrinterV2(j);
            executorService.submit(singleNumberPrinterV2);
        }
        executorService.shutdown();
    }
}
