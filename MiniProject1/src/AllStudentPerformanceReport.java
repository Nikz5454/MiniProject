import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AllStudentPerformanceReport {

	public void getReport() {
//		taking user validation for getting performance report of all students
		System.out.println("Do you want to check the performance of report of all the student"
				+ " who has attempted the test till now? (Y/N)");

		Scanner scanner = new Scanner(System.in);
		char ch = 'y';
		ch = scanner.next().charAt(0);

//		user input's letter-case validation check
		do {
			if (ch == 'n' || ch == 'N') {

				break;
			} else if (ch == 'y' || ch == 'Y') {

//				Connection created with the database
				Connection connection = SupportTest.getStudDetDBConnection();

				System.out.println("Performance report of all the student who has attempted the test till now: \n\n");

//				Creating a query to get all the data studentstestdetailsdb
				String allData = "select * from studenttestscoredetails";

				try {
					PreparedStatement pStatement = connection.prepareStatement(allData);
					ResultSet resultSet = pStatement.executeQuery();

//					printing the result to the console
					System.out.println("{ Student_id , Student_name , Score , Grade}\n");
					while (resultSet.next()) {
						System.out.println("[ " + resultSet.getInt(1) + " , " + resultSet.getString(2) + " , "
								+ resultSet.getInt(3) + " , " + resultSet.getString(4) + " ]");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("Invalid Input.");
				System.out.println("Enter the correct input again (Y/N): ");
				ch = scanner.next().charAt(0);

			}

		} while (ch != 'y' && ch != 'Y');

		System.out.println("**************   Thank you   **************");

		System.out.println("\n----------------------------------------------------------------------------\n");

	}
}
