import java.lang.*;
/*实现泛型接口是的具体类*/
public class IGenericImpl implements IGeneric<String>{
	@Override
	public String getName(String name) {
		return name;
	}
}