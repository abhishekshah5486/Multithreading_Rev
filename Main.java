class HelloWorldPrinter extends Thread{
    @Override
    public void run(){
        System.out.println("Hello World."); 
        System.out.println(Thread.currentThread().getName());
    }
}

public class Main{
    public static void main(String[] args) {
        System.out.println("Hello World!\n" + Thread.currentThread().getName());
        HelloWorldPrinter helloWorldPrinter = new HelloWorldPrinter();
        helloWorldPrinter.run();
        helloWorldPrinter.start();
        HelloWorldPrinter helloWorldPrinter2 = new HelloWorldPrinter();
        helloWorldPrinter2.start();
    }
}