import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.net.URL;
//工具类
public class GameUtil {
	
	public static final int FRAME_WIDTH = 480;
	public static final int FRAME_HIDTH = 700;
	
	//构造器私有防止外部类创建对象
	private GameUtil(){}
	
	public static Image getImage(String path) {
			Image img = null;
			// URL url = GameUtil.class.getClassLoader().getResource(path);
			
			// try{
				// img = ImageIO.read(url);
			// } catch (IOException e) {
				// e.printStackTrace();
			// }
			// return img;
		try {
            // 修正1：添加正确的资源获取方式
            URL url = GameUtil.class.getClassLoader().getResource(path);
            
            if (url == null) {
                // 修正2：添加资源不存在时的错误处理
                throw new IOException("资源不存在: " + path);
            }
            
            img = ImageIO.read(url);
        } catch (IOException e) {
            // 修正3：改进错误处理
            System.err.println("加载图像资源失败: " + path);
            e.printStackTrace();
        }
        return img;
	}
}