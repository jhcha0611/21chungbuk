package frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.naming.directory.SearchControls;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JProgressBar;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import jdbc.DBManager;

public class Music extends JFrame {

	int uno;
	String uname;
	JPanel panel, West, Center, South, Npanel, panel4, panel33, pl, pl6;
	Color c = new Color(48, 48, 48);
	JLabel star[] = new JLabel[5];
	JLabel star2[] = new JLabel[5];
	JProgressBar bar;
	JTextField field;
	JScrollPane scrollPane;
	boolean playChk = false;
	int num = 0, colorInt = 0, AllCnt = 0, Anum = 0, Snum = 0;
	JScrollBar verticalScrollBar;
	String tit;
	ArrayList<String> arrayList = new ArrayList<String>();

	public Music() {
		setTitle("Music");
		setSize(1400, 840);
		setResizable(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(2);
		setIconImage(getToolkit().getImage("지급자료/images/logo.png"));

		panel = new JPanel(new BorderLayout());
		panel.setBackground(c);
		add(panel);

		m1();

		m2();

		setVisible(true);
	}

	class DrawPanel extends JPanel {

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			g.setColor(Color.gray);
			g.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);

		}
	}

	private void m1() {
		panel.removeAll();

		West = new JPanel(new BorderLayout());
		West.setPreferredSize(new Dimension(250, 0));
		panel.add(West, BorderLayout.WEST);

		Center = new JPanel(new BorderLayout());
		Center.setBorder(new EmptyBorder(10, 10, 10, 10));
		scrollPane = new JScrollPane(Center, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		Center.setBackground(c);

		scrollPane.getViewport().setViewPosition(new Point(0, 0));

		// **************************************************************************

		scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
			@Override
			protected JButton createDecreaseButton(int orientation) {
				return createZeroButton();
			}

			@Override
			protected JButton createIncreaseButton(int orientation) {
				return createZeroButton();
			}

			private JButton createZeroButton() {
				JButton button = new JButton();
				Dimension zeroDim = new Dimension(0, 0);
				button.setPreferredSize(zeroDim);
				button.setMinimumSize(zeroDim);
				button.setMaximumSize(zeroDim);
				return button;
			}

			@Override
			protected void configureScrollBarColors() {
				this.thumbColor = Color.gray; // 스크롤 바의 색상을 어두운 회색(Dark Gray)으로 설정
				this.trackColor = Color.DARK_GRAY; // 스크롤 바의 트랙 색상을 밝은 회색(Gray)으로 설정
			}
		});
		panel.add(scrollPane, BorderLayout.CENTER);
		// **************************************************************************

		South = new JPanel(new BorderLayout());
		South.setPreferredSize(new Dimension(0, 90));
		South.setBorder(new CompoundBorder(new MatteBorder(2, 0, 0, 0, Color.lightGray), new EmptyBorder(0, 5, 0, 5)));
		South.setBackground(c);
		panel.add(South, BorderLayout.SOUTH);

		panel.repaint();
		panel.validate();

	}

	private void m2() {
		West.setVisible(false);
		South.setVisible(false);

		JPanel panel2 = new JPanel(new GridBagLayout());
		panel2.setBackground(c);
		Center.add(panel2);

		DrawPanel panel3 = new DrawPanel();
		panel3.setBackground(c);
		panel3.setPreferredSize(new Dimension(230, 250));
		panel2.add(panel3);

		JPanel panel4 = new JPanel(new BorderLayout());
		panel4.setBackground(new Color(0, 0, 0, 0));
		panel3.add(panel4, BorderLayout.CENTER);

		JLabel label = new JLabel("Music Player", JLabel.CENTER);
		label.setFont(new Font("맑은 고딕", Font.BOLD, 23));
		label.setForeground(Color.white);
		panel4.add(label, BorderLayout.NORTH);

		JPanel panel5 = new JPanel(new GridLayout(4, 1, 0, 5));
		panel5.setPreferredSize(new Dimension(210, 170));
		panel5.setBorder(new EmptyBorder(5, 0, 5, 0));
		panel5.setBackground(Color.gray);
		panel4.add(panel5, BorderLayout.CENTER);

		JLabel label2 = new JLabel("ID");
		label2.setForeground(Color.white);
		panel5.add(label2);

		JTextField field = new JTextField();
		field.setBackground(Color.DARK_GRAY);
		field.setForeground(Color.white);
		panel5.add(field);

		JLabel label3 = new JLabel("PW");
		label3.setForeground(Color.white);
		panel5.add(label3);

		JPasswordField field2 = new JPasswordField();
		field2.setBackground(Color.DARK_GRAY);
		field2.setForeground(Color.white);
		panel5.add(field2);

		JButton btn = new JButton("로그인");
		btn.setBackground(Color.white);
		btn.setPreferredSize(new Dimension(0, 35));
		panel4.add(btn, BorderLayout.SOUTH);

		btn.addActionListener(new ActionListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				try {

					if (field.getText().equals("") || field2.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "아이디와 비밀번호를 모두 입력해야 합니다.", "경고", JOptionPane.ERROR_MESSAGE);
						return;
					}
					ResultSet rs = DBManager.getResultSet("SELECT * FROM 충북21.user where id = '" + field.getText()
							+ "' and pw = '" + field2.getText() + "';");

					if (rs.next()) {
						JOptionPane.showMessageDialog(null, rs.getString(4) + "님 환영합니다.", "정보",
								JOptionPane.INFORMATION_MESSAGE);

						uno = rs.getInt(1);
						uname = rs.getString(4);
						
					m1();
					m3();
					
					} else {
						JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호가 일치하지 않습니다.", "경고", JOptionPane.ERROR_MESSAGE);
					}
					ResultSet rs2 = DBManager.getResultSet(
							"SELECT song.name, substring(song.length, 4) as time, titlesong FROM 충북21.favorite"
									+ " inner join user on user.serial = favorite.user"
									+ " inner join song on song.serial = favorite.song where user.serial = " + uno
									+ ";");
					arrayList.removeAll(arrayList);
					while (rs2.next()) {
						arrayList.add(rs2.getString(1));
					}

				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
	}

	private void m3() {

		West.setVisible(true);
		South.setVisible(true);

		Center.removeAll();

		JPanel panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);

				ImageIcon img2 = new ImageIcon("지급자료/images/side.png");
				Image img = img2.getImage();

				g.drawImage(img, 0, 0, getWidth(), getHeight(), this);

			}
		};
		West.add(panel);

		try {

			JPanel p = new JPanel(new BorderLayout());
			p.setBackground(new Color(0, 0, 0, 0));
			p.setBorder(new EmptyBorder(10, 0, 0, 0));
			p.setPreferredSize(new Dimension(235, 480));
			panel.add(p);

			JPanel p2 = new JPanel(new GridLayout(3, 1, 0, 10));
			p2.setBackground(new Color(0, 0, 0, 0));
			p.add(p2, BorderLayout.NORTH);

			for (int i = 0; i < 3; i++) {
				JLabel label = new JLabel(new String[] { "MENU", "홈", "검색하기" }[i]);
				label.setFont(new Font("맑은 고딕", Font.BOLD, 13));
				label.setForeground(Color.white);
				p2.add(label);

				if (i == 0) {
					label.setFont(new Font("맑은 고딕", Font.BOLD, 15));
				}
				if (i == 1)
					label.setIcon(new ImageIcon(
							new ImageIcon("지급자료/images/home.png").getImage().getScaledInstance(25, 25, 4)));
				if (i == 2)
					label.setIcon(new ImageIcon(
							new ImageIcon("지급자료/images/search.png").getImage().getScaledInstance(25, 25, 4)));

				int j = i;
				label.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						if (j == 1) {
							try {
								ResultSet rs2 = DBManager.getResultSet(
										"SELECT song.name, substring(song.length, 4) as time, titlesong FROM 충북21.favorite"
												+ " inner join user on user.serial = favorite.user"
												+ " inner join song on song.serial = favorite.song where user.serial = "
												+ uno + ";");
								arrayList.removeAll(arrayList);
								while (rs2.next()) {
									arrayList.add(rs2.getString(1));
								}
							} catch (Exception e2) {
								e2.printStackTrace();
							}
							m1();
							m3();
						}
						if (j == 2) {
							try {
								ResultSet rs2 = DBManager.getResultSet(
										"SELECT song.name, substring(song.length, 4) as time, titlesong FROM 충북21.favorite"
												+ " inner join user on user.serial = favorite.user"
												+ " inner join song on song.serial = favorite.song where user.serial = "
												+ uno + ";");
								arrayList.removeAll(arrayList);
								while (rs2.next()) {
									arrayList.add(rs2.getString(1));
								}
							} catch (Exception e2) {
								e2.printStackTrace();
							}
							m1();
							m3();
							Search();
						}
					}
				});
			}

			JPanel p3 = new JPanel(new GridLayout(3, 1, 0, -10));
			p3.setBackground(new Color(0, 0, 0, 0));
			p3.setBorder(new EmptyBorder(30, 0, 30, 0));
			p.add(p3, BorderLayout.CENTER);

			for (int i = 0; i < 3; i++) {
				JLabel label = new JLabel(new String[] { "LIBRARY", "좋아요", "재생기록" }[i]);
				label.setFont(new Font("맑은 고딕", Font.BOLD, 13));
				label.setForeground(Color.white);
				p3.add(label);

				if (i == 0) {
					label.setFont(new Font("맑은 고딕", Font.BOLD, 15));
				}
				if (i == 1) {
					label.setIcon(new ImageIcon(
							new ImageIcon("지급자료/images/like.png").getImage().getScaledInstance(25, 25, 4)));
				}
				if (i == 2) {
					label.setIcon(new ImageIcon(
							new ImageIcon("지급자료/images/history.png").getImage().getScaledInstance(25, 25, 4)));
				}
				int j = i;
				label.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						if (j == 1) {
							try {
								ResultSet rs2 = DBManager.getResultSet(
										"SELECT song.name, substring(song.length, 4) as time, titlesong FROM 충북21.favorite"
												+ " inner join user on user.serial = favorite.user"
												+ " inner join song on song.serial = favorite.song where user.serial = "
												+ uno + ";");
								arrayList.removeAll(arrayList);
								while (rs2.next()) {
									arrayList.add(rs2.getString(1));
								}
							} catch (Exception e2) {
								e2.printStackTrace();
							}
							m1();
							m3();
							like();
						}
						if (j == 2) {
							try {
								ResultSet rs2 = DBManager.getResultSet(
										"SELECT song.name, substring(song.length, 4) as time, titlesong FROM 충북21.favorite"
												+ " inner join user on user.serial = favorite.user"
												+ " inner join song on song.serial = favorite.song where user.serial = "
												+ uno + ";");
								arrayList.removeAll(arrayList);
								while (rs2.next()) {
									arrayList.add(rs2.getString(1));
								}
							} catch (Exception e2) {
								e2.printStackTrace();
							}
							m1();
							m3();
							history();
						}
					}
				});
			}

			JPanel p4 = new JPanel(new GridLayout(6, 1, 0, 13));
			p4.setBackground(new Color(0, 0, 0, 0));
			p.add(p4, BorderLayout.SOUTH);

			for (int i = 0; i < 6; i++) {
				JLabel label = new JLabel(
						new String[] { "PLAYLIST", "재생목록 추가", "방탄소년단 모음", "K팝", "내가 듣고 싶은거", "플레이리스트" }[i]);
				label.setFont(new Font("맑은 고딕", Font.BOLD, 13));
				label.setForeground(Color.white);
				p4.add(label);
				if (i == 0) {
					label.setFont(new Font("맑은 고딕", Font.BOLD, 15));
				}
			}

			JPanel panel2 = new JPanel(new BorderLayout());
			panel2.setBackground(c);
			Center.add(panel2);

			JPanel pa = new JPanel(new BorderLayout());
			pa.setBackground(c);
			panel2.add(pa, BorderLayout.NORTH);

			JPanel pa2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			pa2.setBackground(c);
			pa.add(pa2, BorderLayout.NORTH);

			JButton btn = new JButton("로그아웃");
			btn.setBackground(Color.white);
			btn.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
			btn.setPreferredSize(new Dimension(90, 35));
			pa2.add(btn);

			btn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					m1();
					m2();
				}
			});

			JPanel pa3 = new JPanel(new GridLayout(1, 3, 10, 0));
			pa3.setPreferredSize(new Dimension(0, 200));
			pa3.setBackground(c);
			pa.add(pa3, BorderLayout.CENTER);

			for (int i = 0; i < 3; i++) {

				JPanel pa4 = new JPanel(null) {
					@Override
					protected void paintComponent(Graphics g) {
						super.paintComponent(g);

						g.setColor(new Color(63, 72, 203));
						g.fillRoundRect(0, 8, 360, 180, 30, 30);
					}
				};
				pa3.add(pa4);
				pa4.setBackground(c);

				JPanel panel3 = new JPanel(new BorderLayout());
				panel3.setBackground(new Color(63, 72, 203));
				panel3.setBounds(7, 18, 340, 160);
				pa4.add(panel3);

				JLabel label = new JLabel(new String[] { "이번달 히트 곡", "최근 발매된 앨범", "서울특별시의 인기 음악" }[i]);
				label.setForeground(Color.white);
				label.setFont(new Font("맑은 고딕", Font.BOLD, 14));
				label.setBorder(new EmptyBorder(0, 0, 10, 0));
				panel3.add(label, BorderLayout.NORTH);

//				int value = (int) (Math.random() * 108) + 1; // ************************** 임시 **************************

				int value = i == 0 ? 81 : i == 1 ? 83 : 101;

				JLabel label2 = new JLabel(new ImageIcon(new ImageIcon("지급자료/images/album/" + value + ".jpg").getImage()
						.getScaledInstance(130, 130, 4)));
				panel3.add(label2, BorderLayout.WEST);

				JPanel panel4 = new JPanel(new BorderLayout());
				panel4.setBorder(new EmptyBorder(10, 8, 10, 0));
				panel4.setBackground(new Color(63, 72, 203));
				panel3.add(panel4, BorderLayout.CENTER);

				String nm = i == 0 ? "<html>Answer: Love Myself<br /><br />Love Yourself 'Answer'"
						: i == 1 ? "<html>Intro: Persona<br /><br />MAP OF THE SOUL : 7"
								: "<html>Seattle Alone<br /><br />사춘기집! 꽃기운 - EP";

				JLabel label3 = new JLabel(nm);
				label3.setForeground(Color.white);
				label3.setFont(new Font("맑은 고딕", Font.BOLD, 14));
				panel4.add(label3);
			}

			JPanel panel3 = new JPanel(new BorderLayout());
			panel3.setBackground(c);
			panel2.add(panel3, BorderLayout.CENTER);

			JPanel panel4 = new JPanel(new BorderLayout());
			panel4.setBackground(c);
			panel4.setBorder(new MatteBorder(0, 0, 2, 0, Color.gray));
			panel3.add(panel4, BorderLayout.NORTH);

			JLabel lab = new JLabel("카테고리 둘러보기");
			lab.setForeground(Color.white);
			lab.setFont(new Font("맑은 고딕", Font.BOLD, 16));
			panel4.add(lab);

			JPanel panel5 = new JPanel(new GridLayout(5, 3, 8, 8));
			panel5.setBorder(new EmptyBorder(8, 0, 0, 0));
			panel5.setBackground(c);
			panel3.add(panel5, BorderLayout.CENTER);

			ResultSet rs = DBManager.getResultSet("SELECT * FROM 충북21.category;");

			while (rs.next()) {

				String str = rs.getString(2);
				JPanel panel6 = new JPanel(new BorderLayout()) {
					@Override
					protected void paintComponent(Graphics g) {
						super.paintComponent(g);

						ImageIcon icon = new ImageIcon(new ImageIcon("지급자료/images/category/" + str + ".jpg").getImage()
								.getScaledInstance(360, 150, 4));
						Image img = icon.getImage();
						g.drawImage(img, 0, 0, 360, 150, this);

						g.setColor(new Color(155, 190, 239, colorInt));
						g.fillRect(0, 0, 360, 150);
					}
				};
				panel6.setPreferredSize(new Dimension(0, 150));
				panel5.add(panel6);

				JLabel label = new JLabel(str, JLabel.CENTER);
				label.setForeground(Color.white);
				label.setFont(new Font("맑은 고딕", Font.BOLD, 15));
				panel6.add(label);

				panel6.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						category(str);
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						JPanel panel = (JPanel) e.getComponent();
//						colorInt = 180;
						panel.repaint();
						panel.validate();
						label.setForeground(Color.green);
					}

					@Override
					public void mouseExited(MouseEvent e) {
						JPanel panel = (JPanel) e.getComponent();
//						colorInt = 0;
						panel.repaint();
						panel.validate();
						label.setForeground(Color.white);
					}
				});
			}

			JPanel panel3_1 = new JPanel(new BorderLayout());
			panel3_1.setBorder(new EmptyBorder(5, 0, 2, 0));
			panel3_1.setPreferredSize(new Dimension(0, 10));
			panel3_1.setBackground(c);
			South.add(panel3_1, BorderLayout.NORTH);

			bar = new JProgressBar(0, 60000);
			bar.setBorder(new EmptyBorder(0, 0, 0, 0));
			bar.setForeground(Color.orange);
			bar.setValue(0);
			bar.setVisible(false);
			panel3_1.add(bar);

			JPanel panel3_2 = new JPanel(new BorderLayout());
			panel3_2.setBorder(new EmptyBorder(3, 0, 5, 0));
			panel3_2.setBackground(c);
			South.add(panel3_2, BorderLayout.CENTER);

			JLabel label = new JLabel();
			label.setPreferredSize(new Dimension(70, 70));
			label.setBorder(new LineBorder(Color.gray));
			panel3_2.add(label, BorderLayout.WEST);

			JPanel pan = new JPanel(new BorderLayout());
			pan.setBorder(new EmptyBorder(0, 80, 0, 80));
			pan.setBackground(c);
			panel3_2.add(pan, BorderLayout.CENTER);

			JLabel label2 = new JLabel("재생중이 아닌", JLabel.CENTER);
			label2.setFont(new Font("맑은 고딕", Font.BOLD, 13));
			label2.setForeground(Color.white);
			pan.add(label2, BorderLayout.NORTH);

			JLabel label3 = new JLabel(
					new ImageIcon(new ImageIcon("지급자료/images/prev.png").getImage().getScaledInstance(25, 25, 4)));
			pan.add(label3, BorderLayout.WEST);

			JLabel label4 = new JLabel(
					new ImageIcon(new ImageIcon("지급자료/images/play.png").getImage().getScaledInstance(35, 35, 4)));
			pan.add(label4, BorderLayout.CENTER);

			JLabel label5 = new JLabel(
					new ImageIcon(new ImageIcon("지급자료/images/next.png").getImage().getScaledInstance(25, 25, 4)));
			pan.add(label5, BorderLayout.EAST);

			JPanel pan2 = new JPanel(new BorderLayout());
			pan2.setPreferredSize(new Dimension(50, 0));
			pan2.setBackground(c);
			panel3_2.add(pan2, BorderLayout.EAST);

			JLabel lbl = new JLabel(
					new ImageIcon(new ImageIcon("지급자료/images/queue.png").getImage().getScaledInstance(40, 40, 4)));
			pan2.add(lbl, BorderLayout.EAST);

			label4.addMouseListener(new MouseAdapter() {

				@Override
				public void mousePressed(MouseEvent e) {

					if (bar.isVisible() == false) {
						return;
					}

					PrintThread printThread = new PrintThread();
					printThread.start();

					if (playChk == false) {
						label4.setIcon(new ImageIcon(
								new ImageIcon("지급자료/images/pause.png").getImage().getScaledInstance(40, 40, 4)));
						playChk = true;
						return;
					} else {
						label4.setIcon(new ImageIcon(
								new ImageIcon("지급자료/images/play.png").getImage().getScaledInstance(40, 40, 4)));

						printThread.setStop(true);// Thraed Stop

						playChk = false;
						return;
					}

				}

			});

		} catch (Exception e) {
			e.printStackTrace();
		}
		Center.repaint();
		Center.validate();
	}

	private void Search() {
		try {

			Center.removeAll();

			JPanel panel2 = new JPanel(new BorderLayout());
			panel2.setBackground(c);
			Center.add(panel2);

			JPanel pa = new JPanel(new BorderLayout());
			pa.setBackground(c);
			panel2.add(pa, BorderLayout.NORTH);

			JPanel panel3 = new JPanel(new BorderLayout());
			panel3.setBackground(c);
			panel2.add(panel3, BorderLayout.CENTER);

			JPanel panel4 = new JPanel(new BorderLayout());
			panel4.setPreferredSize(new Dimension(0, 35));
			panel4.setBackground(c);
			panel3.add(panel4, BorderLayout.NORTH);

			field = new JTextField();
			field.setBackground(new Color(96, 96, 96));
			field.setBorder(new LineBorder(Color.white));
			field.setForeground(Color.white);
			field.setFont(new Font("맑은 고딕", Font.BOLD, 14));
			panel4.add(field, BorderLayout.CENTER);

			JPanel p = new JPanel(new BorderLayout());
			p.setBorder(new EmptyBorder(0, 10, 0, 0));
			p.setPreferredSize(new Dimension(70, 0));
			p.setBackground(c);
			panel4.add(p, BorderLayout.EAST);

			JButton btn = new JButton("검색");
			btn.setBackground(Color.white);
			btn.setFont(new Font("맑은 고딕", Font.BOLD, 13));
			p.add(btn);

			JPanel panel5 = new JPanel(new GridLayout(5, 3, 8, 8));
			panel5.setBorder(new EmptyBorder(8, 0, 0, 0));
			panel5.setBackground(c);
			panel3.add(panel5, BorderLayout.CENTER);

			btn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try {

						if (field.getText().equals("")) {
							return;
						}
						panel5.removeAll();

						panel5.setLayout(new BorderLayout());

						JPanel panel = new JPanel(new GridLayout(2, 2, 10, 0));
						panel.setBackground(c);
						panel5.add(panel, BorderLayout.CENTER);

						AllCnt = 0;

						ResultSet r = DBManager.getResultSet(
								"SELECT *, (SELECT count(*) FROM 충북21.song inner join album on album.serial = song.album where song.name like '%"
										+ field.getText()
										+ "%') as cnt FROM 충북21.song inner join album on album.serial = song.album where song.name like '%"
										+ field.getText() + "%' limit 4;");
						if (r.next()) {
							AllCnt = r.getInt("cnt") + AllCnt;
						}
						ResultSet r1 = DBManager
								.getResultSet("SELECT *, (SELECT count(*) FROM 충북21.album where name like '%"
										+ field.getText() + "%') as cnt FROM 충북21.album where name like '%"
										+ field.getText() + "%' limit 4;");
						if (r1.next()) {
							AllCnt = r1.getInt("cnt") + AllCnt;
						}
						ResultSet r2 = DBManager
								.getResultSet("SELECT *, (SELECT count(*) FROM 충북21.artist where name like '%"
										+ field.getText() + "%') as cnt FROM 충북21.artist where name like '%"
										+ field.getText() + "%' limit 4;");
						if (r2.next()) {
							AllCnt = r2.getInt("cnt") + AllCnt;
						}
						ResultSet r3 = DBManager
								.getResultSet("SELECT *, (SELECT count(*) FROM 충북21.playlist where name like '%"
										+ field.getText() + "%') as cnt FROM 충북21.playlist where name like '%"
										+ field.getText() + "%' limit 4;");
						if (r3.next()) {
							AllCnt = r3.getInt("cnt") + AllCnt;
						}

						JLabel label = new JLabel(field.getText() + "에 대한 검색결과 총 " + AllCnt + "건");
						label.setForeground(Color.white);
						label.setFont(new Font("맑은 고딕", Font.BOLD, 15));
						label.setBorder(new EmptyBorder(0, 0, 10, 0));
						panel5.add(label, BorderLayout.NORTH);

						for (int i = 0; i < 4; i++) {
							JPanel panel2 = new JPanel(new BorderLayout());
							panel2.setBackground(c);
							panel.add(panel2);

							JPanel panel3 = new JPanel(new BorderLayout());
							panel3.setBackground(c);
							panel3.setBorder(new MatteBorder(0, 0, 2, 0, Color.gray));
							panel2.add(panel3, BorderLayout.NORTH);

							JPanel panel4 = new JPanel(new GridLayout(2, 2, 0, 10));
							panel4.setBorder(new EmptyBorder(5, 0, 5, 0));
							panel4.setBackground(c);
							panel2.add(panel4, BorderLayout.CENTER);

							int music = 0, album = 0, artist = 0, playlist = 0;

							String str = i == 0
									? "SELECT *, (SELECT count(*) FROM 충북21.song inner join album on album.serial = song.album where song.name like '%"
											+ field.getText()
											+ "%') as cnt FROM 충북21.song inner join album on album.serial = song.album where song.name like '%"
											+ field.getText() + "%' limit 4;"
									: i == 1 ? "SELECT *, (SELECT count(*) FROM 충북21.album where name like '%"
											+ field.getText() + "%') as cnt FROM 충북21.album where name like '%"
											+ field.getText() + "%' limit 4;"
											: i == 2 ? "SELECT *, (SELECT count(*) FROM 충북21.artist where name like '%"
													+ field.getText() + "%') as cnt FROM 충북21.artist where name like '%"
													+ field.getText() + "%' limit 4;"
													: "SELECT *, (SELECT count(*) FROM 충북21.playlist where name like '%"
															+ field.getText()
															+ "%') as cnt FROM 충북21.playlist where name like '%"
															+ field.getText() + "%' limit 4;";

							ResultSet rs = DBManager.getResultSet(str);

							while (rs.next()) {

								JPanel panel5 = new JPanel(new BorderLayout());
								panel5.setBorder(new EmptyBorder(5, 5, 5, 5));
								panel5.setBackground(c);
								panel4.add(panel5);

								JLabel label1 = new JLabel();
								if (i == 0) {
									music = rs.getInt("cnt");
									label1.setIcon(
											new ImageIcon(new ImageIcon("지급자료/images/album/" + rs.getString(4) + ".jpg")
													.getImage().getScaledInstance(110, 110, 4)));
								}
								if (i == 1) {
									album = rs.getInt("cnt");
									label1.setIcon(
											new ImageIcon(new ImageIcon("지급자료/images/album/" + rs.getString(1) + ".jpg")
													.getImage().getScaledInstance(110, 110, 4)));
								}
								if (i == 2) {
									artist = rs.getInt("cnt");
									label1.setIcon(new ImageIcon(
											new ImageIcon("지급자료/images/artist/" + rs.getString(1) + ".jpg").getImage()
													.getScaledInstance(110, 110, 4)));
								}
								if (i == 3) {
									playlist = rs.getInt("cnt");
									label1.setIcon(
											new ImageIcon(new ImageIcon("지급자료/images/album/" + rs.getString(1) + ".jpg")
													.getImage().getScaledInstance(110, 110, 4)));
								}
								panel5.add(label1, BorderLayout.WEST);

								JTextArea area = new JTextArea(rs.getString(2));
								area.setBorder(new EmptyBorder(50, 10, 0, 0));
								area.setLineWrap(true);
								area.setBackground(c);
								area.setFont(new Font("맑은 고딕", Font.BOLD, 13));
								area.setForeground(Color.white);
								area.setEditable(false);
								panel5.add(area, BorderLayout.CENTER);

								panel5.addMouseListener(new MouseAdapter() {
									@Override
									public void mouseEntered(MouseEvent e) {
										panel5.setBackground(new Color(96, 96, 96));
										area.setBackground(new Color(96, 96, 96));
									}

									@Override
									public void mouseExited(MouseEvent e) {
										panel5.setBackground(c);
										area.setBackground(c);
									}
								});
								area.addMouseListener(new MouseAdapter() {
									@Override
									public void mouseEntered(MouseEvent e) {
										panel5.setBackground(new Color(96, 96, 96));
										area.setBackground(new Color(96, 96, 96));
									}

									@Override
									public void mouseExited(MouseEvent e) {
										panel5.setBackground(c);
										area.setBackground(c);
									}
								});
								label1.addMouseListener(new MouseAdapter() {
									@Override
									public void mousePressed(MouseEvent e) {
										if (e.getButton() == MouseEvent.BUTTON3) {

											JPopupMenu menu = new JPopupMenu();
											menu.setFont(new Font("맑은 고딕", Font.BOLD, 13));
											menu.add("앨범으로 이동").addActionListener(new ActionListener() {

												@Override
												public void actionPerformed(ActionEvent e) {
													album(area.getText());
												}
											});
											menu.add("아티스트로 이동").addActionListener(new ActionListener() {

												@Override
												public void actionPerformed(ActionEvent e) {
													try {

														ResultSet rs = DBManager.getResultSet(
																"SELECT * FROM 충북21.album inner join artist on artist.serial = album.artist where album.name = '"
																		+ area.getText() + "';");

														if (rs.next()) {
															String st = rs.getString(7);
															artist(st);
														}

													} catch (Exception e2) {
														e2.printStackTrace();
													}
												}
											});
											menu.show(label1, e.getX(), e.getY());
										}
//										album(area.getText());
									}

									@Override
									public void mouseEntered(MouseEvent e) {
										panel5.setBackground(new Color(96, 96, 96));
										area.setBackground(new Color(96, 96, 96));
									}

									@Override
									public void mouseExited(MouseEvent e) {
										panel5.setBackground(c);
										area.setBackground(c);
									}
								});
							}
							int value[] = { music, album, artist, playlist };
							String st[] = { "음악", "앨범", "아티스트", "플레이리스트" };
							JLabel label2 = new JLabel(new String[] { "음악 " + music + "건", "앨범 " + album + "건",
									"아티스트 " + artist + "건", "플레이리스트 " + playlist + "건" }[i]);
							label2.setForeground(Color.white);
							label2.setFont(new Font("맑은 고딕", Font.BOLD, 12));
							panel3.add(label2, BorderLayout.WEST);

							JLabel label3 = new JLabel("모두 보기");
							label3.setForeground(Color.white);
							label3.setFont(new Font("맑은 고딕", Font.BOLD, 12));
							panel3.add(label3, BorderLayout.EAST);

							int j = i;
							label3.addMouseListener(new MouseAdapter() {

								public void mousePressed(MouseEvent e) {
									if (value[j] == 0) {
										return;
									}
									m1();
									m3();
									Search2(value[j], st[j]);
								};

								public void mouseEntered(MouseEvent e) {
									label3.setForeground(Color.green);
								};

								public void mouseExited(MouseEvent e) {
									label3.setForeground(Color.white);
								};
							});

						}

						panel5.repaint();
						panel5.validate();
					} catch (

					Exception e2) {
						e2.printStackTrace();
					}
				}
			});

			ResultSet rs = DBManager.getResultSet("SELECT * FROM 충북21.category;");

			while (rs.next()) {

				String str = rs.getString(2);
				JPanel panel6 = new JPanel(new BorderLayout()) {
					@Override
					protected void paintComponent(Graphics g) {
						super.paintComponent(g);

						ImageIcon icon = new ImageIcon(new ImageIcon("지급자료/images/category/" + str + ".jpg").getImage()
								.getScaledInstance(360, 150, 4));
						Image img = icon.getImage();
						g.drawImage(img, 0, 0, 360, 150, this);

						g.setColor(new Color(155, 190, 239, colorInt));
						g.fillRect(0, 0, 360, 150);
					}
				};
				panel6.setPreferredSize(new Dimension(0, 150));
				panel5.add(panel6);

				JLabel label = new JLabel(str, JLabel.CENTER);
				label.setForeground(Color.white);
				label.setFont(new Font("맑은 고딕", Font.BOLD, 15));
				panel6.add(label);

				panel6.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						m1();
						m3();
						category(str);
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						JPanel panel = (JPanel) e.getComponent();
//						colorInt = 180;
						panel.repaint();
						panel.validate();
						label.setForeground(Color.green);
					}

					@Override
					public void mouseExited(MouseEvent e) {
						JPanel panel = (JPanel) e.getComponent();
//						colorInt = 0;
						panel.repaint();
						panel.validate();
						label.setForeground(Color.white);
					}
				});
			}
			Center.repaint();
			Center.validate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private Image FadeImg(Image img) {

		Graphics g = img.getGraphics();
		g.drawImage(img, 0, 0, 360, 150, this);

		return FadeImg(img);
	}

	private Image OrignalImg(String str) {

		ImageIcon icon = new ImageIcon(
				new ImageIcon("지급자료/images/category/" + str + ".jpg").getImage().getScaledInstance(360, 150, 4));
		Image img = icon.getImage();
		Graphics g = img.getGraphics();
		g.drawImage(img, 0, 0, 360, 150, this);

		return OrignalImg(str);
	}

	private Image WhiteImg(String str) {

		ImageIcon icon = new ImageIcon(
				new ImageIcon("지급자료/images/category/" + str + ".jpg").getImage().getScaledInstance(360, 150, 4));
		Image img = icon.getImage();
		Graphics g = img.getGraphics();
		g.drawImage(img, 0, 0, 360, 150, this);

		g.setColor(new Color(155, 190, 239, 100));
		g.drawRect(0, 0, 360, 150);

		return WhiteImg(str);
	}

	private void artist(String str) {
		try {
			Center.removeAll();

			Ns(str);

			JPanel panel6 = new JPanel(new BorderLayout());
			panel6.setBackground(c);
			Npanel.add(panel6, BorderLayout.CENTER);

			JLabel label = new JLabel("인기 있는 음악");
			label.setForeground(Color.white);
			label.setBorder(new EmptyBorder(10, 0, 0, 0));
			label.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			panel6.add(label, BorderLayout.NORTH);

			JPanel panel7 = new JPanel(new BorderLayout());
			panel7.setBackground(c);
			panel6.add(panel7, BorderLayout.CENTER);

			JPanel panel8 = new JPanel(new GridLayout(5, 1, 0, 5));
			panel8.setBackground(c);
			panel8.setBorder(new MatteBorder(0, 0, 2, 0, Color.gray));
			panel7.add(panel8, BorderLayout.CENTER);

			ResultSet rs2 = DBManager.getResultSet(
					"SELECT artist.name, song.name, substring(length, 4) as time, titlesong, count(song.name) as cnt, 2023 - substring(user.birth, 1, 4) as sub, song.serial FROM 충북21.artist inner join album on album.artist = artist.serial inner join song on song.album = album.serial inner join history on history.song = song.serial inner join user on user.serial = history.user where artist.name = '"
							+ str + "' group by song.name order by cnt desc, song.serial asc limit 5;");

			int cnt = 1;
			while (rs2.next()) {
				JPanel p = new JPanel(new BorderLayout());
				p.setPreferredSize(new Dimension(0, 35));
				p.setBackground(c);
				panel8.add(p);

				JPanel p2 = new JPanel(new BorderLayout());
				p2.setBackground(c);
				p.add(p2, BorderLayout.WEST);

				JPanel p3 = new JPanel(new BorderLayout());
				p3.setPreferredSize(new Dimension(20, 20));
				p3.setBackground(c);
				p2.add(p3, BorderLayout.WEST);

				JLabel lbl = new JLabel("★", JLabel.CENTER);
				lbl.setForeground(Color.white);
				lbl.setFont(new Font("맑은 고딕", Font.BOLD, 13));
				lbl.setVisible(false);
				p3.add(lbl);

				if (rs2.getInt(4) == 1) {
					lbl.setVisible(true);
				}

				JPanel p4 = new JPanel(new BorderLayout());
				p4.setBackground(c);
				p2.add(p4, BorderLayout.CENTER);

				JLabel lbl2 = new JLabel(String.valueOf(cnt), JLabel.CENTER);
				lbl2.setFont(new Font("맑은 고딕", Font.BOLD, 13));
				lbl2.setForeground(Color.white);
				lbl2.setBorder(new EmptyBorder(0, 5, 0, 12));
				p4.add(lbl2, BorderLayout.WEST);

				JLabel lbl3 = new JLabel(rs2.getString(2), JLabel.CENTER);
				lbl3.setFont(new Font("맑은 고딕", Font.BOLD, 13));
				lbl3.setForeground(Color.white);
				p4.add(lbl3, BorderLayout.CENTER);

				JPanel p5 = new JPanel(new BorderLayout());
				p5.setBackground(c);
				p.add(p5, BorderLayout.EAST);

				JLabel lb4 = new JLabel("♡", JLabel.CENTER); // ♥ ♡
				lb4.setFont(new Font("맑은 고딕", Font.BOLD, 13));
				lb4.setForeground(Color.white);
				p5.add(lb4, BorderLayout.WEST);

				for (int i = 0; i < arrayList.size(); i++) {
					if (arrayList.get(i).equals(lbl3.getText())) {
						lb4.setText("♥");
					}
				}
				int serial = rs2.getInt(7);
				lb4.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {

						if (lb4.getText().equals("♡")) {
							lb4.setText("♥");
							try (PreparedStatement pst = DBManager
									.getPreparedStatement("insert into favorite values(?, ?, ?)")) {

								pst.setString(1, "0");
								pst.setInt(2, uno);
								pst.setInt(3, serial);

								pst.executeUpdate();

							} catch (Exception e2) {
								e2.printStackTrace();
							}
							return;
						} else {
							lb4.setText("♡");
							try {

								DBManager.updateQ("delete from favorite where user = " + uno + " and song = " + serial);

							} catch (Exception e2) {
								e2.printStackTrace();
							}
							return;
						}
					}
				});

				JLabel lbl5 = new JLabel(rs2.getString("time"), JLabel.CENTER);
				lbl5.setFont(new Font("맑은 고딕", Font.BOLD, 13));
				lbl5.setForeground(Color.white);
				lbl5.setBorder(new EmptyBorder(0, 8, 0, 15));
				p5.add(lbl5, BorderLayout.CENTER);

				p.addMouseListener(new MouseAdapter() {

					@Override
					public void mouseEntered(MouseEvent e) {
						p.setBackground(Color.gray);
						p2.setBackground(Color.gray);
						p3.setBackground(Color.gray);
						p4.setBackground(Color.gray);
						p5.setBackground(Color.gray);
					}

					@Override
					public void mouseExited(MouseEvent e) {
						p.setBackground(c);
						p2.setBackground(c);
						p3.setBackground(c);
						p4.setBackground(c);
						p5.setBackground(c);
					}
				});

				cnt++;
			}
			JPanel panel9 = new JPanel(new BorderLayout());
			panel9.setBackground(c);
			panel9.setPreferredSize(new Dimension(240, 0));
			panel9.setBorder(new EmptyBorder(0, 20, 0, 0));
			panel7.add(panel9, BorderLayout.EAST);

			JPanel panel10 = new JPanel(new BorderLayout()) {

				@Override
				protected void paintComponent(Graphics g) {
					super.paintComponent(g);

					int a10 = 0, a20 = 0, a30 = 0, a40 = 0, a50 = 0;
					try {

						ResultSet rs3 = DBManager.getResultSet(
								"SELECT artist.name, song.name, substring(length, 4) as time, titlesong, count(song.name) as cnt, 2023 - substring(user.birth, 1, 4) as sub FROM 충북21.artist inner join album on album.artist = artist.serial inner join song on song.album = album.serial inner join history on history.song = song.serial inner join user on user.serial = history.user where artist.name = '"
										+ str + "' group by song.name order by cnt desc, song.serial asc;");

						while (rs3.next()) {
							if (rs3.getInt(6) > 0 && rs3.getInt(6) < 20) {
								a10++;
							} else if (rs3.getInt(6) >= 20 && rs3.getInt(6) < 30) {
								a20++;
							} else if (rs3.getInt(6) >= 30 && rs3.getInt(6) < 40) {
								a30++;
							} else if (rs3.getInt(6) >= 40 && rs3.getInt(6) < 50) {
								a40++;
							} else {
								a50++;
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					List<Integer> list = List.of(a10, a20, a30, a40, a50);
					int max = Collections.max(list);

					for (int i = 0; i < 5; i++) {
						int v = i == 0 ? a10 : i == 1 ? a20 : i == 2 ? a30 : i == 3 ? a40 : a50;
						int v2 = i == 0 ? 0 : i == 1 ? 40 : i == 2 ? 80 : i == 3 ? 120 : 160;

						if (max == v) {
							g.setColor(Color.red);
						} else {
							g.setColor(Color.white);
						}
						g.fillRect(v2, 170, 30, -(v * 5));

						g.setColor(Color.white);
						g.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
						g.drawString(new String[] { "10대", "20대", "30대", "40대", "50대" }[i], v2, 183);
					}

				}
			};
			panel10.setBackground(c);
			panel9.add(panel10, BorderLayout.CENTER);

			pl = new JPanel();
			pl.setLayout(new BoxLayout(pl, BoxLayout.Y_AXIS));
			pl.setBackground(c);
			Center.add(pl, BorderLayout.SOUTH);

			ResultSet rs5 = DBManager.getResultSet(
					"SELECT *, substring(album.release, 1, 4) as sub FROM 충북21.album inner join artist on artist.serial = album.artist inner join category on category.serial = album.category where artist.name = '"
							+ str + "' order by `release` desc;");

			ArrayList<String> array = new ArrayList<String>();
			ArrayList<String> array2 = new ArrayList<String>();
			ArrayList<String> array3 = new ArrayList<String>();
			ArrayList<String> array4 = new ArrayList<String>();
			ArrayList<String> array5 = new ArrayList<String>();
			int nt = 0;
			while (rs5.next()) {

				array.add(rs5.getString(1));
				array2.add(rs5.getString(2));
				array3.add(rs5.getString(7));
				array4.add(rs5.getString(10));
				array5.add(rs5.getString("sub"));

				nt++;
			}

			for (int j = 0; j < nt; j++) {

				JPanel pl2 = new JPanel(new BorderLayout());
				pl2.setBackground(c);
				pl.add(pl2);

				JPanel pl3 = new JPanel(new BorderLayout());
				pl3.setBackground(c);
				pl3.setBorder(
						new CompoundBorder(new MatteBorder(0, 0, 2, 0, Color.gray), new EmptyBorder(10, 0, 5, 0)));
				pl2.add(pl3, BorderLayout.NORTH);

				JLabel label2 = new JLabel(new ImageIcon(new ImageIcon("지급자료/images/album/" + array.get(j) + ".jpg")
						.getImage().getScaledInstance(180, 180, 4)));
				pl3.add(label2, BorderLayout.WEST);

				JPanel pl4 = new JPanel(new BorderLayout());
				pl4.setBorder(new EmptyBorder(0, 10, 0, 0));
				pl4.setBackground(c);
				pl3.add(pl4, BorderLayout.CENTER);

				JPanel pl5 = new JPanel(new GridLayout(3, 1));
				pl5.setBackground(c);
				pl5.setBorder(new EmptyBorder(40, 0, 40, 0));
				pl4.add(pl5, BorderLayout.CENTER);

				for (int k = 0; k < 3; k++) {
					JLabel label3 = new JLabel(
							new String[] { array2.get(j), array3.get(j), array4.get(j) + " · " + array5.get(j) }[k]);
					label3.setFont(new Font("맑은 고딕", Font.BOLD, 13));
					label3.setForeground(Color.white);
					pl5.add(label3);
				}

				JLabel label3 = new JLabel("모두 보기");
				label3.setForeground(Color.white);
				label3.setFont(new Font("맑은 고딕", Font.BOLD, 13));
				pl4.add(label3, BorderLayout.SOUTH);

				ResultSet re = DBManager.getResultSet(
						"SELECT count(*) FROM 충북21.album inner join artist on artist.serial = album.artist inner join song on song.album = album.serial where artist.name = '"
								+ str + "' and album.name like '%"
								+ array2.get(j).replace("Love Yourself ", "").replace("'", "")
								+ "%' order by `release` desc;");

				if (re.next()) {

					pl6 = new JPanel(new GridLayout(re.getInt(1), 1));
					pl6.setBackground(c);
					pl6.setBorder(new EmptyBorder(10, 0, 0, 0));
					pl2.add(pl6, BorderLayout.CENTER);
				}

				ResultSet re2 = DBManager.getResultSet(
						"SELECT album.serial, album.name, artist.name, song.name, substring(length, 4) as sub, titlesong, song.serial FROM 충북21.album inner join artist on artist.serial = album.artist inner join song on song.album = album.serial where artist.name = '"
								+ str + "' and album.name like '%"
								+ array2.get(j).replace("Love Yourself ", "").replace("'", "")
								+ "%' order by `release` desc;");

				int ct = 1;
				while (re2.next()) {

					JPanel pl7 = new JPanel(new BorderLayout());
					pl7.setPreferredSize(new Dimension(0, 35));
					pl7.setBackground(c);
					pl6.add(pl7);

					JPanel p2 = new JPanel(new BorderLayout());
					p2.setBackground(c);
					pl7.add(p2, BorderLayout.WEST);

					JPanel p3 = new JPanel(new BorderLayout());
					p3.setPreferredSize(new Dimension(20, 20));
					p3.setBackground(c);
					p2.add(p3, BorderLayout.WEST);

					JLabel lbl = new JLabel("★", JLabel.CENTER);
					lbl.setForeground(Color.white);
					lbl.setFont(new Font("맑은 고딕", Font.BOLD, 13));
					lbl.setVisible(false);
					p3.add(lbl);

					if (re2.getInt(6) == 1) {
						lbl.setVisible(true);
					}

					JPanel p4 = new JPanel(new BorderLayout());
					p4.setBackground(c);
					p2.add(p4, BorderLayout.CENTER);

					JLabel lbl2 = new JLabel(String.valueOf(ct), JLabel.CENTER);
					lbl2.setFont(new Font("맑은 고딕", Font.BOLD, 13));
					lbl2.setForeground(Color.white);
					lbl2.setBorder(new EmptyBorder(0, 5, 0, 12));
					p4.add(lbl2, BorderLayout.WEST);

					JLabel lbl3 = new JLabel(re2.getString(4), JLabel.CENTER);
					lbl3.setFont(new Font("맑은 고딕", Font.BOLD, 13));
					lbl3.setForeground(Color.white);
					p4.add(lbl3, BorderLayout.CENTER);

					JPanel p5 = new JPanel(new BorderLayout());
					p5.setBackground(c);
					pl7.add(p5, BorderLayout.EAST);

					JLabel lb4 = new JLabel("♡", JLabel.CENTER); // ♥ ♡
					lb4.setFont(new Font("맑은 고딕", Font.BOLD, 13));
					lb4.setForeground(Color.white);
					p5.add(lb4, BorderLayout.WEST);

					for (int i = 0; i < arrayList.size(); i++) {
						if (arrayList.get(i).equals(lbl3.getText())) {
							lb4.setText("♥");
						}
					}

					int serial = re2.getInt(7);
					lb4.addMouseListener(new MouseAdapter() {
						@Override
						public void mousePressed(MouseEvent e) {

							if (lb4.getText().equals("♡")) {
								lb4.setText("♥");
								try (PreparedStatement pst = DBManager
										.getPreparedStatement("insert into favorite values(?, ?, ?)")) {

									pst.setString(1, "0");
									pst.setInt(2, uno);
									pst.setInt(3, serial);

									pst.executeUpdate();

								} catch (Exception e2) {
									e2.printStackTrace();
								}
								return;
							} else {
								lb4.setText("♡");
								try {

									DBManager.updateQ(
											"delete from favorite where user = " + uno + " and song = " + serial);

								} catch (Exception e2) {
									e2.printStackTrace();
								}
								return;
							}
						}
					});

					JLabel lbl5 = new JLabel(re2.getString("sub"), JLabel.CENTER);
					lbl5.setFont(new Font("맑은 고딕", Font.BOLD, 13));
					lbl5.setForeground(Color.white);
					lbl5.setBorder(new EmptyBorder(0, 8, 0, 15));
					p5.add(lbl5, BorderLayout.CENTER);

					pl7.addMouseListener(new MouseAdapter() {

						@Override
						public void mouseEntered(MouseEvent e) {
							pl7.setBackground(Color.gray);
							p2.setBackground(Color.gray);
							p3.setBackground(Color.gray);
							p4.setBackground(Color.gray);
							p5.setBackground(Color.gray);
						}

						@Override
						public void mouseExited(MouseEvent e) {
							pl7.setBackground(c);
							p2.setBackground(c);
							p3.setBackground(c);
							p4.setBackground(c);
							p5.setBackground(c);
						}
					});

					ct++;
				}

			}

			Center.repaint();
			Center.validate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void Search2(int grid, String name) {
		try {
			Center.removeAll();

			JPanel panel2 = new JPanel(new BorderLayout());
			panel2.setBackground(c);
			panel2.setBorder(new MatteBorder(0, 0, 2, 0, Color.gray));
			Center.add(panel2, BorderLayout.NORTH);

			JLabel label = new JLabel(name + " " + grid + "건");
			label.setForeground(Color.white);
			label.setFont(new Font("맑은 고딕", Font.BOLD, 14));
			panel2.add(label);

			int g = grid % 5 == 0 ? grid / 5 : (grid / 5) + 1;

			JPanel panel3 = new JPanel(new GridLayout(g, 5, 10, 10));
			panel3.setBackground(c);
			panel3.setBorder(new EmptyBorder(10, 0, 0, 0));
			Center.add(panel3, BorderLayout.CENTER);

			String str = "";
			String str2 = "";
			int it = 0;
			if (name.equals("음악")) {
				str = "SELECT * FROM 충북21.song inner join album on album.serial = song.album where song.name like '%"
						+ field.getText() + "%';";
				str2 = "지급자료/images/album/";
				it = 4;
			}
			if (name.equals("앨범")) {
				str = "SELECT * FROM 충북21.album where name like '%" + field.getText() + "%';";
				str2 = "지급자료/images/album/";
				it = 1;
			}
			if (name.equals("아티스트")) {
				str = "SELECT * FROM 충북21.artist where name like '%" + field.getText() + "%';";
				str2 = "지급자료/images/artist/";
				it = 1;
			}
			if (name.equals("플레이리스트")) {
				str = "SELECT * FROM 충북21.playlist where name like '%" + field.getText() + "%';";
				str2 = "지급자료/images/album/";
				it = 1;
			}

			ResultSet rs = DBManager.getResultSet(str);

			while (rs.next()) {
				JPanel panel4 = new JPanel(new BorderLayout());
				panel4.setBackground(c);
				panel4.setPreferredSize(new Dimension(0, 220));
				panel4.setBorder(new EmptyBorder(5, 0, 0, 0));
				panel3.add(panel4);

				JLabel label2 = new JLabel(new ImageIcon(
						new ImageIcon(str2 + rs.getString(it) + ".jpg").getImage().getScaledInstance(200, 200, 4)));
				panel4.add(label2, BorderLayout.CENTER);

				JLabel label3 = new JLabel(rs.getString(2), JLabel.CENTER);
				label3.setForeground(Color.white);
				label3.setFont(new Font("맑은 고딕", Font.BOLD, 13));
				panel4.add(label3, BorderLayout.SOUTH);

				panel4.addMouseListener(new MouseAdapter() {

					@Override
					public void mouseEntered(MouseEvent e) {
						panel4.setBackground(new Color(96, 96, 96));
					}

					@Override
					public void mouseExited(MouseEvent e) {
						panel4.setBackground(c);
					}
				});
				label2.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						if (e.getButton() == MouseEvent.BUTTON3) {

							JPopupMenu menu = new JPopupMenu();
							menu.setFont(new Font("맑은 고딕", Font.BOLD, 13));
							menu.add("앨범으로 이동").addActionListener(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent e) {
									album(label3.getText());
								}
							});
							menu.add("아티스트로 이동").addActionListener(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent e) {
									try {

										ResultSet rs = DBManager.getResultSet(
												"SELECT * FROM 충북21.album inner join artist on artist.serial = album.artist where album.name = '"
														+ label3.getText() + "';");

										if (rs.next()) {
											String st = rs.getString(7);
											artist(st);
										}

									} catch (Exception e2) {
										e2.printStackTrace();
									}
								}
							});
							menu.show(panel4, e.getX(), e.getY());
						}
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						panel4.setBackground(new Color(96, 96, 96));
					}

					@Override
					public void mouseExited(MouseEvent e) {
						panel4.setBackground(c);
					}
				});
			}

			Center.repaint();
			Center.validate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void Ns(String str) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// UI 수정 코드
				Center.revalidate();
			}
		});
		try {
			Center.removeAll();

			JPanel panel = new JPanel(new BorderLayout());
			panel.setBackground(c);
			Center.add(panel);

			JPanel panel2 = new JPanel(new BorderLayout());
			panel2.setBackground(c);
			panel2.setBorder(new EmptyBorder(0, 0, 7, 0));
			panel.add(panel2, BorderLayout.NORTH);

			ResultSet rs = DBManager.getResultSet("SELECT * FROM 충북21.artist where name = '" + str + "';");

			if (rs.next()) {
				JLabel label = new JLabel(new ImageIcon(new ImageIcon("지급자료/images/artist/" + rs.getString(1) + ".jpg")
						.getImage().getScaledInstance(180, 180, 4)));
				panel2.add(label, BorderLayout.WEST);

				JLabel label2 = new JLabel(rs.getString(2));
				label2.setForeground(Color.white);
				label2.setFont(new Font("맑은 고딕", Font.BOLD, 24));
				label2.setBorder(new EmptyBorder(0, 10, 0, 0));
				panel2.add(label2, BorderLayout.CENTER);
			}

			panel33 = new JPanel(new BorderLayout());
			panel33.setBorder(new MatteBorder(2, 0, 0, 0, Color.gray));
			panel33.setBackground(c);
			panel.add(panel33, BorderLayout.CENTER);

			Npanel = new JPanel(new BorderLayout());
			Npanel.setBackground(c);
			panel33.add(Npanel, BorderLayout.NORTH);

			JPanel panel5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
			panel5.setBackground(c);
			Npanel.add(panel5, BorderLayout.NORTH);

			for (int i = 0; i < 2; i++) {
				JButton btn = new JButton(new String[] { "개요", "소개" }[i]);
				btn.setBackground(Color.white);
				btn.setFont(new Font("맑은 고딕", Font.BOLD, 13));
				panel5.add(btn);

				int j = i;
				btn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (j == 0) {
							m1();
							m3();
							artist(str);
						}
						if (j == 1) {
							try {
								Center.removeAll();

								m1();
								m3();
								Ns(str);

								Np(str);

								Center.repaint();
								Center.revalidate();

								scrollPane.getViewport().setViewPosition(new Point(0, 0));
								JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
								verticalScrollBar.setValue(0);

							} catch (Exception e2) {
								e2.printStackTrace();
							}
						}
					}
				});
			}
			Center.repaint();
			Center.validate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void Np(String str) {
		try {
			JPanel panel = new JPanel(new BorderLayout());
			panel.setBackground(c);
			Npanel.add(panel, BorderLayout.CENTER);

			JPanel pJPanel = new JPanel(new BorderLayout());
			pJPanel.setBackground(c);
			panel.add(pJPanel, BorderLayout.WEST);

			ResultSet rs = DBManager.getResultSet("SELECT * FROM 충북21.artist where name = '" + str + "';");

			Anum = 0;
			if (rs.next()) {
				Anum = rs.getInt(1);

				JLabel label = new JLabel("세계 순위");
				label.setForeground(Color.white);
				label.setBorder(new EmptyBorder(10, 0, 10, 0));
				label.setFont(new Font("맑은 고딕", Font.BOLD, 16));
				pJPanel.add(label, BorderLayout.NORTH);

				JTextArea area = new JTextArea(rs.getString(3));
				area.setBackground(c);
				area.setFont(new Font("맑은 고딕", Font.BOLD, 11));
				area.setLineWrap(true);
				area.setPreferredSize(new Dimension(280, 50));
				area.setForeground(Color.white);
				pJPanel.add(area, BorderLayout.CENTER);
			}

			JPanel panel21 = new JPanel(new BorderLayout());
			panel21.setBackground(c);
			panel.add(panel21, BorderLayout.CENTER);

			JPanel panel31 = new JPanel(new BorderLayout());
			panel31.setBackground(c);
			panel21.add(panel31, BorderLayout.NORTH);

			JTextArea area = new JTextArea();
			area.setBackground(new Color(96, 96, 96));
			area.setFont(new Font("맑은 고딕", Font.BOLD, 13));
			area.setLineWrap(true);
			area.setPreferredSize(new Dimension(0, 90));
			area.setBorder(new LineBorder(Color.white));
			area.setForeground(Color.white);
			panel31.add(area, BorderLayout.CENTER);

			JPanel panel41 = new JPanel(new BorderLayout());
			panel41.setBackground(c);
			panel41.setPreferredSize(new Dimension(0, 50));
			panel31.add(panel41, BorderLayout.SOUTH);

			JPanel panel51 = new JPanel(new FlowLayout());
			panel51.setBackground(c);
			panel41.add(panel51, BorderLayout.WEST);

			for (int k = 0; k < 5; k++) {
				star[k] = new JLabel("☆");
				star[k].setForeground(Color.orange);
				star[k].setFont(new Font("맑은 고딕", Font.BOLD, 18));
				panel51.add(star[k]);
			}
			Snum = 0;
			star[0].addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					star[0].setText("★");
					star[1].setText("☆");
					star[2].setText("☆");
					star[3].setText("☆");
					star[4].setText("☆");
					Snum = 1;
				};
			});
			star[1].addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					star[0].setText("★");
					star[1].setText("★");
					star[2].setText("☆");
					star[3].setText("☆");
					star[4].setText("☆");
					Snum = 2;
				};
			});
			star[2].addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					star[0].setText("★");
					star[1].setText("★");
					star[2].setText("★");
					star[3].setText("☆");
					star[4].setText("☆");
					Snum = 3;
				};
			});
			star[3].addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					star[0].setText("★");
					star[1].setText("★");
					star[2].setText("★");
					star[3].setText("★");
					star[4].setText("☆");
					Snum = 4;
				};
			});
			star[4].addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					star[0].setText("★");
					star[1].setText("★");
					star[2].setText("★");
					star[3].setText("★");
					star[4].setText("★");
					Snum = 5;
				};
			});
			JPanel panel6 = new JPanel(new BorderLayout());
			panel6.setBackground(c);
			panel6.setBorder(new EmptyBorder(10, 0, 5, 0));
			panel6.setPreferredSize(new Dimension(70, 30));
			panel41.add(panel6, BorderLayout.EAST);

			JButton btn = new JButton("등록");
			btn.setBackground(Color.white);
			btn.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
			panel6.add(btn);

			ResultSet rs2 = DBManager.getResultSet(
					"SELECT count(*) FROM 충북21.artist inner join community on community.artist = artist.serial inner join user on user.serial = community.user where artist.name = '"
							+ str + "' order by date desc;");

			int v = 0;
			if (rs2.next()) {
				v = rs2.getInt(1);
			}

			JPanel panel71 = new JPanel(new GridLayout(v, 1));
			panel71.setBackground(c);
			panel21.add(panel71, BorderLayout.CENTER);

			ResultSet rs3 = DBManager.getResultSet(
					"SELECT user.name, community.date, community.rate, community.content FROM 충북21.artist inner join community on community.artist = artist.serial inner join user on user.serial = community.user where artist.name = '"
							+ str + "' order by date desc, community.serial desc;");

			while (rs3.next()) {
				JPanel p = new JPanel(new BorderLayout());
				p.setBackground(c);
				panel71.add(p);

				JPanel p2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
				p2.setBackground(c);
				p.add(p2, BorderLayout.NORTH);

				JLabel label = new JLabel(rs3.getString(1) + " -");
				label.setFont(new Font("맑은 고딕", Font.BOLD, 13));
				label.setForeground(Color.white);
				p2.add(label);

				for (int j2 = 0; j2 < 5; j2++) {
					star2[j2] = new JLabel("☆");
					star2[j2].setForeground(Color.orange);
					star2[j2].setFont(new Font("맑은 고딕", Font.BOLD, 13));
					star2[j2].setBorder(new EmptyBorder(0, 0, 5, 0));
					p2.add(star2[j2]);
				}
				if (rs3.getInt(3) == 1) {
					star2[0].setText("★");
					star2[1].setText("☆");
					star2[2].setText("☆");
					star2[3].setText("☆");
					star2[4].setText("☆");
				} else if (rs3.getInt(3) == 2) {
					star2[0].setText("★");
					star2[1].setText("★");
					star2[2].setText("☆");
					star2[3].setText("☆");
					star2[4].setText("☆");
				} else if (rs3.getInt(3) == 3) {
					star2[0].setText("★");
					star2[1].setText("★");
					star2[2].setText("★");
					star2[3].setText("☆");
					star2[4].setText("☆");
				} else if (rs3.getInt(3) == 4) {
					star2[0].setText("★");
					star2[1].setText("★");
					star2[2].setText("★");
					star2[3].setText("★");
					star2[4].setText("☆");
				} else {
					star2[0].setText("★");
					star2[1].setText("★");
					star2[2].setText("★");
					star2[3].setText("★");
					star2[4].setText("★");
				}
				JLabel label2 = new JLabel(" " + rs3.getString(2) + " 게시");
				label2.setFont(new Font("맑은 고딕", Font.BOLD, 13));
				label2.setForeground(Color.white);
				p2.add(label2);

				JPanel pp = new JPanel(new BorderLayout());
				pp.setBackground(c);
				pp.setBorder(new EmptyBorder(0, 5, 0, 5));
				pp.setPreferredSize(new Dimension(0, 90));
				p.add(pp, BorderLayout.CENTER);

				JTextArea area2 = new JTextArea(rs3.getString(4));
				area2.setBackground(new Color(96, 96, 96));
				area2.setFont(new Font("맑은 고딕", Font.BOLD, 13));
				area2.setLineWrap(true);
				area2.setBorder(new LineBorder(Color.white));
				area2.setForeground(Color.white);
				pp.add(area2);
			}
			btn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					if (area.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "내용을 입력해야합니다.", "경고", JOptionPane.ERROR_MESSAGE);
						return;
					}
					if (Snum == 0) {
						JOptionPane.showMessageDialog(null, "평점을 입력해야합니다.", "경고", JOptionPane.ERROR_MESSAGE);
						return;
					}
