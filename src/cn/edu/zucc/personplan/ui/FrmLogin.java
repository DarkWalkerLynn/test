package cn.edu.zucc.personplan.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import cn.edu.zucc.personplan.PersonPlanUtil;
import cn.edu.zucc.personplan.model.BeanUser;
import cn.edu.zucc.personplan.util.BaseException;

//��¼����
public class FrmLogin extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();//������һ�����������������input�������ڴ�����ť����ǩ(��������)
	private JButton btnRegister = new JButton("ע��");
	private JButton btnLogin = new JButton("��½");
	private JButton btnCancel = new JButton("�˳�");//���ð�ť
	private JButton btnTest = new JButton("����");
	
	private JLabel labelUser = new JLabel("�û���");
	private JLabel labelPwd = new JLabel("���룺");//���ñ�ǩ
	private JTextField edtUserId = new JTextField(20);
	private JPasswordField edtPwd = new JPasswordField(20);//�հ���������볤��

	public FrmLogin(Frame f, String s, boolean b) {//frame=���
		super(f, s, b);//ָ����(JDialog�Ĺ��췽��)������乹����һ����¼��(?)����ͨ��super����ʹ�ø���ķ���������
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));//���̲���(?
		toolBar.add(this.btnRegister);
		toolBar.add(btnLogin);
		toolBar.add(btnCancel);//��Ӱ�ť(�����ù��ܣ�˳�򼴽����а�ť˳��
		toolBar.add(btnTest);
		
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelUser);
		workPane.add(edtUserId);
		workPane.add(labelPwd);
		workPane.add(edtPwd);//���ù������ڣ���ǩ�Ϳհ������
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(320, 140);//��¼�����ʼ�ߴ�(Ĭ�ϳߴ�
		// ��Ļ������ʾ
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);//���õ�¼���ʼλ��(λ����Ļ����

		this.validate();//?
		
		//add Action Listener ��Ӳ���������
		btnTest.addActionListener(this);
		btnLogin.addActionListener(this);//��¼��ť
		btnCancel.addActionListener(this);//�˳���ť
		this.btnRegister.addActionListener(this);//ע�ᰴť
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});//?
	}

	@Override//��д
	public void actionPerformed(ActionEvent e) {//ִ�еĲ���
		if (e.getSource() == this.btnLogin) {//��¼��ť
			String userid=this.edtUserId.getText();
			String pwd=new String(this.edtPwd.getPassword());
			try {
				BeanUser.currentLoginUser= PersonPlanUtil.userManager.login(userid, pwd);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
				return;
			}
			this.setVisible(false);
			
		} else if (e.getSource() == this.btnCancel) {//�˳���ť
			System.exit(0);
		} else if(e.getSource()==this.btnRegister){//ע�ᰴť
			FrmRegister dlg=new FrmRegister(this,"ע��",true);
			dlg.setVisible(true);
		} else if(e.getSource() == this.btnTest) {
			System.exit(0);
		}
	}//���ð�ť����

}
