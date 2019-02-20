

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.classic.Session;

public class ManageStudents {
	private static SessionFactory factory;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			factory= new AnnotationConfiguration().configure().addAnnotatedClass(Student.class).buildSessionFactory();
		} catch (Throwable ex) {
			// TODO Auto-generated catch block
			System.err.println("Failed to create session Factory object."+ex);
			throw new ExceptionInInitializerError(ex);
		} 
		ManageStudents MS=new ManageStudents();
		
		Integer studentroll1=MS.addStudent(4, "krishan");
		Integer studentroll2=MS.addStudent(34, "ravi");
		Integer studentroll3=MS.addStudent(37, "Jyoti");
		Integer studentroll4=MS.addStudent(40, "saurabh");
		
		
		
		

	}
	
	public Integer addStudent(int rollno,String name)
	{
		Session session = factory.openSession();
		Transaction tx =null;
		Integer studentroll=null;
		
		
			try {
				tx=session.beginTransaction();
				Student student = new Student();
				student.setName(name);
				student.setRollNo(rollno);
				studentroll=(Integer) session.save(student);
				tx.commit();
			} catch (HibernateException e) {
				// TODO Auto-generated catch block
				if(tx!=null)
					tx.rollback();
			}
			finally 
			{
				session.close();
			}
			
			return studentroll;
		
	}

}
