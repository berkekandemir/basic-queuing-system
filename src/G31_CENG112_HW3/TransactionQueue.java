package G31_CENG112_HW3;

/**
 * This class is the implementation of the priority queue.
 * @author Berke Can Kandemir and Cem Ozan
 * @param <T> the generic data type
 */
@SuppressWarnings("rawtypes")
public class TransactionQueue<T> implements IPriorityQueue<T>{
	
	private Transaction head;
	private int queueLength = 0;
	private int totalWaitingTime;
	private String date;
	private TransactionQueue next;
	private Node firstNode;
	private Node lastNode;
	
	/**
	 * This method is being used for the process of adding an objects into the queue according to its priority.
	 * @param transaction the given transaction object
	 */
	@SuppressWarnings("unchecked")
	public void insert(Transaction transaction) {
		
		Node transactionNode = new Node((T) transaction);
		date = transaction.getDate();
		Node nodeAfter;
		Node target;
		if (isEmpty()) {
			firstNode = transactionNode;
			lastNode = transactionNode;
			queueLength++;
		} else {
			/**
			 * We use necessary method according to the transaction objects' priority level.
			 * getNodeAt method returns the node that will be the previous of our transaction object.
			 */
			if (transaction.getCustomer().getPriority() == 1) {
				target = getNodeAt(1, transaction.getId());
				/**
				 * If the transaction objects should be added at the beginning of the queue, getNodeAt method return null.
				 * We check here if the target is null, or not.
				 */
				if (target == null) {
					transactionNode.setNextNode(firstNode);
					lastNode = firstNode;
					firstNode = transactionNode;
					queueLength++;
				} else {
					nodeAfter = target.getNextNode();
					transactionNode.setNextNode(nodeAfter);
					target.setNextNode(transactionNode);
					queueLength++;
					lastNode = nodeAfter;
				}
			} else if (transaction.getCustomer().getPriority() == 2) {
				target = getNodeAt(2, transaction.getId());
				if (target == null) {
					transactionNode.setNextNode(firstNode);
					lastNode = firstNode;
					firstNode = transactionNode;
					queueLength++;
				} else {
					nodeAfter = target.getNextNode();
					transactionNode.setNextNode(nodeAfter);
					target.setNextNode(transactionNode);
					queueLength++;
					lastNode = nodeAfter;
				}
			} else {
				target = getNodeAt(3, transaction.getId());
				if (target == null) {
					transactionNode.setNextNode(firstNode);
					lastNode = firstNode;
					firstNode = transactionNode;
					queueLength++;
				} else {
					nodeAfter = target.getNextNode();
					transactionNode.setNextNode(nodeAfter);
					target.setNextNode(transactionNode);
					queueLength++;
					lastNode = nodeAfter;
				}
			}
		}
	}
	/**
	 * This method places the waiting values into the transaction objects according to their occupation value and their allignment.
	 */
	private void placeWaitings() {
		Node currentNode = firstNode;
		int index = 0;
		int waiting = 0;
		int occupation = ((Transaction) currentNode.getData()).getOccupation();
		((Transaction) currentNode.getData()).setWaiting(waiting);
		while ((index < queueLength) && (currentNode != null)) {
			occupation = ((Transaction) currentNode.getData()).getOccupation();
			((Transaction) currentNode.getData()).setWaiting(waiting);
			waiting = waiting + occupation;
			currentNode = currentNode.getNextNode();
		}
	}
	/**
	 * This method calculates the queues total waiting value.
	 * @return the total waiting value
	 */
	public int getTotalWaiting() {
		placeWaitings();
		int result = 0;
		int index = 0;
		Node currentNode = firstNode;
		while ((index < queueLength) && (currentNode != null)) {
			result = result + ((Transaction) currentNode.getData()).getWaiting();
			currentNode = currentNode.getNextNode();
		}
		return result;
	}
	/**
	 * This method calculates the transaction count for a specific type of customer.
	 * @param customer the customer type
	 * @return the transaction count of the given type of customer
	 */
	public int transactionCount(String customer) {
		int count = 0;
		int index = 0;
		Node currentNode = firstNode;
		while ((index < queueLength) && (currentNode != null)) {
			if (((Transaction) currentNode.getData()).getType().equals(customer)) {
				count++;
			}
			currentNode = currentNode.getNextNode();
		}
		return count;
	}
	/**
	 * This method calculates the average waiting time  of the queue.
	 * @param waiting the waiting value
	 * @param count the number of transactions
	 * @return the average waiting time
	 */
	public float averageWaiting(int waiting, int count) {
		if (count == 0) {
			return 0;
		} else {
			float averageWaiting = (float) waiting / count;
			return averageWaiting;
		}
	}
	/**
	 * This method calculates the total waiting time of the specific type of customer.
	 * @param customer the customer type
	 * @return the waiting time of the specified customer type
	 */
	public int waitingCustomer(String customer) {
		int waiting = 0;
		int index = 0;
		Node currentNode = firstNode;
		while ((index < queueLength) && (currentNode != null)) {
			if (((Transaction) currentNode.getData()).getType().equals(customer)) {
				waiting = waiting + ((Transaction) currentNode.getData()).getWaiting();
			}
			currentNode = currentNode.getNextNode();
		}
		return waiting;
	}
	/**
	 * This method makes sense of the data in the queue.
	 * @return the final result of the data in the queue
	 */
	public String toString() { 
		placeWaitings();
		String result = "COUNTER ";
		Node currentNode = firstNode;
		int index = 0;
		while ((index < queueLength) && (currentNode != null)) {
			int id = ((Transaction) currentNode.getData()).getId();
			String type = ((Transaction) currentNode.getData()).getType();
			if (type.equals("CORPORATE")) {
				type = "COR";
			} else if (type.equals("INDIVIDUAL")) {
				type = "IND";
			} else {
				type = "NON";
			}
			int occupation = ((Transaction) currentNode.getData()).getOccupation();
			int waiting = ((Transaction) currentNode.getData()).getWaiting();
			result = result + " <-- " + String.valueOf(id) + "|" + type + "|" + String.valueOf(occupation) + "|" + String.valueOf(waiting);
			currentNode = currentNode.getNextNode();
		}
		return result;
	}
	/**
	 * the body constructor of the queue. Other attributes will be assigned in methods.
	 */
	public TransactionQueue() {
		firstNode = null;
		lastNode = null;
	}
	/**
	 * This method is for the simple adding to the queue. It basically enqueues the objects.
	 */
	public void add(T newEntry) {
		Node newNode = new Node(newEntry, null);
		if (isEmpty()) {
			firstNode = newNode;
		} else {
			lastNode.setNextNode(newNode);
		}
		lastNode = newNode;
	}
	/**
	 * This method is for the removing of objects. It basically dequeues the objects. 
	 * @return the removed element
	 */
	@SuppressWarnings("unchecked")
	public T remove() { 
		T front = (T) firstNode;
		assert firstNode != null;
		firstNode.setData(null);
		firstNode = firstNode.getNextNode();
		if (firstNode == null) {
			lastNode = null;
		}
		return front;
	}
	/**
	 * This method is for the observation of the top object of the queue.
	 *  @return the data of the firstNode
	 */
	public T peek() {
		if (isEmpty()) {
			throw new IllegalStateException();
		} else {
			return firstNode.getData();
		}
	}
	/**
	 * This method is for the checking of the emptiness of the queue.
	 * @return true or false according to the result
	 */
	public boolean isEmpty() {
		return (firstNode == null) && (lastNode == null);
	}
	/**
	 * The method for reaching to the queue length.
	 * @return queueLength
	 */
	public int getSize() {
		return queueLength;
	}
	/**
	 * This method finds the node that will be the previous node of our transaction node that will be added to the queue.
	 * @param priority the priority level
	 * @param id the unique id number
	 * @return null or the previous node according to the given priority and id.
	 */
	public Node getNodeAt(int priority, int id) {
		Node currentNode = firstNode;
		@SuppressWarnings("unused")
		Node nodeBefore = null;
		Node nodeAfter = currentNode.getNextNode();
		Node result = null;
		for (int i = 0; i < queueLength; i++) {
			if (isEmpty()) {
				result = firstNode;
				/**
				 * First it looks for the objects that have the same priority level and if it finds, it checks the id.
				 * According to the next node it returns the result.
				 */
			} else if (((Transaction) currentNode.getData()).getCustomer().getPriority() == priority) {
				if (((Transaction) currentNode.getData()).getId() < id) {
					if (nodeAfter == null) {
						result = currentNode;
						return result;
					} else if (((Transaction) nodeAfter.getData()).getCustomer().getPriority() != priority) {
						result = currentNode;
						return result;
					}
				} else {
					continue;
				}
				/**
				 * If it cannot finds the objects that have the same priority level, it checks the other nodes's priority levels and finds the suitable place. 
				 */
			} else if (((Transaction) currentNode.getData()).getCustomer().getPriority() > priority) {
				result = nodeBefore;
				return result;
			} else if ((((Transaction) currentNode.getData()).getCustomer().getPriority() < priority) && (((Transaction) nodeAfter.getData()).getCustomer().getPriority() > priority)){
				result = currentNode;;
				return result;
			}
			nodeBefore = currentNode;
			currentNode = currentNode.getNextNode();
			nodeAfter = currentNode.getNextNode();
		}
		return result;
	}

