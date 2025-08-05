import java.lang.*;

public class TestObject extends Object {
	int id;
	String name;
	String pwd;
	
	/*@Override
	public boolean equals(Object o){
		if (this == o) return true;	//检查是否是同一个对象引用，如果是同一个对象，直接相等
		if (o == null || getClass() != o.getClass()) return false;	//检查参数是否有效，不为空值且属于相同类之中
		TestObject that = (TestObject) o;	// 类型转换让o变成TestObject的对象，方便进一步的比较，调用o.id这个字段
		return id == that.id;	// 核心属性比较
	}*/
	/*@Override
	public int hasCode(){
		return Object.hash(id);
	}*/
	
	public static void main(String[] args){
		TestObject t1 = new TestObject(1001,"张三","123@456");
		TestObject t2 = new TestObject(1001,"张三","123@456");
		System.out.println(t1.toString());
		System.out.println(t2.toString());
		System.out.println(t1.equals(t2));
	}
	
	public TestObject(int id,String name,String pwd){
		this.id = id;
		this.name = name;
		this.pwd = pwd;
	}
	
	
}