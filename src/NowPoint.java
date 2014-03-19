
public class NowPoint {
private static int nowPointed=0;
public static synchronized int renewPoint() {
	int n2=nowPointed;
	nowPointed+=Ini.fetchNum;
	return n2;
}
}
