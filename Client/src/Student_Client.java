import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Scanner;

public class Student_Client
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        try
        {
            Registry reg = LocateRegistry.getRegistry(Student_Interface.port);
            Student_Interface studentStub = (Student_Interface) reg.lookup(Student_Interface.serviceName);

            System.out.println("(add) Add new student\n(search) Search for a student\n(get) Get students above average");

            String method = scan.nextLine();
            switch (method)
            {
                case "add":
                    //Create new student
                    Student student = new Student();
                    System.out.println("Enter Student ID:");
                    student.setId(scan.nextLine());

                    System.out.println("Enter Student Name:");
                    student.setName(scan.nextLine());

                    System.out.println("Enter Student Department:");
                    student.setDepartment(scan.nextLine());

                    System.out.println("Enter Student GPA:");
                    student.setGpa(scan.nextDouble());

                    System.out.println("Enter Student Semester:");
                    student.setSemester(scan.nextInt());

                    System.out.println("Student Obj Created\n");

                    //Send student to server
                    studentStub.AddStudent(student);
                    System.out.println("Student Obj Sent\n");
                    break;

                case "search":
                    //Search for specific student
                    System.out.println("Enter student id");
                    Student student_returned = studentStub.getStudentInfo(scan.nextLine());
                    if (student_returned == null)
                    {
                        System.out.println("Student not found");
                        break;
                    }
                    else
                    {
                        System.out.println("Got Student\n");

                        System.out.println("Id:"+student_returned.getId());
                        System.out.println("Name:"+student_returned.getName());
                        System.out.println("Dep:"+student_returned.getDepartment());
                        System.out.println("Sem:"+student_returned.getSemester());
                        System.out.println("GPA:"+student_returned.getGpa());
                    }
                    break;

                case "get":
                    //Get students with avg GPA
                    ArrayList<Student> student_aboveGPA = studentStub.aboveAvgGPA();
                    if (student_aboveGPA == null)
                    {
                        System.out.println("No Students Found");
                        break;
                    }
                    else
                    {
                        System.out.println("Student with GPA above average");
                        for (int i=0; i<student_aboveGPA.size(); i++)
                        {
                            System.out.println("Id:"+student_aboveGPA.get(i).getId()+" Name:"+student_aboveGPA.get(i).getName()+" Department:"+student_aboveGPA.get(i).getDepartment()+" GPA:"+student_aboveGPA.get(i).getGpa()+"Semester:"+student_aboveGPA.get(i).getSemester());
                        }
                    }
                    break;

                default:
                    System.out.println("Invalid choice");
                    break;
            }

        }

        catch (RemoteException | NotBoundException e)
        {
            e.printStackTrace();
        }
    }
}
