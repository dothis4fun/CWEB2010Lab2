
import residents.*;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class CWEB2010JavaLab2 {

	static Scanner input = new Scanner(System.in);
	static ArrayList<Resident> residentList = new ArrayList<Resident>();
	public static void main(String[] args) {
		WelcomeUser();
		do {
			AddResident();
		}while(ShouldContinue());
		PrintListOfResidents();
	}
	
	public static void WelcomeUser() {
		System.out.println("Welcome, this program will allow you to enter residents");
		System.out.println("The program will then show you a list of all entered residents");
		System.out.println("Lets get started by adding a new resident");
	}
	
	public static void AddResident() {
		Resident resident = null;
		String residentType = AskResidentType();
		switch(residentType) {
		case "Athlete":
			resident = new Athlete();
			break;
		case "Scholarship":
			resident = new Scholarship();
			break;
		case "Worker":
			resident = new Worker();
			break;
		}
		resident.SetStudentType(residentType);
		resident.SetFirstName(AskResidentName());
		resident.SetLastName(AskResidentName(resident.GetFirstName()));
		resident.SetFloorNumber(AskResidentFloorNumber(resident));
		resident.SetDormNumber(AskResidentDormNumber(resident, residentList));
		if(resident.getClass().getSimpleName().equals("Worker")) {
			((Worker)resident).SetHoursWorked(AskResidentWorkedHours());
			resident.SetMonthlyRent();
		}
		residentList.add(resident);
	}
	
	public static String AskResidentType() {
		int x = 1;
		System.out.println("Enter the number next to the type of resident you would like to add?");
		for(Resident.Type residentType : Resident.Type.values()) {
			System.out.println(x + " : " + residentType);
			++x;
		}
		String userAnswer = input.next();
		while(!intTryParse(userAnswer)  || !ValidNumber(Integer.parseInt(userAnswer), 0, Resident.Type.values().length)) {
			System.out.println("Please choose a value from the list above");
			userAnswer = input.next();
		}
		x = Integer.parseInt(userAnswer);
		String residentType = Resident.Type.values()[x].toString();
		return residentType;
	}
	public static boolean ValidNumber(int numberToCheck, int lowRange, int highRange) {
		if(numberToCheck > lowRange && numberToCheck < highRange) {
			return true;
		}
		else {
			return false;
		}
	}
	public static boolean intTryParse(String integer) {
		try {
			int x = Integer.parseInt(integer);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
	public static String AskResidentName() {
		String name;
		System.out.println("Please enter the first name of the resident");
		name = input.next();
		return name;
	}
	
	public static String AskResidentName(String firstName) {
		String name;
		System.out.println("Please enter the last name of " + firstName);
		name = input.next();
		return name;
	}
//	public static boolean stringHasContents(String string) {
//		if(string.trim().length() > 0) {
//			return true;
//		}
//		else {
//			return false;
//		}
//	}
	
	
	public static int AskResidentFloorNumber(Resident resident) {
		int x = 0;
		String userAnswer;
		boolean validFloor = false;
		ArrayList<Integer> floors = new ArrayList<Integer>();
		System.out.println("Please enter the number next to the floor " + resident.GetFirstName() + " will be staying");
		System.out.println("The options are as follows:");
		Class<?> residentClass = resident.getClass();
		Class<?> enumClass = residentClass.getDeclaredClasses()[0];
		Enum<?>[] enumConstants = (Enum<?>[]) enumClass.getEnumConstants();
		
		for(int i = 0; i < enumConstants.length ; ++i) {
			System.out.println(enumConstants[i].toString() + " : " + enumConstants[i].name());
			floors.add(Integer.parseInt(enumConstants[i].toString()));
		}
		while(!validFloor) {
			userAnswer = input.next();
			while(!intTryParse(userAnswer)) {
				System.out.println("Entered value must be an integer");
				userAnswer = input.next();
			}
			x = Integer.parseInt(userAnswer);
			if(!floors.contains(x)) {
				System.out.println("Note: Different types of residents are restricted to certain floors");
				System.out.println("Please enter a valid floor from the list above");
			}
			else {
				validFloor = true;
			}
		}
		return x;
	}
	
	public static int AskResidentDormNumber(Resident resident, ArrayList<Resident> residentList) {
		int x = 0;
		String userAnswer;
		boolean validDorm = false;
		System.out.println("Please enter the dorm number for " + resident.GetFirstName() + ". 1-99");
		userAnswer = input.next();
		while(!validDorm) {
			while(!intTryParse(userAnswer)) {
				System.out.println("Entered value must be an integer");
				userAnswer = input.next();
			}
			x = Integer.parseInt(userAnswer);
			if(x < 0 || x > 100) {
				System.out.println("Only rooms 1-99 are available for students");
				System.out.println("Please enter a valid room number");
				while(!intTryParse(userAnswer = input.next())) {
					System.out.println("Entered value must be an integer");
				}
				x = Integer.parseInt(userAnswer);
			}
			else if(!IsEmptyRoom(residentList, resident, x)) {
				System.out.println("That room is occupied by another resident");
				System.out.println("Please enter another room number");
				while(!intTryParse(userAnswer = input.next())) {
					System.out.println("Entered value must be an integer");
				}
				x = Integer.parseInt(userAnswer);
			}
			else {
				validDorm = true;
			}
		}
		return x;
	}
	public static boolean IsEmptyRoom(ArrayList<Resident> residentList, Resident applicant, int x) {
		if(residentList.stream()
				  .filter((occupant) -> occupant.GetFloorNumber() == applicant.GetFloorNumber() && occupant.GetDormNumber() == x)
				  .findFirst().orElseGet(() -> null) == null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static double AskResidentWorkedHours() {
		String userAnswer;
		double hoursWorked;
		System.out.println("How many hours does the resident work each month");
		userAnswer = input.next();
		while(!doubleTryParse(userAnswer)) {
			System.out.println("Please enter a valid number for hours worked");
			System.out.println("Note: Enter a decimal for portions of hours worked");
			userAnswer = input.next();
		}
		hoursWorked = Double.parseDouble(userAnswer);
		return hoursWorked;
	}
	public static boolean doubleTryParse(String numberToCheck) {
		try {
			double d = Double.parseDouble(numberToCheck);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
	
	public static boolean ShouldContinue() {
		String answer;
		boolean askAgain = true;
		boolean shouldContinue = false;
		input.nextLine();
		while(askAgain) {
			System.out.println("Do you want to add another user? Y/N");
			answer = input.nextLine();
			if(answer.toLowerCase().equals("y")) {
				shouldContinue = true;
				askAgain = false;
			}
			else if(answer.toLowerCase().equals("n")) {
				shouldContinue = false;
				askAgain = false;
			}
		}
		return shouldContinue;
	}
	
	public static void PrintListOfResidents() {
		for(Resident resident : residentList) {
			System.out.println(String.format("Student Type : %-15s ID: %d \nFirst Name: %-18s Last Name: %s \nFloor: %-2d Room: %-14d Monthly Rent: " + NumberFormat.getCurrencyInstance().format(resident.GetMonthlyRent()) + "\n",resident.GetStudentType(), resident.GetId(), resident.GetFirstName(), resident.GetLastName(), resident.GetFloorNumber(), resident.GetDormNumber()));
			ResidentDashedSeparator(50);
		}
	}
	
	public static void ResidentDashedSeparator(int stringLength) {
		String string = "-";
		for(int i = 0; i < stringLength; ++i) {
			string += string.charAt(0);
		}
		System.out.println(string);
	}
}
