/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.server;

import model.managers.MessageManager;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

/**
 *
 * @author danielgomezrico
 */
public class UDPHandler extends IoHandlerAdapter {

    private MessageManager _manager;
    private IoSession _session;

    public UDPHandler() {
        _manager = new MessageManager();
    }

    public void close() {
        _session.close(true);
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
