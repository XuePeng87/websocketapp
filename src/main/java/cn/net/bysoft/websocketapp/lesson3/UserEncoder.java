package cn.net.bysoft.websocketapp.lesson3;

import javax.websocket.EncodeException;
import javax.websocket.EndpointConfig;

import com.alibaba.fastjson.JSON;

public class UserEncoder implements javax.websocket.Encoder.Text<User> {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(EndpointConfig arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public String encode(User user) throws EncodeException {
		return JSON.toJSONString(user);
	}

}
