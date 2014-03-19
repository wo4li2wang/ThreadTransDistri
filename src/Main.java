import java.io.File;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main {
public static void main(String[] args) {
	ExecutorService pool=Executors.newCachedThreadPool();//�̳߳�
//	/*
	
	 String ip="192.168.1.2";
	  Ini.sourceFile="D:\\2G1";
//	 */
	
	File d=new File(Ini.sourceFile);//������ǰĿ¼���ļ���File����   
	File[] fl=d.listFiles();//ȡ��Ŀ¼�������ļ���File��������
	File[] fl2;//ȡ��Ŀ¼�������ļ���File��������
	int length=fl.length;
	for(int i=0;i<fl.length;i+=Ini.fetchNum){
		int temps=length-i;
		if(temps<Ini.fetchNum){
			fl2=new File[temps];
			for(int j=0;j<temps;j++)
				fl2[j]=fl[i+j];
			ThreadList.Now.add(fl2);
		}
		else{
			fl2=new File[Ini.fetchNum];
			for(int j=0;j<Ini.fetchNum;j++)
				fl2[j]=fl[i+j];
			ThreadList.Now.add(fl2);
		}
		
	}
	
	
	Client client[]=new Client[Ini.processNum];
	GetFileThread gft[]=new GetFileThread[Ini.processNum];
	for(int i=0;i<Ini.processNum;i++){
		client[i]=new Client(ip,Ini.socketBegin+i);
		gft[i]=new GetFileThread();
		pool.execute(client[i]);//�����߳�
		pool.execute(gft[i]);//�����߳�
	}
	
	for(int i=0;i<30;i++)
	{
		try{
		Thread.sleep(1000);
		}catch(Exception e){}
		System.out.println(i);
	}
	
	for(int i=0;i<Ini.processNum;i++){
		client[i].stop();
		gft[i].stop();
	}
	pool.shutdownNow();
	System.exit(0);
}
}