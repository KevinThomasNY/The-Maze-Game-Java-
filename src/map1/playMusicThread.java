	package map1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;



public class playMusicThread extends Thread {

		static boolean exit=false;
		String []musicName={"resource/Gerudo Valley.wav","resource/Mining Melancholy.wav","resource/Stage 5.wav","resource/Thunderwave Cave.wav"};
		File file;
		
		float value;
		static Clip clip ;
		
		
		
		
		@Override
		public void run() {
			
			
			
			
		
				AudioInputStream audioInputStream = null;
				try {
					audioInputStream = AudioSystem.getAudioInputStream(
						    new File(musicName[map.musicNext]));
				} catch (UnsupportedAudioFileException e1) {
					 
					e1.printStackTrace();
				} catch (IOException e1) {
					 
					e1.printStackTrace();
				}
				
					try {
						clip = AudioSystem.getClip();
					} catch (LineUnavailableException e1) {
						 
						e1.printStackTrace();
					}
					try {
						clip.open(audioInputStream);
						FloatControl gainControl = 
							    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
							gainControl.setValue(0); 
							clip.start();
													
					} catch (LineUnavailableException | IOException e1) {
						 
						e1.printStackTrace();
					}
					
				
					
					
		
		
					
	
			
		}
	
	
}
