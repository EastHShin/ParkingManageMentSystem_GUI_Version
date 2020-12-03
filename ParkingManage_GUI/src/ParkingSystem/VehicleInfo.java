package ParkingSystem;

import java.awt.*;
import javax.swing.*;


public class VehicleInfo extends JPanel{
	private JTextField TypeVehicle;
	private JTextField Size;
	private JTextField time;
	private JTextField VehicleNum;
	private JButton okButton;
	private boolean ok;
	private JDialog dialog;

	public VehicleInfo() {
		setLayout(new BorderLayout());
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4,4));
		panel.add(new JLabel("차량 종류를 입력하세요! 승용차(c), 버스(b), 트럭(t)"));
		panel.add(TypeVehicle = new JTextField(""));
		panel.add(new JLabel("용량을 입력하세요! : "));
		panel.add(Size = new JTextField(""));
		panel.add(new JLabel("차량번호를 입력하세요!  : "));
		panel.add(VehicleNum = new JTextField(""));
		panel.add(new JLabel("입차시간을 입력하세요  : "));
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
	public String getVehicleType() {
		return TypeVehicle.getText();
	}
	public String getsize() {
		return Size.getText();
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
