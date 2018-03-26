package residents;
import java.util.ArrayList;
import java.util.Random;
public abstract class Resident {
	
	Resident(){
		SetId();
	}
	 private static ArrayList<Integer> idNumbers = new ArrayList<Integer>();
	 private int Id;
	 private String firstName;
	 private String lastName;
	 private int dormNumber;
	 private int floorNumber;
	 private String studentType;
	 protected double monthlyRent;
	 
	 // Set ID
	 public void SetId() {
		 int x;
		 Random randomNumber = new Random();
		 Boolean isValid = false;
		 while(!isValid) {
			 x = randomNumber.nextInt(900_000) + 100_000;
			 if(!idNumbers.contains(x)) {
				 idNumbers.add(x);
				 Id = x;
				 isValid = true;
			 }
		 }
	 }
	 public int GetId() {
		 return Id;
	 }
	 
	 //Set First Name
	 public void SetFirstName(String name) {
		 firstName = name;
	 }
	 public String GetFirstName() {
		 return firstName;
	 }
	 
	 //Set Last Name
	 public void SetLastName(String name) {
		 lastName = name;
	 }
	 public String GetLastName() {
		 return lastName;
	 }
	 
	 //Set Dorm Number
	 public void SetDormNumber(int dorm) {
		 dormNumber = dorm;
	 }
	 public int GetDormNumber() {
		 return dormNumber;
	 }
	 
	 //Set Floor Number
	 public void SetFloorNumber(int floor) {
		 floorNumber = floor;
	 }
	 public int GetFloorNumber() {
		 return floorNumber;
	 }
	 
	 //Set Student Type
	 public void SetStudentType(String type) {
		 studentType = type;
	 }
	 public String GetStudentType() {
		 return studentType;
	 }
	 
	 //Abstract methods for monthly Rent
	 public abstract void SetMonthlyRent();
	 public double GetMonthlyRent() {
		 return monthlyRent;
	 };
}
