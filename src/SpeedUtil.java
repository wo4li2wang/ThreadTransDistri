public class SpeedUtil{
	
	SectionSpeed ss;
	
	/**
	 * 获得平均速度
	 * */
public float getAverageSpeed() {
return ((float)StreamCounter.returnStream()/(float)Timing.getNowTime());
}
/**
 * 获得阶段速度
 * */
public void getSectionSpeed(long halt) {
	ss=new SectionSpeed(halt);
	ss.run();
}


}

class SectionSpeed extends Thread{
	long halt;
long beginTime=0;
	long beginSize=0;
	long endTime=0;
	long endSize=0;
	public SectionSpeed(long halt) {
		this.halt=halt;
	}
	public void run() {
		beginSize=StreamCounter.returnStream();
		beginTime=Timing.getNowTime();
		try{
		sleep(halt);
		}catch(Exception e){}
		endSize=StreamCounter.returnStream();
		endTime=Timing.getNowTime();
float speeds= ((float)endSize-beginSize)/((float)endTime-beginTime);
System.out.println("速度:"+speeds);
	}
}
/**
 * 无限循环的输出时间
 * */
class FunctionSpeed extends Thread
{
	long beginTime=0;
	long beginSize=0;
	long endTime=0;
	long endSize=0;
public void run() {
	beginSize=StreamCounter.returnStream();
	beginTime=Timing.getNowTime();
	while(true)
	{
		
		try{
		sleep(1000);
		}catch(Exception e){
			System.err.println(e);
		}
		endSize=StreamCounter.returnStream();
		endTime=Timing.getNowTime();
float speeds= ((float)endSize-beginSize)/((float)endTime-beginTime);
System.out.println("速度:"+speeds);
beginSize=endSize;
beginTime=endTime;
		
	}
}	
}