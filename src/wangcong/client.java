package wangcong;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.UnsupportedEncodingException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

import java.util.Date;

public class client extends JFrame {
    JTextField txt1, txt2, txt4, txt5;
    JTextArea txt3;
    JButton button;
    JLabel label1, label2, label3, label4, label5, label6, label7, label8;
    JRadioButton radio[];
    JScrollPane sc;
    String sourcename, targetname, rate, treename;
    boolean flag = true;
    readFile afile, tfile;
    eleAndfreq a;
    huffmanTree ht;
    coding codes;
    fileToCode target;
    codeToFile ctf;
    writeFile wf, wt;

    class txt1Action implements ActionListener {// 实现txt1的动作事件监听器

        public void actionPerformed(ActionEvent e) {
            sourcename = txt1.getText();// 获取输入的源文件地址，为绝对路径
            txt3.append("\n输入的初始文件地址是：" + sourcename);
            txt3.setCaretPosition(txt3.getText().length());
            System.out.println(sourcename);
        }
    }

    class txt2Action implements ActionListener {// 实现txt2的动作事件监听器

        public void actionPerformed(ActionEvent e) {
            targetname = txt2.getText();// 获取输入的目标文件地址，为绝对路径
            txt3.append("\n输入的目标文件地址是：" + targetname);
            System.out.println(targetname);
        }
    }

    class txt5Action implements ActionListener {// 实现txt2的动作事件监听器

        public void actionPerformed(ActionEvent e) {
            treename = txt5.getText();// 获取输入的哈夫曼树地址，为绝对路径
            txt3.append("\n输入的哈夫曼树地址是：" + treename);
            System.out.println(treename);
        }
    }

    class radioAction implements ItemListener {// 实现radio[]的触发项事件监听器

        public void itemStateChanged(ItemEvent e) {
            if (!radio[0].isSelected()) {// 判断是“压缩”选中还是“解压”选中
                flag = false;
                txt1.setText("E:\\ht\\codes.code");
                txt2.setText("E:\\ht\\decodes.txt");
            } else {
                flag = true;
                txt1.setText("E:\\ht\\test.txt");
                txt2.setText("E:\\ht\\codes.code");
            }
        }
    }

    class ButtonAction implements ActionListener {// 实现button的动作事件监听器

        public void actionPerformed(ActionEvent e) {
            if (flag) {// 如果“压缩”选中，执行压缩操作
                try {
                    Date d1 = new Date();
                    afile = new readFile(sourcename, true);
                    a = new eleAndfreq(afile);
                    ht = new huffmanTree(a);
                    codes = new coding(ht);
                    target = new fileToCode(afile, codes, a);
                    wt = new writeFile(ht, a, treename);
                    wf = new writeFile(target, targetname);
                    rate = wf.rate(sourcename, targetname);
                    Date d2 = new Date();
                    long t1 = d2.getTime() - d1.getTime();
                    txt4.setText(rate);
                    txt3.append("\n文件" + sourcename + "将被编码并压缩到：" + targetname + "\n编码并压缩成功！\n哈夫曼树文件导出成功！\n用时：" + t1
                            + "ms");
                    txt3.append("\n读入的文件内容为：\n" + afile.content);
                    txt3.append("\n读入的文件中不同的字符及其使用次数为：\n");
                    for (int i = 0; i < a.num; i++) {
                        txt3.append(a.ele[i] + ":" + a.freq[i] + "  ");
                    }
                    txt3.append("\n不同的字符及其哈夫曼编码为：\n");
                    for (int i = 0; i < a.num; i++) {
                        txt3.append(a.ele[i] + ":" + codes.huffmanCode[i] + "  ");
                    }
                    txt3.append("\n整个文件的哈夫曼编码为：\n" + target.hfcode + "\n");
                } catch (Exception exp) {
                    txt3.append("\n压缩失败，输入的文件不存在或文件地址不正确或目标文件\n创建失败！请重试。\n");
                }
            } else {// 执行解压操作
                try {
                    Date d1 = new Date();
                    afile = new readFile(sourcename, false);
                    tfile = new readFile(treename, true);
                    // System.out.println(tfile.content);
                    ctf = new codeToFile(afile, tfile);
                    wf = new writeFile(ctf, targetname);
                    Date d2 = new Date();
                    long t2 = d2.getTime() - d1.getTime();
                    txt3.append("\n哈夫曼树文件导入成功！\n文件" + sourcename + "将被解压并译码到：" + targetname + "\n解压并译码成功！\n用时：" + t2
                            + "ms");
                    txt3.append("\n译码结果为：\n" + ctf.filecontent + "\n");
                } catch (Exception exp) {
                    txt3.append("\n解压失败，输入的文件不存在或文件地址不正确或目标文件\n创建失败！请重试。\n");
                }
            }
        }
    }

