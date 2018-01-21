package wangcong;

public class eleAndfreq {
	char ele[];// 记录源文件中出现的各种不同的字符
	int freq[];// 记录源文件中出现的各种不同的字符的出现的频率
	int num;// 记录源文件中出现的各种不同的字符的个数

	public eleAndfreq(readFile afile) {
		char file[] = afile.content.toCharArray();// 暂存源文件
		int m = file.length;// 源文件的长度
		ele = new char[m + 1];
		freq = new int[m + 1];
		char temp;
		int x = 0;// 保存不同字符的个数
		for (int i = 0; i < m; i++) {// 遍历整个源文件，找出不同的字符
			if (file[i] != '\0') {// 不是特殊标记则加入ele[]中
				temp = file[i];
				ele[x] = temp;
				freq[x] = 1;
				x++;
				for (int j = i + 1; j < m; j++)
					// 对已经确定的字符，对源文件中与之相同的字符置特殊标记
					if (temp == file[j]) {
						freq[i]++;// 出现一次，频率加一
						file[j] = '\0';
					}
			} else
				continue;
		}
		num = x;
		ele[x] = '\0';
		freq[x] = -1;
	}

	public eleAndfreq(int m) {
		this.ele = new char[m];
		this.freq = new int[m];
		this.num = m;
	}

	/*
	 * public static void main(String args[]) { eleAndfreq a = new
	 * eleAndfreq("asfb7\n23478\n"); for (int i = 0; a.ele[i] != '\0'; i++)
	 * System.out.print(a.ele[i] + " "); System.out.println(); for (int j = 0;
	 * a.freq[j] != -1; j++) System.out.print(a.freq[j] + " ");
	 * System.out.println(); System.out.println(a.num); }
	 */
}