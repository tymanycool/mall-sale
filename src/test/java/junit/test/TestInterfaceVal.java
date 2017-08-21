package junit.test;

interface MyInterface{
	public static final int a=23;
	
}
class MyClass implements MyInterface{
	public static final int a=24;
	public static void test(){
		//int b = a;;
		System.out.println(a);
	}

}

public class TestInterfaceVal {
	public static void main(String[] args) {
		MyClass.test();
	}
}
