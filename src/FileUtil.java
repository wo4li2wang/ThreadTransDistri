import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


 class FileUtil {
	 /** 
	 17.     * 将源文件的数据写入到目标文件中， 
	 18.     * 不会检查源文件是否存在， 
	 19.     * 若目标文件存在则直接写入， 
	 20.     * 否则创建目标文件后再进行写入。 
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
     * 创建文件夹，如果文件夹存在则不进行创建。 
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
     * 分隔符替换
     * window下测试通过
     * @param path
     * @return
     */
    protected static String separatorReplace(String path){
    	return path.replace("\\","/");
    }
    
    /**
     * 根据文件路径删除文件，如果路径指向的文件不存在或删除失败则抛出异常。
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
	 * 通过路径获得文件，
	 * 若不存在则抛异常，
	 * 若存在则返回该文件。
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
