import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;


public class StudentDetails {
	static int id;
	static String str;

	public int setStudentDetails() {

//		take user input for student details (student id & name)
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Your Id: ");

//		 data-type validation check for student id input
		do {
			if (!scanner.hasNextInt()) {
				System.out.println("You have entered invalid ID, expecting Integer");
				System.out.println("Enter Id again");
			} else {
				id = scanner.nextInt();

				if (id <= 0) {
					System.out.println("Pl enter positive ID only");
				}
			}

			scanner.nextLine();

		} while (id <= 0);



//		 Establishing connection with the database
		Connection con = SupportTest.getStudDetDBConnection();
		try {
//			 create a query to get the list of student ids from the database
			String checkId = "select Student_id from StudentTestScoreDetails ";

			PreparedStatement prepStatement1 = con.prepareStatement(checkId);

			ResultSet rs = prepStatement1.executeQuery();

//			 creating a array list which contains the existing student ids
			ArrayList<Integer> idList = new ArrayList<>();
		
			while (rs.next()) {
				int i = rs.getInt(1);
				idList.add(i);
			}


//			 Creating a query to insert student id and name into the corresponding
//			 database
			String addDetails = "insert into StudentTestScoreDetails (Student_id , Student_name ) values ( ?,? )";
			PreparedStatement prepStatement = con.prepareStatement(addDetails);


//			 check whether id is already present in the database or not
			do {

				if (idList.contains(id) != true) {
//					 if id is not present in the database, then allow the student proceed further
					prepStatement.setInt(1, id);
					break;

				} else {
//					 if id is already present in the database that means it is a wrong id of that
//					 student and student with the original id has already attempted the exam
					try {
//						 handling the exception with custom exception message
						throw new DuplicateStudentIdException();
					} catch (DuplicateStudentIdException e) {
						System.out.println(e);


					} finally {

						do {
							if (!scanner.hasNextInt()) {
								do {
								System.out.println("You have entered invalid ID, expecting Integer");
								System.out.println("Enter Id again");
								scanner.nextLine();
							} while (!scanner.hasNextInt());

							} 
							if (scanner.hasNextInt()) {
								do {
									if (!scanner.hasNextInt()) {
										System.out.println("You have entered invalid ID, expecting Integer");
										System.out.println("Enter Id again");
									} else {
										id = scanner.nextInt();

										if (id <= 0) {
											System.out.println("Pl enter positive ID only");
										}
									}

									scanner.nextLine();

								} while (id <= 0);
							}
						} while (id <= 0 );
					}
				}
			}

			while (true) ;

			System.out.println("Enter Student Name");

			while (!scanner.hasNext("[A-Za-z]+")) {
				System.out.println("Only alphabetical words are allowed in the Student Name. "
						+ "\nEnter the Student Name again: ");
				scanner.nextLine();
			}

			String name = scanner.nextLine();

			prepStatement.setString(2, name);

//			 executing the query which inserts the student details into database
			prepStatement.execute();
//			 Successfully added the student details into database

			System.out.println("\n\n.....ALL THE BEST FOR TEST.....\n\n");
			
//			 close all the connections
			prepStatement.close();
			rs.close();
			prepStatement1.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return id;
	}
	

}

