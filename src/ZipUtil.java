
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

/**
 * ZIP�ļ�ѹ���ͽ�ѹ(Ҫʹ��apache ant.jar�Դ�����������)
 * 
 */
public class ZipUtil {

 /**
  * ѹ���ļ�file��zip�ļ�zipFile
  * 
  * @param file
  *            Ҫѹ�����ļ�
  * @param zipFile
  *            ѹ���ļ���ŵط�
  * @throws Exception
  */
private static void zip(File file, File zipFile) throws Exception {
  ZipOutputStream output = null;
  try {
   output = new ZipOutputStream(new FileOutputStream(zipFile));
   // ����Ŀ¼��ʼ
   zipFile(output, file, "");
  } catch (Exception ex) {
   ex.printStackTrace();
  } finally {
   // �ر���
   if (output != null) {
    output.flush();
    output.close();
   }
  }
 }

 /**
  * ѹ���ļ�Ϊzip��ʽ
  * 
  * @param output
  *            ZipOutputStream����
  * @param file
  *            Ҫѹ�����ļ����ļ���
  * @param basePath
  *            ��Ŀ��Ŀ¼
  * @throws IOException
  */
 private static void zipFile(ZipOutputStream output, File file,
   String basePath) throws IOException {
  FileInputStream input = null;
  try {
   // �ļ�ΪĿ¼
   if (file.isDirectory()) {
    // �õ���ǰĿ¼������ļ��б�
    File list[] = file.listFiles();
    basePath = basePath + (basePath.length() == 0 ? "" : "/")
      + file.getName();
    // ѭ���ݹ�ѹ��ÿ���ļ�
    for (File f : list)
     zipFile(output, f, basePath);
   } else {
    // ѹ���ļ�
    basePath = (basePath.length() == 0 ? "" : basePath + "/")
      + file.getName();
    // System.out.println(basePath);
    output.putNextEntry(new ZipEntry(basePath));
    input = new FileInputStream(file);
    int readLen = 0;
    byte[] buffer = new byte[1024 * 8];
    while ((readLen = input.read(buffer, 0, 1024 * 8)) != -1)
     output.write(buffer, 0, readLen);
   }
  } catch (Exception ex) {
   ex.printStackTrace();
  } finally {
   // �ر���
   if (input != null)
    input.close();
  }
 }

 /**
  * ��ѹzip�ļ�
  * 
  * @param zipFilePath
  *            zip�ļ�����·��
  * @param unzipDirectory
  *            ��ѹ����Ŀ¼
  * @throws Exception
  */
 private static void unzip(String zipFilePath, String unzipDirectory)
   throws Exception {
  // �����������������
  InputStream input = null;
  OutputStream output = null;
  try {
   // �����ļ�����
   File file = new File(zipFilePath);
   // ����zip�ļ�����
   ZipFile zipFile = new ZipFile(file);
   // ������zip�ļ���ѹĿ¼
   String name = file.getName().substring(0,
     file.getName().lastIndexOf("."));
   File unzipFile = new File(unzipDirectory + "/" + name);
   if (unzipFile.exists())
    unzipFile.delete();
   unzipFile.mkdir();
   // �õ�zip�ļ���Ŀö�ٶ���
   Enumeration zipEnum = zipFile.getEntries();
   // �������
   ZipEntry entry = null;
   String entryName = null, path = null;
   String names[] = null;
   int length;
   // ѭ����ȡ��Ŀ
   while (zipEnum.hasMoreElements()) {
    // �õ���ǰ��Ŀ
    entry = (ZipEntry) zipEnum.nextElement();
    entryName = new String(entry.getName());
    // ��/�ָ���Ŀ����
    names = entryName.split("\\/");
    length = names.length;
    path = unzipFile.getAbsolutePath();
    for (int v = 0; v < length; v++) {
     if (v < length - 1) // ���һ��Ŀ¼֮ǰ��Ŀ¼
      FileUtil.createDirectory(path += "/" + names[v] + "/");
     else { // ���һ��
      if (entryName.endsWith("/")) // ΪĿ¼,�򴴽��ļ���
       FileUtil.createDirectory(unzipFile
         .getAbsolutePath()
         + "/" + entryName);
      else { // Ϊ�ļ�,��������ļ�
       input = zipFile.getInputStream(entry);
       output = new FileOutputStream(new File(unzipFile
         .getAbsolutePath()
         + "/" + entryName));
       byte[] buffer = new byte[1024 * 8];
       int readLen = 0;
       while ((readLen = input.read(buffer, 0, 1024 * 8)) != -1)
        output.write(buffer, 0, readLen);
      }
     }
    }
   }
  } catch (Exception ex) {
   ex.printStackTrace();
  } finally {
   // �ر���
   if (input != null)
    input.close();
   if (output != null) {
    output.flush();
    output.close();
   }
  }
 }

 /**
  * ����
  * 
  * @param args
  * @throws Exception
  */
 /**
  * ѹ���ļ���
  * @param input
  * �����ļ���
  * @param output
  * ����ļ�
  * */
 public static void zipping(String input ,String output) throws Exception{
	 zip(new File(input), new File(output));
 }
 /**
  * ��ѹ�ļ���
  * @param input
  * ѹ�����ļ�
  * @param output
  * ��ѹ���ļ���
  * */
 public static void unzipping(String input ,String output) throws Exception{
	 unzip(input, output);
 }
}