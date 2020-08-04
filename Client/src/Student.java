import java.io.Serializable;

public class Student implements Serializable
{
    private String id, name, department;
    private double gpa;
    private int semester;

    public Student ()
    {
        //
    }

    public String getId()
    {
        //System.out.println("Client: getId()");
        return id;
    }

    public void setId(String id)
    {
        //System.out.println("Client: setId()");
        this.id = id;
    }

    public String getName()
    {
        //System.out.println("Client: getName()");
        return name;
    }

    public void setName(String name)
    {
        //System.out.println("Client: setName()");
        this.name = name;
    }

    public String getDepartment()
    {
        //System.out.println("Client: getDepartment()");
        return department;
    }

    public void setDepartment(String department)
    {
        //System.out.println("Client: setDepartment()");
        this.department = department;
    }

    public double getGpa()
    {
        //System.out.println("Client: getGpa()");
        return gpa;
    }

    public void setGpa(double gpa)
    {
        //System.out.println("Client: setGpa()");
        this.gpa = gpa;
    }

    public int getSemester()
    {
        //System.out.println("Client: getSemester()");
        return semester;
    }

    public void setSemester(int semester)
    {
        //System.out.println("Client: setSemester()");
        this.semester = semester;
    }
}
