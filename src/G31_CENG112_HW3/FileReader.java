package G31_CENG112_HW3;

import java.util.*;
import java.io.*;
import java.lang.*;

/**
 * This class is used to read the file and do the necessary operations.
 * @author Berke Can Kandemir and Cem Ozan
 */
@SuppressWarnings("unused")
public class FileReader {
	
	IPriorityQueue<Transaction> transactionQueue1 = new TransactionQueue<Transaction>();
	IPriorityQueue<Transaction> transactionQueue2 = new TransactionQueue<Transaction>();
	IPriorityQueue<Transaction> transactionQueue3 = new TransactionQueue<Transaction>();
	IPriorityQueue<Transaction> transactionQueue4 = new TransactionQueue<Transaction>();
	IPriorityQueue<Transaction> transactionQueue5 = new TransactionQueue<Transaction>();
	ILinkedList<IPriorityQueue<Transaction>> transactionQueueList = new TransactionQueueList<IPriorityQueue<Transaction>>();
	/**
	 * This method is used for the calculating the day with the max work load.
	 * @param a an integer
	 * @param b an integer
	 * @param c an integer
	 * @param d an integer
	 * @param e an integer
	 * @return the date with the max work load
	 */
	private String maxLoad(int a, int b, int c, int d, int e) {
		int[] numbers = {a, b, c, d, e};
		int max1 = Math.max(a, b);
		int max2 = Math.max(c, d);
		int max3 = Math.max(max1, max2);
		int max = Math.max(max3, e);
		
		String maxLoad ="";
		for (int i = 0; i < numbers.length; i++) {
			if (max == numbers[i]) {
				if (i == 0) {
					maxLoad = "01.05.2019";
				} else if ( i == 1) {
					maxLoad = "02.05.2019";
				} else if (i == 2) {
					maxLoad = "03.05.2019";
				} else if (i == 3) {
					maxLoad = "04.05.2019";
				} else {
					maxLoad = "05.05.2019";
				}
			}
		}
		return maxLoad;
	}
	
