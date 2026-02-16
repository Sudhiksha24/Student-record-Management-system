package com.wipro.srms.entity;

public class AttendanceRecord {

    private String attendanceId;
    private String studentId;
    private int semester;
    private String subjectCode;
    private int totalClasses;
    private int attendedClasses;

    public AttendanceRecord(String attendanceId, String studentId,
                            int semester, String subjectCode,
                            int totalClasses, int attendedClasses) {

        this.attendanceId = attendanceId;
        this.studentId = studentId;
        this.semester = semester;
        this.subjectCode = subjectCode;
        this.totalClasses = totalClasses;
        this.attendedClasses = attendedClasses;
    }

    public String getAttendanceId() { return attendanceId; }
    public String getStudentId() { return studentId; }
    public int getSemester() { return semester; }
    public String getSubjectCode() { return subjectCode; }

    public int getTotalClasses() { return totalClasses; }
    public int getAttendedClasses() { return attendedClasses; }

    public void setTotalClasses(int totalClasses) {
        this.totalClasses = totalClasses;
    }

    public void setAttendedClasses(int attendedClasses) {
        this.attendedClasses = attendedClasses;
    }
}
