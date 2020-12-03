package ParkingSystem;

import java.awt.*;
import javax.swing.*;

public class MaxParking extends JPanel{
	private JTextField maxP;
	
	private JButton okButton;
	private boolean ok;
	private JDialog dialog;

	public MaxParking() {
		setLayout(new BorderLayout());
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,1));
		panel.add(new JLabel("주차장에 최대 몇대를 주차할 수 있습니까?"));
		panel.add(maxP = new JTextField(""));
		
		add(panel, BorderLayout.CENTER);
		okButton = new JButton("Ok");
		okButton.addActionListener(event -> {
			ok = true;
			dialog.setVisible(false);
		});
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(event -> dialog.setVisible(false));
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(okButton);
		buttonPanel.add(cancelButton);
		add(buttonPanel, BorderLayout.SOUTH);
	}
	public String getmaxP() {
		return maxP.getText();
	}
	
	public boolean showDialog(Component parent, String title) {
		ok = false;
		Frame owner = null;
		if(parent instanceof Frame)
			owner = (Frame)parent;
		else
			owner = (Frame)SwingUtilities.getAncestorOfClass(Frame.class, parent);
		if(dialog == null || dialog.getOwner() != owner) {
			dialog = new JDialog(owner, true);
			dialog.add(this);
			dialog.getRootPane().setDefaultButton(okButton);
			dialog.pack();
		}
		dialog.setTitle(title);
		dialog.setVisible(true);
		return ok;
	}
}

