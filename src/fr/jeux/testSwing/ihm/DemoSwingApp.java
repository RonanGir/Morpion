package fr.jeux.testSwing.ihm;

import javax.swing.SwingUtilities;

public class DemoSwingApp {

	public static void main(String[] args) {
		
		//Executer l'écran principal dans un thread specifique
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
				EcranMorpion frame = new EcranMorpion();
				frame.setVisible(true);
				
			}
			
		});
	}

}