//					try (PreparedStatement pst = DBManager
//							.getPreparedStatement("insert into community values(?, ?, ?, ?, ?, ?)")) {
//
//						LocalDate now = LocalDate.now();
//
//						pst.setString(1, "0");
//						pst.setInt(2, uno);
//						pst.setInt(3, Anum);
//						pst.setInt(4, Snum);
//						pst.setString(5, area.getText());
//						pst.setObject(6, now);
//
//						pst.executeUpdate();
//
//						Center.removeAll();
//
//						Ns(str);
//
//						Np(str);
//
//						Center.repaint();
//						Center.revalidate();
//
//						scrollPane.getViewport().setViewPosition(new Point(0, 0));
//						JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
//						verticalScrollBar.setValue(0);
//
//					} catch (Exception e2) {
//						e2.printStackTrace();
//					}
					
//****************************************************************************************************************
					
					try {
					    
					    ResultSet rs = DBManager.getResultSet("SELECT serial FROM user WHERE serial = " + uno);
					    if (!rs.next()) {
					        JOptionPane.showMessageDialog(null, "유효하지 않은 사용자입니다.", "오류", JOptionPane.ERROR_MESSAGE);
					        return;
					    }

					    
					    try (PreparedStatement pst = DBManager.getPreparedStatement(
					            "INSERT INTO community (serial, user, artist, rate, content, date) VALUES (?, ?, ?, ?, ?, ?)")) {

					        LocalDate now = LocalDate.now();

					        pst.setInt(1, 0);  // AUTO_INCREMENT이면 0 또는 NULL로 설정
					        pst.setInt(2, uno);
					        pst.setInt(3, Anum);
					        pst.setInt(4, Snum);
					        pst.setString(5, area.getText());
					        pst.setObject(6, now);

					        pst.executeUpdate();

					        Center.removeAll();
					        Ns(str);
					        Np(str);
					        Center.repaint();
					        Center.revalidate();

					        scrollPane.getViewport().setViewPosition(new Point(0, 0));
					        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
					        verticalScrollBar.setValue(0);
					    }

					} catch (Exception e2) {
					    e2.printStackTrace();
					}
					
