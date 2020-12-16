package G31_CENG112_HW3;

/**
 * This class is the implementation of the linked list.
 * @author Berke Can Kandemir and Cem Ozan
 * @param <T> the generic data type
 */
public class TransactionQueueList<T> implements ILinkedList<T>{
	private Node firstNode;
	private int numberOfEntries;
	
	/**
	 * The body constructor of the class.
	 */
	public TransactionQueueList() {
		initializeDataFields();
	}
	/**
	 * The data initializer.
	 */
	private void initializeDataFields() {
		firstNode = null;
		numberOfEntries = 0;
	}
	/**
	 * This node gets the node at the given position.
	 * @param givenPosition
	 * @return the node that is wanted
	 */
	@SuppressWarnings("unused")
	private Node getNodeAt(int givenPosition) {
		assert (firstNode != null) && (1 <= givenPosition) && (givenPosition <= numberOfEntries);
		Node currentNode = firstNode;
		for (int counter = 1; counter < givenPosition; counter++) {
			currentNode = currentNode.getNextNode();
			assert currentNode != null;
		}
		return currentNode;
	}
	/**
	 * The adding method for the linked list.
	 * @param newEntry the new element
	 */
	public void add(T newEntry) {
		Node newNode = new Node(newEntry);
		if (isEmpty()) {
			firstNode = newNode;
		} else {
			Node lastNode = getNodeAt(numberOfEntries);
			lastNode.setNextNode(newNode);
		}
		numberOfEntries++;
	}
	/**
	 * The adding method for the linked list with position.
	 * @param newPosition the wanted position
	 * @param newEntry the new element
	 */
	public void add(int newPosition, T newEntry) {
		if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1)) {
			Node newNode = new Node(newEntry);
			if (newPosition == 1) {
				newNode.setNextNode(firstNode);
				firstNode = newNode;
			} else {
				Node nodeBefore = getNodeAt(newPosition - 1);
				Node nodeAfter = nodeBefore.getNextNode();
				newNode.setNextNode(nodeAfter);
				nodeBefore.setNextNode(newNode);
			}
			numberOfEntries++;
		} else throw new IndexOutOfBoundsException("Illegal position given to add operation!");
	}
	/**
	 * The removing method for the linked list.
	 * @param givenPosition the wanted position
	 * @return the removed element
	 */
	public T remove(int givenPosition) {
		T result = null;
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
			assert !isEmpty();
			if (givenPosition == 1) {
				result = firstNode.getData();
				firstNode = firstNode.getNextNode();
			} else {
				Node nodeBefore = getNodeAt(givenPosition -1);
				Node nodeToRemove = nodeBefore.getNextNode();
				result = nodeToRemove.getData();
				Node nodeAfter = nodeToRemove.getNextNode();
				nodeBefore.setNextNode(nodeAfter);
			}
			numberOfEntries--;
			return result;
		} else throw new IndexOutOfBoundsException("Illegal position given to remove operation!");
	}
	/**
	 * The clear method for the linked list. Basically returns the linked list to the beginning.
	 */
	public void clear() {
		initializeDataFields();
	}
	/**
	 * The replace method for the linked list. It replaces the element in the wanted position with the new element.
	 * @param givenPosition the wanted position
	 * @param newEntry the new element
	 * @return the removed element from the list
	 */
	public T replace(int givenPosition, T newEntry) {
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
			assert !isEmpty();
			Node desiredNode = getNodeAt(givenPosition);
			T originalEntry = desiredNode.getData();
			desiredNode.setData(newEntry);
			return originalEntry;
		} else throw new IndexOutOfBoundsException("Illegal position given to replace operation!");
	}
	/**
	 * This method gets the data of the wanted position.
	 * @param givenPosition wanted position
	 * @return the data of the desired node
	 */
	public T getEntry(int givenPosition) {
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
			assert !isEmpty();
			return getNodeAt(givenPosition).getData();
		} else throw new IndexOutOfBoundsException("ıllegal position given to getEntry operation!");
	}
	/**
	 * This method creates an array that stores the entries of the list.
	 * @return the array version of the linked list
	 */
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] result = (T[]) new Object[numberOfEntries];
		int index = 0;
		Node currentNode = firstNode;
		while ((index < numberOfEntries) && (currentNode != null)) {
			result[index] = currentNode.getData();
			currentNode = currentNode.getNextNode();
			index++;
		}
		return result;
	}
	/**
	 * This method checks whether the wanted entry is in the list, or not.
	 * @param anEntry the wanted entry
	 * @return true or false
	 */
	public boolean contains(T anEntry) {
		boolean found = false;
		Node currentNode = firstNode;
		while (!found && (currentNode != null)) {
			if (anEntry.equals(currentNode.getData())) {
				found = true;
			} else {
				currentNode = currentNode.getNextNode();
			}
		}
		return found;
	}
	/**
	 * The method that will be used to reach the list size.
	 * @return the length of the list
	 */
	public int getLength() {
		return numberOfEntries;
	}
	/**
	 * This method checks whether the list is empty or not.
	 * @return true or false
	 */
	public boolean isEmpty() {
		boolean result;
		if (numberOfEntries == 0) {
			assert firstNode == null;
			result = true;
		} else {
			assert firstNode != null;
			result = false;
		}
		return result;
	}
	/**
	 * The node class that is being used for the linked list implementation.
	 * @author Berke Can Kandemir and Cem Ozan
	 */
	private class Node { // # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
		private T data;
		private Node next;
		
		private Node(T dataPortion) {
			this(dataPortion, null);
		}
		
		private Node(T dataPortion, Node nextNode) {
			data = dataPortion;
			next = nextNode;
		}
		
		private T getData() {
			return data;
		}
		
		private void setData(T newData) {
			data = newData;
		}
		
		private Node getNextNode() {
			return next;
		}
		
		private void setNextNode(Node nextNode) {
			next = nextNode;
		}
		
		
		
	}
}
