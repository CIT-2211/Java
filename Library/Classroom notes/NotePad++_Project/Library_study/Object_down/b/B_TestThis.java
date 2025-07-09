public class B_TestThis{
	int a, b, c;
	
	B_TestThis(){
		System.out.println("正要初始化对象： " + this);
	}
	B_TestThis(int a, int b){
		//B_TestThis();	//这样是无法调用构造方法的！
		this();			//调用无参构造方法，并且必须位于第一列！	
		a = a;			//这里都是指的是局部变量而不是成员变量！
						//这样就区分的局部变量和成员变量，这种情况占了this使用情况的大多数！
		this.a = a;
		this.b = b;
	}
	B_TestThis(int a, int b,int c){
		this(a,b);		//调用带参数的构造方法，并且必须要位于第一位！
		this.c = c;
	}
	
	void sing(){
		
	}
	void eat(){
		System.out.println("当前的对象： " + this);
		this.sing();	//调用本类中的sing();
		System.out.println("你妈妈喊你回家吃饭了!");
	}
	
	public static void main(String[] args){
		B_TestThis hi = new B_TestThis(2,3);
		hi.eat();
	}
}