package wangcong;

public class codeToFile {
	String filecontent;// 保存译码后的文件
	eleAndfreq ef;
	huffmanTree htree;

	public codeToFile(readFile afile, readFile tfile) {// 将哈夫曼编码后的文件转换成源文件
		generateHftree(tfile);
		byte b[] = afile.bb;// 暂存读入的二进制字节文件
		StringBuffer tem = new StringBuffer();
		int u;
		for (u = 0; u < b.length - 1; u++) {
			tem.append(byteToBinStr(b[u]));
		}// 除最后一个字节外，其余的调用byteToBinStr方法转换成字符串形式二进制
		tem.append(lastToBinStr(b[u]));// 最后一个字节调用lastToBinStr方法转换成字符串形式二进制
		String file = tem.toString();
		StringBuffer sb = new StringBuffer();// 暂存译码文件
		char temp;
		HtNode p = htree.ht[htree.root];// p初始为树根
		HtNode q = p;
		for (int i = 0; i < file.length(); i++) {// 从树根开始向下
			temp = file.charAt(i);
			q = p;// 保存父结点
			if (temp == '0')// 如果是0，进入左子树
				p = htree.ht[p.llink];
			else
				p = htree.ht[p.rlink];// 如果是1，进入右子树
			if (p.llink == -1 && p.rlink == -1) {// 到达叶子节点，再找到对应原字符，加入filecontent中
				if (temp == '0')
					sb.append(ef.ele[q.llink]);
				else
					sb.append(ef.ele[q.rlink]);
				p = htree.ht[htree.root];// 找到一个原字符后，令p为根结点，开始找下一个
			}
		}
		this.filecontent = sb.toString();
	}

	void generateHftree(readFile file) {
		int k = 0;
		while (file.content.charAt(k) != '|')
			k++;
		String alldata = file.content.substring(0, k);
		String allchar = file.content.substring(k + 1, file.content.length());
		// System.out.println(alldata);
		// System.out.println(allchar);
		String all[] = alldata.split(",");
		int m = (all.length + 1) / 2;
		this.ef = new eleAndfreq(m);
		this.htree = new huffmanTree(m);
		int i, j;
		for (i = 0; i < 2 * m - 1; i++) {
			String data[] = all[i].split(" ");
			htree.ht[i].parent = Integer.parseInt(data[0]);
			// System.out.print(htree.ht[i].parent);
			htree.ht[i].llink = Integer.parseInt(data[1]);
			// System.out.print(htree.ht[i].llink);
			htree.ht[i].rlink = Integer.parseInt(data[2]);
			// System.out.print(htree.ht[i].rlink);
		}
		char element[] = allchar.toCharArray();
		for (j = 0; j < element.length; j++) {
			ef.ele[j] = element[j];
			// System.out.println(element[j]);
		}
	}

	/*
	 * static void generateHftree(String content){ int k=0;
	 * while(content.charAt(k)!='|') k++; String alldata=content.substring(0,
	 * k); String allchar=content.substring(k+1, content.length());
	 * System.out.println(alldata); System.out.println(allchar); String
	 * all[]=alldata.split(","); int m=(all.length+1)/2; //this.ef=new
	 * eleAndfreq(m); //this.htree=new huffmanTree(m); int i,j;
	 * for(i=0;i<2*m-1;i++){ String data[]=all[i].split(" ");
	 * //htree.ht[i].parent= System.out.print(Integer.parseInt(data[0]));
	 * //htree.ht[i].llink= System.out.print(Integer.parseInt(data[1]));
	 * //htree.ht[i].rlink= System.out.println(Integer.parseInt(data[2])); }
	 * char element[]=allchar.toCharArray(); for(j=0;j<element.length;j++)
	 * //ef.ele[j]= System.out.print(element[j]); }
	 */

	String byteToBinStr(byte b) {// 将除最后一个字节外的其余字节转换成字符串形式二进制
		StringBuffer tem = new StringBuffer();
		while (b / 2 != 0) {// “除基取余”法求二进制
			tem.append(b % 2);
			b = (byte) (b / 2);
		}
		tem.append(b % 2);
		if (tem.length() != 7) {
			int numb = tem.length();
			for (int f = 1; f <= 7 - numb; f++)
				tem.append('0');
		}
		return tem.reverse().toString();// 需要转置
	}

	String lastToBinStr(byte b) {// 将最后一个字节（补码形式）转换成字符串形式二进制
		boolean fl = false;
		if (b < 0) {// 最后一个是负数
			fl = true;
			b = (byte) (0 - b);
		}
		StringBuffer tem = new StringBuffer();
		while (b / 2 != 0) {// “除基取余”法求二进制
			tem.append(b % 2);
			b = (byte) (b / 2);
		}
		tem.append(b % 2);
		if (fl) {// 将负数补码转换成原码
			int first1 = 0;
			for (; first1 < tem.length(); first1++)
				if (tem.charAt(first1) == '1')// 找到最后一个1
					break;
			for (int g = first1 + 1; g < tem.length(); g++)
				if (tem.charAt(g) == '1')// 是1转换成0，是0转换成1
					tem.setCharAt(g, '0');
				else
					tem.setCharAt(g, '1');
		}
		return tem.reverse().toString();// 需要转置
	}
	/*
	 * public static void main(String args[]){
	 * generateHftree("4 -1 -1,4 -1 -1,5 -1 -1,5 -1 -1,6 0 1,6 2 3,-1 4 5|我叫王聪"
	 * ); }
	 */
}