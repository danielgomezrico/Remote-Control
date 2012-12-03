/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.makingiants.model.server;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import com.makingiants.model.managers.MessageManager;

/**
 *
 * @author danielgomezrico
 */
public class UDPHandler extends IoHandlerAdapter {
	
	// ---------------------------------------------
	// Attributes
	// ---------------------------------------------
	
	private MessageManager _manager;
	private IoSession _session;
	
	// ---------------------------------------------
	// Constructor
	// ---------------------------------------------
	
	public UDPHandler() {
		_manager = new MessageManager();
	}
	
	// ---------------------------------------------
	// Methods
	// ---------------------------------------------
	
	public void close() throws NullPointerException {
		try {
			_session.close(true);
		} catch (NullPointerException e) {
			new NullPointerException("There's no session to close");
		}
	}
	
	@Override
	public void sessionOpened(IoSession session) throws Exception {
		super.sessionOpened(session);
		_session = session;
	}
	
	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		session.close(true);//true - avoid queued messages and close now
	}
	
	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		
		if (message != null) {
			String text = new String(((IoBuffer) message).array());
			_manager.manage(text);
		}
		
	}
}
