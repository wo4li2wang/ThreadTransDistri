import java.io.File;
import java.util.Stack;

/**
 * a class save the zip files
 * 
 * */
public class ThreadList {
private  static Stack<String> zipStack=new Stack<String>();
public static Stack<File []> Now=new Stack<File []>();
public static synchronized String pushOrPop(boolean isPush,String pushPath){
	if(isPush){//push a file into the Stack
		zipStack.push(pushPath);
		return null;
	}
	else{
		return zipStack.pop();
	}
}
public static synchronized File[] requireFile(){
	if(Now.isEmpty()){
		return null;
	}
	else		return Now.pop();
	
}
}
