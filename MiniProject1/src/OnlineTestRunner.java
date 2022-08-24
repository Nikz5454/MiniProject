public class OnlineTestRunner {

	public static void main(String[] args) {
		
//		// Print the test instructions
//		SupportTest support = new SupportTest();
//		support.instructions();
//
//		// Take student details
//		StudentDetails studentDetails = new StudentDetails();
//		int studId = studentDetails.setStudentDetails();
//
//		Date date = new Date();
//		System.out.println(date + "\n");
//		// Start the test
//		RandomQuestionTest test = new RandomQuestionTest();
//		int testScore = test.javaAptTest();
//
//		// Check the grade achieved
//		StudentGrade grd = new StudentGrade();
//		String grade = grd.checkGrade(testScore);
//
//		// Storing the score in the database
//		StudentPerformanceReport store = new StudentPerformanceReport();
//		store.storeScore(studId, testScore);
//
//		// Storing the grade in the database
//		store.storeGrade(studId, grade);



		// Get the performance report of any particular student who has attempted the
//		// test
		RandomPerformanceReport.getRandomStudentPerformance();

		// Print the list of all students with their score and grade
		AllStudentPerformanceReport allRep = new AllStudentPerformanceReport();
		allRep.getReport();
		
		


	}

}
