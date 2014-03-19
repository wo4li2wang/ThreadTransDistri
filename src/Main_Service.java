

public class Main_Service {
	
public static void main(String [] args) {
	
	Server server=new Server(10000,"E:\\text.exe");
	server.run();
	System.out.println(Timing.gettime());
}
}
