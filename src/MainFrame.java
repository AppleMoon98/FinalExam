import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.TableRowSorter;

public class MainFrame extends MainProject{
	FrameSystem system = new FrameSystem();
	
	MainFrame(double[][] data){
		// 변수
		String Title = "기말프로젝트";
		String FildTitle[] = {"팀", "근무기간", "교육방법", "시험점수"};
		String[][] str_data;
		
		// 타이틀과 사이즈
		JFrame jf = new JFrame(Title);
		jf.setSize(system.x, system.y);
		jf.setResizable(false);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// 패널
		JPanel btp = new JPanel();
		JPanel tbp = new JPanel();
		jf.add(btp, BorderLayout.NORTH);
		jf.add(tbp, BorderLayout.CENTER);
		
		// '요약 출력' 버튼
		JButton bt01 = new JButton("요약 출력");
		ActionListener bt01eve = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SecondFrame(data);
			}
		};
		bt01.addActionListener(bt01eve);
		btp.add(bt01, BorderLayout.EAST);
		
		// Double to String, 테이블 만드려고 값 변환
		str_data = new String[data.length-1][4];
		for(int i = 0; i < data.length-1; i++) {
			if(data[i][0] == 1.0)
				str_data[i][0] = "1팀";
			else if(data[i][0] == 2.0)
				str_data[i][0] = "2팀";
			else if(data[i][0] == 3.0)
				str_data[i][0] = "3팀";
			
			str_data[i][1] = data[i][1] + "년";
			
			if(data[i][2] == 1.0)
				str_data[i][2] = "집체교육";
			else if(data[i][2] == 2.0)
				str_data[i][2] = "사이버교육";
			
			str_data[i][3] = (int)data[i][3] + "점";
		}
		
		// 테이블
		JTable table = new JTable(str_data, FildTitle) {
			// 내용 수정 금지
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.setAutoCreateRowSorter(true);
		TableRowSorter tablesorter = new TableRowSorter(table.getModel());
		table.setRowSorter(tablesorter);
		JScrollPane scrollpane = new JScrollPane(table);
		scrollpane.setPreferredSize(new Dimension(450, 200));
		tbp.add(scrollpane);
		//table.getColumnModel().getColumn(0).setPreferredWidth(40);
		
		// frame visible true
		jf.setVisible(true);
	}
}

// 요약출력
class SecondFrame{
	FrameSystem system = new FrameSystem();
	
