package example;

import example.test.*;

public class Program { 
	public static void main(String args[]) { 
		Test2 t = new Test2();
		new Test3();
		t.test();
	}

	public static void n() { 
		System.out.println("n");
	}

	public static void y() { 
		//unused
	} 
}