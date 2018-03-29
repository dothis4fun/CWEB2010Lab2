package residents;

public class Worker extends Resident {

	public static enum floors{
		First(1), Second(2), Third(3);
		
		private int value;    

		  private floors(int value) {
		    this.value = value;
		  }

		  public int getValue() {
		    return value;
		  }
		  
		  public String toString() {
			  return Integer.toString(value);
		  }
	}
	private double hoursWorked;
	private final double BASE_PAY = 14.00;
	public double GetHoursWorked() {
		return hoursWorked;
	}
	public void SetHoursWorked(double hours) {
		hoursWorked = hours;
	}
	
	@Override
	public void SetMonthlyRent() {
		final int BASE_RENT = 1245;
		monthlyRent = BASE_RENT - ((BASE_PAY * hoursWorked) / 2);
	}

	
}
