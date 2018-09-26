package wsocket.push;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 功能说明：websocket处理类, 使用J2EE7的标准 切忌直接在该连接处理类中加入业务处理代码 作者：liuxing(2014-11-14
 * 04:20)
 */
// relationId和userCode是我的业务标识参数,websocket.ws是连接的路径，可以自行定义
@ServerEndpoint("/wsocket/push")
public class WebsocketServer {

	private static Log log = LogFactory.getLog(WebsocketServer.class);
	private Session session;
	
	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
		
	}

	/**
	 * 打开连接时触发
	 * 
	 * @param relationId
	 * @param userCode
	 * @param session
	 */
	@OnOpen
	public void onOpen(Session session) {
		//init thread 
		this.session=session;
		MyThread myTh=new MyThread();
		myTh.setWs(this);
		new Thread(myTh).start();
		
	}

	/**
	 * 收到客户端消息时触发
	 * 
	 * @param relationId
	 * @param userCode
	 * @param message
	 * @return
	 */
	@OnMessage
	public String onMessage(String message) {
		return "Got your message (" + message + ").Thanks !";
	}

	/**
	 * 异常时触发
	 * 
	 * @param relationId
	 * @param userCode
	 * @param session
	 */
	@OnError
	public void onError(Throwable t) throws Throwable {
		System.out.println("Error: " + t.toString());
	}

	
	/**
	 * 关闭连接时触发
	 * 
	 * @param relationId
	 * @param userCode
	 * @param session
	 */
	@OnClose
	public void onClose(Session session) {
		log.info("Websocket Close Connection");
		
	}




}