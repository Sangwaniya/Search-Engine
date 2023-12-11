package com.fileexplorer.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

	Socket socket;
	BufferedReader br;
	PrintWriter out;

	public Client() {
		try {
			System.out.println("Sending request to server");
			socket = new Socket("192.168.1.34", 8080);
			System.out.println("Connection done");
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream());
			startReading();
			startWriting();

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	private void startReading() {

		Runnable r1 = () -> {
			System.out.println("reader started....");
			try {
				while (true) {
					String msgString = br.readLine();
					if (msgString.equals("exit")) {
						System.out.println("Server terminated the chat");
						socket.close();
						break;
					}
					System.out.println("Server : " + msgString);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		};
		new Thread(r1).start();
	}

	public void startWriting() {
		Runnable r2 = () -> {
			System.out.println("Writer starter");
			while (true) {
				try {

					BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
					String content = br1.readLine();
					out.println(content);
					out.flush();
					if (content.equals("exit")) {
						socket.close();
						break;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		new Thread(r2).start();
	}

	public static void main(String[] args) {
		System.out.println("this is client");
		new Client();

	}
}
