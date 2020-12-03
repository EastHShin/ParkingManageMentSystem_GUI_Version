package ParkingSystem;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Frame;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class outVehicleInfo extends JPanel{
	private JTextField time;
	private JTextField VehicleNum;
	private JButton okButton;
	private boolean ok;
	private JDialog dialog;

	public outVehicleInfo() {
		time = null;
		VehicleNum = null;
		setLayout(new BorderLayout());
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2,2));
		
		panel.add(new JLabel("������ȣ�� �Է��ϼ���!  : "));
		panel.add(VehicleNum = new JTextField(""));
		panel.add(new JLabel("�����ð��� �Է��ϼ���  : "));
		panel.add(time = new JTextField(""));
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
	public String gettime() {
		return time.getText();
	}
	public String getVehicleNum() {
		return VehicleNum.getText();
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
