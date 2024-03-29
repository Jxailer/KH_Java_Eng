package day18.socket2;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Client {
	
	private Socket socket;
	
	public void receive() { // method receiving message from server
		Thread t = new Thread(()->{
			ObjectInputStream ois = null;
			try {
				ois = new ObjectInputStream(socket.getInputStream()); 
				// gets stream in Object form received from Socket
				// Stream: Iterator that able to refer to objects in Collection one by one
				while(true) {
					String str = ois.readUTF(); //  stream is transform in UTF and stored as String
					if(str.equals("-1")) {
						break;
					}
					System.out.println(str);
				}
			}catch(Exception e) {
					e.printStackTrace();
					System.out.println("예외가 발생해서 읽기 기능을 종료합니다.");
			}
				
			
		});
		t.start();
		
		/*Thread t1 = new Thread(new Runnable() {
		
		@Override
		public void run() {
			
			
		}
	});*/
	}
	
	public void send() { // method sending message from client to server
		Thread t = new Thread(()->{
			ObjectOutputStream oos = null;
			try {
				oos = new ObjectOutputStream(socket.getOutputStream());
				Scanner scan = new Scanner(System.in);
				while(true) {
					String str = scan.nextLine();
					oos.writeUTF(str);
					oos.flush();
					if(str.equals("-1")) {
						break;
					}
				}
				System.out.println("전송 기능이 정상적으로 종료됩니다.");
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("예외가 발생해서 전송 기능을 종료합니다.");
			}
		});
		t.start();
	}
	
	
}

