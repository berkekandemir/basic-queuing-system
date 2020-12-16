package G31_CENG112_HW3;

/**
 * This class is used to create individual customer objects.
 * @author Berke Can Kandemir and Cem Ozan
 */
public class Individual implements ICustomer {
	
	private String type;
	private int priority;

	public Individual(String type, int priority) {
		this.type = type;
		this.priority = priority;
	}

	public String getType() {
		return type;
	}

	public int getPriority() {
		return priority;
	}
	public String toString() {
		return "INDIVIDUAL";
	}

}
