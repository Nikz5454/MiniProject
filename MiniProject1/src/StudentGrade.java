public class StudentGrade {

	public String checkGrade(int score) {

		// Initialize a local variable to store the grade as per the score
		String grade = null;

		// validation for grade
		if (score >= 8 && score <= 10) {
			grade = "A_Grade";
		} else if (score >= 6 && score < 8) {
			grade = "B_Grade";
		} else if (score == 5) {
			grade = "C_Grade";
		} else {
			grade = "You are failed";
		}

//		Printing the grade achieved by the student to console
		System.out.println("Grade achieved: " + grade);
		System.out.println("\n--------------------------------------\n");
		return grade;
	}


}
