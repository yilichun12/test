package com.example.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1",8888);
            OutputStream o = socket.getOutputStream();
            PrintWriter p =  new PrintWriter(o);
            p.write("用户名：uc,密码：123");
            p.flush();
            socket.shutdownOutput();

            InputStream in = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(in);//将字节流转为字符流
            BufferedReader bf = new BufferedReader(isr);
            String info =null;
            while ((info = bf.readLine()) != null) {
                System.out.println("我是客户端，服务器说：" + info);
            }
            bf.close();
            isr.close();
            in.close();
            //关闭其他资源
            p.close();
            o.close();
            o.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
