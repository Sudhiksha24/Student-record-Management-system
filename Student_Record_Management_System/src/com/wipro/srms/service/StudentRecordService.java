package com.wipro.srms.service;

import java.util.ArrayList;

import com.wipro.srms.entity.*;
import com.wipro.srms.util.*;

public class StudentRecordService {

    private ArrayList<Student> students;
    private ArrayList<AcademicRecord> academicRecords;
    private ArrayList<AttendanceRecord> attendanceRecords;

    public StudentRecordService(ArrayList<Student> students,
                                ArrayList<AcademicRecord> academicRecords,
                                ArrayList<AttendanceRecord> attendanceRecords) {

        this.students = students;
        this.academicRecords = academicRecords;
        this.attendanceRecords = attendanceRecords;
    }

    // ✅ Validate Student
    public boolean validateStudent(String studentId)
            throws InvalidStudentException {

        if (studentId == null || studentId.isEmpty()) {
            throw new InvalidStudentException("Student ID cannot be null or empty");
        }

        for (Student s : students) {
            if (s.getStudentId().equals(studentId)) {
                return true;
            }
        }

        throw new InvalidStudentException("Student not found");
    }

    // ✅ Add Academic Record
    public void addAcademicRecord(AcademicRecord record)
            throws DuplicateRecordException, InvalidStudentException {

        validateStudent(record.getStudentId());

        for (AcademicRecord ar : academicRecords) {
            if (ar.getStudentId().equals(record.getStudentId()) &&
                ar.getSemester() == record.getSemester() &&
                ar.getSubjectCode().equals(record.getSubjectCode())) {

                throw new DuplicateRecordException("Duplicate academic record exists");
            }
        }

        academicRecords.add(record);
    }

    // ✅ Update Marks
    public void updateAcademicMarks(String recordId,
                                    double newInternalMarks,
                                    double newExternalMarks)
            throws RecordNotFoundException {

        for (AcademicRecord ar : academicRecords) {
            if (ar.getRecordId().equals(recordId)) {

                ar.setInternalMarks(newInternalMarks);
                ar.setExternalMarks(newExternalMarks);
                return;
            }
        }

        throw new RecordNotFoundException("Academic record not found");
    }

    // ✅ Add or Update Attendance
    public void addOrUpdateAttendance(AttendanceRecord newRecord)
            throws InvalidStudentException {

        validateStudent(newRecord.getStudentId());

        for (AttendanceRecord ar : attendanceRecords) {
            if (ar.getStudentId().equals(newRecord.getStudentId()) &&
                ar.getSemester() == newRecord.getSemester() &&
                ar.getSubjectCode().equals(newRecord.getSubjectCode())) {

                ar.setTotalClasses(newRecord.getTotalClasses());
                ar.setAttendedClasses(newRecord.getAttendedClasses());
                return;
            }
        }

        attendanceRecords.add(newRecord);
    }

    // ✅ Calculate Attendance %
    public double calculateAttendancePercentage(String studentId, int semester)
            throws RecordNotFoundException, InvalidStudentException {

        validateStudent(studentId);

        int totalClasses = 0;
        int attendedClasses = 0;

        for (AttendanceRecord ar : attendanceRecords) {
            if (ar.getStudentId().equals(studentId) &&
                ar.getSemester() == semester) {

                totalClasses += ar.getTotalClasses();
                attendedClasses += ar.getAttendedClasses();
            }
        }

        if (totalClasses == 0) {
            throw new RecordNotFoundException("No attendance records found");
        }

        return (attendedClasses * 100.0) / totalClasses;
    }

    // ✅ Calculate Semester Average
    public double calculateSemesterAverage(String studentId, int semester)
            throws RecordNotFoundException, InvalidStudentException {

        validateStudent(studentId);

        double total = 0;
        int count = 0;

        for (AcademicRecord ar : academicRecords) {
            if (ar.getStudentId().equals(studentId) &&
                ar.getSemester() == semester) {

                total += ar.getTotalMarks();
                count++;
            }
        }

        if (count == 0) {
            throw new RecordNotFoundException("No academic records found");
        }

        return total / count;
    }

    // ✅ Generate Summary
    public String generateStudentSummary(String studentId, int semester)
            throws InvalidStudentException, RecordNotFoundException {

        validateStudent(studentId);

        Student student = null;

        for (Student s : students) {
            if (s.getStudentId().equals(studentId)) {
                student = s;
                break;
            }
        }

        double avg = calculateSemesterAverage(studentId, semester);
        double attendance = calculateAttendancePercentage(studentId, semester);

        return "------ STUDENT SUMMARY ------\n" +
               "Name: " + student.getName() + "\n" +
               "Program: " + student.getProgram() + "\n" +
               "Semester: " + semester + "\n" +
               "Average Marks: " + avg + "\n" +
               "Attendance: " + attendance + "%\n";
    }
}
