import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * get some file ,move them into a folder and packing them
 * */
public class GetFileAndPacking{
	private int name=0;//counter the zip name
	private String temp;
	/**
	 * initalize
	 * @param temp
	 * 	temp folder to save the zip file
	 * such as "C:\\temp\\"
	 * */
	GetFileAndPacking(String temp){
		this.temp=temp;
		
	}
	/**
	 * copy file
	 * */
    private static void nioTransferCopy(File source, File target) {
        FileChannel in = null;
        FileChannel out = null;
        FileInputStream inStream = null;
        FileOutputStream outStream = null;
        try {
            inStream = new FileInputStream(source);
            outStream = new FileOutputStream(target);
            in = inStream.getChannel();
            out = outStream.getChannel();
            in.transferTo(0, in.size(), out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	try{
            inStream.close();
            in.close();
            outStream.close();
            out.close();
        	}catch(Exception e){
        		e.printStackTrace();
        	}
        }
    }
/**
 * create a zip file and push it to the ThreadList waiting for transparent
 * 
 * @param fileSets
 * 	the list of the fetch files
 * @param zipNumber
 *  name the zip file
 * */
    public void createZip(String fileSets[],int zipNumber) {
		String dest=temp+zipNumber+"\\";
		//the name of the folder
		 File folder = new File(dest);
		 folder.mkdirs();
		 //create new Folder
		for(int i=0;i<fileSets.length;i++)
//			nioTransferCopy(new File(fileSets[i]),new File(dest+getRealName(fileSets[i])));
			{
//			System.out.println(dest+getRealName(fileSets[i]));
			FileUtil.copyFile(fileSets[i], dest+getRealName(fileSets[i]));
			}
		//copy to the folder
		try{
			String destny=temp+zipNumber+".zip";
		ZipUtil.zipping(dest,destny);//
		ThreadList.pushOrPop(true, destny);
		}catch(Exception e){
			e.printStackTrace();
		}
	
}
private String getRealName(String string) {
//	System.err.println(">>"+string);
	int a=string.lastIndexOf("\\");
	if(a==-1)return string;
	else return string.substring(a+1);
}
}