    public client() {
        super("哈夫曼压缩与解压软件");
        Container c = getContentPane();
        c.setLayout(new FlowLayout(FlowLayout.LEFT));
        label1 = new JLabel("初始文件地址:");
        label2 = new JLabel("目标文件地址:");
        label8 = new JLabel("哈夫曼树地址:");
        label3 = new JLabel("**********************");
        label4 = new JLabel("*********************");
        label6 = new JLabel("***********************Author:Wang Cong**********************");
        label7 = new JLabel("文件的压缩率:");
        button = new JButton("确认");
        button.addActionListener(new ButtonAction());// 注册button的监听器
        radio = new JRadioButton[2];
        radio[0] = new JRadioButton("压缩");
        radio[0].addItemListener(new radioAction());// 注册radio[]的监听器
        radio[1] = new JRadioButton("解压");
        txt1 = new JTextField(15);
        txt1.addActionListener(new txt1Action());// 注册txt1的监听器
        txt2 = new JTextField(15);
        txt2.addActionListener(new txt2Action());// 注册txt2的监听器
        txt3 = new JTextArea(
                "欢迎使用！\n请在每次压缩时导出相应哈夫曼树文件，每次解压时导入相应哈夫曼树文件。\n所有地址在每次输入完毕后请按回车！\n地址请输入绝对路径！\n地址输入完毕后按“确定“执行操作。\n", 28,
                30);// 文本区域初始化
        txt3.setEditable(false);
        txt3.setLineWrap(true); // 激活自动换行功能
        txt3.setWrapStyleWord(true); // 激活断行不断字功能
        txt4 = new JTextField(15);
        txt4.setEditable(false);
        txt5 = new JTextField("E:\\ht\\tree.tree", 15);
        txt5.addActionListener(new txt5Action());// 注册txt5的监听器
        // txt3.setBackground(Color.green);
        // txt3.setForeground(Color.white);
        sc = new JScrollPane(txt3);
        ButtonGroup bg = new ButtonGroup();
        c.add(label3);
        c.add(radio[0]);
        bg.add(radio[0]);
        c.add(radio[1]);
        bg.add(radio[1]);
        radio[0].setSelected(true);
        radio[1].setSelected(false);
        c.add(label4);
        c.add(label1);
        c.add(txt1);
        c.add(label2);
        c.add(txt2);
        c.add(label8);
        c.add(txt5);
        c.add(label7);
        c.add(txt4);
        c.add(button);
        c.add(sc);
        c.add(label6);
    }

    /*
     * static String byteToBinStr(byte b){ StringBuffer tem = new
     * StringBuffer(); while(b/2!=0){ tem.append(b%2); b=(byte) (b/2); }
     * tem.append(b%2); if(tem.length()!=7){ int numb=tem.length(); for(int
     * f=1;f<=7-numb;f++) tem.append('0');} return tem.reverse().toString(); }
     * static String lastToBinStr(byte b){ boolean fl=false; if(b<0){ fl=true;
     * b=(byte) (0-b); } StringBuffer tem = new StringBuffer(); while(b/2!=0){
     * tem.append(b%2); b=(byte) (b/2); } tem.append(b%2); if(fl){ int first1=0;
     * for(;first1<tem.length();first1++) if(tem.charAt(first1)=='1') break;
     * for(int g=first1+1;g<tem.length();g++) if(tem.charAt(g)=='1')
     * tem.setCharAt(g, '0'); else tem.setCharAt(g, '1');} return
     * tem.reverse().toString(); }
     */

