
public class Timing {
	static long startTime=0;
	static long endTime  =0;
public static void begin() {
	if(startTime==0)
	startTime = System.currentTimeMillis();
}
public static void end() {
	endTime= System.currentTimeMillis();
}
public static String gettime() {
	return Float.toString((endTime - startTime) / 1000F) + " seconds.";
}
public static long getNowTime(){
	return System.currentTimeMillis() - startTime;
}
}
