package org.iii.www.MinaTest;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class MyClinetHandler extends IoHandlerAdapter{
	MyClinetHandler(){
		
	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		System.out.println(message.toString());
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		System.out.println("messageSent" + message);
		
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		session.write("quit");
		System.out.println("sessionOpened");
	}
	
}
