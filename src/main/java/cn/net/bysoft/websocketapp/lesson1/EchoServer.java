package cn.net.bysoft.websocketapp.lesson1;

import javax.websocket.OnMessage;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/echo")
public class EchoServer {

	@OnMessage
	public String echo(String incomingMessage) {
		return "I got this (" + incomingMessage + ") so I am sending it back !";
	}
}