//********************************************************************************************************
					
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void like() {
		try {
			Center.removeAll();

			JPanel panel = new JPanel(new BorderLayout());
			panel.setBackground(c);
			Center.add(panel);

			JPanel panel2 = new JPanel(new BorderLayout());
			panel2.setBorder(new MatteBorder(0, 0, 2, 0, Color.white));
			panel2.setBackground(c);
			panel.add(panel2, BorderLayout.NORTH);

			JLabel label = new JLabel("좋아요 한 음악");
			label.setForeground(Color.white);
			label.setFont(new Font("맑은 고딕", Font.BOLD, 16));
			panel2.add(label, BorderLayout.NORTH);

			JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
			panel3.setBackground(c);
			panel2.add(panel3, BorderLayout.CENTER);

			JButton btn = new JButton("재생하기");
			btn.setBackground(Color.white);
			panel3.add(btn);

			JLabel label2 = new JLabel("총 " + arrayList.size() + "개의 음악");
			label2.setForeground(Color.white);
			label2.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
			panel3.add(label2);

			JPanel panel4 = new JPanel();
			panel.add(panel4, BorderLayout.CENTER);
			panel4.setBackground(c);
			panel4.setLayout(new BoxLayout(panel4, BoxLayout.Y_AXIS));
			panel4.setBorder(new EmptyBorder(10, 0, 0, 0));

			ResultSet rs2 = DBManager.getResultSet(
					"SELECT song.name, substring(song.length, 4) as time, titlesong, song.serial FROM 충북21.favorite inner join user on user.serial"
							+ " = favorite.user inner join song on song.serial"
							+ " = favorite.song where user.serial = " + uno + ";");
			int empty = 0;
			int cnt = 1;
			while (rs2.next()) {

				empty = rs2.getRow();

				JPanel panel31 = new JPanel(new BorderLayout());
				panel31.setPreferredSize(new Dimension(0, 40));
				panel31.setBackground(c);
				panel4.add(panel31);

				JPanel panel41 = new JPanel(new BorderLayout());
				panel41.setBackground(c);
				panel31.add(panel41, BorderLayout.WEST);

				JPanel panel6 = new JPanel(new BorderLayout());
				panel6.setPreferredSize(new Dimension(20, 20));
				panel6.setBackground(c);
				panel41.add(panel6, BorderLayout.WEST);

				JLabel label1 = new JLabel("★", JLabel.CENTER);
				label1.setForeground(Color.white);
				label1.setFont(new Font("맑은 고딕", Font.BOLD, 13));
				label1.setVisible(false);
				panel6.add(label1);

				if (rs2.getInt("titlesong") == 1) {
					label1.setVisible(true);
				}

				JPanel panel7 = new JPanel(new BorderLayout());
				panel7.setBackground(c);
				panel41.add(panel7, BorderLayout.CENTER);

				JLabel label21 = new JLabel(String.valueOf(cnt), JLabel.CENTER);
				label21.setFont(new Font("맑은 고딕", Font.BOLD, 13));
				label21.setForeground(Color.white);
				label21.setBorder(new EmptyBorder(0, 5, 0, 12));
				panel7.add(label21, BorderLayout.WEST);

				JLabel label3 = new JLabel(rs2.getString("name"), JLabel.CENTER);
				label3.setFont(new Font("맑은 고딕", Font.BOLD, 13));
				label3.setForeground(Color.white);
				panel7.add(label3, BorderLayout.CENTER);

				JPanel panel8 = new JPanel(new BorderLayout());
				panel8.setBackground(c);
				panel31.add(panel8, BorderLayout.EAST);

				JLabel label4 = new JLabel("♡", JLabel.CENTER); // ♥ ♡
				label4.setFont(new Font("맑은 고딕", Font.BOLD, 13));
				label4.setForeground(Color.white);
				panel8.add(label4, BorderLayout.WEST);

				for (int i = 0; i < arrayList.size(); i++) {
					if (arrayList.get(i).equals(label3.getText())) {
						label4.setText("♥");
					}
				}

				int serial = rs2.getInt(4);
				label4.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {

						if (label4.getText().equals("♡")) {
							label4.setText("♥");
							try (PreparedStatement pst = DBManager
									.getPreparedStatement("insert into favorite values(?, ?, ?)")) {

								pst.setString(1, "0");
								pst.setInt(2, uno);
								pst.setInt(3, serial);

								pst.executeUpdate();

							} catch (Exception e2) {
								e2.printStackTrace();
							}
							return;
						} else {
							label4.setText("♡");
							try {

								DBManager.updateQ("delete from favorite where user = " + uno + " and song = " + serial);

							} catch (Exception e2) {
								e2.printStackTrace();
							}
							return;
						}
					}
				});

				JLabel label5 = new JLabel(rs2.getString("time"), JLabel.CENTER);
				label5.setFont(new Font("맑은 고딕", Font.BOLD, 13));
				label5.setForeground(Color.white);
				label5.setBorder(new EmptyBorder(0, 8, 0, 15));
				panel8.add(label5, BorderLayout.CENTER);

				panel31.addMouseListener(new MouseAdapter() {

					@Override
					public void mousePressed(MouseEvent e) {

						if (e.getButton() == MouseEvent.BUTTON3) {
							JPopupMenu menu = new JPopupMenu();
							menu.setFont(new Font("맑은 고딕", Font.BOLD, 13));
							menu.add("아티스트로 이동").addActionListener(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent e) {
									try {

										ResultSet rs = DBManager.getResultSet(
												"SELECT * FROM 충북21.album inner join artist on artist.serial = album.artist where album.name = '"
														+ label3.getText() + "';");

										if (rs.next()) {
											String st = rs.getString(7);
											artist(st);
										}

									} catch (Exception e2) {
										e2.printStackTrace();
									}
								}
							});
							menu.add("플레이리스트에 추가").addActionListener(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent e) {

								}
							});
							menu.show(panel41, e.getX(), e.getY());
						}
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						panel31.setBackground(Color.gray);
						panel41.setBackground(Color.gray);
						panel6.setBackground(Color.gray);
						panel7.setBackground(Color.gray);
						panel8.setBackground(Color.gray);
					}

					@Override
					public void mouseExited(MouseEvent e) {
						panel31.setBackground(c);
						panel41.setBackground(c);
						panel6.setBackground(c);
						panel7.setBackground(c);
						panel8.setBackground(c);
					}
				});

				cnt++;
			}
			if (empty < 16) {
				panel4.setBorder(new EmptyBorder(10, 0, 30 * (16 - empty), 0));
				if (empty < 8) {
					panel4.setBorder(new EmptyBorder(10, 0, 30 * (18 - empty), 0));
				}
			}

			Center.repaint();
			Center.validate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void history() {
		try {
			Center.removeAll();

			JPanel panel = new JPanel(new BorderLayout());
			panel.setBackground(c);
			Center.add(panel);

			JPanel panel2 = new JPanel(new BorderLayout());
			panel2.setBackground(c);
			panel2.setBorder(new MatteBorder(0, 0, 2, 0, Color.LIGHT_GRAY));
			panel.add(panel2, BorderLayout.NORTH);

			JLabel label = new JLabel("최근 내가 들은 음악");
			label.setForeground(Color.white);
			label.setFont(new Font("맑은 고딕", Font.BOLD, 16));
			panel2.add(label);

			JPanel panel3 = new JPanel();
			panel3.setBackground(c);
			panel.add(panel3, BorderLayout.CENTER);
			panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));
			panel3.setBorder(new EmptyBorder(10, 0, 0, 0));

			ResultSet rs2 = DBManager.getResultSet(
					"SELECT *, substring(song.length, 4) as time FROM 충북21.history inner join song on song.serial = history.song where user = "
							+ uno + ";");
			int empty = 0;
			int cnt = 1;
			while (rs2.next()) {

				empty = rs2.getRow();

				JPanel panel31 = new JPanel(new BorderLayout());
				panel31.setPreferredSize(new Dimension(0, 40));
				panel31.setBackground(c);
				panel3.add(panel31);

				JPanel panel41 = new JPanel(new BorderLayout());
				panel41.setBackground(c);
				panel31.add(panel41, BorderLayout.WEST);

				JPanel panel6 = new JPanel(new BorderLayout());
				panel6.setPreferredSize(new Dimension(20, 20));
				panel6.setBackground(c);
				panel41.add(panel6, BorderLayout.WEST);

				JLabel label1 = new JLabel("★", JLabel.CENTER);
				label1.setForeground(Color.white);
				label1.setFont(new Font("맑은 고딕", Font.BOLD, 13));
				label1.setVisible(false);
				panel6.add(label1);

				if (rs2.getInt(9) == 1) {
					label1.setVisible(true);
				}

				JPanel panel7 = new JPanel(new BorderLayout());
				panel7.setBackground(c);
				panel41.add(panel7, BorderLayout.CENTER);

				JLabel label21 = new JLabel(String.valueOf(cnt), JLabel.CENTER);
				label21.setFont(new Font("맑은 고딕", Font.BOLD, 13));
				label21.setForeground(Color.white);
				label21.setBorder(new EmptyBorder(0, 5, 0, 12));
				panel7.add(label21, BorderLayout.WEST);

				JLabel label3 = new JLabel(rs2.getString(6), JLabel.CENTER);
				label3.setFont(new Font("맑은 고딕", Font.BOLD, 13));
				label3.setForeground(Color.white);
				panel7.add(label3, BorderLayout.CENTER);

				JPanel panel8 = new JPanel(new BorderLayout());
				panel8.setBackground(c);
				panel31.add(panel8, BorderLayout.EAST);

				JLabel label4 = new JLabel("♡", JLabel.CENTER); // ♥ ♡
				label4.setFont(new Font("맑은 고딕", Font.BOLD, 13));
				label4.setForeground(Color.white);
				panel8.add(label4, BorderLayout.WEST);

				for (int i = 0; i < arrayList.size(); i++) {
					if (arrayList.get(i).equals(label3.getText())) {
						label4.setText("♥");
					}
				}

				int serial = rs2.getInt(2);
				label4.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {

						if (label4.getText().equals("♡")) {
							label4.setText("♥");
							try (PreparedStatement pst = DBManager
									.getPreparedStatement("insert into favorite values(?, ?, ?)")) {

								pst.setString(1, "0");
								pst.setInt(2, uno);
								pst.setInt(3, serial);

								pst.executeUpdate();

							} catch (Exception e2) {
								e2.printStackTrace();
							}
							return;
						} else {
							label4.setText("♡");
							try {

								DBManager.updateQ("delete from favorite where user = " + uno + " and song = " + serial);

							} catch (Exception e2) {
								e2.printStackTrace();
							}
							return;
						}
					}
				});

				JLabel l = new JLabel(rs2.getString(8));

				JLabel label5 = new JLabel(rs2.getString("time"), JLabel.CENTER);
				label5.setFont(new Font("맑은 고딕", Font.BOLD, 13));
				label5.setForeground(Color.white);
				label5.setBorder(new EmptyBorder(0, 8, 0, 15));
				panel8.add(label5, BorderLayout.CENTER);

				panel31.addMouseListener(new MouseAdapter() {

					@Override
					public void mousePressed(MouseEvent e) {

						if (e.getButton() == MouseEvent.BUTTON3) {

							JPopupMenu menu = new JPopupMenu();
							menu.setFont(new Font("맑은 고딕", Font.BOLD, 13));
							menu.add("아티스트로 이동").addActionListener(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent e) {
									try {

										ResultSet rs = DBManager.getResultSet(
												"SELECT * FROM 충북21.album inner join artist on artist.serial = album.artist where album.serial = '"
														+ l.getText() + "';");

										if (rs.next()) {
											String st = rs.getString(7);
											artist(st);
										}

									} catch (Exception e2) {
										e2.printStackTrace();
									}
								}
							});
							menu.add("플레이리스트에 추가").addActionListener(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent e) {

								}
							});
							menu.show(panel31, e.getX(), e.getY());
						}
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						panel31.setBackground(Color.gray);
						panel41.setBackground(Color.gray);
						panel6.setBackground(Color.gray);
						panel7.setBackground(Color.gray);
						panel8.setBackground(Color.gray);
					}

					@Override
					public void mouseExited(MouseEvent e) {
						panel31.setBackground(c);
						panel41.setBackground(c);
						panel6.setBackground(c);
						panel7.setBackground(c);
						panel8.setBackground(c);
					}
				});

				cnt++;
			}

			Center.repaint();
			Center.validate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void category(String str) {
		try {
			Center.removeAll();

			JPanel panel = new JPanel(new BorderLayout());
			Center.add(panel);

			JPanel panel2 = new JPanel(new BorderLayout()) {
				@Override
				protected void paintComponent(Graphics g) {
					super.paintComponent(g);

					ImageIcon icon = new ImageIcon(new ImageIcon("지급자료/images/category/" + str + ".jpg").getImage()
							.getScaledInstance(getWidth(), 235, 4));
					Image img = icon.getImage();

					g.drawImage(img, 0, 0, getWidth(), 235, this);
				}
			};
			panel2.setPreferredSize(new Dimension(0, 235));
			panel.add(panel2, BorderLayout.NORTH);

			JLabel label = new JLabel(str, JLabel.CENTER);
			label.setForeground(Color.white);
			label.setFont(new Font("맑은 고딕", Font.BOLD, 24));
			panel2.add(label);

			JPanel panel3 = new JPanel(new BorderLayout());
			panel3.setBackground(c);
			panel.add(panel3, BorderLayout.CENTER);

			JLabel label2 = new JLabel("최근 발매한 앨범");
			label2.setBorder(new EmptyBorder(10, 0, 10, 0));
			label2.setForeground(Color.white);
			label2.setFont(new Font("맑은 고딕", Font.BOLD, 14));
			panel3.add(label2, BorderLayout.NORTH);

			ResultSet rs = DBManager.getResultSet(
					"SELECT count(*) FROM 충북21.album inner join category on category.serial = album.category where category.name = '"
							+ str + "' order by `release` desc;");
			int i = 0;
			if (rs.next()) {
				int v;
				if (rs.getInt(1) > 6) {
					v = rs.getInt(1) % 3 == 0 ? rs.getInt(1) / 3 : (rs.getInt(1) / 3) + 1;
				} else {
					v = 3;
				}

				panel4 = new JPanel(new GridLayout(v, 3, 10, 5));
				panel4.setBackground(c);
				panel3.add(panel4, BorderLayout.CENTER);
				i = rs.getInt(1);
			}

			ResultSet rs2 = DBManager.getResultSet(
					"SELECT *, substring(`release`, 1, 4) as sub FROM 충북21.album inner join category on category.serial = album.category where category.name = '"
							+ str + "' order by `release` desc;");

			int k = 1;
			while (rs2.next()) {

				JPanel panel5 = new JPanel(new BorderLayout());
				panel5.setPreferredSize(new Dimension(0, 125));
				panel5.setBorder(new EmptyBorder(5, 5, 5, 5));
				panel5.setBackground(c);
				panel4.add(panel5);

				JLabel label3 = new JLabel(new ImageIcon(new ImageIcon("지급자료/images/album/" + rs2.getString(1) + ".jpg")
						.getImage().getScaledInstance(120, 120, 4)));
				panel5.add(label3, BorderLayout.WEST);

				if (k == i && i < 9) {
					for (int j = 0; j < (9 - i); j++) {
						JPanel p = new JPanel(new BorderLayout());
						p.setPreferredSize(new Dimension(0, 125));
						p.setBorder(new EmptyBorder(5, 5, 5, 5));
						p.setBackground(c);
						panel4.add(p);
					}
				}

				JPanel panel6 = new JPanel(new GridLayout(2, 1));
				panel6.setBorder(new EmptyBorder(35, 8, 35, 0));
				panel6.setBackground(c);
				panel5.add(panel6, BorderLayout.CENTER);

				JLabel label4 = new JLabel(rs2.getString(2));
				label4.setForeground(Color.white);
				label4.setFont(new Font("맑은 고딕", Font.BOLD, 13));
				panel6.add(label4);

				JLabel label5 = new JLabel(rs2.getString("sub") + "년");
				label5.setForeground(Color.white);
				label5.setFont(new Font("맑은 고딕", Font.BOLD, 13));
				panel6.add(label5);

				panel5.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						album(label4.getText());
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						panel5.setBackground(new Color(96, 96, 96));
						panel6.setBackground(new Color(96, 96, 96));
					}

					@Override
					public void mouseExited(MouseEvent e) {
						panel5.setBackground(c);
						panel6.setBackground(c);
					}
				});

				k++;
			}

			Center.repaint();
			Center.validate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void album(String str) {
		Center.removeAll();
		try {

			JPanel panel = new JPanel(new BorderLayout());
			Center.add(panel);

			JPanel panel2 = new JPanel(new BorderLayout());
			panel2.setBackground(c);
			panel2.setBorder(new EmptyBorder(0, 0, 8, 0));
			panel.add(panel2, BorderLayout.NORTH);

			String sql = "";
			if (str.contains("'")) {
				sql = "SELECT album.serial, album.name, artist.name, category.name, substring(album.release, 1, 4) as sub FROM 충북21.album inner join artist on artist.serial = album.artist inner join category on category.serial = album.category where album.name like '%"
						+ "re through thinking, say yes" + "%';";
			} else {
				sql = "SELECT album.serial, album.name, artist.name, category.name, substring(album.release, 1, 4) as sub FROM 충북21.album inner join artist on artist.serial = album.artist inner join category on category.serial = album.category where album.name = '"
						+ str + "';";
			}

			ResultSet rs = DBManager.getResultSet(sql);
			String album = "", artist = "";
			if (rs.next()) {

				album = rs.getString(2);
				artist = rs.getString(3);

				JLabel label = new JLabel(new ImageIcon(new ImageIcon("지급자료/images/album/" + rs.getString(1) + ".jpg")
						.getImage().getScaledInstance(180, 180, 4)));
				panel2.add(label, BorderLayout.WEST);

				JPanel panel3 = new JPanel(new BorderLayout());
				panel3.setBackground(c);
				panel3.setBorder(new EmptyBorder(0, 8, 0, 0));
				panel2.add(panel3, BorderLayout.CENTER);

				JPanel panel4 = new JPanel(new GridLayout(3, 1));
				panel4.setBorder(new EmptyBorder(30, 0, 30, 0));
				panel4.setBackground(c);
				panel3.add(panel4, BorderLayout.CENTER);

				tit = "";
				tit = rs.getString(2);
				for (int i = 0; i < 3; i++) {
					JLabel label2 = new JLabel(new String[] { rs.getString(2), rs.getString(3),
							rs.getString(4) + " · " + rs.getString(5) + "년" }[i]);
					label2.setFont(new Font("맑은 고딕", Font.BOLD, 14));
					label2.setForeground(Color.white);
					panel4.add(label2);
				}

				JButton btn = new JButton("재생하기");
				btn.setBackground(Color.white);
				btn.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
				btn.setPreferredSize(new Dimension(0, 35));
				panel3.add(btn, BorderLayout.SOUTH);

			}

			String sql2 = "";
			if (str.contains("'")) {
				sql2 = "SELECT *, count(song.name) as cnt FROM 충북21.song inner join album on album.serial = song.album inner join artist on artist.serial = album.artist left join history on history.song = song.serial where artist.name = '"
						+ artist + "' and album.name like '%" + "re through thinking, say yes"
						+ "%' group by song.name order by cnt desc, song.serial asc;";
			} else {
				sql2 = "SELECT *, count(song.name) as cnt FROM 충북21.song inner join album on album.serial = song.album inner join artist on artist.serial = album.artist left join history on history.song = song.serial where artist.name = '"
						+ artist + "' and album.name = '" + album
						+ "' group by song.name order by cnt desc, song.serial asc;";
			}
			ResultSet rs2 = DBManager.getResultSet(sql2);

			int row = 0;
			while (rs2.next()) {
				row = rs2.getRow();
			}

			JPanel panel5 = new JPanel(new GridLayout(row, 1, 0, 5));
			panel5.setBorder(new CompoundBorder(new MatteBorder(1, 0, 0, 0, Color.gray), new EmptyBorder(15, 0, 0, 0)));
			panel5.setBackground(c);
			panel.add(panel5, BorderLayout.CENTER);

			String sql3 = "";
			if (str.contains("'")) {
				sql3 = "SELECT *, substring(length, 4) as time, count(song.name) as cnt, titlesong FROM 충북21.song inner join album on album.serial = song.album inner join artist on artist.serial = album.artist left join history on history.song = song.serial where artist.name = '"
						+ artist + "' and album.name like '%" + "re through thinking, say yes"
						+ "%' group by song.name order by cnt desc, song.serial asc;";
			} else {
				sql3 = "SELECT *, substring(length, 4) as time, count(song.name) as cnt, titlesong FROM 충북21.song inner join album on album.serial = song.album inner join artist on artist.serial = album.artist left join history on history.song = song.serial where artist.name = '"
						+ artist + "' and album.name = '" + album
						+ "' group by song.name order by cnt desc, song.serial asc;";
			}

			ResultSet rs3 = DBManager.getResultSet(sql3);

			int cnt = 1;
			while (rs3.next()) {
				JPanel panel3 = new JPanel(new BorderLayout());
				panel3.setPreferredSize(new Dimension(0, 30));
				panel3.setBackground(c);
				panel5.add(panel3);

				JPanel panel4 = new JPanel(new BorderLayout());
				panel4.setBackground(c);
				panel3.add(panel4, BorderLayout.WEST);

				JPanel panel6 = new JPanel(new BorderLayout());
				panel6.setPreferredSize(new Dimension(20, 20));
				panel6.setBackground(c);
				panel4.add(panel6, BorderLayout.WEST);

				JLabel label = new JLabel("★", JLabel.CENTER);
				label.setForeground(Color.white);
				label.setFont(new Font("맑은 고딕", Font.BOLD, 13));
				label.setVisible(false);
				panel6.add(label);

				if (rs3.getInt(5) == 1) {
					label.setVisible(true);
				}

				JPanel panel7 = new JPanel(new BorderLayout());
				panel7.setBackground(c);
				panel4.add(panel7, BorderLayout.CENTER);

				JLabel label2 = new JLabel(String.valueOf(cnt), JLabel.CENTER);
				label2.setFont(new Font("맑은 고딕", Font.BOLD, 13));
				label2.setForeground(Color.white);
				label2.setBorder(new EmptyBorder(0, 5, 0, 12));
				panel7.add(label2, BorderLayout.WEST);

				JLabel label3 = new JLabel(rs3.getString(2), JLabel.CENTER);
				label3.setFont(new Font("맑은 고딕", Font.BOLD, 13));
				label3.setForeground(Color.white);
				panel7.add(label3, BorderLayout.CENTER);

				JPanel panel8 = new JPanel(new BorderLayout());
				panel8.setBackground(c);
				panel3.add(panel8, BorderLayout.EAST);

				JLabel label4 = new JLabel("♡", JLabel.CENTER); // ♥ ♡
				label4.setFont(new Font("맑은 고딕", Font.BOLD, 13));
				label4.setForeground(Color.white);
				panel8.add(label4, BorderLayout.WEST);

				for (int i = 0; i < arrayList.size(); i++) {
					if (arrayList.get(i).equals(label3.getText())) {
						label4.setText("♥");
					}
				}

				int serial = rs3.getInt(1);
				label4.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {

						if (label4.getText().equals("♡")) {
							label4.setText("♥");
							try (PreparedStatement pst = DBManager
									.getPreparedStatement("insert into favorite values(?, ?, ?)")) {

								pst.setString(1, "0");
								pst.setInt(2, uno);
								pst.setInt(3, serial);

								pst.executeUpdate();

							} catch (Exception e2) {
								e2.printStackTrace();
							}
							return;
						} else {
							label4.setText("♡");
							try {

								DBManager.updateQ("delete from favorite where user = " + uno + " and song = " + serial);

							} catch (Exception e2) {
								e2.printStackTrace();
							}
							return;
						}
					}
				});

				JLabel label5 = new JLabel(rs3.getString("time"), JLabel.CENTER);
				label5.setFont(new Font("맑은 고딕", Font.BOLD, 13));
				label5.setForeground(Color.white);
				label5.setBorder(new EmptyBorder(0, 8, 0, 15));
				panel8.add(label5, BorderLayout.CENTER);

				panel3.addMouseListener(new MouseAdapter() {

					@Override
					public void mousePressed(MouseEvent e) {

						if (e.getButton() == MouseEvent.BUTTON3) {
							JPopupMenu menu = new JPopupMenu();
							menu.setFont(new Font("맑은 고딕", Font.BOLD, 13));
							menu.add("아티스트로 이동").addActionListener(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent e) {
									try {

										ResultSet rs = DBManager.getResultSet(
												"SELECT * FROM 충북21.album inner join artist on artist.serial = album.artist where album.name = '"
														+ tit + "';");

										if (rs.next()) {
											String st = rs.getString(7);
											artist(st);
										}

									} catch (Exception e2) {
										e2.printStackTrace();
									}
								}
							});
							menu.add("플레이리스트에 추가").addActionListener(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent e) {

								}
							});
							menu.show(panel4, e.getX(), e.getY());
						}
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						panel3.setBackground(Color.gray);
						panel4.setBackground(Color.gray);
						panel6.setBackground(Color.gray);
						panel7.setBackground(Color.gray);
						panel8.setBackground(Color.gray);
					}

					@Override
					public void mouseExited(MouseEvent e) {
						panel3.setBackground(c);
						panel4.setBackground(c);
						panel6.setBackground(c);
						panel7.setBackground(c);
						panel8.setBackground(c);
					}
				});

				cnt++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		Center.repaint();
		Center.validate();
	}

	public class PrintThread extends Thread {

		private boolean stop;

		public void setStop(boolean stop) {
			this.stop = stop;
		}

		@Override
		public void run() {

			while (num <= 60000 || !stop) {
				bar.setValue(num);
				System.out.println(num);
				try {
					Thread.sleep(1);
				} catch (InterruptedException e2) {
					e2.printStackTrace();
				}
				num++;
			}

		}

	}

	public static void main(String[] args) {
		new DBManager();
		new Music();
	}
}