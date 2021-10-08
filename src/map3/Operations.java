package map3;

import java.io.File;
import java.io.RandomAccessFile;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import frame.team_about;





public class Operations {
	
    static int m, n;   
    
    
    static int m_startx, m_starty;   
    static int m_startx2;  
  	static int m_starty2; 
      static int m_startx2_1;   
  	static int m_starty2_1;		
    static int m_startx2_2, m_starty2_2;  
    static int m_startx2_3, m_starty2_3;  
    static int m_startx3_1, m_starty3_1;   
   
    static int m_currex, m_currey;   
    static int m_currex_1, m_currey_1;   
    
    static int num =1; 
    
    static boolean changeable_key = false;     
    static boolean restart = false; 
    
    
    private static boolean[] isBeVisit = null;
    
   
    public static void creatMaze() throws Exception {
    	   m = map.m;
           n = map.n;
           
          
           isBeVisit = new boolean[m * n];
           for (int i = 0; i < m * n; i++) isBeVisit[i] = false;     

        
           for (int i = 0; i < m; i++) {                 
               map.tp[i][0].change(Math.random() * 3 > 1 ? 0 : 1);
               map.tp[i][n - 1].change(Math.random() * 3 > 1 ? 0 : 1);
           }
           for (int i = 0; i < n; i++) {
               map.tp[0][i].change(Math.random() * 3 > 1 ? 0 : 1);
               map.tp[m - 1][i].change(Math.random() * 3 > 1 ? 0 : 1);
           }
           for (int i = 1; i < m - 1; i++)
               for (int j = 1; j < n - 1; j++)
                   map.tp[i][j].change(0);  

           m_startx = (int) (Math.random() * m / 2);
           m_starty = (int) (Math.random() * n / 2);
          
          
           m_startx2 = (int) (Math.random() * m / 2);
           m_starty2 = (int) (Math.random() * n / 2);   
           
           m_startx2_1 = (int) (Math.random() * m / 2);
           m_starty2_1 = (int) (Math.random() * n / 2);  
           
           m_startx2_2 = (int) (Math.random() * m / 2);
           m_starty2_2 = (int) (Math.random() * n / 2);  
           
           m_startx2_3 = (int) (Math.random() * m / 2);
           m_starty2_3 = (int) (Math.random() * n / 2);  
           
          
           m_startx3_1 = (int) (Math.random() * m / 2);
           m_starty3_1 = (int) (Math.random() * n / 2);
           

           DFS(m_startx * n + m_starty);
         
            math(m_startx,m_starty,m_startx2,m_starty2);
            math(m_startx,m_starty,m_startx2_1,m_starty2_1);
            math(m_startx,m_starty,m_startx2_2,m_starty2_2);
            math(m_startx,m_starty,m_startx2_3,m_starty2_3);
            
            math(m_startx,m_starty,m_startx3_1,m_starty3_1);
            
            if(m_startx==m_startx3_1&m_starty==m_starty3_1) {
          	  
          	  math(m_startx,m_starty,m_startx3_1,m_starty3_1);
            }
            
             
       
           
            
        
            
            
       
       
           
           
           
          
     
           
         
           if (Math.random() * 2 > 1)
                map.tp[m - 2][n - 1].change(1);
           else
               map.tp[m - 1][n - 2].change(1);    

       
         
         
         
            
              map.tp[m_startx][m_starty].change(2);  
              map.tp[m-1][n-1].change(3);  
              map.tp[m_startx3_1][m_starty3_1].change(5);  
              
              
              changeable_key = false;  
              m_currex = m_startx;
              m_currey = m_starty;  
              restart = false;
    
    }
    
