package com.example.test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPClient {

    public static void main(String[] args) throws IOException {
        InetAddress address = InetAddress.getByName("www.baidu.com");
        int port = 8800;
        byte[] data = "用户名yilc2,密码：456".getBytes();
        DatagramPacket packet = new DatagramPacket(data, data.length,address,port);
        //
        DatagramSocket socket = new DatagramSocket();
        socket.send(packet);

        //接受服务端响应数据
        byte[] data2 = new byte[1024];
        DatagramPacket packet2 = new DatagramPacket(data2,data2.length);
        socket.receive(packet2);

        String reply = new String(data2,0,packet2.getLength());
        System.out.println("我是客户端，服务器说：" + reply);

    }
}
