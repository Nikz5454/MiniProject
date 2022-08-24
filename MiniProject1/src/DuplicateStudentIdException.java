public class DuplicateStudentIdException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	String message;

	@Override
	public String toString() {
		return "Student with this ID has been already submitted the test.  \n\nPlease enter the correct Id:";
	}

	public DuplicateStudentIdException() {
		// super(message);

	}

}
