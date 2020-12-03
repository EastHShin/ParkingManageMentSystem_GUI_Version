package ParkingSystem;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class main {

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
	         JFrame frame;
			try {
				frame = new ParkingMFrame();
				frame.setTitle("Parking Management System");
		         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		         frame.setVisible(true);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	         
	      });

	}

}