    public static void main(String args[]) throws UnsupportedEncodingException {
        client app = new client();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setSize(360, 720);
        app.setResizable(false);
        app.setVisible(true);

        /*
         * String s = "1101110001100001111100110"; byte temp; int times =
         * s.length() / 7; int left = s.length() % 7; if (left != 0) times++;
         * byte[] bb; bb = new byte[times]; StringBuffer sb = new
         * StringBuffer(); int j = 0; boolean fl=false; for (int i = 0; i <
         * times; i++) { if (i == times - 1 && left != 0){
         * sb.append(s.substring(j, j + left)); if(sb.charAt(0)=='0'){ fl=true;
         * StringBuffer tem=sb.reverse(); int first1=0;
         * for(;first1<tem.length();first1++) if(tem.charAt(first1)=='1') break;
         * for(int g=first1+1;g<tem.length();g++){ if(tem.charAt(g)=='1')
         * tem.setCharAt(g, '0'); else tem.setCharAt(g, '1');} sb=tem.reverse();
         * } } else if(i==times-1){ sb.append(s.substring(j, j + left));
         * if(sb.charAt(0)=='0'){ fl=true; StringBuffer tem=sb.reverse(); int
         * first1=0; for(;first1<tem.length();first1++)
         * if(tem.charAt(first1)=='1') break; for(int
         * g=first1+1;g<tem.length();g++){ if(tem.charAt(g)=='1')
         * tem.setCharAt(g, '0'); else tem.setCharAt(g, '1');} sb=tem.reverse();
         * } } else sb.append(s.substring(j, j + 7)); System.out.println(sb);
         * temp = Byte.parseByte(sb.toString(), 2); if(fl) bb[i] = (byte)
         * (0-temp); else bb[i]=temp; System.out.println(bb[i]); sb = new
         * StringBuffer(); j = j + 7; } System.out.println(lastToBinStr(bb[3]));
         * /*byte ts=55; int tes=55%2; byte tse=(byte) (ts/2);
         * System.out.println(ts+" "+tes+" "+tse); /* String re=new
         * BigInteger(1, bb).toString(2); System.out.println(re); //byte
         * aa[]=s.getBytes(); //for(int h=0;h<aa.length;h++)
         * //System.out.print(aa[h]+" "); //String s="1111111"; byte
         * a=Byte.parseByte(s, 2); //System.out.println(a); /* String
         * s="00000010"; byte a=Byte.parseByte(s, 2); System.out.println(a);
         *
         * /*readFile afile = new readFile("D:\\test.txt"); afile.printFile();
         *
         * eleAndfreq a = new eleAndfreq(afile); for (int i = 0; i < a.num; i++)
         * System.out.print(a.ele[i] + " "); System.out.print('\n'); for (int j
         * = 0; j < a.num; j++) System.out.print(a.freq[j] + " ");
         * System.out.println(); System.out.print('\n');
         *
         * // int w[]={2,3,5,7,11,13,17,19,23,29,31,37,41}; huffmanTree ht = new
         * huffmanTree(a); // huffmanTree ht=new huffmanTree(13,w);
         * ht.printHuffman();
         *
         * coding codes = new coding(ht); codes.printCode();
         *
         * fileToCode target = new fileToCode(afile, codes, a); writeFile wf =
         * new writeFile(target, "D:\\codes.txt");
         * System.out.println(target.Rate());
         *
         * readFile re = new readFile("D:\\codes.txt"); //
         * System.out.println(re.content); codeToFile ctf = new codeToFile(re,
         * a, ht); writeFile wf1 = new writeFile(ctf, "D:\\decodes.txt");
         */
    }
}