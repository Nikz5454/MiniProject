import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SupportTest extends Thread {

	public static Connection getQBConnection() {

//		Establishing the connection with the question bank database
		Connection connection = null;
		try {
//			 Register the driver class
			Class.forName("com.mysql.cj.jdbc.Driver");

//			 Create connection object
			String url = "jdbc:mysql://localhost:3306/QuestionBankDB";
			String username = "root";
			String password = "Nikhil@2694";
			connection = DriverManager.getConnection(url, username, password);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return connection;
	}

	public static Connection getStudDetDBConnection() {

//		Establishing the connection with the question bank database
		Connection connection = null;
		try {
//			 Register the driver class
			Class.forName("com.mysql.cj.jdbc.Driver");

//			 Create connection object
			String url = "jdbc:mysql://localhost:3306/studenttestdetailsdatabase";
			String username = "root";
			String password = "Nikhil@2694";
			connection = DriverManager.getConnection(url, username, password);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return connection;
	}

	public void instructions() {
//		Printing instructions to students using sleep method of thread class
		try {
			System.out.println("********** JAVA DEVELOPER APTITUDE TEST **********\n");
			Thread.sleep(1000);
			System.out.println("Please read the instuctions carefully: ");
			Thread.sleep(1000);
			System.out.println("1. Student is allowed to appear for the test only once. ");
			Thread.sleep(1000);
			System.out.println("2. All questions are compulsory.");
			Thread.sleep(1000);
			System.out.println("3. Test will automatically end after entering the answer for last question.");
			Thread.sleep(1000);
			System.out.println("4. There is no negative marking for the test.\n");
			Thread.sleep(1000);

			System.out.println("Please proceed to fill your student details: \n");
			Thread.sleep(1000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	}
}
