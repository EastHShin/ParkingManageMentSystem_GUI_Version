package ParkingSystem;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
				JOptionPane.showMessageDialog(null, "잘못된 입력입니다.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
			}
	         
	      });

	}

}
