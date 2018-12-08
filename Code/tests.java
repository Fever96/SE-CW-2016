

public abstract class tests {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestArrangement t1= new TestArrangement();
		t1.test();
		System.out.println("TestArrangement are done");
		TestNewScreen t2= new TestNewScreen();
		t2.test();
		System.out.println("TestNewScreen are done");
		TestNewShow t3= new TestNewShow();
		t3.test();
		System.out.println("TestNewShow are done");
		System.out.println("All tests are done");
	}

}
