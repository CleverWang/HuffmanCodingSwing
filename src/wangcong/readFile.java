package wangcong;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class readFile {
	String content;// 保存读取的文件内容
	byte bb[];

	public readFile(String filePath, boolean flag) throws Exception {// 根据输入的文件的绝对路径读取文件
		if (flag) {
			content = new String();
			StringBuffer temp = new StringBuffer();// 暂存读取的文件
			// try {
			File file = new File(filePath);
			if (file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file));
				BufferedReader bufferedReader = new BufferedReader(read);
				for (int c = bufferedReader.read(); c != -1; c = bufferedReader
						.read())
					// 逐个读入文件中的字符
					temp.append((char) c);
				bufferedReader.close();
				read.close();
				content = temp.toString();
			} else {
				System.out.println("找不到指定的文件");
				throw new Exception();
			}
		} else {
			// try {
			File file = new File(filePath);
			if (file.exists()) { // 判断文件是否存在
				FileInputStream fi = new FileInputStream(file);
				BufferedInputStream read = new BufferedInputStream(fi);
				long filesize = file.length();
				this.bb = new byte[(int) filesize];
				int c = read.read();
				for (int i = 0; c != -1; i++) {
					// 逐个读入文件中的字符
					this.bb[i] = (byte) c;
					c = read.read();
				}
				fi.close();
				read.close();
			} else {
				System.out.println("找不到指定的文件");
				throw new Exception();
			}
		}

		/*
		 * } catch (Exception e) { System.out.println("读取文件内容出错");
		 * e.printStackTrace(); }
		 */
	}

	/*
	 * void printFile() { System.out.println(this.content); }
	 * 
	 * public static void main(String args[]) { readFile afile = new
	 * readFile("D:\\test.txt"); afile.printFile(); }
	 */
}