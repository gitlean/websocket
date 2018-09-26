package wsocket.push;

public class MyThread implements Runnable {

	private WebsocketServer ws;

	public WebsocketServer getWs() {
		return ws;
	}

	public void setWs(WebsocketServer ws) {
		this.ws = ws;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 5; i++) {
			try {
				ws.getSession().getBasicRemote().sendText("The server push...");
				Thread.currentThread().sleep(2000);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
