ThreadTransDistri
==========
对2G大小大量1K小文件进行打包、并用多线程传输
主要分为两类线程
1. 打包线程，通过将一定数量的文件分配到指定的线程内实现打包，并将打包结果放入到zipStack堆栈内(ThreadList.java)
2. 传输线程，向zipStack申请打包文件并通过Socket传输到指定的IP
通过同步的方法对这两类线程进行任务分配，两台电脑100M带宽的双绞线直连传输速度基本能保持90%
Main_Client为发送方(客户端)执行程序
Main_Service为接受方(服务器)执行程序