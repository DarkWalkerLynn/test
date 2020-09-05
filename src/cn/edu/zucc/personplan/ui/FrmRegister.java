package cn.edu.zucc.personplan.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import cn.edu.zucc.personplan.PersonPlanUtil;
import cn.edu.zucc.personplan.model.BeanUser;
import cn.edu.zucc.personplan.util.BaseException;
//注册界面
public class FrmRegister extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("注册");
	private Button btnCancel = new Button("取消");//设置按钮
	
	private JLabel labelUser = new JLabel("用户名称：");
	private JLabel labelPwd = new JLabel("输入密码：");
	private JLabel labelPwd2 = new JLabel("确认密码：");
	private JTextField edtUserId = new JTextField(20);
	private JPasswordField edtPwd = new JPasswordField(20);
	private JPasswordField edtPwd2 = new JPasswordField(20);//设置标签及工作窗格
	public FrmRegister(Dialog f, String s, boolean b) {
		super(f, s, b);//构造对象，作用未知
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));//设置布局管理器(?
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);//添加按钮
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelUser);
		workPane.add(edtUserId);
		workPane.add(labelPwd);
		workPane.add(edtPwd);
		workPane.add(labelPwd2);
		workPane.add(edtPwd2);//添加工作窗格
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(320, 180);//设置注册界面初始尺寸
		this.btnCancel.addActionListener(this);
		this.btnOk.addActionListener(this);//为按钮添加操作监听器
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);//新加；将注册框居中显示
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnCancel)//getSource 监测到有动作，进行对象判断
			this.setVisible(false);//若按钮为取消，则设置注册框为不可见
		else if(e.getSource()==this.btnOk){
			String userid=this.edtUserId.getText();
			String pwd1=new String(this.edtPwd.getPassword());
			String pwd2=new String(this.edtPwd2.getPassword());
			try {
				BeanUser user=PersonPlanUtil.userManager.reg(userid,pwd1,pwd2);//调用beanuser进行注册
				this.setVisible(false);//注册成功，设置注册框为不可见
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
				return;//失败后抛出异常，显示消息对话框，显示注册错误
			}
			
		}//若按钮为注册，则将已输入的字符串进行保存
			
		
	}


}
