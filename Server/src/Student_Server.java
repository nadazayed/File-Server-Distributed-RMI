import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Student_Server extends UnicastRemoteObject implements Student_Interface
{
    ArrayList <Student> list = new ArrayList<Student>();
    int cnt=0;

    protected Student_Server () throws RemoteException
    {
        super();
    }

    @Override
    public void AddStudent(Student student) throws RemoteException
    {
        System.out.println("Student added\n");
        list.add(student);
        cnt++;
    }

    @Override
    public Student getStudentInfo(String id) throws RemoteException
    {
        for (int i=0; i<cnt; i++)
        {
            if (list.get(i).getId().matches(id))
            {
                System.out.println("Student Found");
                return list.get(i);
            }
        }
        return null;
    }

    @Override
    public ArrayList<Student> aboveAvgGPA() throws RemoteException
    {
        double avg = avg();
        boolean flag = false;
        ArrayList <Student> aboveAvg = new ArrayList<Student>();

        for (int i=0; i<cnt; i++)
        {
            if (list.get(i).getGpa() >= avg)
            {
                flag = true;
                System.out.println(list.get(i).getGpa()+" >= "+avg);
                aboveAvg.add(list.get(i));
            }
        }
        if (list.isEmpty() || flag == false)
            return null;
        else
            return aboveAvg;
    }

    public double avg()
    {
        double sum = 0;
        for (int i=0; i<cnt; i++)
        {
            sum+= list.get(i).getGpa();
        }

        System.out.println("sum:"+sum+" cnt:"+cnt+" avg:"+(sum/cnt));
        return (sum/cnt);
    }

    public static void main(String[] args)
    {
        try
        {
            Student_Server server = new Student_Server();

            Registry registry = LocateRegistry.createRegistry(Student_Interface.port);
            registry.rebind(Student_Interface.serviceName, server);

            System.out.println("Server is listening..");
        }

        catch (RemoteException e)
        {
            e.printStackTrace();
        }
    }
}
