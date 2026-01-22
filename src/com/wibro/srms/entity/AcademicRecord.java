package com.wibro.srms.entity;

public class AcademicRecord {
	private String recordId;
	private String studentId;
	private int semester;
	private String subjectCode;
	private String subjectName;
	private double internalMarks;
	private double externalMarks;
	private double totalMarks;
	public AcademicRecord(String recordId, String studentId, int semester, String subjectCode, String subjectName,
			double internalMarks, double externalMarks, double totalMarks) {
		super();
		this.recordId = recordId;
		this.studentId = studentId;
		this.semester = semester;
		this.subjectCode = subjectCode;
		this.subjectName = subjectName;
		this.internalMarks = internalMarks;
		this.externalMarks = externalMarks;
		this.totalMarks = totalMarks;
	}
	public String getRecordId() {
		return recordId;
	}
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public int getSemester() {
		return semester;
	}
	public void setSemester(int semester) {
		this.semester = semester;
	}
	public String getSubjectCode() {
		return subjectCode;
	}
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public double getInternalMarks() {
		return internalMarks;
	}
	public void setInternalMarks(double internalMarks) {
		this.internalMarks = internalMarks;
	}
	public double getExternalMarks() {
		return externalMarks;
	}
	public void setExternalMarks(double externalMarks) {
		this.externalMarks = externalMarks;
	}
	public double getTotalMarks() {
		return totalMarks;
	}
	public void setTotalMarks(double totalMarks) {
		this.totalMarks = totalMarks;
	}
	@Override
	public String toString() {
		return "AcademicRecord [recordId=" + recordId + ", studentId=" + studentId + ", semester=" + semester
				+ ", subjectCode=" + subjectCode + ", subjectName=" + subjectName + ", internalMarks=" + internalMarks
				+ ", externalMarks=" + externalMarks + ", totalMarks=" + totalMarks + "]";
	}
	
}
