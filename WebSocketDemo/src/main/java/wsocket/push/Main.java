package wsocket.push;

import java.net.URI;

import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

@ClientEndpoint
public class Main {

	private static final String uri = "ws://localhost:8080/WebSocketDemo/wsocket/push";

	private static int count = 0;

	public static void main(String[] args) throws Exception {
		WebSocketContainer wsContainer = ContainerProvider.getWebSocketContainer();

		// 此处接受推送过来信息的处理类 （Main.class）
		Session session = wsContainer.connectToServer(Main.class, new URI(uri));

		// session.getBasicRemote().sendText("Here is a message!");

		while (count < 5) {
			Thread.sleep(5000);
		}
		session.close();
		System.out.println("<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>..");
	}

	// 由于是接受推送过来信息的处理类，客户端 onMessage() 接口收到推送内容，进行处理
	@OnMessage
	public void processMessage(String message) {
		System.out.println(message);
		count++;
	}

}
