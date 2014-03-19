import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


 class FileUtil {
	 /** 
	 17.     * ��Դ�ļ�������д�뵽Ŀ���ļ��У� 
	 18.     * ������Դ�ļ��Ƿ���ڣ� 
	 19.     * ��Ŀ���ļ�������ֱ��д�룬 
	 20.     * ���򴴽�Ŀ���ļ����ٽ���д�롣 
	 21.     * @param srcPath 
	 22.     * @param desPath 
	 23.     */  
	     public static void copyFile(String srcPath,String desPath){  
	         try {  
	             FileInputStream in = new FileInputStream(srcPath);  
	             FileOutputStream out = new FileOutputStream(desPath);  
	             byte[] bt = new byte[1024];  
	             int count;  
	             while ((count = in.read(bt)) > 0) {  
	                 out.write(bt, 0, count);  
	             }  
	             in.close();  
	             out.close();   
	         } catch (IOException ex) {  
	             ex.printStackTrace();  
	         }         
	     }  
	 /** 
     * �����ļ��У�����ļ��д����򲻽��д����� 
     * @param path 
     * @throws Exception  
     */  
    protected static void createDirectory(String path) throws Exception{  
        path = separatorReplace(path);  
        File folder = new File(path);  
        if(folder.isDirectory()){  
            return;  
        }else if(folder.isFile()){  
            deleteFile(path);  
        }  
        folder.mkdirs();  
    }  
    
    /**
     * �ָ����滻
     * window�²���ͨ��
     * @param path
     * @return
     */
    protected static String separatorReplace(String path){
    	return path.replace("\\","/");
    }
    
    /**
     * �����ļ�·��ɾ���ļ������·��ָ����ļ������ڻ�ɾ��ʧ�����׳��쳣��
     * @param path
     * @return
     * @throws Exception 
     */
    protected static void deleteFile(String path) throws Exception {
    	path = separatorReplace(path);
        File file = getFile(path);    
        if(!file.delete()){
        	throw new Exception("delete file failure");
        }                      
    }
    
    /**
	 * ͨ��·������ļ���
	 * �������������쳣��
	 * �������򷵻ظ��ļ���
	 * @param path
	 * @return
	 * @throws FileNotFoundException
	 */
	protected static File getFile(String path) throws FileNotFoundException{
		path = separatorReplace(path);				
		File file = new File(path);
		if(!file.isFile()){
			throw new FileNotFoundException("file not found!");
		}
		return file;
	}
}
