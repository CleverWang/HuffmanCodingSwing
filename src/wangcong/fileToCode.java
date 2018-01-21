package wangcong;

public class fileToCode {
	String hfcode;// 保存源文件经过哈夫曼编码后的编码

	// String rate;// 压缩比

	public fileToCode(readFile sourcefile, coding codes, eleAndfreq elements) {
		String source = new String();
		StringBuffer temp = new StringBuffer();// 暂存编码
		this.hfcode = new String();
		source = sourcefile.content;
		for (int i = 0; i < source.length(); i++) {// 对源文件中的每个字符，根据coding类中已编好的哈夫曼编码，写入hfcode中，形成整体文件的编码
			for (int j = 0; j < elements.num; j++)
				if (source.charAt(i) == elements.ele[j])
					temp.append(codes.huffmanCode[j]);
		}
		hfcode = temp.toString();

		/*
		 * int m = elements.num; int k;// 用等长编码需要的位数 for (k = 1; Math.pow(2.0,
		 * k) < m; k++) { } double orig = k * source.length();// 用等长编码需要的总位数
		 * this.rate = Double.toString(this.hfcode.length() / orig * 100) + '%';
		 */
	}

	/*
	 * String Rate() { return this.rate; }
	 */
}