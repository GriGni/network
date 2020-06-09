package comstudy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Client2 implements Runnable {
	BufferedReader in;
	BufferedWriter out;
	ServerSocket listener;
	Socket socket;
	Scanner scan = new Scanner(System.in);
	
	@Override
	public void run() {
		try {					// 다른 서버 연결 하는 방법. 다른 사람 서버 ip 적기.
			socket = new Socket("localhost", 9999);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			while(true) {
				//서버로 한행(내용) 보내기
				System.out.println("메시지 입력: ");
				String outputMessage = scan.nextLine(); // 라인 한줄 읽는 것.
				if (outputMessage.equalsIgnoreCase("bye")) {
					out.write(outputMessage+"\n"); // "bye" 문자열 전송
					out.flush();
					break; // 사용자가 "bye"를 입력한 경우 서버로 전송 후 실행 종료
				}

				out.write(outputMessage + "\n"); //엔터값을 꼭 넣어준다.
				out.flush();
				String inputMessage = in.readLine();
				System.out.println("서버>> " + inputMessage);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				scan.close();
				if(socket != null) socket.close(); // 클라이언트 소켓 닫기
			} catch (IOException e) {
				System.out.println("서버와 채팅 중 오류가 발생했습니다.");
			}
		}
		
	}
	
	public static void main(String[] args) {
		Client2 a = new Client2();
		a.run();
	}
}