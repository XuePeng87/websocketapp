package cn.net.bysoft.websocketapp.lesson2;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/lifecycle")
public class LifeCycle {

	private static String START_TIME = "Start Time";
	private Session ws_session;

	@OnOpen
	public void onOpen(Session ws_session) {
		// 设置session，并记录建立连接时间
		this.ws_session = ws_session;
		ws_session.getUserProperties().put(START_TIME, System.currentTimeMillis());
		// 通知客户端连接成功
		this.sendMessage("success:opened.");
	}

	@OnMessage
	public void onMessage(String message) {
		// 如果客户端发送过来close，则关闭连接
		if ("close".equals(message)) {
			try {
				// 关闭前向客户端发送消息
				this.sendMessage("danger:server closing after " + this.getConnectionSeconds() + "s.");
				// 关闭连接
				ws_session.close();
			} catch (IOException e) {
				System.out.println("Method: onMessage, Error closeing session " + e.getMessage());
			}
			return;
		}
		// 如果消息不是close，则正常处理，处理完毕后通知客户端
		this.sendMessage("info:processed a message.");
	}

	@OnError
	public void onError(Throwable t) {
		// 发生异常时，如果连接还是打开状态，则通知客户端错误信息
		if (ws_session.isOpen()) {
			this.sendMessage("warning:Error：" + t.getMessage());
		}
	}

	@OnClose
	public void onClose() {
		// 关闭连接时，需要做的事情在该函数内完成，例如关闭数据库连接等
		System.out.println("serivce close.");
	}

	private void sendMessage(String message) {
		try {
			// 以同步的方式向客户端发送消息
			ws_session.getBasicRemote().sendText(message);
		} catch (IOException e) {
			System.out.println("Method: sendMessage, Error closeing session " + e.getMessage());
		}
	}

	private int getConnectionSeconds() {
		long millis = System.currentTimeMillis() - ((Long) this.ws_session.getUserProperties().get(START_TIME));
		return (int) millis / 1000;
	}
}
