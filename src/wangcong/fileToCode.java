package wangcong;

public class fileToCode {
	String hfcode;// ����Դ�ļ����������������ı���

	// String rate;// ѹ����

	public fileToCode(readFile sourcefile, coding codes, eleAndfreq elements) {
		String source = new String();
		StringBuffer temp = new StringBuffer();// �ݴ����
		this.hfcode = new String();
		source = sourcefile.content;
		for (int i = 0; i < source.length(); i++) {// ��Դ�ļ��е�ÿ���ַ�������coding�����ѱ�õĹ��������룬д��hfcode�У��γ������ļ��ı���
			for (int j = 0; j < elements.num; j++)
				if (source.charAt(i) == elements.ele[j])
					temp.append(codes.huffmanCode[j]);
		}
		hfcode = temp.toString();

		/*
		 * int m = elements.num; int k;// �õȳ�������Ҫ��λ�� for (k = 1; Math.pow(2.0,
		 * k) < m; k++) { } double orig = k * source.length();// �õȳ�������Ҫ����λ��
		 * this.rate = Double.toString(this.hfcode.length() / orig * 100) + '%';
		 */
	}

	/*
	 * String Rate() { return this.rate; }
	 */
}