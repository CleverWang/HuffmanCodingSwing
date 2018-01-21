package wangcong;

public class coding {
	String huffmanCode[];// 用于保存在源文件中出现的字符的哈夫曼编码

	public coding(huffmanTree tree) {
		this.huffmanCode = new String[tree.m];// 初始化数组
		for (int i = 0; i < tree.m; i++) {// 从叶子结点向父结点，自下而上，循环m次来确定m个字符的哈夫曼编码
			StringBuffer temp = new StringBuffer();// 用于暂存编码
			HtNode p = tree.ht[i];// 保存当前结点
			HtNode q = tree.ht[p.parent];// 保存父结点
			int pos = i;
			while (q.parent != -1) {
				if (q.llink == pos)// 是左儿子则往temp中加入0
					temp.append('0');
				else
					temp.append('1');// 是右儿子则往temp中加入1
				pos = p.parent;// 保存当下结点的父结点在哈夫曼数组中的下标
				p = q;
				q = tree.ht[q.parent];// 向父结点靠近
			}
			if (q.llink == pos)
				temp.append('0');
			else
				temp.append('1');
			temp = temp.reverse();// 由于是从下往上，所以需要转置才是最终的哈夫曼编码
			this.huffmanCode[i] = temp.toString();
		}
	}

	/*
	 * public void printCode() { for (int i = 0; i < this.huffmanCode.length;
	 * i++) System.out.println(this.huffmanCode[i]); }
	 */
}