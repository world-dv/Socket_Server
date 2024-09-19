package org.example;

import org.example.receive.ReceiveThread;
import org.example.send.SendThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {

        int port = 8888;
        ServerSocket serverSocket = new ServerSocket(port); //port를 지정해 socket과 binding
        while (true) {
            Socket socket = serverSocket.accept(); //client와 연결 생성
            System.out.println("ip : " + socket.getInetAddress() + "와 연결되었습니다."); //연결 시 메시지 출력

            ReceiveThread receiveThread = new ReceiveThread(socket); //송신 스레드 생성
            receiveThread.start(); //송신 스레드 실행

            SendThread sendThread = new SendThread(socket); //수신 스레드 생성
            sendThread.start(); //수신 스레드 실행
        }
    }
}