package wangcong;

public class codeToFile {
	String filecontent;// �����������ļ�
	eleAndfreq ef;
	huffmanTree htree;

	public codeToFile(readFile afile, readFile tfile) {// ���������������ļ�ת����Դ�ļ�
		generateHftree(tfile);
		byte b[] = afile.bb;// �ݴ����Ķ������ֽ��ļ�
		StringBuffer tem = new StringBuffer();
		int u;
		for (u = 0; u < b.length - 1; u++) {
			tem.append(byteToBinStr(b[u]));
		}// �����һ���ֽ��⣬����ĵ���byteToBinStr����ת�����ַ�����ʽ������
		tem.append(lastToBinStr(b[u]));// ���һ���ֽڵ���lastToBinStr����ת�����ַ�����ʽ������
		String file = tem.toString();
		StringBuffer sb = new StringBuffer();// �ݴ������ļ�
		char temp;
		HtNode p = htree.ht[htree.root];// p��ʼΪ����
		HtNode q = p;
		for (int i = 0; i < file.length(); i++) {// ��������ʼ����
			temp = file.charAt(i);
			q = p;// ���游���
			if (temp == '0')// �����0������������
				p = htree.ht[p.llink];
			else
				p = htree.ht[p.rlink];// �����1������������
			if (p.llink == -1 && p.rlink == -1) {// ����Ҷ�ӽڵ㣬���ҵ���Ӧԭ�ַ�������filecontent��
				if (temp == '0')
					sb.append(ef.ele[q.llink]);
				else
					sb.append(ef.ele[q.rlink]);
				p = htree.ht[htree.root];// �ҵ�һ��ԭ�ַ�����pΪ����㣬��ʼ����һ��
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

	String byteToBinStr(byte b) {// �������һ���ֽ���������ֽ�ת�����ַ�����ʽ������
		StringBuffer tem = new StringBuffer();
		while (b / 2 != 0) {// ������ȡ�ࡱ���������
			tem.append(b % 2);
			b = (byte) (b / 2);
		}
		tem.append(b % 2);
		if (tem.length() != 7) {
			int numb = tem.length();
			for (int f = 1; f <= 7 - numb; f++)
				tem.append('0');
		}
		return tem.reverse().toString();// ��Ҫת��
	}

	String lastToBinStr(byte b) {// �����һ���ֽڣ�������ʽ��ת�����ַ�����ʽ������
		boolean fl = false;
		if (b < 0) {// ���һ���Ǹ���
			fl = true;
			b = (byte) (0 - b);
		}
		StringBuffer tem = new StringBuffer();
		while (b / 2 != 0) {// ������ȡ�ࡱ���������
			tem.append(b % 2);
			b = (byte) (b / 2);
		}
		tem.append(b % 2);
		if (fl) {// ����������ת����ԭ��
			int first1 = 0;
			for (; first1 < tem.length(); first1++)
				if (tem.charAt(first1) == '1')// �ҵ����һ��1
					break;
			for (int g = first1 + 1; g < tem.length(); g++)
				if (tem.charAt(g) == '1')// ��1ת����0����0ת����1
					tem.setCharAt(g, '0');
				else
					tem.setCharAt(g, '1');
		}
		return tem.reverse().toString();// ��Ҫת��
	}
	/*
	 * public static void main(String args[]){
	 * generateHftree("4 -1 -1,4 -1 -1,5 -1 -1,5 -1 -1,6 0 1,6 2 3,-1 4 5|�ҽ�����"
	 * ); }
	 */
}