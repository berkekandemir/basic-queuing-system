package G31_CENG112_HW3;

/**
 * This class is for the creating of the transaction objects. This constructs the body of the objects.
 * @author BCK
 *
 */
public class Transaction {
	private int id;
	private ICustomer customer;
	private int occupation;
	private int waiting;
	private Transaction next;
	private String type;
	private String date;
	
	/**
	 * The body constructor.
	 * @param id an unique integer that will be given from outside.
	 * @param customer the customer objects that will be given from outside.
	 * @param occupation an integer that will be given from outside.
	 * @param type a string that will be given from outside.
	 * @param date a string that will be given from outside.
	 * @param next a transaction object that will be given from outside, but actually it will be null at the beginning but, as the objects added the priority queue, next attribute will be assigned.
	 */
	public Transaction(int id, ICustomer customer, int occupation, String type, String date, Transaction next) {
		this.id = id;
		this.customer = customer;
		this.occupation = occupation;
		this.next = next;
		this.type = type;
		this.date = date;
	}
	
	public String toString() {
		return type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ICustomer getCustomer() {
		return customer;
	}

	public void setCustomer(ICustomer customer) {
		this.customer = customer;
	}

	public int getOccupation() {
		return occupation;
	}

	public void setOccupation(int occupation) {
		this.occupation = occupation;
	}

	public int getWaiting() {
		return waiting;
	}

	public void setWaiting(int waiting) {
		this.waiting = waiting;
	}

	public Transaction getNext() {
		return next;
	}

	public void setNext(Transaction next) {
		this.next = next;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	
}
