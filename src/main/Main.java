package main;

import entity.Entity;
import entity.TmpMob;
import fight.EntityFight;
import fight.Fight;
import utils.Environment;

import javax.swing.JFrame;



public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Short Adventure");

		Entity e = new TmpMob(10, 1, 10);
		Fight f = new EntityFight(e, window);

		f.run();

		/* window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		gamePanel.startGameThread();*/

	}

}
