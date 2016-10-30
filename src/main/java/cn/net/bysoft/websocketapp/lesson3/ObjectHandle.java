package cn.net.bysoft.websocketapp.lesson3;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/objecthandle", decoders = { UserDecoder.class }, encoders = { UserEncoder.class })
public class ObjectHandle {

	private Session ws_session;

	@OnOpen
	public void onOpen(Session ws_session) {
		// 设置session，并记录建立连接时间
		this.ws_session = ws_session;
		// 通知客户端连接成功
		this.sendMessage("success:opened.");
	}

	@OnMessage
	public User onMessage(User user) {
		user.setName("yes, jack");
		return user;
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
	}

	private void sendMessage(String message) {
		try {
			// 以同步的方式向客户端发送消息
			ws_session.getBasicRemote().sendText(message);
		} catch (IOException e) {
			System.out.println("Method: sendMessage, Error closeing session " + e.getMessage());
		}
	}
}
