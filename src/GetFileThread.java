import java.io.File;

/**
 * this thread is packing all the files until all is packed 
 * */
public class GetFileThread extends Thread{
	public void run(){
		
		FileMgr fm = new FileMgr();
		while(true){
			try{
			String path=Ini.tempFile+NowPoint.renewPoint()+".zip";
		fm.ZipFiles(ThreadList.requireFile(),new File(path));
		ThreadList.pushOrPop(true, path);
		
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
}
}
