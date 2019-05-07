package com.sdsoon;

import com.sdsoon.client.RequestPacket;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tio.core.Tio;

import java.io.UnsupportedEncodingException;

import static com.sdsoon.client.ClientStarter.clientChannelContext;

/**
 * Created By Chr on 2019/4/28.
 */
@RestController
@RequestMapping("/tio")
public class Controller {

    @RequestMapping
    public String show() throws UnsupportedEncodingException {

        RequestPacket requestPacket = new RequestPacket();

        requestPacket.setBody("=====2======测试数据======2=====".getBytes(RequestPacket.CHARSET));
        //requestPacket.setBody("1111111111111111111111".getBytes(RequestPacket.CHARSET));

        //需要在channel中发送 数据信息
        Tio.send(clientChannelContext, requestPacket);
        return "suc";
    }
}
