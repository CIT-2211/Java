
public interface MessageSender {
    void sendMessage(String message);
}

public class EmailSender implements MessageSender {
    @Override
    public void sendMessage(String message) {
        System.out.println("Sending email: " + message);
        // 实际的发邮件逻辑...
    }
}

public class SMSSender implements MessageSender {
    @Override
    public void sendMessage(String message) {
        System.out.println("Sending SMS: " + message);
        // 实际的发短信逻辑...
    }
}

public class NotificationService {
    // 这个方法接收任何实现了 MessageSender 接口的对象
    public void sendNotification(String message, MessageSender sender) {
        // ... 可能有一些预处理逻辑 (比如记录日志、格式化消息) ...
        // 关键点：调用接口方法，实际执行哪个实现类的方法，由传入的 sender 对象决定
        sender.sendMessage(message);
        // ... 可能有一些后处理逻辑 (比如更新状态) ...
    }
}

public class Main {
    public static void main(String[] args) {
        NotificationService service = new NotificationService();
        String alert = "Server is down!";

        // 传递 EmailSender 实例 -> 回调 EmailSender.sendMessage()
        service.sendNotification(alert, new EmailSender());

        // 传递 SMSSender 实例 -> 回调 SMSSender.sendMessage()
        service.sendNotification(alert, new SMSSender());
    }
}