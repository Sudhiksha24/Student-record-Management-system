package com.wipro.srms.entity;

public class AcademicRecord {

    private String recordId;
    private String studentId;
    private int semester;
    private String subjectCode;
    private String subjectName;
    private double internalMarks;
    private double externalMarks;
    private double totalMarks;

    public AcademicRecord(String recordId, String studentId, int semester,
            String subjectCode, String subjectName,
            double internalMarks, double externalMarks) {

                 this.recordId = recordId;
                 this.studentId = studentId;
                 this.semester = semester;
                 this.subjectCode = subjectCode;
                 this.subjectName = subjectName;
                 this.internalMarks = internalMarks;
                 this.externalMarks = externalMarks;
                 this.totalMarks = internalMarks + externalMarks;
}

    public String getRecordId() { return recordId; }
    public String getStudentId() { return studentId; }
    public int getSemester() { return semester; }
    public String getSubjectCode() { return subjectCode; }
    public String getSubjectName() { return subjectName; }

    public double getInternalMarks() { return internalMarks; }
    public double getExternalMarks() { return externalMarks; }
    public double getTotalMarks() { return totalMarks; }

    public void setInternalMarks(double internalMarks) {
        this.internalMarks = internalMarks;
        this.totalMarks = this.internalMarks + this.externalMarks;
    }

    public void setExternalMarks(double externalMarks) {
        this.externalMarks = externalMarks;
        this.totalMarks = this.internalMarks + this.externalMarks;
    }
}
