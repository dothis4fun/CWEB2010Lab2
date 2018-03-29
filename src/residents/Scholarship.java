package residents;

public final class Scholarship extends Resident implements ResidentFloors {

	public static enum floors{
		Seventh(7), Eigth(8);
		
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
	@Override
	public void SetMonthlyRent() {
		final int BASE_RENT = 100;
		monthlyRent = BASE_RENT;
	}
	
	
}
