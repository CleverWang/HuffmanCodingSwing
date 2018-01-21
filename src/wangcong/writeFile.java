package wangcong;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.text.NumberFormat;

public class writeFile {
	String hfcode;// ����Ҫд�뵽�ļ��е�Դ�ļ�
	byte bbb[];
	String huffmantree;

	public writeFile(fileToCode co, String targetfilename) throws Exception {// ������������õ��ļ�д��ָ������·�����ļ���
		this.bbb = strToByte(co.hfcode);
		// System.out.println(this.bbb);
		File file = new File(targetfilename);
		if (!file.exists()) {// �ж��ļ��Ƿ���ڣ������ھʹ���
			// try {
			file.createNewFile();
			// } catch (IOException e) {
			// System.out.println("�ļ�����ʧ�ܣ�");
			// }
		}
		FileOutputStream fw;
		// try {
		fw = new FileOutputStream(file);
		BufferedOutputStream bw = new BufferedOutputStream(fw);
		bw.write(this.bbb);// ���ļ�д��ָ���ļ���
		bw.close();
		fw.close();
		// } catch (IOException e) {
		// System.out.println("Ŀ���ļ������ڣ�");
		// }
	}

	public writeFile(codeToFile fl, String targetfilename) throws Exception {// ���������ļ�д��ָ������·�����ļ���
		this.hfcode = fl.filecontent;
		// System.out.println(this.hfcode);
		File file = new File(targetfilename);
		if (!file.exists()) {// �ж��ļ��Ƿ���ڣ������ھʹ���
			// try {
			file.createNewFile();
			// } catch (IOException e) {
			// System.out.println("�ļ�����ʧ�ܣ�");
			// }
		}
		FileWriter fw;
		// try {
		fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(this.hfcode);// ���ļ�д��ָ���ļ���
		bw.close();
		fw.close();
		// } catch (IOException e) {
		// System.out.println("Ŀ���ļ������ڣ�");
		// }
	}

	public writeFile(huffmanTree htree, eleAndfreq elem, String targetfilename)
			throws Exception {
		StringBuffer temp = new StringBuffer();
		int i;
		for (i = 0; i < htree.m * 2 - 2; i++)
			temp.append(String.valueOf(htree.ht[i].parent) + ' '
					+ String.valueOf(htree.ht[i].llink) + ' '
					+ String.valueOf(htree.ht[i].rlink) + ',');
		temp.append(String.valueOf(htree.ht[i].parent) + ' '
				+ String.valueOf(htree.ht[i].llink) + ' '
				+ String.valueOf(htree.ht[i].rlink) + "|");
		for (int j = 0; j < elem.num; j++)
			temp.append(elem.ele[j]);
		this.huffmantree = temp.toString();
		// System.out.println(this.huffmantree);

		File file = new File(targetfilename);
		if (!file.exists()) {// �ж��ļ��Ƿ���ڣ������ھʹ���
			file.createNewFile();
		}
		FileWriter fw;
		fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(this.huffmantree);// ���ļ�д��ָ���ļ���
		bw.close();
		fw.close();
	}

	byte[] strToByte(String s) {
		byte temp;
		int times = s.length() / 7;// ȡ7λ����һ���ֽ�
		int left = s.length() % 7;// �Ƿ�������
		if (left != 0)
			times++;// ���������ֽ�����Ԫ�ظ�����1
		byte[] bb;
		bb = new byte[times];
		StringBuffer sb = new StringBuffer();
		int j = 0;
		boolean fl = false;
		for (int i = 0; i < times; i++) {// ѭ��times����times���ֽ�
			if (i == times - 1 && left != 0) {// ���һ�����Ҳ���7λ��ת���ɲ���
				sb.append(s.substring(j, j + left));
				if (sb.charAt(0) == '0') {// ��ͷ��0��ת���ɸ�����ʽ����
					fl = true;
					StringBuffer tem = sb.reverse();
					int first1 = 0;
					for (; first1 < tem.length(); first1++)
						// �ҵ���һ��1
						if (tem.charAt(first1) == '1')
							break;
					for (int g = first1 + 1; g < tem.length(); g++) {// ��1ת��0����0ת��1
						if (tem.charAt(g) == '1')
							tem.setCharAt(g, '0');
						else
							tem.setCharAt(g, '1');
					}
					sb = tem.reverse();// ��Ҫת��
				}
			} else if (i == times - 1) {// �����һ��Ҳ��7λ��Ҳת���ɲ���
				sb.append(s.substring(j, j + left));
				if (sb.charAt(0) == '0') {
					fl = true;
					StringBuffer tem = sb.reverse();
					int first1 = 0;
					for (; first1 < tem.length(); first1++)
						if (tem.charAt(first1) == '1')
							break;
					for (int g = first1 + 1; g < tem.length(); g++) {
						if (tem.charAt(g) == '1')
							tem.setCharAt(g, '0');
						else
							tem.setCharAt(g, '1');
					}
					sb = tem.reverse();
				}
			} else
				sb.append(s.substring(j, j + 7));
			// System.out.println(sb);
			temp = Byte.parseByte(sb.toString(), 2);// ���ַ�����ʽ������ת�����ֽ���ʽ��������
			if (fl)
				bb[i] = (byte) (0 - temp);
			else
				bb[i] = temp;
			// System.out.println(bb[i]);
			sb = new StringBuffer();
			j = j + 7;// ȡ��һ��7λ
		}
		return bb;
	}

	String rate(String source, String target) {
		File so = new File(source);
		File ta = new File(target);
		if (so.exists() && so.isFile() && ta.exists() && ta.isFile()) {
			double r = ta.length() * 100.0 / so.length();
			NumberFormat ddf1 = NumberFormat.getNumberInstance();
			ddf1.setMaximumFractionDigits(2);
			String s = ddf1.format(r);
			return s + '%';
		} else
			return "����ʧ��";
	}
}