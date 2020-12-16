package G31_CENG112_HW3;

/**
 * This class is used to create non-registered customer objects.
 * @author Berke Can Kandemir and Cem Ozan
 */
public class NonRegistered implements ICustomer {

	private String type;
	private int priority;

	public NonRegistered(String type, int priority) {
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
		return "NON-REGISTERED";
	}

}
