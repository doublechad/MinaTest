package org.iii.www.MinaTest;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

//伺服器端
public class Server {
	//listen Port
	public static final int PORT=9922;
	Server(){
		IoAcceptor acceptor = new NioSocketAcceptor();
		
		 acceptor.getFilterChain().addLast( "logger", new LoggingFilter() );
	     acceptor.getFilterChain().addLast( "codec", new ProtocolCodecFilter( new TextLineCodecFactory(Charset.forName("UTF-8"))));
	     //設置一個Adpart處理接受到的請求
	     acceptor.setHandler(new MyServerHandler());
	     //緩衝區大小
	     acceptor.getSessionConfig().setReadBufferSize( 2048 );
	     //閒置時間10s
	     acceptor.getSessionConfig().setIdleTime( IdleStatus.BOTH_IDLE, 10 );
		try {
			//綁定Port
			acceptor.bind( new InetSocketAddress("127.0.0.1",PORT));
			
		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}
	public static void main(String[] arg0) {
		new Server();
	}
}
