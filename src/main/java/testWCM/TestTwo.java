package testWCM;

import ps222vt.DirectedGraph;
import ps222vt.MyGraph;

public class TestTwo {
	public static void main(String[] args) {
		TestOne.test1();
		TestOne.test2();
		DirectedGraph<String> DG = new MyGraph<String>();
	}
	
	public static void test6(){
		TestOne.test3();
		TestOne.test2();
		TestOne.test1();
		TestOne.test4();
	}

	public class TestThree {
	    int a;
	    int b;
	    void testMethod () {
            System.out.println("Test Method");
        }
    }
}
