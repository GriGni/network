package comstudy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import comstudy.ClientEx;

public class ClientEx extends Thread implements Runnable {
	BufferedReader in;
	BufferedWriter out;
	ServerSocket listener;
	Socket socket;
	Scanner scan = new Scanner(System.in);
	@Override
	public void run() {
		
		try {					// �ٸ� ���� ���� �ϴ� ���. �ٸ� ��� ���� ip ����.
			socket = new Socket("localhost", 9999);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			while(true) {
				//������ ����(����) ������
				System.out.println("�޽��� �Է�: ");
				String outputMessage = scan.nextLine(); // ���� ���� �д� ��.
				if (outputMessage.equalsIgnoreCase("bye")) {
					out.write(outputMessage+"\n"); // "bye" ���ڿ� ����
					out.flush();
					break; // ����ڰ� "bye"�� �Է��� ��� ������ ���� �� ���� ����
				}

				out.write(outputMessage + "\n"); //���Ͱ��� �� �־��ش�.
				out.flush();
				String inputMessage = in.readLine();
				System.out.println("����>> " + inputMessage);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				scan.close();
				if(socket != null) socket.close(); // Ŭ���̾�Ʈ ���� �ݱ�
			} catch (IOException e) {
				System.out.println("������ ä�� �� ������ �߻��߽��ϴ�.");
			}
		}
		
	}
	public ClientEx() { //127.0.0.1 �Ǵ� localhost --> ��� ��ǻ�Ϳ� ������ �ּҰ� �ִ�. ( �ڽ��� �ּҸ� ����Ű�� �ּ�)

		run();
	}
	
	
	public class Client2 extends Thread implements Runnable {
		BufferedReader in;
		BufferedWriter out;
		ServerSocket listener;
		Socket socket;
		Scanner scan = new Scanner(System.in);
		
		public Client2() {
			run();// TODO Auto-generated constructor stub
		}
		@Override
		public void run() {
			try {					// �ٸ� ���� ���� �ϴ� ���. �ٸ� ��� ���� ip ����.
				socket = new Socket("localhost", 9999);
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				while(true) {
					//������ ����(����) ������
					System.out.println("�޽��� �Է�: ");
					String outputMessage = scan.nextLine(); // ���� ���� �д� ��.
					if (outputMessage.equalsIgnoreCase("bye")) {
						out.write(outputMessage+"\n"); // "bye" ���ڿ� ����
						out.flush();
						break; // ����ڰ� "bye"�� �Է��� ��� ������ ���� �� ���� ����
					}

					out.write(outputMessage + "\n"); //���Ͱ��� �� �־��ش�.
					out.flush();
					String inputMessage = in.readLine();
					System.out.println("����>> " + inputMessage);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					scan.close();
					if(socket != null) socket.close(); // Ŭ���̾�Ʈ ���� �ݱ�
				} catch (IOException e) {
					System.out.println("������ ä�� �� ������ �߻��߽��ϴ�.");
				}
			}
			
		}
		
	}
	
	public static void main(String[] args) {
	ClientEx b =new ClientEx(); 
	b.run();
	b.start();
	

	
	}
}
