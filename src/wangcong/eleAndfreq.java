package wangcong;

public class eleAndfreq {
	char ele[];// ��¼Դ�ļ��г��ֵĸ��ֲ�ͬ���ַ�
	int freq[];// ��¼Դ�ļ��г��ֵĸ��ֲ�ͬ���ַ��ĳ��ֵ�Ƶ��
	int num;// ��¼Դ�ļ��г��ֵĸ��ֲ�ͬ���ַ��ĸ���

	public eleAndfreq(readFile afile) {
		char file[] = afile.content.toCharArray();// �ݴ�Դ�ļ�
		int m = file.length;// Դ�ļ��ĳ���
		ele = new char[m + 1];
		freq = new int[m + 1];
		char temp;
		int x = 0;// ���治ͬ�ַ��ĸ���
		for (int i = 0; i < m; i++) {// ��������Դ�ļ����ҳ���ͬ���ַ�
			if (file[i] != '\0') {// ���������������ele[]��
				temp = file[i];
				ele[x] = temp;
				freq[x] = 1;
				x++;
				for (int j = i + 1; j < m; j++)
					// ���Ѿ�ȷ�����ַ�����Դ�ļ�����֮��ͬ���ַ���������
					if (temp == file[j]) {
						freq[i]++;// ����һ�Σ�Ƶ�ʼ�һ
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