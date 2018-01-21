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

    class txt1Action implements ActionListener {// ʵ��txt1�Ķ����¼�������

        public void actionPerformed(ActionEvent e) {
            sourcename = txt1.getText();// ��ȡ�����Դ�ļ���ַ��Ϊ����·��
            txt3.append("\n����ĳ�ʼ�ļ���ַ�ǣ�" + sourcename);
            txt3.setCaretPosition(txt3.getText().length());
            System.out.println(sourcename);
        }
    }

    class txt2Action implements ActionListener {// ʵ��txt2�Ķ����¼�������

        public void actionPerformed(ActionEvent e) {
            targetname = txt2.getText();// ��ȡ�����Ŀ���ļ���ַ��Ϊ����·��
            txt3.append("\n�����Ŀ���ļ���ַ�ǣ�" + targetname);
            System.out.println(targetname);
        }
    }

    class txt5Action implements ActionListener {// ʵ��txt2�Ķ����¼�������

        public void actionPerformed(ActionEvent e) {
            treename = txt5.getText();// ��ȡ����Ĺ���������ַ��Ϊ����·��
            txt3.append("\n����Ĺ���������ַ�ǣ�" + treename);
            System.out.println(treename);
        }
    }

    class radioAction implements ItemListener {// ʵ��radio[]�Ĵ������¼�������

        public void itemStateChanged(ItemEvent e) {
            if (!radio[0].isSelected()) {// �ж��ǡ�ѹ����ѡ�л��ǡ���ѹ��ѡ��
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

    class ButtonAction implements ActionListener {// ʵ��button�Ķ����¼�������

        public void actionPerformed(ActionEvent e) {
            if (flag) {// �����ѹ����ѡ�У�ִ��ѹ������
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
                    txt3.append("\n�ļ�" + sourcename + "�������벢ѹ������" + targetname + "\n���벢ѹ���ɹ���\n���������ļ������ɹ���\n��ʱ��" + t1
                            + "ms");
                    txt3.append("\n������ļ�����Ϊ��\n" + afile.content);
                    txt3.append("\n������ļ��в�ͬ���ַ�����ʹ�ô���Ϊ��\n");
                    for (int i = 0; i < a.num; i++) {
                        txt3.append(a.ele[i] + ":" + a.freq[i] + "  ");
                    }
                    txt3.append("\n��ͬ���ַ��������������Ϊ��\n");
                    for (int i = 0; i < a.num; i++) {
                        txt3.append(a.ele[i] + ":" + codes.huffmanCode[i] + "  ");
                    }
                    txt3.append("\n�����ļ��Ĺ���������Ϊ��\n" + target.hfcode + "\n");
                } catch (Exception exp) {
                    txt3.append("\nѹ��ʧ�ܣ�������ļ������ڻ��ļ���ַ����ȷ��Ŀ���ļ�\n����ʧ�ܣ������ԡ�\n");
                }
            } else {// ִ�н�ѹ����
                try {
                    Date d1 = new Date();
                    afile = new readFile(sourcename, false);
                    tfile = new readFile(treename, true);
                    // System.out.println(tfile.content);
                    ctf = new codeToFile(afile, tfile);
                    wf = new writeFile(ctf, targetname);
                    Date d2 = new Date();
                    long t2 = d2.getTime() - d1.getTime();
                    txt3.append("\n���������ļ�����ɹ���\n�ļ�" + sourcename + "������ѹ�����뵽��" + targetname + "\n��ѹ������ɹ���\n��ʱ��" + t2
                            + "ms");
                    txt3.append("\n������Ϊ��\n" + ctf.filecontent + "\n");
                } catch (Exception exp) {
                    txt3.append("\n��ѹʧ�ܣ�������ļ������ڻ��ļ���ַ����ȷ��Ŀ���ļ�\n����ʧ�ܣ������ԡ�\n");
                }
            }
        }
    }

    public client() {
        super("������ѹ�����ѹ���");
        Container c = getContentPane();
        c.setLayout(new FlowLayout(FlowLayout.LEFT));
        label1 = new JLabel("��ʼ�ļ���ַ:");
        label2 = new JLabel("Ŀ���ļ���ַ:");
        label8 = new JLabel("����������ַ:");
        label3 = new JLabel("**********************");
        label4 = new JLabel("*********************");
        label6 = new JLabel("***********************Author:Wang Cong**********************");
        label7 = new JLabel("�ļ���ѹ����:");
        button = new JButton("ȷ��");
        button.addActionListener(new ButtonAction());// ע��button�ļ�����
        radio = new JRadioButton[2];
        radio[0] = new JRadioButton("ѹ��");
        radio[0].addItemListener(new radioAction());// ע��radio[]�ļ�����
        radio[1] = new JRadioButton("��ѹ");
        txt1 = new JTextField(15);
        txt1.addActionListener(new txt1Action());// ע��txt1�ļ�����
        txt2 = new JTextField(15);
        txt2.addActionListener(new txt2Action());// ע��txt2�ļ�����
        txt3 = new JTextArea(
                "��ӭʹ�ã�\n����ÿ��ѹ��ʱ������Ӧ���������ļ���ÿ�ν�ѹʱ������Ӧ���������ļ���\n���е�ַ��ÿ��������Ϻ��밴�س���\n��ַ���������·����\n��ַ������Ϻ󰴡�ȷ����ִ�в�����\n", 28,
                30);// �ı������ʼ��
        txt3.setEditable(false);
        txt3.setLineWrap(true); // �����Զ����й���
        txt3.setWrapStyleWord(true); // ������в����ֹ���
        txt4 = new JTextField(15);
        txt4.setEditable(false);
        txt5 = new JTextField("E:\\ht\\tree.tree", 15);
        txt5.addActionListener(new txt5Action());// ע��txt5�ļ�����
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