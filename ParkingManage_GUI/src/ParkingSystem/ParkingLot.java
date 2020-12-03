package ParkingSystem;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

import javax.swing.JOptionPane;

public class ParkingLot {
	private static int capacity;
	private static int income;
	public static String str;
	public ParkingLot(int capa) {
		capacity = capa;
		income = 0;
	}
	
public	void CurParking(Vehicle[] vehi) {
		int i,j;
		int size = ParkingMFrame.getCurrentIndex();
		Vehicle temp;
		str = "";
		for(i=0; i<size-1; i++) {
			for(j= 1; j<size-i; j++) {
				if(vehi[j-1].time.getinTime().getTime() > vehi[j].time.getinTime().getTime()) {
					temp = vehi[j-1];
					vehi[j-1] = vehi[j];
					vehi[j] = temp;
				}
			}
		}
		for(i =0; i<size; i++)
			printInfoCar(vehi[i]);
		for(i =0; i<size; i++)
			printInfoTruc(vehi[i]);
		for(i =0; i<size; i++)
			printInfoBus(vehi[i]);
		JOptionPane.showMessageDialog(null, str);
	}
	public void printInfoCar(Vehicle car) {
		SimpleDateFormat transform = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		if(car instanceof Car) {
			str += "�¿��� " + car.VehicleNum + " " + transform.format(car.time.getinTime()) + "\n";
			
		}
	}
	public void printInfoTruc(Vehicle truc) {
		SimpleDateFormat transform = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		if(truc instanceof Truc) {
			str += "Ʈ�� " + truc.VehicleNum + " " + transform.format(truc.time.getinTime()) + "\n";
			
		}
	}
	public void printInfoBus(Vehicle bus) {
		SimpleDateFormat transform = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		if(bus instanceof Bus) {
			str += "���� " + bus.VehicleNum + " " + transform.format(bus.time.getinTime()) + "\n";
		}
	}
	
	
	public void findVehicleNum(Vehicle[] vehi, int vehinum,Date date) {
		str = "";
		int i;
		int j = ParkingMFrame.getCurrentIndex();
		int isThere = 0;
		for(i=0; i<j; i++) {
			if(vehi[i].VehicleNum == vehinum) {
				isThere = 1;
				break;
			}
		}
		if(isThere == 0) {
			JOptionPane.showMessageDialog(null, "�� ��ȣ�� ������ ��ϵǾ� ���� �ʽ��ϴ�.");
			return;
		}
		if((date.getTime() - vehi[i].time.getinTime().getTime()) < 0) {
			JOptionPane.showMessageDialog(null, "�����ð��� �����ð����� ���� �ð��̾�� �մϴ�!");
			return;
		}
		vehi[i].outTime(date);
		if(vehi[i] instanceof Car) {
			str += "�����ð��� " + (vehi[i].time.getoutTime()/60) + "�ð� " + (vehi[i].time.getoutTime() % 60) + "�� �Դϴ�.\n";
		}
		else if(vehi[i] instanceof Bus) {
			str += "�����ð��� " + (vehi[i].time.getoutTime()/60) + "�ð� " + (vehi[i].time.getoutTime() % 60) + "�� �Դϴ�.\n";
		}
		else if(vehi[i] instanceof Truc) {
			str += "�����ð��� " + vehi[i].time.getoutTime() + "�ð� �Դϴ�.\n";
		}
		String fee = String.format("%,d", vehi[i].calcFee());
		str += "��������� " + fee + "�� �Դϴ�.\n";
		JOptionPane.showMessageDialog(null, str);
		income += vehi[i].calcFee();
		int delIndex = i;
		for(i = delIndex; i<j-1; i++) {
			vehi[i] = vehi[i+1];
		}
		ParkingMFrame.setCurrentIndex(--j);
	}

	public int TotalIncome() {
		return income;
	}
	public boolean existNum(Vehicle[] vehicle, int num) {
		int i;
		int j = ParkingMFrame.getCurrentIndex();
		
		for(i=0; i<j; i++) {
			if(vehicle[i].VehicleNum == num) {
				return true;
			}
		}
		return false;
		
	}
	
}