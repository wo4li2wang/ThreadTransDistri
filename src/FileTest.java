import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FileTest {
 private static ArrayList<String> filelist = new ArrayList<String>();
 private static Map<String,String> maplist = new HashMap<String, String>();
// private static ArrayList<HashMap<String,String>> maplist = new ArrayList<HashMap<String,String>>();
 public static void main(String[] args) throws Exception {
    
    String filePath = "D://testde";
    getFiles(filePath);
 } 
 /*
  * ͨ���ݹ�õ�ĳһ·�������е�Ŀ¼�����ļ�
  */
 private static void getFiles(String filePath){
//	 HashMap<String, String> temp = new HashMap<String, String>();
	 File root = new File(filePath);
    File[] files = root.listFiles();
    for(File file:files){
   // 	temp.put(file.getName(), filePath);
 //   	maplist.add(temp);
    	maplist.put(file.getName(), filePath);
	     if(file.isDirectory()){
	      /*
	       * �ݹ����
	       */
	      getFiles(file.getAbsolutePath());
	      filelist.add(file.getAbsolutePath());
	      System.out.println("��ʾ"+filePath+"��������Ŀ¼�����ļ�"+file.getAbsolutePath());
	     }else{
	      System.out.println("��ʾ"+filePath+"��������Ŀ¼"+file.getAbsolutePath());
	     }     
    }
 }
}
