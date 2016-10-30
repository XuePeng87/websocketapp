package cn.net.bysoft.websocketapp.lesson3;

import javax.websocket.DecodeException;
import javax.websocket.EndpointConfig;

import com.alibaba.fastjson.JSON;

public class UserDecoder implements javax.websocket.Decoder.Text<User> {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(EndpointConfig arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User decode(String user) throws DecodeException {
		return JSON.parseObject(user, User.class);
	}

	@Override
	public boolean willDecode(String arg0) {
		return true;
	}

}
