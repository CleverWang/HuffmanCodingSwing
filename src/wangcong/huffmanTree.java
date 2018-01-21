package wangcong;

public class huffmanTree {// 哈夫曼树
	int m;// 哈夫曼树外外部结点个数
	int root;// 哈夫曼树的根结点下标
	HtNode ht[];// 哈夫曼数组

	/*
	 * void printHuffman() { int i; for (i = 0; i < this.m * 2 - 1; i++) {
	 * System.out.println(this.ht[i].ww + " " + this.ht[i].parent + " " +
	 * this.ht[i].llink + " " + this.ht[i].rlink); } }
	 */

	public huffmanTree(eleAndfreq ele) {
		int m = ele.num;
		this.ht = new HtNode[2 * m - 1];
		int i, j, x1, x2, m1, m2;
		this.m = ele.num;
		for (i = 0; i < 2 * m - 1; i++) {// 置哈夫曼数组初始状态
			this.ht[i] = new HtNode();
			this.ht[i].llink = this.ht[i].rlink = this.ht[i].parent = -1;
			if (i < m)
				this.ht[i].ww = ele.freq[i];// 权值为源文件中字符出现的频率
			else
				this.ht[i].ww = -1;
		}
		for (i = 0; i < m - 1; i++) {// 每循环一次构造一个内部结点
			m1 = m2 = 9999;// 用9999代替∞
			x1 = x2 = -1;
			for (j = 0; j < m + i; j++)
				// 找两个最小权的无父结点的结点下标
				if (this.ht[j].ww < m1 && this.ht[j].parent == -1) {
					m2 = m1;
					x2 = x1;
					m1 = this.ht[j].ww;
					x1 = j;// x1只存放最小权的无父结点的结点下标
				} else if (this.ht[j].ww < m2 && this.ht[j].parent == -1) {
					m2 = this.ht[j].ww;
					x2 = j;// x2只存放次小权的无父结点的结点下标
				}
			this.ht[x1].parent = this.ht[x2].parent = m + i;
			this.ht[m + i].ww = m1 + m2;
			this.ht[m + i].llink = x1;
			this.ht[m + i].rlink = x2;// 构造内部结点
		}
		this.root = 2 * m - 2;
	}

	public huffmanTree(int m) {
		this.m = m;
		this.root = 2 * m - 2;
		this.ht = new HtNode[2 * m - 1];
		HtNode temp;
		for (int i = 0; i < 2 * m - 1; i++) {
			temp = new HtNode();
			ht[i] = temp;
		}
	}

	/*
	 * public static void main(String args[]) { int w[] = { 1, 2, 3, 4 };
	 * huffmanTree ht=new huffmanTree(4, w); ht.printHuffman(); }
	 */
}