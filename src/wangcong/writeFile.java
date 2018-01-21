package wangcong;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.text.NumberFormat;

public class writeFile {
	String hfcode;// 保存要写入到文件中的源文件
	byte bbb[];
	String huffmantree;

	public writeFile(fileToCode co, String targetfilename) throws Exception {// 将哈夫曼编码好的文件写入指定绝对路径的文件中
		this.bbb = strToByte(co.hfcode);
		// System.out.println(this.bbb);
		File file = new File(targetfilename);
		if (!file.exists()) {// 判断文件是否存在，不存在就创建
			// try {
			file.createNewFile();
			// } catch (IOException e) {
			// System.out.println("文件创建失败！");
			// }
		}
		FileOutputStream fw;
		// try {
		fw = new FileOutputStream(file);
		BufferedOutputStream bw = new BufferedOutputStream(fw);
		bw.write(this.bbb);// 将文件写入指定文件中
		bw.close();
		fw.close();
		// } catch (IOException e) {
		// System.out.println("目标文件不存在！");
		// }
	}

	public writeFile(codeToFile fl, String targetfilename) throws Exception {// 将译码后的文件写入指定绝对路径的文件中
		this.hfcode = fl.filecontent;
		// System.out.println(this.hfcode);
		File file = new File(targetfilename);
		if (!file.exists()) {// 判断文件是否存在，不存在就创建
			// try {
			file.createNewFile();
			// } catch (IOException e) {
			// System.out.println("文件创建失败！");
			// }
		}
		FileWriter fw;
		// try {
		fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(this.hfcode);// 将文件写入指定文件中
		bw.close();
		fw.close();
		// } catch (IOException e) {
		// System.out.println("目标文件不存在！");
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
		if (!file.exists()) {// 判断文件是否存在，不存在就创建
			file.createNewFile();
		}
		FileWriter fw;
		fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(this.huffmantree);// 将文件写入指定文件中
		bw.close();
		fw.close();
	}

	byte[] strToByte(String s) {
		byte temp;
		int times = s.length() / 7;// 取7位生成一个字节
		int left = s.length() % 7;// 是否有余数
		if (left != 0)
			times++;// 有余数则字节数组元素个数加1
		byte[] bb;
		bb = new byte[times];
		StringBuffer sb = new StringBuffer();
		int j = 0;
		boolean fl = false;
		for (int i = 0; i < times; i++) {// 循环times生成times个字节
			if (i == times - 1 && left != 0) {// 最后一个并且不是7位，转换成补码
				sb.append(s.substring(j, j + left));
				if (sb.charAt(0) == '0') {// 开头是0，转换成负数形式补码
					fl = true;
					StringBuffer tem = sb.reverse();
					int first1 = 0;
					for (; first1 < tem.length(); first1++)
						// 找到第一个1
						if (tem.charAt(first1) == '1')
							break;
					for (int g = first1 + 1; g < tem.length(); g++) {// 是1转成0，是0转成1
						if (tem.charAt(g) == '1')
							tem.setCharAt(g, '0');
						else
							tem.setCharAt(g, '1');
					}
					sb = tem.reverse();// 需要转置
				}
			} else if (i == times - 1) {// 是最后一个也是7位，也转换成补码
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
			temp = Byte.parseByte(sb.toString(), 2);// 将字符串形式二进制转换成字节形式二进制数
			if (fl)
				bb[i] = (byte) (0 - temp);
			else
				bb[i] = temp;
			// System.out.println(bb[i]);
			sb = new StringBuffer();
			j = j + 7;// 取下一个7位
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
			return "计算失败";
	}
}