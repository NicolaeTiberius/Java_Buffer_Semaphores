import java.util.*;

public class StartServer {

	int elements = 0;
	Semaphore u = new Semaphore(1);

	Buffer b; // Creation of buffer object

	public StartServer(int bufferSize, int user_num, int server_num, int element) { // Creates execution scenario
																					// between user and webservers
																					// on buffer

		long startTime = System.currentTimeMillis();

		// Instantiate all objects (webserver, users, buffer)
		b = new Buffer(bufferSize);

		// Equally subdivide user inputted elements across all user objects

		int user_remainder = element % user_num; // gets the remainder when dividing the amount of elements by users

		int server_remainder = element % server_num; // gets the remainder when dividing the amount of elements by
														// servers

		// Uses threads to put users in an array loop to print them out one at a time

		User users[] = new User[user_num]; // array to store each user number.

		for (int i = 0; i < user_num; i++) {

			if (user_remainder > i) { // if there is a remainder then add it to the first user, etc .
				users[i] = new User(i, (element / user_num) + 1, b, u);
			} else { // don't add a remainder just add the normal element values to the users.
				users[i] = new User(i, element / user_num, b, u);
			}
		}

		// Initiated user threads to be used when displaying each user at a time without
		// them all crashing together.
		Thread thread[] = new Thread[user_num];

		for (int i = 0; i < user_num; i++) {

			thread[i] = new Thread(users[i]);
			thread[i].start();
			System.out.println("Thread Start");
		}

		// Web server iterations, goes through the array of web servers that was
		// inputted and displays them out, also not forgetting to get which element out
		// of that server was put.
		Webserver webserver[] = new Webserver[server_num];

		for (int i = 0; i < server_num; i++) {

			if (server_remainder > i) {
				webserver[i] = new Webserver(i, (element / server_num) + 1, b, u);
			} else {
				webserver[i] = new Webserver(i, element / server_num, b, u);
			}
		}

		Thread thread2[] = new Thread[server_num];

		for (int i = 0; i < server_num; i++) {

			thread2[i] = new Thread(webserver[i]);
			thread2[i].start();
			System.out.println("Thread 2  Start");
		}

		// Started all the threads, waits for user and servers to finish adding and
		// removing elements, then displays Buffer size.
		try {
			// thread.sleep(1000);
			for (int i = 0; i < user_num; i++) {
				thread[i].join();
			}
			for (int i = 0; i < server_num; i++) {
				thread2[i].join();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Displays how many elements each Consumer has gotten by counting the amount
		// each server had to remove, in a iterative
		for (int i = 0; i < server_num; i++) {
			System.out
					.println(" Consumer " + i + " consumed a total of " + webserver[i].items_Removed() + " elements ");
		}

		// Displays how many elements the User has gotten by counting the amount the
		// each user added.
		for (int i = 0; i < user_num; i++) {
			System.out.println("User " + i + " created a total of " + users[i].elements_added() + " elements ");
		}

		System.out.println("-----------------------");

		// Outputs the total number of elements added/removed from user and webserver

		System.out.println("-----------------------");

		System.out.println("Buffer has " + b.bufferTotal() + " elements remaining"); // Check to see
		// buffer if all elements produced from users have been successfully removed by
		// webservers
		System.out.println("-----------------------");
		// Checks if all users and web servers successfully finished

		long endTime = System.currentTimeMillis();
		System.out.println("-----------------------");
		System.out.println("Program took " + (endTime - startTime) + " milliseconds to complete");

	}

	public static void main(String[] args) {

		System.out.println("Enter buffer capacity"); // Insert user inputted values for program execution

		Scanner scan = new Scanner(System.in);
		int bufferSize = 0;
		bufferSize = scan.nextInt();

		System.out.println("Enter number of users");

		int num_users = 0;
		num_users = scan.nextInt();

		System.out.println("Enter number of servers");

		int num_servers = 0;
		num_servers = scan.nextInt();

		System.out.println("Enter total number of elements");

		int num_elements = 0;
		num_elements = scan.nextInt();

		scan.close();

		StartServer start = new StartServer(bufferSize, num_users, num_servers, num_elements);

	}
}
