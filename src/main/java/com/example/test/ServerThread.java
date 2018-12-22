package com.example.test;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.util.concurrent.Executors.*;

public class ServerThread extends Thread {

    private Socket socket = null;

    public ServerThread(Socket socket){
        this.socket = socket;
    }


    public void run() {
        InputStream is = null;//字节流
        InputStreamReader isr = null;
        BufferedReader br = null;
        OutputStream o = null;
        PrintWriter p = null;
        try {
            is = socket.getInputStream();
            isr = new InputStreamReader(is);//将字节流转为字符流
            br = new BufferedReader(isr);//为输入流添加缓冲
            String info = null;
            while ((info = br.readLine()) != null) {
                ServerBootstrap bootstrap = new ServerBootstrap();
                bootstrap.bind(8888);
                EventLoopGroup loopGroup = new NioEventLoopGroup();
                SocketChannel socketChannel = null;
                ChannelPipeline pipeline = socketChannel.pipeline();
                //ExecutorService s = newSingleThreadExecutor();

                System.out.println("我是服务端，客户端说" + info );
            }
            socket.shutdownInput();
            o = socket.getOutputStream();
            p = new PrintWriter(o);
            p.write("欢迎登录！");
            p.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != p) {
                    p.close();
                }
                if (null != o) {
                o.close();
                }
                if (null !=br ) {
                    br.close();
                }
                if (null != isr) {
                    isr.close();
                }
                if (null != is) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
