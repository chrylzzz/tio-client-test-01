package com.sdsoon.client;

import org.tio.client.ClientChannelContext;
import org.tio.client.ClientGroupContext;
import org.tio.client.ReconnConf;
import org.tio.client.TioClient;
import org.tio.client.intf.ClientAioHandler;
import org.tio.client.intf.ClientAioListener;
import org.tio.core.Node;
import org.tio.core.Tio;

import java.io.UnsupportedEncodingException;

/**
 * Created By Chr on 2019/4/16.
 */
public class ClientStarter {
    //连接服务器端的ip+port
    public static Node serverNode = new Node(TcpConfig.HOST, TcpConfig.PORT);
    //handler：
    public static ClientAioHandler clientAioHandler = new ClientHandler();

    //listener：
    public static ClientAioListener clientAioListener = new ClientListener();


    //短链后自动连接，不想自动连接设为null：（long ,int）
    private static ReconnConf reconnConf = new ReconnConf(5000L);
    //context：客户端上下文，客户端连接的是服务端的group？？？，客户端没有group？？？
    public static ClientGroupContext clientGroupContext = new ClientGroupContext(clientAioHandler, clientAioListener, reconnConf);

    //channel：tcp建立通道连接建立之后  就会产生channel，
    public static ClientChannelContext clientChannelContext = null;

    //tioClient客户端入口,等到连接的时候再连接
    public static TioClient tioClient = null;



    public static void start() throws Exception {
        //设置 心跳时间
        clientGroupContext.setHeartbeatTimeout(TcpConfig.TimeOut);


        //打开客户端入口
        tioClient = new TioClient(clientGroupContext);

        //根据连接服务器端，建立通道
        clientChannelContext = tioClient.connect(serverNode);

        send();
    }
    /**
     * 客户端程序入口
     */
    public static void main(String args[]) throws Exception {


        //设置 心跳时间
        clientGroupContext.setHeartbeatTimeout(TcpConfig.TimeOut);


        //打开客户端入口
        tioClient = new TioClient(clientGroupContext);

        //根据连接服务器端，建立通道
        clientChannelContext = tioClient.connect(serverNode);

        //客户端发送  数据信息
//        send();

//        show();
        //sendS();
    }


    public static void sendS() throws UnsupportedEncodingException {
        RequestPacket requestPacket = new RequestPacket();

        requestPacket.setBody("3A3AF70000005D5D".getBytes(RequestPacket.CHARSET));

        Tio.send(clientChannelContext, requestPacket);
    }


    public static void send() throws UnsupportedEncodingException {
        RequestPacket requestPacket = new RequestPacket();

        requestPacket.setBody("===========测试数据===========".getBytes(RequestPacket.CHARSET));
        //requestPacket.setBody("1111111111111111111111".getBytes(RequestPacket.CHARSET));

        //需要在channel中发送 数据信息
        Tio.send(clientChannelContext, requestPacket);
        // Tio.bindGroup(clientChannelContext,"aaa");
        // Tio.sendToGroup(clientGroupContext, "aaa", requestPacket);


    }

    public static void show() throws UnsupportedEncodingException {

        RequestPacket r = new RequestPacket();
        r.setBody(" 主动发送 ~ ".getBytes(RequestPacket.CHARSET));


        //bind
        Tio.bindGroup(clientChannelContext, "tio-context");
        Tio.send(clientChannelContext, r);
    }
}
