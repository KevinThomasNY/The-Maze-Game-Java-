package map1;

import javax.swing.JOptionPane;

public class monsterThread extends Thread {
	public boolean exit=false;
	public void run() {
		
		
		
		
			
		
		while (!exit) {
			
			

			if (map.m_currentx2 == Operations.m_startx3_1 && Operations .m_starty2== Operations.m_starty3_1) {
				map.tp[map.m_currentx2][Operations .m_starty2].change(5);
			}else {
				map.tp[map.m_currentx2][Operations .m_starty2].change(1);
			}
				map.m_currentx2+=map.x;
				map.tp[map.m_currentx2][Operations .m_starty2].change(4);



		
			
			if (map.m_currentx2 -Operations.m_startx2== 4) {

				map.x = -1;
		

			}
			if (map.m_currentx2 == Operations.m_startx2) {

				map.x = 1;

			}


			if (map.m_currentx2 == Operations.m_currex && Operations.m_starty2 == Operations.m_currey) {
				Operations.eat();
			}
			
			
			
			
			if (map.m_currentx2 == map.m -1) {

				map.x = -1;

			}
			
			if (map.m_currentx2 == 1) {

				map.x = 1;

			}
			
			
			
			
			
			
		
			
			if (Operations.m_startx2_1 == Operations.m_startx3_1 && map.m_currenty2_1== Operations.m_starty3_1) {
				map.tp[Operations.m_startx2_1][map.m_currenty2_1].change(5);
			}else {
				map.tp[Operations.m_startx2_1][map.m_currenty2_1].change(1);
			}
				map.m_currenty2_1+=map.y;
				map.tp[Operations.m_startx2_1][map.m_currenty2_1].change(4);
			
		
			
			if (map.m_currenty2_1 -Operations.m_starty2_1== 4) {

				map.y = -1;

			}
			if (map.m_currenty2_1 == Operations.m_starty2_1) {

				map.y = 1;

			}


			if (Operations.m_startx2_1 == Operations.m_currex && map.m_currenty2_1 == Operations.m_currey) {
				Operations.eat();
			}
			
		
			
			
			
			if (map.m_currenty2_1 == map.n -1) {

				map.y= -1;

			}
			
			if (map.m_currenty2_1 == 1) {

				map.y = 1;

			}

			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
		

	}


}
