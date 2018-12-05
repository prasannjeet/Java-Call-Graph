package testWCM;

public class LoopTesting {
    int a;

    public void m() {
        System.out.println("Test one without parameters");
    }

    public void m(int i, Object o) {
        System.out.println("Test with parameters.");
    }

    public void testLoopDepth() {
        for (int i = 0; i < 10; i++) {
            int k = 100;
            while (k > 50) {
                k--;
                for (int j = 0; j < 33; j++) {
                    for (int l = 0; l < 44; l++) {
                        System.out.println("Hello World");
                    }
                    for (int x = 0; x < 10; x++) {
                        System.out.println("random text");
                        int y = 250;
                        while (y > 200){
                            y--;
                            for (int a=0;a<10;a++){
                                for(int b=0;b<2;b++){
                                    System.out.println("hello");
                                }
                            }
                        }
                    }
                }
                int q = 50;
                do {
                    System.out.println("OK");
                    q++;
                } while (q < 100);
            }
        }
    }

    private void test9(){
        System.out.println("Hello World");
    }

}