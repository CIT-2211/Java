import java.lang.*;

public abstract class Animal{
	private String species;
	private String name;
	private byte age;
	private char sex;
	
	public Animal(String species,String name,byte age,char sex){
		setSpecies(species);
        setName(name);
        setAge(age);
        setSex(sex);
	}
	
	public String getSpecies(){
		return species;
	}
	public void setSpecies(String species ){
		if (species == null || species.trim().isEmpty()) {
            throw new IllegalArgumentException("物种不能为空");
        }
        this.species = species;
	}
	public String getName(){
		return name;
	}
	public void setName(String name ){
		 if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("名字不能为空");
        }
        this.name = name;
	}
	public int getAge(){
		return age;
	}
	public void setAge(byte age){
		 if (age < 0) {
            throw new IllegalArgumentException("年龄不能为负数");
        }
        this.age = age;
	}
	public int getSex(){
		return sex;
	}
	public void setSex(char sex){
		 if (sex != '男' && sex != '女') {
            throw new IllegalArgumentException("性别必须是'男'或'女'");
        }
        this.sex = sex;
	}
	
	public abstract void shout();
	
	public void eat(){
		System.out.println(name+"正在进食！");
	}
	
@Override
	public String toString(){
		return species + " [名字=" + name + ", 年龄=" + age + ", 性别=" + (sex == '男' ? "雄性" : "雌性") + "]";
	}
	
	public static void main(String[] args){
		Animal dog = new Dog("汉斯",(byte)2,'男');
		Animal cat = new Cat("贝蒂",(byte)3,'女');
		
		System.out.println(dog);
		dog.shout();
		dog.eat();
		if(dog instanceof Dog){
			((Dog)dog).seeDoor();
		}
		
		System.out.println("====================");
		System.out.println(cat);
		cat.shout();
		cat.eat();
		if(cat instanceof Cat){
			((Cat)cat).climbTree();
		}
		
	}
}

class Dog extends Animal{
	public Dog(String name,byte age,char sex){
		super("犬科",name,age,sex);
	}
	@Override
	public void shout(){
		System.out.println(getName()+"旺旺旺！");
	}
	public void seeDoor(){
		System.out.println(getName()+"看门中…………");
	}
}
class Cat extends Animal{
	public Cat(String name,byte age,char sex){
		super("猫科",name,age,sex);
	}
	@Override
	public void shout(){
		System.out.println(getName()+"喵喵！喵喵！");
	}
	public void climbTree(){
		System.out.println(getName()+"正在爬树…………");
	}
}