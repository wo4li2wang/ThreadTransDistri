import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class FileMgr {
  public FileMgr() {
  }
  /**
   * ѹ���ļ�
   * @param srcfile File[]  ��Ҫѹ�����ļ��б�
   * @param zipfile File    ѹ������ļ�
   */
  public void ZipFiles(java.io.File[] srcfile, java.io.File zipfile) {
    byte[] buf = new byte[1024];
    try {
      // Create the ZIP file
      ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipfile));
      // Compress the files
      for (int i = 0; i < srcfile.length; i++) {
        FileInputStream in = new FileInputStream(srcfile[i]);
        // Add ZIP entry to output stream.
        out.putNextEntry(new ZipEntry(srcfile[i].getName()));
        // Transfer bytes from the file to the ZIP file
        int len;
        while ( (len = in.read(buf)) > 0) {
          out.write(buf, 0, len);
        }
        // Complete the entry
        out.closeEntry();
        in.close();
      }
      // Complete the ZIP file
      out.close();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
  /**
   * ��ѹ��
   * @param zipfile File ��Ҫ��ѹ�����ļ�
   * @param descDir String  ��ѹ���Ŀ��Ŀ¼
   */
  public static void UnZipFiles(java.io.File zipfile, String descDir) {
    try {
      // Open the ZIP file
      ZipFile zf = new ZipFile(zipfile);
      for (Enumeration entries = zf.entries(); entries.hasMoreElements(); ) {
        // Get the entry name
        ZipEntry entry = ( (ZipEntry) entries.nextElement());
        String zipEntryName = entry.getName();
        InputStream in = zf.getInputStream(entry);
        // System.out.println(zipEntryName);
        OutputStream out = new FileOutputStream(descDir + zipEntryName);
        byte[] buf1 = new byte[1024];
        int len;
        while ( (len = in.read(buf1)) > 0) {
          out.write(buf1, 0, len);
        }
        // Close the file and stream
        in.close();
        out.close();
        System.out.println("��ѹ�����.");
      }
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
}
