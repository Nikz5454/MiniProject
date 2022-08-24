import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentPerformanceReport {

	public void getYourPerformance(int id) {

//		taking user input for performance check validation
		System.out.println("Do you want to check the performance of your test? (Y/N)");
		Scanner scanner = new Scanner(System.in);
		char ch = scanner.next().charAt(0);

//		letter-case validation
		do {
			if (ch == 'n' || ch == 'N') {

				break;
			} else if (ch == 'y' || ch == 'Y') {

//				created the connection with the database
				Connection con = SupportTest.getStudDetDBConnection();

//				creating the query to get all the details of a particular student
				String yourPerf = "Select * from studenttestscoredetails where Student_id = ?";

				PreparedStatement pst;
				try {
					pst = con.prepareStatement(yourPerf);
					pst.setInt(1, id);
					ResultSet rs = pst.executeQuery();

					while (rs.next()) {
						System.out.println("\nStudent ID: " + rs.getInt(1) + "\nStudent Name: " + rs.getString(2)
								+ "\nStudent Score: " + rs.getInt(3) + "\nStudent Grade: " + rs.getString(4));
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
		
		System.out.println("\n----------------------------------------------------------------------------\n");

		scanner.close();
	}


	public void storeScore(int id, int score) {

//		created the connection with the database
		Connection con = SupportTest.getStudDetDBConnection();

//		Creating a query to store the scores of student into database
		String storeMarks = "update StudentTestScoreDetails set Score = ? where (Student_id = ?)";
		try {
			PreparedStatement prepStatement = con.prepareStatement(storeMarks);
			prepStatement.setInt(1, score);
			prepStatement.setInt(2, id);

//			executing the query
			prepStatement.execute();
			
			prepStatement.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void storeGrade(int id, String grade) {
//		 Connection created by calling getConnection method
		Connection connection = SupportTest.getStudDetDBConnection();

//		 Initialized the references for object of PreparedStatement interface
		PreparedStatement prepStatement = null;

		try {

//			 creating a query to update the student grade in the student database
			String addGradeQuery = "Update StudentTestScoreDetails set Grade = ? where Student_id = ?";
			prepStatement = connection.prepareStatement(addGradeQuery);
			
			prepStatement.setString(1, grade);
			prepStatement.setInt(2, id);

//			 Execute the query
			prepStatement.execute();


		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
//			 Closing the connection
			try {
				prepStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}


	}




}
