package remote.mouse.model.udp;

import java.net.InetSocketAddress;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.nio.NioDatagramConnector;

public class Client {

	private IoSession session;
	private ConnectFuture connectFuture;
	private Handler handler;
	
	private static Client instance;
	
	private Client(){
	}
	
	public static Client getInstance(){
		if(instance == null){
			instance = new Client();
		}
		
		return instance;
	}

	public void connect(String ip, int port) {

		if (session == null || !session.isConnected()) {

			// Initialize connection
			NioDatagramConnector datagramConnector = new NioDatagramConnector();
			handler = new Handler();
			datagramConnector.setHandler(handler);
			
			connectFuture = datagramConnector.connect(new InetSocketAddress(ip, port));
			connectFuture.addListener(new IoFutureListener<ConnectFuture>() {

				@Override
				public void operationComplete(ConnectFuture arg0) {
					if (connectFuture.isConnected()) {
						session = connectFuture.getSession();
					}
				}
			});

		}
	}
	
	public void disconnect(){
		if(session != null){
			session.close(false);//Now
			connectFuture.cancel();
		}
	}

	public void send(String message){
		send(message.getBytes());
	}
	
	private void send(byte[] data){
		
		if(data == null || session == null || !session.isConnected()){
			return;
		}
		
		IoBuffer buffer = IoBuffer.allocate(data.length);
		buffer.put(data);
		buffer.flip();
		
		session.write(buffer);
	}
}
