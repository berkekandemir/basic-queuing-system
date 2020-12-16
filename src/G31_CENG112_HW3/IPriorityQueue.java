package G31_CENG112_HW3;

/**
 * This interface is for the priority queue.
 * @author Berke Can Kandemir and Cem Ozan
 * @param <T> the generic data type
 */
public interface IPriorityQueue<T> { // <T extends Comparable<? super T>> ???????????????
	public void add(T newEntry);
	public T remove();
	public T peek();
	public boolean isEmpty();
	public int getSize();
	public boolean clear();

}
