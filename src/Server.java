//服务器端代码
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
  
public class Server  extends Thread {
    private ServerSocket server = null;
    private Socket socket = null;
    int port;
    String path;
    public Server(int port,String path) {
    	this.port=port;
    	this.path=path;
		// TODO Auto-generated constructor stub
	}
    public void connect() throws IOException{
        server =new ServerSocket(port,5);
        socket = server.accept();
    }
      
    public void getFile() throws IOException{
        InputStream in = socket.getInputStream();
        File file =new File(path);
        FileOutputStream os =new FileOutputStream(file);
        
        DataInputStream fileStream =new DataInputStream(in);
        DataOutputStream out =new DataOutputStream(os);
          
        int n = 512,m;
        byte buffer[] = new byte[n];
        
        while(((m=fileStream.read(buffer,0, n))!=-1) && (m>0)){
        	//将n个字节读入到buffern内，0偏移
            out.write(buffer,0,m);
            //将m个字节从buffern写出，0偏移
        }
        out.close();
        in.close();
        os.close();
        System.out.println("ok");
        socket.close();
        server.close();
    }
    public void run() {
    	try{
    		Timing.begin();
    		connect();
    		getFile();
    		Timing.end();
    	}catch(Exception e){}
}
    
}