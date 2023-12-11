package com.fileexplorer.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import com.fileexplorer.drives.SystemDriverFinder;
import com.fileexplorer.search.*;

public class Server {
	ServerSocket server;
	Socket socket;
	BufferedReader br;
	PrintWriter out;
	private String msgString = "";
	private String outputString = "";

	public Server() throws IOException {
		server = new ServerSocket(777);
		System.out.println("Server is ready to accept connections, ");
		System.out.println("Waiting.....");
		socket = server.accept();
		br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream());
		startReading();
		startWriting();
	}

	private void startReading() {
		Runnable r1 = () -> {
			System.out.println("reader started....");
			try {
				while (true) {
					msgString = br.readLine();
					if (msgString.equals("exit")) {
						System.out.println("Client terminated the chat");
						socket.close();
					} else if ((msgString.length() >= 6) && ((msgString.charAt(msgString.length() - 4) == '.')
							|| (msgString.charAt(msgString.length() - 5) == '.'))) {

						System.out.println("Client requested for searching file " + msgString);
						SearchEngineMain s = new SearchEngineMain();
						List<String> paths = s.mainSearch(msgString);
						outputString = "Location: " + paths;
						System.out.println(outputString);
						out.println(outputString);
						out.flush();
						outputString = "";
					}
					System.out.println("Client : " + msgString);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		};
		new Thread(r1).start();
	}

	public void startWriting() {
		Runnable r2 = () -> {
			System.out.println("Writer started......");
			try {
				while (true) {
					BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
					if (outputString.startsWith("Location: ") == false) {
						outputString = br1.readLine();
						out.println(outputString);
						out.flush();
					}
					if (outputString.equals("exit")) {
						socket.close();
						break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		};
		new Thread(r2).start();
	}

	public static void main(String[] args) throws IOException {
		System.out.println("Going to start server::");
		new Server();
	}
}
