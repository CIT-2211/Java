import java.lang.*;

public class IGenericImpl2<T> implements IGeneric <T>{
	@Override
	public T getName(T name){
		return name;
	}
}
