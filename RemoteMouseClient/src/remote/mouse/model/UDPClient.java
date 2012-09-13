package remote.mouse.model;

import java.net.InetSocketAddress;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.nio.NioDatagramConnector;

public class UDPClient {

	private IoSession _session;
	private ConnectFuture _connectFuture;
	private UDPHandler _handler;
	
	private static UDPClient _instance;
	
	private UDPClient(){
	}
	
	public static UDPClient getInstance(){
		if(_instance == null){
			_instance = new UDPClient();
		}
		
		return _instance;
	}

	public void connect(String ip, int port) {

		if (_session == null || !_session.isConnected()) {

			// Initialize connection
			NioDatagramConnector datagramConnector = new NioDatagramConnector();
			_handler = new UDPHandler();
			datagramConnector.setHandler(_handler);
			
			_connectFuture = datagramConnector.connect(new InetSocketAddress(ip, port));
			_connectFuture.addListener(new IoFutureListener<ConnectFuture>() {

				@Override
				public void operationComplete(ConnectFuture arg0) {
					if (_connectFuture.isConnected()) {
						_session = _connectFuture.getSession();
					}
				}
			});

		}
	}
	
	public void disconnect(){
		if(_session != null){
			_session.close(false);//Now
			_connectFuture.cancel();
		}
	}

	public void send(String message){
		send(message.getBytes());
	}
	
	private void send(byte[] data){
		
		if(data == null || _session == null || !_session.isConnected()){
			return;
		}
		
		IoBuffer buffer = IoBuffer.allocate(data.length);
		buffer.put(data);
		buffer.flip();
		
		_session.write(buffer);
	}
}
