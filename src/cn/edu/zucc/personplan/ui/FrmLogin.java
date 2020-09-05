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

//登录界面
public class FrmLogin extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();//创建了一个对象，类似输入对象“input”；用于创建按钮及标签(工作窗格)
	private JButton btnRegister = new JButton("注册");
	private JButton btnLogin = new JButton("登陆");
	private JButton btnCancel = new JButton("退出");//设置按钮
	private JButton btnTest = new JButton("测试");
	
	private JLabel labelUser = new JLabel("用户：");
	private JLabel labelPwd = new JLabel("密码：");//设置标签
	private JTextField edtUserId = new JTextField(20);
	private JPasswordField edtPwd = new JPasswordField(20);//空白输入框及输入长度

	public FrmLogin(Frame f, String s, boolean b) {//frame=框架
		super(f, s, b);//指向父类(JDialog的构造方法)，该语句构造了一个登录框(?)对象；通过super可以使用父类的方法和属性
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));//流程布局(?
		toolBar.add(this.btnRegister);
		toolBar.add(btnLogin);
		toolBar.add(btnCancel);//添加按钮(不设置功能，顺序即界面中按钮顺序
		toolBar.add(btnTest);
		
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelUser);
		workPane.add(edtUserId);
		workPane.add(labelPwd);
		workPane.add(edtPwd);//设置工作窗口（标签和空白输入框
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(320, 140);//登录界面初始尺寸(默认尺寸
		// 屏幕居中显示
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);//设置登录框初始位置(位于屏幕中央

		this.validate();//?
		
		//add Action Listener 添加操作监听器
		btnTest.addActionListener(this);
		btnLogin.addActionListener(this);//登录按钮
		btnCancel.addActionListener(this);//退出按钮
		this.btnRegister.addActionListener(this);//注册按钮
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});//?
	}

	@Override//重写
	public void actionPerformed(ActionEvent e) {//执行的操作
		if (e.getSource() == this.btnLogin) {//登录按钮
			String userid=this.edtUserId.getText();
			String pwd=new String(this.edtPwd.getPassword());
			try {
				BeanUser.currentLoginUser= PersonPlanUtil.userManager.login(userid, pwd);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			this.setVisible(false);
			
		} else if (e.getSource() == this.btnCancel) {//退出按钮
			System.exit(0);
		} else if(e.getSource()==this.btnRegister){//注册按钮
			FrmRegister dlg=new FrmRegister(this,"注册",true);
			dlg.setVisible(true);
		} else if(e.getSource() == this.btnTest) {
			System.exit(0);
		}
	}//设置按钮功能

}
