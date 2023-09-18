package dto;

public class StudentDto {
	private int studentId;
	private String studentNm;
	
	
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getStudentNm() {
		return studentNm;
	}
	public void setStudentNm(String studentNm) {
		this.studentNm = studentNm;
	}
	public StudentDto() {}
	public StudentDto(int studentId, String studentNm) {
		super();
		this.studentId = studentId;
		this.studentNm = studentNm;
	}
	@Override
	public String toString() {
		return "StudentDto [studentId=" + studentId + ", studentNm=" + studentNm + "]";
	}
	
	
}