	public Transaction getHead() {
		return head;
	}

	public void setHead(Transaction head) {
		this.head = head;
	}

	public int getQueueLength() {
		return queueLength;
	}

	public void setQueueLength(int queueLength) {
		this.queueLength = queueLength;
	}

	public int getTotalWaitingTime() {
		return totalWaitingTime;
	}

	public void setTotalWaitingTime(int totalWaitingTime) {
		this.totalWaitingTime = totalWaitingTime;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@SuppressWarnings("unchecked")
	public TransactionQueue<T> getNext() {
		return next;
	}

	public void setNext(TransactionQueue<T> next) {
		this.next = next;
	}

	public Node getFirstNode() {
		return firstNode;
	}

	public void setFirstNode(Node firstNode) {
		this.firstNode = firstNode;
	}

	public Node getLastNode() {
		return lastNode;
	}

	public void setLastNode(Node lastNode) {
		this.lastNode = lastNode;
	}
	/**
	 * This method simply clears the queue.
	 */
	public boolean clear() { 
		boolean result = false;
		firstNode = null;
		lastNode = null;
		if ((firstNode == null) && (lastNode == null)) {
			result = true;
		}
		return result;
	}
	/**
	 * The node class that is being used in the linked queue implementation.
	 * @author Berke Can Kandemir and Cem Ozan
	 *
	 */
	private class Node { // # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # 
		private T data;
		private Node next;
		
		private Node(T data) {
			this(data, null);
		}
		
		private Node(T dataPortion, Node nextNode) {
			data = dataPortion;
			next = nextNode;
		}

		public T getData() {
			return data;
		}

		public void setData(T newData) {
			data = newData;
		}

		public Node getNextNode() {
			return next;
		}

		public void setNextNode(Node nextNode) {
			this.next = nextNode;
		}
	}
}