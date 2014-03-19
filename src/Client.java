
//客户端
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
  
class Client extends Thread{
    private Socket socket = null;
    PrintWriter socketWriter =null;
    String ip;
    int port;
    String file;
  public Client(String ip,int port) {
	  this.ip=ip;
	  this.port=port;
}
    public void connect() throws UnknownHostException, IOException {
        socket =new Socket(ip,port);
}
  
    public void transferFile() throws IOException {
        FileInputStream is =new FileInputStream(file);
  
        DataInputStream fiins =new DataInputStream(new BufferedInputStream(is));
        DataOutputStream fileous =new DataOutputStream(socket.getOutputStream());
  
        int n = 2048, m;
        byte buffer[] = new byte[n];
        while((m = fiins.read(buffer, 0, n)) != -1&& m > 0) {
            fileous.write(buffer,0, m); //这里要注意  读取多少字节 写入多少字节
            StreamCounter.addStream(m);
            fileous.flush();
        }
//        System.out.println("hehe");
        fileous.flush();
        fileous.close();
        fiins.close();
        socket.getOutputStream().close();
        socketWriter.close();
    }
    public void run() {
    	while(true){
    	try{
    		file=ThreadList.pushOrPop(false,null);
    		if(file==null){
    			Ini.jamNum++;
    		sleep(100);	
    		}
    		else{
    			Ini.noJamNum++;
    	connect();
    	transferFile();
    		}
    	}catch(Exception e){}
    	}
}
}