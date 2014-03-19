
public class StreamCounter {
private	static long byteNum=0;
	public static synchronized void addStream(long add){
		byteNum+=add;
	}
	public static synchronized long returnStream(){
		return byteNum;
	}
	
}
