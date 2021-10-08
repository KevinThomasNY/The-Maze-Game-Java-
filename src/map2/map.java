package map2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import frame.team_about;










public class map extends JFrame implements ActionListener, KeyListener, Runnable {
	static boolean newMap = false;
	static int x = 1; 
	static int y = 1; 
	static int m, n; 
	public static int b = 0;
	
	static wrmPane[][] tp = null;
	public static int musicNext = 1; 
	public static boolean timeExit = false; 
	public  static int restartTime =0;
	public  static boolean close =false;
	
	

	public static JPanel cover = null; 
	static playMusicThread musicThread ;
	public static Thread timeThread; 
	 static monsterThread gtThread; 
	static int timelimit, remaintime; 
	
	static int m_currentx2 = Operations.m_startx2; 	
	static int m_currenty2_1 = Operations.m_starty2_1; 
	static int m_currentx2_2 = Operations.m_startx2_2;
	static JPanel timePanel=new JPanel(){ 

	public void paintComponent(Graphics g){super.paintComponent(g);String rt;if(timelimit==0){rt="unlimit";setForeground(Color.GREEN);  
	}else{rt=remaintime/3600+" : "+(remaintime-(remaintime/3600)*3600)/60+" : "+remaintime%60;if(remaintime>10)setForeground(Color.BLUE);  
	else setForeground(Color.RED); 
	}g.drawString("RemainTime:  "+rt,220,16);}};
	public static int musicOption =1 ;

	private JMenuItem returnH = new JMenuItem("Return Home");
	private JMenuItem reload = new JMenuItem("Role Control");
	private JMenuItem m_start = new JMenuItem("Restart");
	private JMenuItem m_musicNext = new JMenuItem("Switch Music");
	
	
	
	private JMenuItem m_exit = new JMenuItem("Exit");
	private JMenuItem m_help = new JMenuItem("Game Rules");
	private JMenuItem m_about = new JMenuItem("Group Information");

	public map(int x, int y, boolean newMap,int restartTime) throws Exception {
		m = x;
		n = y;
		tp = new wrmPane[m][n];
		timelimit = remaintime = 0; 
		

		
		
		JMenu returnHome = new JMenu("Return Home");
		JMenu game = new JMenu("Level 2");
		JMenu music = new JMenu("Music Control");
		JMenu role = new JMenu("Role Control");
	
		JMenu rules = new JMenu("Game Rules");
		JMenu about = new JMenu("Group Information");
		music.add(m_musicNext);
	
		game.add(m_start);
		game.add(m_exit);
		role.add(reload);
		rules.add(m_help);
		about.add(m_about);
		returnHome.add(returnH);

	
		JMenuBar menu = new JMenuBar();
		menu.add(game);
		menu.add(role);
		menu.add(music);
		menu.add(rules);
		menu.add(about);
		menu.add(returnHome);
		
		

				JPanel northPanel = new JPanel();
				northPanel.setLayout(new GridLayout(1, 1));
				northPanel.add(menu);
				northPanel.add(timePanel);
				timePanel.setBackground(new Color(245, 240, 245));
				menu.setBackground(new Color(245, 240, 245));

			
				getContentPane().setLayout(new BorderLayout());
				getContentPane().add(northPanel, BorderLayout.NORTH);
				
			
				for (int i = 0; i < m; i++)
					for (int j = 0; j < n; j++) {
						tp[i][j] = new wrmPane();
					}
				Operations.creatMaze(); 

			
				JPanel mazePane = new JPanel();
				mazePane.setLayout(new GridLayout(m, n, 0, 0));
				for (int i = 0; i < m; i++) {
					for (int j = 0; j < n; j++) {
						mazePane.add(tp[i][j]);
					}
				}
				
				
				if (cover == null) {
					cover = new JPanel();
					getContentPane().add(cover, BorderLayout.CENTER);
					cover.setLayout(null);
				
					ImageIcon role1 = new ImageIcon("resource/maze.png");
					role1.setImage(role1.getImage().getScaledInstance(595, 448, Image.SCALE_DEFAULT));
					JLabel lblNewLabel = new JLabel(role1);
					lblNewLabel.setBounds(113, 40, 595, 448);
					cover.add(lblNewLabel,BorderLayout.CENTER);

				}else {
					getContentPane().add(mazePane, BorderLayout.CENTER);
				}
				getContentPane().add(new JPanel(), BorderLayout.SOUTH);
		
				m_exit.addActionListener(this);
				returnH.addActionListener(this);
				
				reload.addActionListener(this);
				m_start.addActionListener(this);
				m_musicNext.addActionListener(this);
				m_help.addActionListener(this);
				m_about.addActionListener(this);
				addKeyListener(this);
				

			
				setTitle("Maze Game");
				setSize(850, 650);
				setLocationRelativeTo(null);
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setVisible(true);
				setResizable(true);
				
				if(newMap ==true&&restartTime==1) {
				timelimit = remaintime = 60; 
			
					timeThread = new Thread(this);
					timeThread.start();

					musicThread =new playMusicThread();
					musicThread.start();
					
					gtThread =new monsterThread();
					gtThread.start();
				
					Operations.changeable_key = true;    
					Operations.setEditable(false);      
				}
				if(newMap ==true&&restartTime!=1) {
					timelimit = remaintime = 60; 
					Operations.changeable_key = true;    
					Operations.setEditable(false);      
				}

			

	}