	SecondFrame(double data[][]){
		// 기타
		P_Tam_Nunmul num = new P_Tam_Nunmul();
		double[] score = new double[data.length];
		
		// String to Double
		for(int i = 0; i < data.length; i++)
			score[i] = data[i][3];
		
		// 표기 형식
		DecimalFormat fo = new DecimalFormat("0.####");
		
		// 폼 설정
		JFrame jf = new JFrame("요약 분석");
		jf.setSize(200, 180);
		jf.setResizable(false);
		
		// 폼 이벤트
		jf.addWindowListener(new WindowAdapter() {
			public void windowColosing(WindowEvent e) {
				// 폼 종료
				jf.disable();
			}
		});
		
		// 패널 탭설정
		JTabbedPane jtp = new JTabbedPane(JTabbedPane.TOP);
		
		// 패널 추가 및 설정
		JPanel jp1 = new JPanel();
		JPanel jp2 = new JPanel();
		JPanel jp3 = new JPanel();
		
		jtp.add("1팀",jp1);
		jtp.add("2팀",jp2);
		jtp.add("3팀",jp3);

		JPanel jp1left = new JPanel();
		JPanel jp1right = new JPanel();
		JPanel jp2left = new JPanel();
		JPanel jp2right = new JPanel();
		JPanel jp3left = new JPanel();
		JPanel jp3right = new JPanel();
		
		jp1left.setLayout(new BoxLayout(jp1left, BoxLayout.Y_AXIS));
		jp1right.setLayout(new BoxLayout(jp1right, BoxLayout.Y_AXIS));
		jp2left.setLayout(new BoxLayout(jp2left, BoxLayout.Y_AXIS));
		jp2right.setLayout(new BoxLayout(jp2right, BoxLayout.Y_AXIS));
		jp3left.setLayout(new BoxLayout(jp3left, BoxLayout.Y_AXIS));
		jp3right.setLayout(new BoxLayout(jp3right, BoxLayout.Y_AXIS));
		
		jp1.add(jp1left, BorderLayout.WEST);
		jp1.add(jp1right, BorderLayout.EAST);
		jp2.add(jp2left, BorderLayout.WEST);
		jp2.add(jp2right, BorderLayout.EAST);
		jp3.add(jp3left, BorderLayout.WEST);
		jp3.add(jp3right, BorderLayout.EAST);
		
		JLabel jp1jl1 = new JLabel("다중 상관 계수 :");
		jp1jl1.setHorizontalAlignment(JLabel.RIGHT);
		JLabel jp1jl2 = new JLabel("결정 계수 :");
		jp1jl2.setHorizontalAlignment(JLabel.RIGHT);
		JLabel jp1jl3 = new JLabel("조정된 결정 계수 :");
		jp1jl3.setHorizontalAlignment(JLabel.RIGHT);
		JLabel jp1jl4 = new JLabel("표준 오차 :");
		jp1jl4.setHorizontalAlignment(JLabel.RIGHT);
		JLabel jp1jl5 = new JLabel("관측수 :");
		jp1jl5.setHorizontalAlignment(JLabel.RIGHT);
		
		JLabel jp1jl6 = new JLabel("0.145251");
		jp1jl6.setHorizontalAlignment(JLabel.LEFT);
		JLabel jp1jl7 = new JLabel("0.021098");
		jp1jl7.setHorizontalAlignment(JLabel.LEFT);
		JLabel jp1jl8 = new JLabel("-0.04416");
		jp1jl8.setHorizontalAlignment(JLabel.LEFT);
		JLabel jp1jl9 = new JLabel("15.08096");
		jp1jl9.setHorizontalAlignment(JLabel.LEFT);
		JLabel jp1jl10 = new JLabel("17");
		jp1jl10.setHorizontalAlignment(JLabel.LEFT);
		
		jp1left.add(jp1jl1);
		jp1left.add(jp1jl2);
		jp1left.add(jp1jl3);
		jp1left.add(jp1jl4);
		jp1left.add(jp1jl5);
		jp1right.add(jp1jl6);
		jp1right.add(jp1jl7);
		jp1right.add(jp1jl8);
		jp1right.add(jp1jl9);
		jp1right.add(jp1jl10);
		
		JLabel jp2jl1 = new JLabel("다중 상관 계수 :");
		jp2jl1.setHorizontalAlignment(JLabel.RIGHT);
		JLabel jp2jl2 = new JLabel("결정 계수 :");
		jp2jl2.setHorizontalAlignment(JLabel.RIGHT);
		JLabel jp2jl3 = new JLabel("조정된 결정 계수 :");
		jp2jl3.setHorizontalAlignment(JLabel.RIGHT);
		JLabel jp2jl4 = new JLabel("표준 오차 :");
		jp2jl4.setHorizontalAlignment(JLabel.RIGHT);
		JLabel jp2jl5 = new JLabel("관측수 :");
		jp2jl5.setHorizontalAlignment(JLabel.RIGHT);
		
		JLabel jp2jl6 = new JLabel("0.210401");
		jp2jl6.setHorizontalAlignment(JLabel.LEFT);
		JLabel jp2jl7 = new JLabel("0.044269");
		jp2jl7.setHorizontalAlignment(JLabel.LEFT);
		JLabel jp2jl8 = new JLabel("-0.01945");
		jp2jl8.setHorizontalAlignment(JLabel.LEFT);
		JLabel jp2jl9 = new JLabel("16.5171");
		jp2jl9.setHorizontalAlignment(JLabel.LEFT);
		JLabel jp2jl10 = new JLabel("17");
		jp2jl10.setHorizontalAlignment(JLabel.LEFT);
		
		jp2left.add(jp2jl1);
		jp2left.add(jp2jl2);
		jp2left.add(jp2jl3);
		jp2left.add(jp2jl4);
		jp2left.add(jp2jl5);
		jp2right.add(jp2jl6);
		jp2right.add(jp2jl7);
		jp2right.add(jp2jl8);
		jp2right.add(jp2jl9);
		jp2right.add(jp2jl10);
		
		JLabel jp3jl1 = new JLabel("다중 상관 계수 :");
		jp3jl1.setHorizontalAlignment(JLabel.RIGHT);
		JLabel jp3jl2 = new JLabel("결정 계수 :");
		jp3jl2.setHorizontalAlignment(JLabel.RIGHT);
		JLabel jp3jl3 = new JLabel("조정된 결정 계수 :");
		jp3jl3.setHorizontalAlignment(JLabel.RIGHT);
		JLabel jp3jl4 = new JLabel("표준 오차 :");
		jp3jl4.setHorizontalAlignment(JLabel.RIGHT);
		JLabel jp3jl5 = new JLabel("관측수 :");
		jp3jl5.setHorizontalAlignment(JLabel.RIGHT);
		
		JLabel jp3jl6 = new JLabel("0.342694");
		jp3jl6.setHorizontalAlignment(JLabel.LEFT);
		JLabel jp3jl7 = new JLabel("0.117439");
		jp3jl7.setHorizontalAlignment(JLabel.LEFT);
		JLabel jp3jl8 = new JLabel("0.058601");
		jp3jl8.setHorizontalAlignment(JLabel.LEFT);
		JLabel jp3jl9 = new JLabel("17.96113");
		jp3jl9.setHorizontalAlignment(JLabel.LEFT);
		JLabel jp3jl10 = new JLabel("17");
		jp3jl10.setHorizontalAlignment(JLabel.LEFT);
		
		jp3left.add(jp3jl1);
		jp3left.add(jp3jl2);
		jp3left.add(jp3jl3);
		jp3left.add(jp3jl4);
		jp3left.add(jp3jl5);
		jp3right.add(jp3jl6);
		jp3right.add(jp3jl7);
		jp3right.add(jp3jl8);
		jp3right.add(jp3jl9);
		jp3right.add(jp3jl10);
	
		jf.add(jtp);
		
		jf.setVisible(true);
	}
}