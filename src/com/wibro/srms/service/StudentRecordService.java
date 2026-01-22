package com.wibro.srms.service;

import java.util.ArrayList;

import com.wibro.srms.entity.Student;
import com.wibro.srms.entity.AcademicRecord;
import com.wibro.srms.entity.AttendenceRecord;
import com.wibro.srms.util.DuplicateRecordException;
import com.wibro.srms.util.InvalidStudentException;
import com.wibro.srms.util.RecordNotFoundException;

public class StudentRecordService {

    private ArrayList<Student> students;
    private ArrayList<AcademicRecord> academicRecords;
    private ArrayList<AttendenceRecord> attendenceRecords;

    public StudentRecordService(ArrayList<Student> students,
                                ArrayList<AcademicRecord> academicRecords,
                                ArrayList<AttendenceRecord> attendenceRecords) {
        this.students = students;
        this.academicRecords = academicRecords;
        this.attendenceRecords = attendenceRecords;
    }
    public boolean validateStudent(String studentId) throws InvalidStudentException {
        for (Student i: students) {
            if (i.getStudentId().equals(studentId)) {
                return true;
            }
        }
        throw new InvalidStudentException();
    }


    public void addAcademicRecord(AcademicRecord record)throws DuplicateRecordException, InvalidStudentException {

        validateStudent(record.getStudentId());

        for (AcademicRecord i: academicRecords) {
            if (i.getStudentId().equals(record.getStudentId()) &&
                i.getSemester() == record.getSemester() &&
                i.getSubjectCode().equals(record.getSubjectCode())) {
                throw new DuplicateRecordException();
            }
        }
        academicRecords.add(record);
    }
  public void updateAcademicMarks(String recordId,double newInternalMarks,double newExternalMarks) throws RecordNotFoundException {
          for (AcademicRecord i: academicRecords) {
            if (i.getRecordId().equals(recordId)) {
                i.setInternalMarks(newInternalMarks);
                i.setExternalMarks(newExternalMarks);
                i.setTotalMarks(newInternalMarks + newExternalMarks);
                return;
            }
        }
        throw new RecordNotFoundException();
    }

    public void addOrUpdateAttendance(AttendenceRecord newRecord)throws InvalidStudentException {
    	validateStudent(newRecord.getStudentId());

        for (AttendenceRecord i : attendenceRecords) {
            if (i.getStudentId().equals(newRecord.getStudentId()) && i.getSemester() == newRecord.getSemester() && i.getSubjectCode().equals(newRecord.getSubjectCode())) {

                i.setTotalClasses(newRecord.getTotalClasses());
                i.setAttendedClasses(newRecord.getAttendedClasses());
                return;
            }
        }
        attendenceRecords.add(newRecord);
    }
    public double calculateSemesterAverage(String studentId, int semester)throws InvalidStudentException, RecordNotFoundException {
        validateStudent(studentId);

        double sum = 0;
        int count = 0;

        for (AcademicRecord i : academicRecords) {
            if (i.getStudentId().equals(studentId) &&
                i.getSemester() == semester) {
                sum += i.getTotalMarks();
                count++;
            }
        }

        if (count == 0) {
            throw new RecordNotFoundException();
        }
        return sum / count;
    }

 
    public double calculateSemesterAttendance(String studentId, int semester)throws InvalidStudentException, RecordNotFoundException {
    	validateStudent(studentId);
        int total = 0;
        int attended = 0;
        boolean found = false;
        for (AttendenceRecord i : attendenceRecords) {
            if (i.getStudentId().equals(studentId) &&
                i.getSemester() == semester) {
                total += i.getTotalClasses();
                attended += i.getAttendedClasses();
                found = true;
            }
        }

        if (!found || total == 0) {
            throw new RecordNotFoundException();
        }

        return ((double) attended / total) * 100;
    }

  
    public String generateStudentReport(String studentId, int semester)throws InvalidStudentException, RecordNotFoundException {

        Student student = null;
        for (Student i: students) {
            if (i.getStudentId().equals(studentId)) {
                student = i;
                break;
            }
        }

        if (student == null) {
            throw new InvalidStudentException();
        }

        double avg = calculateSemesterAverage(studentId, semester);
        double attendance = calculateSemesterAttendance(studentId, semester);

        return "StudentDashboard\n" +
               "Student Name: " + student.getName() + "\n" +
               "Program: " + student.getProgram() + "\n" +
               "Semester: " + semester + "\n" +
               "Average Marks: " + avg + "\n" +
               "Attendance %: " + attendance + "\n" +
               "------";
        
    }
}
