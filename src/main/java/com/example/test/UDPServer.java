package com.example.test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPServer {

    public static void main(String[] args) {

        try {
            DatagramSocket socket  = new DatagramSocket(8800);
            byte[] data = new byte[1024];
            DatagramPacket datagramPacket = new DatagramPacket(data,data.length);
            System.out.println("服务端开启：");
            socket.receive(datagramPacket);
            String datas = new String(data, 0,datagramPacket.getLength());
            System.out.println("我是服务端，客户大端说：" + datas);

            InetAddress address = datagramPacket.getAddress();
            int port = datagramPacket.getPort();
            byte[] data2 = "欢迎登录".getBytes();
            DatagramPacket packet2 = new DatagramPacket(data2,data2.length,address,port);
            socket.send(packet2);

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