	public void run() {
	
			
	
		
		if (timelimit > 0) {
			while (!timeExit) {
				try {
					Thread.sleep(1000);
					if (remaintime > 0)
						remaintime--;
					timePanel.repaint();
					
					if (timelimit > 0 && remaintime == 0) {
						
						if (Operations.m_currex != m - 1 || Operations.m_currey != n - 1) {
							 Object[] options = {"restart", "go on","Exit "};
							
							   gtThread.suspend();
								  restartTime =0;
			                 int response = JOptionPane.showOptionDialog(null, "Time Over!", "Note!", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			              
			                 gtThread.resume();
			                 if (response == 0) {
			               	  restartTime =1;
			                	 if(Operations. num==0) {
			                		 Operations. num=1;
			                 	}
			                     Operations.restart = true;
			                     try {
									Operations.start();
								} catch (Exception e) {
									 
									e.printStackTrace();
								}
			                 } else if(response == 1) {
			               	  restartTime =1;
			                	 if(Operations.num==0) {
			                	 		Operations.num=1;
			                	 	}
			                	 map.remaintime = map.timelimit;
			                	 if(Operations.num==0) {
			                		 Operations.num=1;
			                		  map.tp[Operations.m_startx3_1][Operations.m_starty3_1].change(5);  
			                	 }
			                	 map.tp[Operations.m_currex][Operations.m_currey].change(1);
			                     Operations.m_currex = Operations.m_startx;
			                     Operations.m_currey = Operations.m_starty;
			                     map.tp[Operations.m_currex][Operations.m_currey].change(2);
			                 }else {
			                	 System.exit(0);
			                 }

						}
					}
				} catch (Exception e) {
				}
			}
		}
		
	}
	
	public void closeThread() {
		if(close ==true) {
			gtThread.exit=true;
			timeExit =true;
		
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		new map(10, 10, false,0);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		 

	}

	
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_DOWN:
			try {
				Operations.down();
			} catch (FileNotFoundException e1) {
				 
				e1.printStackTrace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				 
			
			break;
		case KeyEvent.VK_UP:
			try {
				Operations.up();
			} catch (Exception e4) {
				 
				e4.printStackTrace();
			}
			break;
		case KeyEvent.VK_LEFT:
			try {
				Operations.left();
			} catch (Exception e3) {
				 
				e3.printStackTrace();
			}
			break;
		case KeyEvent.VK_RIGHT:
			try {
				Operations.right();
			} catch (MalformedURLException e2) {
				 
				e2.printStackTrace();
			} catch (FileNotFoundException e1) {
				 
				e1.printStackTrace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;

		}

	}


	

	@Override
	public void keyReleased(KeyEvent e) {
		 

	}


	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == m_start) {
			
			try {	
				
					restartTime+=1;
	        	    
	        		map2.map.cover = new JPanel();
	        	    new map2.map(18,18,true,restartTime);
	               Operations. changeable_key = true;    
	               Operations. setEditable(false);      
				} catch (Exception e1) {
					 
					e1.printStackTrace();
				}
				dispose();

		} else if (e.getSource() == m_exit) {System.exit(0);}  else if (e.getSource() == m_help) {
			if(restartTime!=0) {
				 timeThread.suspend();
				   gtThread.suspend();
				Operations.showHelp();
				map.timeThread.resume();
				   map.gtThread.resume();
			}else {
				
				Operations.showHelp();
				
			}
		
			
		}  else if (e.getSource() == m_about) {
			if(restartTime==0) {
				
				Operations.about();
			}else {
			System.out.println(restartTime);
			
				 map.timeThread.suspend();
				    map.gtThread.suspend();
				   String[] options= {"yes"};
				    JOptionPane.showOptionDialog(null, "Please play game continue, after the game can be viewed", "Ok?",JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			
				
						map.timeThread.resume();
					   map.gtThread.resume();
					 
				
				
				
//			
			}
		}  else if (e.getSource() == m_about) {
	if(restartTime==0) {
				
				Operations.about();
			}else {
				
				 map.timeThread.suspend();
				    map.gtThread.suspend();
				Operations.about();
				if(team_about.isDispose==true) {
			map.timeThread.resume();
				   map.gtThread.resume();
				}
			}
			
		}else if (e.getSource() == returnH) {
			try {
				cover =null;
			
				timeExit =true;
				gtThread.exit=true;
				musicThread.clip.stop();
				new map1.map(10, 10,false,0);
				restartTime =0;
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		} else if (e.getSource() == m_musicNext) {
	
	
			musicThread.clip.stop();
			

			
			int a=1;
			
			if(map.musicNext==3){
				map.musicNext=0;
				a=+1;
			}
			musicNext +=a;
			musicThread = new  playMusicThread();
			musicThread.start();

		}else if (e.getSource() == reload) {
			String []roleImg={"resource/mouse.gif","resource/Iron Man.jpg","resource/Naruto Uzumaki.jpg","resource/One Punch Man.jpg"};
			int a=1;
			
			if(b==roleImg.length-1){
				b=-1;
				a=+1;
			}
			b +=a;
		
			wrmPane.mouse =new ImageIcon(roleImg[b]).getImage();    
			map3.map.b =map2.map.b;
			map3.wrmPane.mouse =map2.wrmPane.mouse;
			  map.tp[Operations.m_currex][Operations.m_currey].change(2);  
			
			try {
				Operations.start();
			} catch (Exception e1) {
				
				e1.printStackTrace();
			}
		}


	}

	
	
	

}