	/**
	 * This method includes main operations of the application. The method firstly read the file and creates transaction objects.
	 * Then stores these transaction objects in priority queue based structures, and lastly it puts these queues in linked list.
	 * Also, it makes the necessary operations to calculate the statistics.
	 */
	public void fileReader() {
		Scanner file = null;
		ICustomer customer = null;
		try {
			file = new Scanner(new File("CENG112_HW3_Transactions.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
		}
		/**
		 * We read the file and according to the data, we create transaction objects.
		 * Then we put these objects into the suitable queue according to its date.
		 */
		while (file.hasNextLine()) {
			String line = file.nextLine();
			String[] lineArr = line.split(",");
			String date = lineArr[0];
			int id = Integer.parseInt(lineArr[1]);
			String type = lineArr[2];
			int occupation = Integer.parseInt(lineArr[3]);
			Transaction next = null;
			if (type.equals("INDIVIDUAL")) {
				customer = new Individual(type, 2);
			} else if (type.equals("CORPORATE")) {
				customer = new Corporate(type, 1);
			} else {
				customer = new NonRegistered(type, 3);
			}
			Transaction transaction = new Transaction(id, customer, occupation, type, date, next);
			if (transaction.getDate().equals("01.05.2019")) {
				((TransactionQueue<Transaction>) transactionQueue1).insert(transaction);
			} else if (transaction.getDate().equals("02.05.2019")) {
				((TransactionQueue<Transaction>) transactionQueue2).insert(transaction);
			} else if (transaction.getDate().equals("03.05.2019")) {
				((TransactionQueue<Transaction>) transactionQueue3).insert(transaction);
			} else if (transaction.getDate().equals("04.05.2019")) {
				((TransactionQueue<Transaction>) transactionQueue4).insert(transaction);
			} else if (transaction.getDate().equals("05.05.2019")) {
				((TransactionQueue<Transaction>) transactionQueue5).insert(transaction);
			}
		}
		transactionQueueList.add(transactionQueue1);
		transactionQueueList.add(transactionQueue2);
		transactionQueueList.add(transactionQueue3);
		transactionQueueList.add(transactionQueue4);
		transactionQueueList.add(transactionQueue5);
		
		/**
		 * The remaining part is for the statistics. We use the methods that we have written for their specific job.
		 * It looks like a little bit "generic chaos" but, actually it is really simple and it exists because of the necessary casts.
		 */
		int totalWaiting1 = ((TransactionQueue<Transaction>) transactionQueue1).getTotalWaiting();
		int totalWaiting2 = ((TransactionQueue<Transaction>) transactionQueue2).getTotalWaiting();
		int totalWaiting3 = ((TransactionQueue<Transaction>) transactionQueue3).getTotalWaiting();
		int totalWaiting4 = ((TransactionQueue<Transaction>) transactionQueue4).getTotalWaiting();
		int totalWaiting5 = ((TransactionQueue<Transaction>) transactionQueue5).getTotalWaiting();
		
		System.out.println("*** WELCOME TO THE BANK OF IZTECH ***");
		System.out.println("");
		System.out.println("The followings are the statistics of the first five days of May, 2019.");
		System.out.println("");
		System.out.println("01.05.2019 " + transactionQueue1.toString());
		System.out.println("02.05.2019 " + transactionQueue2.toString());
		System.out.println("03.05.2019 " + transactionQueue3.toString());
		System.out.println("04.05.2019 " + transactionQueue4.toString());
		System.out.println("05.05.2019 " + transactionQueue5.toString());
		System.out.println("");
		System.out.println("Total transaction count in 01.05.2019 --> " + transactionQueue1.getSize());
		System.out.println("Total transaction count in 02.05.2019 --> " + transactionQueue2.getSize());
		System.out.println("Total transaction count in 03.05.2019 --> " + transactionQueue3.getSize());
		System.out.println("Total transaction count in 04.05.2019 --> " + transactionQueue4.getSize());
		System.out.println("Total transaction count in 05.05.2019 --> " + transactionQueue5.getSize());
		System.out.println("");
		System.out.println("Total waiting time in 01.05.2019 --> " + ((TransactionQueue<Transaction>) transactionQueue1).getTotalWaiting());
		System.out.println("Total waiting time in 02.05.2019 --> " + ((TransactionQueue<Transaction>) transactionQueue2).getTotalWaiting());
		System.out.println("Total waiting time in 03.05.2019 --> " + ((TransactionQueue<Transaction>) transactionQueue3).getTotalWaiting());
		System.out.println("Total waiting time in 04.05.2019 --> " + ((TransactionQueue<Transaction>) transactionQueue4).getTotalWaiting());
		System.out.println("Total waiting time in 05.05.2019 --> " + ((TransactionQueue<Transaction>) transactionQueue5).getTotalWaiting());
		System.out.println("");
		System.out.println("Average waiting time in 01.05.2019 --> " + ((TransactionQueue<Transaction>) transactionQueue1).averageWaiting(((TransactionQueue<Transaction>) transactionQueue1).getTotalWaiting(), transactionQueue1.getSize()));
		System.out.println("Average waiting time in 02.05.2019 --> " + ((TransactionQueue<Transaction>) transactionQueue2).averageWaiting(((TransactionQueue<Transaction>) transactionQueue2).getTotalWaiting(), transactionQueue2.getSize()));
		System.out.println("Average waiting time in 03.05.2019 --> " + ((TransactionQueue<Transaction>) transactionQueue3).averageWaiting(((TransactionQueue<Transaction>) transactionQueue3).getTotalWaiting(), transactionQueue3.getSize()));
		System.out.println("Average waiting time in 04.05.2019 --> " + ((TransactionQueue<Transaction>) transactionQueue4).averageWaiting(((TransactionQueue<Transaction>) transactionQueue4).getTotalWaiting(), transactionQueue4.getSize()));
		System.out.println("Average waiting time in 05.05.2019 --> " + ((TransactionQueue<Transaction>) transactionQueue5).averageWaiting(((TransactionQueue<Transaction>) transactionQueue5).getTotalWaiting(), transactionQueue5.getSize()));
		System.out.println("");
		System.out.println("Total transaction count for CORPORATE customer in 01.05.2019 --> " + ((TransactionQueue<Transaction>) transactionQueue1).transactionCount("CORPORATE"));
		System.out.println("Total transaction count for CORPORATE customer in 02.05.2019 --> " + ((TransactionQueue<Transaction>) transactionQueue2).transactionCount("CORPORATE"));
		System.out.println("Total transaction count for CORPORATE customer in 03.05.2019 --> " + ((TransactionQueue<Transaction>) transactionQueue3).transactionCount("CORPORATE"));
		System.out.println("Total transaction count for CORPORATE customer in 04.05.2019 --> " + ((TransactionQueue<Transaction>) transactionQueue4).transactionCount("CORPORATE"));
		System.out.println("Total transaction count for CORPORATE customer in 05.05.2019 --> " + ((TransactionQueue<Transaction>) transactionQueue5).transactionCount("CORPORATE"));
		System.out.println("");
		System.out.println("Total transaction count for INDIVIDUAL customer in 01.05.2019 --> " + ((TransactionQueue<Transaction>) transactionQueue1).transactionCount("INDIVIDUAL"));
		System.out.println("Total transaction count for INDIVIDUAL customer in 02.05.2019 --> " + ((TransactionQueue<Transaction>) transactionQueue2).transactionCount("INDIVIDUAL"));
		System.out.println("Total transaction count for INDIVIDUAL customer in 03.05.2019 --> " + ((TransactionQueue<Transaction>) transactionQueue3).transactionCount("INDIVIDUAL"));
		System.out.println("Total transaction count for INDIVIDUAL customer in 04.05.2019 --> " + ((TransactionQueue<Transaction>) transactionQueue4).transactionCount("INDIVIDUAL"));
		System.out.println("Total transaction count for INDIVIDUAL customer in 05.05.2019 --> " + ((TransactionQueue<Transaction>) transactionQueue5).transactionCount("INDIVIDUAL"));
		System.out.println("");
		System.out.println("Total transaction count for NON-REGISTERED customer in 01.05.2019 --> " + ((TransactionQueue<Transaction>) transactionQueue1).transactionCount("NON-REGISTERED"));
		System.out.println("Total transaction count for NON-REGISTERED customer in 02.05.2019 --> " + ((TransactionQueue<Transaction>) transactionQueue2).transactionCount("NON-REGISTERED"));
		System.out.println("Total transaction count for NON-REGISTERED customer in 03.05.2019 --> " + ((TransactionQueue<Transaction>) transactionQueue3).transactionCount("NON-REGISTERED"));
		System.out.println("Total transaction count for NON-REGISTERED customer in 04.05.2019 --> " + ((TransactionQueue<Transaction>) transactionQueue4).transactionCount("NON-REGISTERED"));
		System.out.println("Total transaction count for NON-REGISTERED customer in 05.05.2019 --> " + ((TransactionQueue<Transaction>) transactionQueue5).transactionCount("NON-REGISTERED"));
		System.out.println("");
		System.out.println("Total waiting time for CORPORATE customer in 01.05.2019 -> " + ((TransactionQueue<Transaction>) transactionQueue1).waitingCustomer("CORPORATE"));
		System.out.println("Total waiting time for CORPORATE customer in 02.05.2019 -> " + ((TransactionQueue<Transaction>) transactionQueue2).waitingCustomer("CORPORATE"));
		System.out.println("Total waiting time for CORPORATE customer in 03.05.2019 -> " + ((TransactionQueue<Transaction>) transactionQueue3).waitingCustomer("CORPORATE"));
		System.out.println("Total waiting time for CORPORATE customer in 04.05.2019 -> " + ((TransactionQueue<Transaction>) transactionQueue4).waitingCustomer("CORPORATE"));
		System.out.println("Total waiting time for CORPORATE customer in 05.05.2019 -> " + ((TransactionQueue<Transaction>) transactionQueue5).waitingCustomer("CORPORATE"));
		System.out.println("");
		System.out.println("Total waiting time for INDIVIDUAL customer in 01.05.2019 -> " + ((TransactionQueue<Transaction>) transactionQueue1).waitingCustomer("INDIVIDUAL"));
		System.out.println("Total waiting time for INDIVIDUAL customer in 02.05.2019 -> " + ((TransactionQueue<Transaction>) transactionQueue2).waitingCustomer("INDIVIDUAL"));
		System.out.println("Total waiting time for INDIVIDUAL customer in 03.05.2019 -> " + ((TransactionQueue<Transaction>) transactionQueue3).waitingCustomer("INDIVIDUAL"));
		System.out.println("Total waiting time for INDIVIDUAL customer in 04.05.2019 -> " + ((TransactionQueue<Transaction>) transactionQueue4).waitingCustomer("INDIVIDUAL"));
		System.out.println("Total waiting time for INDIVIDUAL customer in 05.05.2019 -> " + ((TransactionQueue<Transaction>) transactionQueue5).waitingCustomer("INDIVIDUAL"));
		System.out.println("");
		System.out.println("Total waiting time for NON-REGISTERED customer in 01.05.2019 -> " + ((TransactionQueue<Transaction>) transactionQueue1).waitingCustomer("NON-REGISTERED"));
		System.out.println("Total waiting time for NON-REGISTERED customer in 02.05.2019 -> " + ((TransactionQueue<Transaction>) transactionQueue2).waitingCustomer("NON-REGISTERED"));
		System.out.println("Total waiting time for NON-REGISTERED customer in 03.05.2019 -> " + ((TransactionQueue<Transaction>) transactionQueue3).waitingCustomer("NON-REGISTERED"));
		System.out.println("Total waiting time for NON-REGISTERED customer in 04.05.2019 -> " + ((TransactionQueue<Transaction>) transactionQueue4).waitingCustomer("NON-REGISTERED"));
		System.out.println("Total waiting time for NON-REGISTERED customer in 05.05.2019 -> " + ((TransactionQueue<Transaction>) transactionQueue5).waitingCustomer("NON-REGISTERED"));
		System.out.println("");
		System.out.println("Average waiting time for CORPORATE customer in 01.05.2019 --> " + ((TransactionQueue<Transaction>) transactionQueue1).averageWaiting(((TransactionQueue<Transaction>) transactionQueue1).waitingCustomer("CORPORATE"), ((TransactionQueue<Transaction>) transactionQueue1).transactionCount("CORPORATE")));
		System.out.println("Average waiting time for CORPORATE customer in 02.05.2019 --> " + ((TransactionQueue<Transaction>) transactionQueue2).averageWaiting(((TransactionQueue<Transaction>) transactionQueue2).waitingCustomer("CORPORATE"), ((TransactionQueue<Transaction>) transactionQueue2).transactionCount("CORPORATE")));
		System.out.println("Average waiting time for CORPORATE customer in 03.05.2019 --> " + ((TransactionQueue<Transaction>) transactionQueue3).averageWaiting(((TransactionQueue<Transaction>) transactionQueue3).waitingCustomer("CORPORATE"), ((TransactionQueue<Transaction>) transactionQueue3).transactionCount("CORPORATE")));
		System.out.println("Average waiting time for CORPORATE customer in 04.05.2019 --> " + ((TransactionQueue<Transaction>) transactionQueue4).averageWaiting(((TransactionQueue<Transaction>) transactionQueue4).waitingCustomer("CORPORATE"), ((TransactionQueue<Transaction>) transactionQueue4).transactionCount("CORPORATE")));
		System.out.println("Average waiting time for CORPORATE customer in 05.05.2019 --> " + ((TransactionQueue<Transaction>) transactionQueue5).averageWaiting(((TransactionQueue<Transaction>) transactionQueue5).waitingCustomer("CORPORATE"), ((TransactionQueue<Transaction>) transactionQueue5).transactionCount("CORPORATE")));
		System.out.println("");
		System.out.println("Average waiting time for INDIVIDUAL customer in 01.05.2019 --> " + ((TransactionQueue<Transaction>) transactionQueue1).averageWaiting(((TransactionQueue<Transaction>) transactionQueue1).waitingCustomer("INDIVIDUAL"), ((TransactionQueue<Transaction>) transactionQueue1).transactionCount("INDIVIDUAL")));
		System.out.println("Average waiting time for INDIVIDUAL customer in 02.05.2019 --> " + ((TransactionQueue<Transaction>) transactionQueue2).averageWaiting(((TransactionQueue<Transaction>) transactionQueue2).waitingCustomer("INDIVIDUAL"), ((TransactionQueue<Transaction>) transactionQueue2).transactionCount("INDIVIDUAL")));
		System.out.println("Average waiting time for INDIVIDUAL customer in 03.05.2019 --> " + ((TransactionQueue<Transaction>) transactionQueue3).averageWaiting(((TransactionQueue<Transaction>) transactionQueue3).waitingCustomer("INDIVIDUAL"), ((TransactionQueue<Transaction>) transactionQueue3).transactionCount("INDIVIDUAL")));
		System.out.println("Average waiting time for INDIVIDUAL customer in 04.05.2019 --> " + ((TransactionQueue<Transaction>) transactionQueue4).averageWaiting(((TransactionQueue<Transaction>) transactionQueue4).waitingCustomer("INDIVIDUAL"), ((TransactionQueue<Transaction>) transactionQueue4).transactionCount("INDIVIDUAL")));
		System.out.println("Average waiting time for INDIVIDUAL customer in 05.05.2019 --> " + ((TransactionQueue<Transaction>) transactionQueue5).averageWaiting(((TransactionQueue<Transaction>) transactionQueue5).waitingCustomer("INDIVIDUAL"), ((TransactionQueue<Transaction>) transactionQueue5).transactionCount("INDIVIDUAL")));
		System.out.println("");
		System.out.println("Average waiting time for NON-REGISTERED customer in 01.05.2019 --> " + ((TransactionQueue<Transaction>) transactionQueue1).averageWaiting(((TransactionQueue<Transaction>) transactionQueue1).waitingCustomer("NON-REGISTERED"), ((TransactionQueue<Transaction>) transactionQueue1).transactionCount("NON-REGISTERED")));
		System.out.println("Average waiting time for NON-REGISTERED customer in 02.05.2019 --> " + ((TransactionQueue<Transaction>) transactionQueue2).averageWaiting(((TransactionQueue<Transaction>) transactionQueue2).waitingCustomer("NON-REGISTERED"), ((TransactionQueue<Transaction>) transactionQueue2).transactionCount("NON-REGISTERED")));
		System.out.println("Average waiting time for NON-REGISTERED customer in 03.05.2019 --> " + ((TransactionQueue<Transaction>) transactionQueue3).averageWaiting(((TransactionQueue<Transaction>) transactionQueue3).waitingCustomer("NON-REGISTERED"), ((TransactionQueue<Transaction>) transactionQueue3).transactionCount("NON-REGISTERED")));
		System.out.println("Average waiting time for NON-REGISTERED customer in 04.05.2019 --> " + ((TransactionQueue<Transaction>) transactionQueue4).averageWaiting(((TransactionQueue<Transaction>) transactionQueue4).waitingCustomer("NON-REGISTERED"), ((TransactionQueue<Transaction>) transactionQueue4).transactionCount("NON-REGISTERED")));
		System.out.println("Average waiting time for NON-REGISTERED customer in 05.05.2019 --> " + ((TransactionQueue<Transaction>) transactionQueue5).averageWaiting(((TransactionQueue<Transaction>) transactionQueue5).waitingCustomer("NON-REGISTERED"), ((TransactionQueue<Transaction>) transactionQueue5).transactionCount("NON-REGISTERED")));
		System.out.println(""); 
		System.out.println("The date with the highest workload (max total waiting time) --> " + maxLoad(totalWaiting1, totalWaiting2, totalWaiting3, totalWaiting4, totalWaiting5));
		
	}
}
