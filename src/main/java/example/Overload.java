package example;

public class Overload{ 
	public void m(){
		System.out.println("Printing Extension string");
	}

	public void m(int i, Object o){
		System.out.println("I have parameters!");
	}
}