package example.test;

interface I {  
	public String strI = "Printing I string"; 
	public void m(); 
} 

class A implements I {  
	public void m() {
		System.out.println(strI);} 
	} 

class B extends A {  
	public B() {
		super();
	}  
} 

class C extends A {
	public void m(){

		System.out.println("Printing C string");
	} 
} 