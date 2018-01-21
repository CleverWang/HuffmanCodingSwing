package wangcong;

public class coding {
	String huffmanCode[];// ���ڱ�����Դ�ļ��г��ֵ��ַ��Ĺ���������

	public coding(huffmanTree tree) {
		this.huffmanCode = new String[tree.m];// ��ʼ������
		for (int i = 0; i < tree.m; i++) {// ��Ҷ�ӽ���򸸽�㣬���¶��ϣ�ѭ��m����ȷ��m���ַ��Ĺ���������
			StringBuffer temp = new StringBuffer();// �����ݴ����
			HtNode p = tree.ht[i];// ���浱ǰ���
			HtNode q = tree.ht[p.parent];// ���游���
			int pos = i;
			while (q.parent != -1) {
				if (q.llink == pos)// �����������temp�м���0
					temp.append('0');
				else
					temp.append('1');// ���Ҷ�������temp�м���1
				pos = p.parent;// ���浱�½��ĸ�����ڹ����������е��±�
				p = q;
				q = tree.ht[q.parent];// �򸸽�㿿��
			}
			if (q.llink == pos)
				temp.append('0');
			else
				temp.append('1');
			temp = temp.reverse();// �����Ǵ������ϣ�������Ҫת�ò������յĹ���������
			this.huffmanCode[i] = temp.toString();
		}
	}

	/*
	 * public void printCode() { for (int i = 0; i < this.huffmanCode.length;
	 * i++) System.out.println(this.huffmanCode[i]); }
	 */
}