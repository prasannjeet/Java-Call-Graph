package callGraphHierarchyGraph;

public class SomeTests2 {
    public static void main (String [] args){
        Thread thread = new Thread(()->{
            for (int i=0; i<10; i++) System.out.println("Hello World");
        });
        thread.start();
    }
}
