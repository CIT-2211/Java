// package two.Exception;
import java.lang.*;
/** IllegalAgeException: 非法年龄异常， 继承Exception类 **/
public class IllegalAgeException extends Exception {
	/* 默认构造器 */
	public IllegalAgeException() {
		super("年龄不合法！");
	}
	/* 待遇详细信息的构造器，信息储存在message中 */
	public IllegalAgeException(String message) {
		super(message);
	}
}