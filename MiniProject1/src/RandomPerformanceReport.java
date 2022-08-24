import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

public class RandomPerformanceReport {
	static char ch;
	static char ch1;

	public static void getRandomStudentPerformance() {

		System.out.println("Do you want to check the performance of any particular student (Y/N) ?");

		Scanner scanner = new Scanner(System.in);


		do {
			ch = scanner.next().charAt(0);
			if (ch == 'y' || ch == 'Y' || ch == 'n' || ch == 'N') {
//				System.out.println("Entered input 1 >> " + ch); 		just manual debugger
				if (ch == 'n' || ch == 'N') {
					break;
				}
				while (ch == 'y' || ch == 'Y') {
					if (ch == 'y' || ch == 'Y') {
						try {
							Connection con = SupportTest.getStudDetDBConnection();

//						 create a query to get the list of student ids from the database
							String checkId = "select Student_id from StudentTestScoreDetails ";

							PreparedStatement prepStatement1 = con.prepareStatement(checkId);

							ResultSet rs = prepStatement1.executeQuery();

//						 creating a array list which contains the existing student ids
							ArrayList<Integer> idList = new ArrayList<>();

							while (rs.next()) {
								int i = rs.getInt(1);
								idList.add(i);
							}

//						Creating a query to get the student performance details corresponding to that id
							String getDet = "select * from studenttestscoredetails where Student_id = ?";

							PreparedStatement pst = con.prepareStatement(getDet);

							System.out.println("Enter the student id to get his/her performance report: ");

						int id = scanner.nextInt();
//						System.out.println("You have entered>> " + id);		just manual debugger

							while (idList.contains(id) == true) {
								pst.setInt(1, id);

								ResultSet reSet = pst.executeQuery();

								while (reSet.next()) {
									System.out.println("Student ID: " + reSet.getInt(1) + "\nStudent Name: "
											+ reSet.getString(2) + "\nStudent Score: " + reSet.getInt(3)
											+ "\nStudent Grade: " + reSet.getString(4));
							}

								break;
							}

							if (idList.contains(id) != true) {
								System.out.println("The student id doesn't exist.");

//					System.out.println("Enter input 2 >> " + ch);	just manual debug
								System.out.println("Do you want to enter again (Y/N)>>");
								ch1 = scanner.next().charAt(0);
								// do {

								while (ch1 != 'y' && ch1 != 'Y') {
//						System.out.println("here");		just manual debug
									if (ch1 != 'n' && ch == 'N' && ch1 != 'y' && ch1 != 'Y') {
										System.out.println("Invalid Input.");
										System.out.println("Enter the correct input again (Y/N): ");
							ch1 = scanner.next().charAt(0);
									}
									if (ch1 == 'n' || ch1 == 'N') {
										break;
									}
								}
							}

							if (idList.contains(id) == true) {
								System.out.println(
										"\nDo you want to check performance of any another student again (Y/N)>>");
								ch1 = scanner.next().charAt(0);


								while (ch1 != 'y' && ch1 != 'Y') {
//								System.out.println("here");		just manual debug
									if (ch1 != 'n' && ch != 'N' && ch1 != 'y' && ch1 != 'Y') {
										System.out.println("Invalid Input.");
										System.out.println("Enter the correct input again (Y/N): ");
									ch1 = scanner.next().charAt(0);
								}
								if (ch1 == 'n' || ch1 == 'N') {
									break;
								}
							}

						}

//					System.out.println("I am here");		just manual debug
//					System.out.println("ch1 is >> " + ch1);	just manual debug
						if (ch1 == 'n' || ch1 == 'N') {
//						System.out.println("reached");		just manual debug
						break;
					}
//					System.out.println("I here too");		just manual debug
				} catch (Exception e) {
				}

			}
			ch = ch1;
		}
		if (ch1 == 'n' || ch1 == 'N') {
//					System.out.println("reached");			just manual debug
			break;
		}
	} else {
		System.out.println("2.Invalid Input.");
		System.out.println("Do you want to enter again (Y/N): ");
			}

} while (ch != 'y' || ch != 'Y' || ch != 'n' || ch != 'N');
System.out.println("\n----------------------------------------------------------------------------\n");

	}
}