    public  static void math(int a_x, int a_y, int b_x, int b_y) {
    	
    	if( a_x==b_x&&a_y==b_y){
    		 a_x =(int) (Math.random() * m / 2);
    		 a_y =(int) (Math.random() * m / 2);
    		 math(a_x,a_y,b_x,b_y);
    	}
		
		
	}
    
  
    public static void DFS(int s) {
        map.tp[s / n][s % n].change(1);
        isBeVisit[s] = true;

        int[] direction = new int[4];              
        boolean[] isStored = new boolean[4];
        for (int i = 0; i < 4; i++) isStored[i] = false;    
        int currex = s / n, currey = s % n;                 

      
        int rand, length = 0;      
        while (length < 4) {
            rand = (int) (Math.random() * 4);    
            if (!isStored[rand]) {
                direction[length++] = rand;
                isStored[rand] = true;           
            }
        }
        for (int i = 0; i < 4; i++) {
            switch (direction[i]) {
                case 0:
                    if (currey + 2 < n) {                            
                        if (!isBeVisit[s + 2]) {
                            map.tp[currex][currey + 1].change(1);
                            DFS(s + 2);
                        }
                    }
                    break;
                case 1:
                    if (currex + 2 < m) {                             
                        if (!isBeVisit[s + 2 * n]) {
                            map.tp[currex + 1][currey].change(1);
                            DFS(s + 2 * n);
                        }
                    }
                    break;
                case 2:                                             
                    if (currey - 2 >= 0) {
                        if (!isBeVisit[s - 2]) {
                            map.tp[currex][currey - 1].change(1);
                            DFS(s - 2);
                        }
                    }
                    break;
                case 3:                                            
                    if (currex - 2 >= 0) {
                        if (!isBeVisit[s - 2 * n]) {
                            map.tp[currex - 1][currey].change(1);
                            DFS(s - 2 * n);
                        }
                    }
                    break;
            }
        }
    }
    
  
    public static void start() throws Exception {
        if (restart) creatMaze();
       

    		
        changeable_key = true;    
        setEditable(false);      
          
         
       
        
      }
    
