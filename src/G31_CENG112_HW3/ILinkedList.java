package G31_CENG112_HW3;

/**
 * This interface is for the linked list.
 * @author Berke Can Kandemir and Cem Ozan
 * @param <T> generic data type
 */
public interface ILinkedList<T> {
	public void add(T newEntry);
	public void add(int newPosition, T newEntry);
	public T remove(int givenPosition);
	public void clear();
	public T replace(int givenPosition, T newEntry);
	public T getEntry(int givenPosition);
	public T[] toArray();
	public boolean contains(T anEntry);
	public int getLength();
	public boolean isEmpty();
}
