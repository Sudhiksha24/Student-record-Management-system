package com.wipro.srms.main;

import java.util.ArrayList;
import java.util.Scanner;

import com.wipro.srms.entity.*;
import com.wipro.srms.service.StudentRecordService;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<Student> students = new ArrayList<>();
        ArrayList<AcademicRecord> academicRecords = new ArrayList<>();
        ArrayList<AttendanceRecord> attendanceRecords = new ArrayList<>();

        StudentRecordService service =
                new StudentRecordService(students, academicRecords, attendanceRecords);

        int choice;

        do {
            System.out.println("\n===== STUDENT RECORD MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. Add Academic Record");
            System.out.println("3. Update Academic Marks");
            System.out.println("4. Add / Update Attendance");
            System.out.println("5. Generate Student Summary");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            try {

                switch (choice) {

                case 1:
                    System.out.print("Enter Student ID: ");
                    String id = sc.nextLine();

                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Program: ");
                    String program = sc.nextLine();

                    System.out.print("Enter Current Semester: ");
                    int sem = sc.nextInt();

                    students.add(new Student(id, name, program, sem));
                    System.out.println("Student added successfully!");
                    break;

                case 2:
                    System.out.print("Enter Record ID: ");
                    String recordId = sc.nextLine();

                    System.out.print("Enter Student ID: ");
                    String sid = sc.nextLine();

                    System.out.print("Enter Semester: ");
                    int semester = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Subject Code: ");
                    String subCode = sc.nextLine();

                    System.out.print("Enter Subject Name: ");
                    String subName = sc.nextLine();

                    System.out.print("Enter Internal Marks: ");
                    double internal = sc.nextDouble();

                    System.out.print("Enter External Marks: ");
                    double external = sc.nextDouble();

                    AcademicRecord record =
                            new AcademicRecord(recordId, sid, semester,
                                    subCode, subName, internal, external);

                    service.addAcademicRecord(record);
                    System.out.println("Academic record added!");
                    break;

                case 3:
                    System.out.print("Enter Record ID to Update: ");
                    String recId = sc.nextLine();

                    System.out.print("Enter New Internal Marks: ");
                    double newInternal = sc.nextDouble();

                    System.out.print("Enter New External Marks: ");
                    double newExternal = sc.nextDouble();

                    service.updateAcademicMarks(recId, newInternal, newExternal);
                    System.out.println("Marks updated successfully!");
                    break;

                case 4:
                    System.out.print("Enter Attendance ID: ");
                    String attId = sc.nextLine();

                    System.out.print("Enter Student ID: ");
                    String stId = sc.nextLine();

                    System.out.print("Enter Semester: ");
                    int semAtt = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Subject Code: ");
                    String subAtt = sc.nextLine();

                    System.out.print("Enter Total Classes: ");
                    int total = sc.nextInt();

                    System.out.print("Enter Attended Classes: ");
                    int attended = sc.nextInt();

                    AttendanceRecord attendance =
                            new AttendanceRecord(attId, stId, semAtt,
                                    subAtt, total, attended);

                    service.addOrUpdateAttendance(attendance);
                    System.out.println("Attendance updated successfully!");
                    break;

                case 5:
                    System.out.print("Enter Student ID: ");
                    String summaryId = sc.nextLine();

                    System.out.print("Enter Semester: ");
                    int summarySem = sc.nextInt();

                    String summary =
                            service.generateStudentSummary(summaryId, summarySem);

                    System.out.println("\n" + summary);
                    break;

                case 6:
                    System.out.println("Exiting System...");
                    break;

                default:
                    System.out.println("Invalid choice!");
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (choice != 6);

        sc.close();
    }
}
