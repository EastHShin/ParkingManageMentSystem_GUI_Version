package ParkingSystem;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.*;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.*;



public class ParkingMFrame extends JFrame {
	private static int i = 0;
	int choice;
	char typeOfvehicle;
	int num;
	int vehiNum;	
	String date;
	String strNum;
	ParkingLot p;
	Vehicle[] vehicle;
	private VehicleInfo dialog1 = null;
	private outVehicleInfo dialog2 = null;
	public ParkingMFrame() throws Exception{
		MaxParking dialog0 = new MaxParking();
		dialog0.showDialog(ParkingMFrame.this, "주차장");
		int maxp = Integer.parseInt(dialog0.getmaxP());
		p = new ParkingLot(maxp);
		vehicle = new Vehicle[maxp];
		
		BorderLayout layout = new BorderLayout();
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JLabel label = new JLabel("기능을 선택하세요");
		JButton btn1 = new JButton("입차");
		JButton btn2 = new JButton("출차");
		JButton btn3 = new JButton("주차차량 보기");
		JButton btn4 = new JButton("총 수입 보기");
		JButton btn5 = new JButton("종료");
		panel1.add(label);
		panel2.add(btn1);
		panel2.add(btn2);
		panel2.add(btn3);
		panel2.add(btn4);
		panel2.add(btn5);
		add(panel1, layout.NORTH);
		add(panel2, layout.CENTER);
		setSize(500,300);
		btn1.addActionListener(
				event -> {
					typeOfvehicle = '\0';
					num = 0;
					vehiNum = 0;
					date = null;
					strNum = null;
					dialog1 = new VehicleInfo();
					dialog1.showDialog(ParkingMFrame.this, "입차");
					typeOfvehicle = dialog1.getVehicleType().charAt(0);
					num = Integer.parseInt(dialog1.getsize());
					strNum = dialog1.getVehicleNum();
					date = dialog1.gettime();
					if(!RightType(typeOfvehicle) || strNum.length() != 4) {
						JOptionPane.showMessageDialog(null, "잘못된 입력입니다.");
					}
					else {
						try {
							vehiNum = Integer.parseInt(strNum);
							SimpleDateFormat fm = new SimpleDateFormat("yyyy MM dd HH mm");
							Date to = fm.parse(date);
							if(p.existNum(vehicle, vehiNum) || !rightTime(date))
								JOptionPane.showMessageDialog(null, "잘못된 입력입니다.");
							else
								insert(vehicle, typeOfvehicle, num, vehiNum, to);
						}catch(NumberFormatException e) {
							JOptionPane.showMessageDialog(null, "잘못된 입력입니다.");
						}catch(ParseException e) {
							JOptionPane.showMessageDialog(null, "잘못된 입력입니다.");
						}
					}
					
				});
		btn2.addActionListener(
				event -> {
					vehiNum = 0;
					date = null;
					strNum = null;
					dialog2 = new outVehicleInfo();
					dialog2.showDialog(ParkingMFrame.this, "출차");
					strNum = dialog2.getVehicleNum();
					date = dialog2.gettime();
					if(strNum.length() != 4) {
						JOptionPane.showMessageDialog(null, "잘못된 입력입니다.");
					}
					else {
						try {
							vehiNum = Integer.parseInt(strNum);
							SimpleDateFormat fm = new SimpleDateFormat("yyyy MM dd HH mm");
							Date to = fm.parse(date);
							if(!rightTime(date))
								JOptionPane.showMessageDialog(null, "잘못된 입력입니다.");
							else
								p.findVehicleNum(vehicle, vehiNum, to);
						}catch(NumberFormatException e) {
							JOptionPane.showMessageDialog(null, "잘못된 입력입니다.");
						}catch(ParseException e) {
							JOptionPane.showMessageDialog(null, "잘못된 입력입니다.");
						}
					}
				});
		btn3.addActionListener(
				event -> {
					p.CurParking(vehicle);
				});
		btn4.addActionListener(
				event -> {
					int total = p.TotalIncome();
					String income = String.format("%,d", total);
					JOptionPane.showMessageDialog(null,income);
				});
		btn5.addActionListener(
				event -> {
					System.exit(0);
				});
		
		
	}
	private static void insert(Vehicle[] vehicle, char type, int num, int vehiNum, Date to) {
		try {
		if(type == 'c') {			//car
			if(num == 0)
				vehicle[i++] = new Car(vehiNum, to);
			else
				vehicle[i++] = new ElecCar(num, vehiNum, to);
		}
		else if(type == 'b') {			//bus
			vehicle[i++] = new Bus(num, vehiNum, to);
		}
		else if(type == 't') {			//truc
			vehicle[i++] = new Truc(num, vehiNum, to);
		}
		}catch(ArrayIndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(null, "주차장이 꽉 찼습니다.");
			i--;
			return;
		}
	}
	public static int getCurrentIndex() {
		return i;
	}
	public static void setCurrentIndex(int index) {
		i = index;
	}
	private static boolean RightType(char type) {
		if(type == 'c' || type == 'b' || type == 't')
			return true;
		else
			return false;
	}
	private static boolean rightTime(String date) {
		int exceptMonth, exceptdate, hour, minute;
		String[] splitArr;
		GregorianCalendar gr = new GregorianCalendar();
		splitArr = date.split(" ");
		exceptMonth = Integer.parseInt(splitArr[1]);
		exceptdate = Integer.parseInt(splitArr[2]);
		hour = Integer.parseInt(splitArr[3]);
		minute = Integer.parseInt(splitArr[4]);
		if(hour > 24 || minute > 59) {
			JOptionPane.showMessageDialog(null, "시간이 올바르지 않습니다.");
			return false;
		}
		switch(exceptMonth) {
		case 1: case 3: case 5: case 7: case 8: case 10: case 12:
			if(exceptdate > 31) {
				JOptionPane.showMessageDialog(null, "이 달은 31일까지 입니다.");
				return false;
			}
			break;
		case 4: case 6: case 11:
			if(exceptdate > 30) {
				JOptionPane.showMessageDialog(null, "이 달은 30일까지 입니다.");
				return false;
			}
			break;
		case 2:
			if(gr.isLeapYear(exceptMonth)) {
				if(exceptdate > 29) {
					JOptionPane.showMessageDialog(null, "이 달은 29일까지 입니다.");
					return false;
				}
			}
			else {
				if(exceptdate > 28) {
					JOptionPane.showMessageDialog(null, "이 달은 28일까지 입니다.");
					return false;
				}
			}
			break;
		default:
			JOptionPane.showMessageDialog(null, "1월~12월까지만 입력 가능합니다.");
			return false;
		}
		return true;
	}
}
