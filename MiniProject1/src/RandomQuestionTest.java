import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

public class RandomQuestionTest {
	
	public int javaAptTest() {
		int score = 0;
		RandomQuestionTest rqt = new RandomQuestionTest();
		Connection con = null;
	    
//		Program to generate random numbers between 1 to 10
	    int[] arr = {1,2,3,4,5,6,7,8,9,10};
	       
	    Random rand = new Random();
	    for(int i=0; i<arr.length; i++) {
	    	
	    	int swap = rand.nextInt(arr.length);    
	        int temp = arr[swap];
	        arr[swap] = arr[i];
	        arr[i] = temp;
	        
	    }

//		Creating connection object
		con = SupportTest.getQBConnection();
	        
		for (int i = 0; i < arr.length; i++) {

	        try {
					
//				Creating a query to select the particular question with its options
				String Query = "Select Q_no, Question, Option_A, Option_B, Option_C, Option_D "
						+ "from QuestionBank where Q_no= ?";

//				 Create preparedStatement object
				PreparedStatement pst = con.prepareStatement(Query);
				pst.setInt(1, arr[i]);
			
//				 Execute the query
				ResultSet rs = pst.executeQuery();
				
				for (int j = i + 1; j <= i + 1; j++) {

//					Printing the questions
					while (rs.next()) {

					System.out.print("Q." + j + " " + rs.getString(2) + "\n" + rs.getString(3)
								+ "\n" + rs.getString(4) + "\n" + rs.getString(5) + "\n" + rs.getString(6) + "\n\n");

				}
			}

//				taking user input for the correct answer option
			System.out.println("Enter your answer (option): ");
					
				Scanner sc = new Scanner(System.in);
				char choice;
				
				while (true) {

					choice = sc.next().charAt(0);
					
					if (choice == 'a' || choice == 'b' || choice == 'c' || choice == 'd') {

						break;
					}
					System.out.println("Invalid Answer Input. \nPlease enter your answer again.");
					
				}
				
				System.out.println("\n----------------------------------------------------------------------------\n");

				int marks = checkSolution(choice, arr[i]);
				score = score + marks;

	        }			
			catch (SQLException e) {
				e.printStackTrace();
			}
		}

//		 show correct answers
		System.out.println("Correct answers for the questions are: \n");
		for (int i = 0; i < arr.length; i++) {

			getAnswerKey(arr[i], i);
		}
		System.out.println("\n----------------------------------------------------------------------------\n");

//		 calculating and printing the total score obtained
		System.out.println("Your score out of 10: " + score);
		System.out.println("\n----------------------------------------------------------------------------\n");

		return score;

	}


//	 checking student answers with correct answers in database
	public static int checkSolution(char choice, int i) {
//		 Initializing the variable score which will be used for storing the score of
//		 student
		int score = 0;

		Connection con = SupportTest.getQBConnection();

//		creating query to get the correct answer for the particular question
		String Query = "select Correct_Ans from questionbank where Q_no = ?";

		try {
			PreparedStatement pst = con.prepareStatement(Query);

			pst.setInt(1, i);

			ResultSet rSet = pst.executeQuery();

//			comparing the correct answer from database with answer obtained from user input
			while (rSet.next()) {
				if (rSet.getString(1).charAt(0) == choice) {
					score++;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return score;
	}

//	get the correct answer key
	public void getAnswerKey(int id, int qNo) {
		Connection con = SupportTest.getQBConnection();

//		creating a query to get the correct answer for the particular question
		String ansKey = "select Correct_Ans from questionbank where Q_No = ?";

		try {
			PreparedStatement prepStatement = con.prepareStatement(ansKey);
			prepStatement.setInt(1, id);

//			Printing the correct answers from database
			ResultSet rSet = prepStatement.executeQuery();
			for (int j = qNo + 1; j <= qNo + 1; j++) {
			while (rSet.next()) {
				System.out.println("Q." + j + " >> " + rSet.getString(1));
			}
		}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}



