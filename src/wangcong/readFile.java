package wangcong;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class readFile {
	String content;// �����ȡ���ļ�����
	byte bb[];

	public readFile(String filePath, boolean flag) throws Exception {// ����������ļ��ľ���·����ȡ�ļ�
		if (flag) {
			content = new String();
			StringBuffer temp = new StringBuffer();// �ݴ��ȡ���ļ�
			// try {
			File file = new File(filePath);
			if (file.exists()) { // �ж��ļ��Ƿ����
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file));
				BufferedReader bufferedReader = new BufferedReader(read);
				for (int c = bufferedReader.read(); c != -1; c = bufferedReader
						.read())
					// ��������ļ��е��ַ�
					temp.append((char) c);
				bufferedReader.close();
				read.close();
				content = temp.toString();
			} else {
				System.out.println("�Ҳ���ָ�����ļ�");
				throw new Exception();
			}
		} else {
			// try {
			File file = new File(filePath);
			if (file.exists()) { // �ж��ļ��Ƿ����
				FileInputStream fi = new FileInputStream(file);
				BufferedInputStream read = new BufferedInputStream(fi);
				long filesize = file.length();
				this.bb = new byte[(int) filesize];
				int c = read.read();
				for (int i = 0; c != -1; i++) {
					// ��������ļ��е��ַ�
					this.bb[i] = (byte) c;
					c = read.read();
				}
				fi.close();
				read.close();
			} else {
				System.out.println("�Ҳ���ָ�����ļ�");
				throw new Exception();
			}
		}

		/*
		 * } catch (Exception e) { System.out.println("��ȡ�ļ����ݳ���");
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