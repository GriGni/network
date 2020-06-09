package comstudy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import comstudy.ServerEx;

public class ServerEx implements Runnable {
	BufferedReader in;
	BufferedWriter out;
	ServerSocket listener;
	Socket socket;
	Scanner scan = new Scanner(System.in);

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			listener = new ServerSocket(9999); // 트라이엔케치
			System.out.println("서버의 연결을 기다리고 있습니다.");
			socket = listener.accept();
			System.out.println("클라이언트와 연결 되었습니다.");
			// socket에서 inputString과 outputstream을 얻어와서 입출력을 준비한다.
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			while (true) {
				// 클라이언트로부터 한 행 읽기
				String inputMessage = in.readLine(); // 라인 한줄 읽는 것.
				if (inputMessage.equalsIgnoreCase("bye")) {
					System.out.println("클라이언트에서 bye로 연결을 종료하였음");
					break; // "bye"를 받으면 연결 종료
				}
				System.out.println("클라이언트: " + inputMessage);
				System.out.println("서버입력: ");
				String outputMessage = scan.nextLine();
				out.write(outputMessage + "\n");
				out.flush();
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			scan.close();
			try {
				if (scan != null) {
					scan.close();
				}
				if (socket != null) {
					socket.close();
				}
				if (listener != null) {
					listener.close();
				}

			} catch (IOException e) {
				System.out.println("클라이언트와 채팅중 오류 발생");
				e.printStackTrace();
			}
		}
	}

	public ServerEx() {
		// 소켓생성 --> 메시지 입력 --> 서버에 보내기


	}

	public static void main(String[] args) {
		ServerEx c = new ServerEx();
		c.run();
	}
}