package residents;

public final class Athlete extends Resident {

	public static enum floors{
		Fourth(4), Fifth(5), Sixth(6);
		
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
		monthlyRent = 1000;
	}
}