    public static void eat() {
    	map.tp[m_currex][m_currey].change(4);
    
    			
    	m_currex=0;
    	m_currey=0;
		 map.timeThread.suspend();
	
		 
		 
		 Object[] options = {"Restar", "Go On","Exit "};

		   
        int response = JOptionPane.showOptionDialog(null, "You are dead!", "Note!", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        map.restartTime =0; map.timeThread.resume();
       
        if (response == 0) {map.restartTime =1;
        	
        	 map.remaintime = map.timelimit;
                Operations.restart = true;
                try {
					Operations.start();
				} catch (Exception e) {
					 
					e.printStackTrace();
				}
        } else if(response == 1) {map.restartTime =1;
        	if(num==0) {
        		num=1;
        	}
        	 m_currex=m_currex_1;
        	 m_currey=m_currey_1;
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

    public static void down() throws Exception {
        if (!changeable_key) return;
        
        	
        	  
        	   
        	   if (m_currex + 1 == m - 1 && m_currey == n - 1) {
                   map.tp[m_currex][m_currey].change(1);
                   m_currex++;
                   restart = true;
                   if(num==1){
                	   map.timeThread.suspend();

                	   map.timeThread.suspend();
                	      map.gtThread.suspend();

                	    String[] options= {"Ok"};  JOptionPane.showOptionDialog(null, "You haven't found the key yet!", null,JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

                	    map.timeThread.resume();
                	   map.gtThread.resume();
                   }else{
                	   
                	  
                	   
                    String[] options= {"Ok"};  int a=JOptionPane.showOptionDialog(null, "Return to the first level!", null,JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                    if(a==0) {
                    	map.cover =null;
            			
                    	map.timeExit =true;
                    	map.gtThread.exit=true;
                    	map.musicThread.clip.stop();
        				new map1.map(10, 10,false,0);
        				map.restartTime =0;
                    }
                   map.restartTime =0;
                   map.timeThread.suspend();
                   return;
                  
                   }
               }
        	 
               if (Operations.m_startx3_1-1 == Operations.m_currex && Operations.m_starty3_1 == Operations.m_currey) {
            	   map.tp[m_startx3_1][m_starty3_1].change(2);
            	   map.tp[m_startx3_1-1][m_starty3_1].change(1);
         
            	   	restart = true;
            	    if(num==1) {
            	    	 map.timeThread.suspend();
            	    	    map.gtThread.suspend();

            	          String[] options= {"Ok"};  JOptionPane.showOptionDialog(null, "You found the key, now you need to find the exit!", null,JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            	          map.timeThread.resume();
            	         map.gtThread.resume();
            	          num =0;
            	          }
            	   	
           
        } try {
            if (m_currex + 1 < m && !map.tp[m_currex + 1][m_currey].isWall()) {
            map.tp[m_currex][m_currey].change(1);
            map.tp[m_currex+=1][m_currey].change(2);
            if (map.m_currentx2 == Operations.m_currex && Operations.m_starty2 == Operations.m_currey) {
            	eat();
            }
            
            if (Operations.m_startx2_1 == Operations.m_currex &&  map.m_currenty2_1 == Operations.m_currey) {
            	eat();
            }

 if (map.m_currentx2_2 == Operations.m_currex && Operations.m_starty2_2 == Operations.m_currey) {
            	eat();
            }

     if (Operations.m_startx2_3 == Operations.m_currex &&  map.m_currenty2_2 == Operations.m_currey) {
            	eat();
            }

        }
    	} catch (Exception e) {
    		// TODO: handle exception
    	}  
    }

    public static void up() throws Exception {
        if (!changeable_key) return;
      
        
        if (Operations.m_startx3_1+1 == Operations.m_currex && Operations.m_starty3_1 == Operations.m_currey) {
        	 map.tp[m_startx3_1][m_starty3_1].change(2);
        	   map.tp[m_startx3_1+1][m_starty3_1].change(1);

         
        	   if(num==1) {
        		   map.timeThread.suspend();
        		    map.gtThread.suspend();String[] options= {"Ok"};  JOptionPane.showOptionDialog(null, "You found the key, now you need to find the exit!", null,JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        		    map.timeThread.resume();
        		   map.gtThread.resume(); num =0;
        	          }
       
      } 
        if (m_currex - 1 >= 0 && !map.tp[m_currex - 1][m_currey].isWall()) {
            map.tp[m_currex][m_currey].change(1);
            map.tp[m_currex-=1][m_currey].change(2);
            map.tp[m-1][n-1].change(3);  
        }
    }

    public static void left() throws Exception {
        if (!changeable_key) return;
      
        if (Operations.m_startx3_1 == Operations.m_currex && Operations.m_starty3_1+1 == Operations.m_currey) {
          map.tp[m_startx3_1][m_starty3_1 ].change(2);
          map.tp[m_startx3_1][m_starty3_1+1 ].change(1);

          restart = true;
          
          if(num==1) {
        	  map.timeThread.suspend();
        	    map.gtThread.suspend();String[] options= {"Ok"};  JOptionPane.showOptionDialog(null, "You found the key, now you need to find the exit!", null,JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        	    map.timeThread.resume();
        	    map.gtThread.resume();num =0;
          }
          
      } 
        if (m_currey - 1 >= 0 && !map.tp[m_currex][m_currey - 1].isWall()) {
            map.tp[m_currex][m_currey].change(1);
            map.tp[m_currex][m_currey-=1].change(2);
            map.tp[m-1][n-1].change(3);  
            if (map.m_currentx2 == Operations.m_currex && Operations.m_starty2 == Operations.m_currey) {
            	eat();
            }
            
            if (Operations.m_startx2_1 == Operations.m_currex &&  map.m_currenty2_1 == Operations.m_currey) {
            	eat();
            }

 if (map.m_currentx2_2 == Operations.m_currex && Operations.m_starty2_2 == Operations.m_currey) {
            	eat();
            }

     if (Operations.m_startx2_3 == Operations.m_currex &&  map.m_currenty2_2 == Operations.m_currey) {
            	eat();
            }

        }
        
    
        
    }

    public static void right() throws Exception {
        if (!changeable_key) return;
      
        
        if (m_currex == m - 1 && m_currey + 1 == n - 1) {
            map.tp[m_currex][m_currey].change(1);
            m_currey++;
            restart = true;
            if(num==1){
            	 

            	   map.timeThread.suspend();
            	      map.gtThread.suspend();
         	    String[] options= {"Ok"};  JOptionPane.showOptionDialog(null, "You haven't found the key yet!", null,JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
         	  map.timeThread.resume();
         	   map.gtThread.resume();
         	               }else{
         	            	
         	            	  String[] options= {"Ok"};  int a=JOptionPane.showOptionDialog(null, "Return to the first level!", null,JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
         	                    if(a==0) {
         	                    	map.cover =null;
         	            			
         	                    	map.timeExit =true;
         	                    	map.gtThread.exit=true;
         	                    	map.musicThread.clip.stop();
         	        				new map1.map(10, 10,false,0);
         	        				
         	        				map.restartTime =0;
         	                    }
         	    map.restartTime =0;
         	     map.timeThread.suspend();
         	     

        }
            
           
        }
      
        if (Operations.m_startx3_1 == Operations.m_currex && Operations.m_starty3_1-1 == Operations.m_currey) {
        	 map.tp[m_startx3_1][m_starty3_1].change(2);
        	 map.tp[m_startx3_1][m_starty3_1-1 ].change(1);
        	   if (map.m_currentx2 == Operations.m_currex && Operations.m_starty2 == Operations.m_currey) {
               	eat();
               }
               
               if (Operations.m_startx2_1 == Operations.m_currex &&  map.m_currenty2_1 == Operations.m_currey) {
               	eat();
               }

    if (map.m_currentx2_2 == Operations.m_currex && Operations.m_starty2_2 == Operations.m_currey) {
               	eat();
               }

        if (Operations.m_startx2_3 == Operations.m_currex &&  map.m_currenty2_2 == Operations.m_currey) {
               	eat();
               }


          restart = true;
          if(num==1) {
        	  map.timeThread.suspend();
        	    map.gtThread.suspend();
              String[] options= {"Ok"};  JOptionPane.showOptionDialog(null, "You found the key, now you need to find the exit!", null,JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
              map.timeThread.resume();
              map.gtThread.resume();
              num =0;
              }
        
      }   try {
        if (m_currey + 1 >= 0 && !map.tp[m_currex][m_currey + 1].isWall()) {
            map.tp[m_currex][m_currey].change(1);
            map.tp[m_currex][m_currey+=1].change(2);
        }
        
  	} catch (Exception e) {
		// TODO: handle exception
	}
    }
    
    


    public static void setEditable(boolean e) {
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                map.tp[i][j].setChangeable_click(e);
       
        map.tp[m - 1][n - 1].setChangeable_click(false);
        map.tp[m_startx][m_starty].setChangeable_click(false);
    }
    
    
  
    public static void showHelp() {
      map.timeThread.suspend();   
      String help = "There are a total of three game maps, and the difficulty of the map will increase when entering the next level, the number of monsters will also increase with the difficulty.\n\n"
              + "Players can control cartoon characters to play the game. Players need to find a key somewhere in the maze to open the door to go out, and then find the correct route to find the exit.  \n\n"
              + " There will be some monsters walking in random areas in the maze. Players need to manipulate cartoon characters to avoid monsters. Once it encounters the monster, the cartoon character will die immediately. \n\n"
             	+"How to operate the game: \n\n"
          	+"ARROW UP(↑)————Control the cartoon character to move up. \n"
             	+"AROW DOWN(↓)————Control the cartoon character to move down. \n"
             	+"ARROW LEFT(←)————Control the cartoon character to move to the left. \n"
             	+"ARROW RIGHT(→)————Control the cartoon character to move to the right.\n"
             	+"Other tips:\r\n\n" + 
             	"The homepage is fixed music, entering the game will play the game music, click the top music control option to switch different music.\n"+
             	"The player starts the game with the default cartoon character. click the top role control option to switch to different cartoon characters.";
          	
        String[] options= {"Ok"};  JOptionPane.showOptionDialog(null, help.toString(), "Games Rules:",JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

  }


    public static void about() {
    	 
        team_about  t =new team_about();
        t.setVisible(true);

    }
    
    



}	 
