import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Student_Interface extends Remote
{
    String serviceName = "StudentService";
    int port = 2000;

    void AddStudent(Student student) throws RemoteException;
    Student getStudentInfo(String id) throws RemoteException;
    ArrayList<Student> aboveAvgGPA() throws RemoteException;
}
