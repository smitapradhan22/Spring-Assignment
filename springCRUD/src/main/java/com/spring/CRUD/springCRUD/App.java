package com.spring.CRUD.springCRUD;

import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.spring.CRUD.springCRUD.Dao.StudentDao;
import com.spring.CRUD.springCRUDentities.Student;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Program Started..." );
        
        //spring jdbc=> jdbcTemplate
        ApplicationContext context=new ClassPathXmlApplicationContext("com/spring/CRUD/springCRUD/config.xml");
        JdbcTemplate template=context.getBean("jdbcTemplate", JdbcTemplate.class);
        
        boolean flag=true;//untill flag is true while will run 
        Scanner sc=new Scanner(System.in);
        StudentDao studentDao=context.getBean("studentDao",StudentDao.class);
        Student student=new Student();
        int roll_no,choice;
        String name,DOB,DOJ;
        while(flag) {
        	System.out.println("***** Menu *****");
        	System.out.println("1: Add New Student");
        	System.out.println("2: Update Student Details");
        	System.out.println("3: Delete Student Details");
        	System.out.println("4: Fetch All Student Details");
        	System.out.println("5: Fetch by Student Roll_No");
        	System.out.println("6: Exit");
        	System.out.println("Enter Your Choice : ");
        	choice=sc.nextInt();
        	switch(choice) {
        	case 1:
        		
                System.out.println("Enter Student Roll No:");
                roll_no=sc.nextInt();
                System.out.println("Enter Student Name:");
                name=sc.next();
                System.out.println("Enter Date Of Birth(YYYY-MM-DD)");
                DOB=sc.next();
                System.out.println("Enter Date Of Joining(YYYY-MM-DD)");
                DOJ=sc.next();
                
                student.setRoll_no(roll_no);
                student.setName(name);
                student.setDOB(DOB);
                student.setDOJ(DOJ);
                try {
	                int res=studentDao.insert(student);
	                if(res!=0)System.out.println("Record Inserted Successfully!!");
	                else System.out.println("Insertion Failed!!");
                }catch(Exception e) {
                	System.out.println("Can not Insert record!!");
                }
                break;
        		
        	case 2:
        		System.out.println("Enter Student Roll No:");
                roll_no=sc.nextInt();
                System.out.println("Enter Student Name:");
                name=sc.next();
                System.out.println("Enter Date Of Birth(YYYY-MM-DD)");
                DOB=sc.next();
                
                student.setName(name);
                student.setDOB(DOB);
                student.setRoll_no(roll_no);
                int res1=studentDao.change(student);
                if(res1!=0)System.out.println("Updated Succssfully!! ");
                else System.out.println("Update Failed!! ");
                break;
        	case 3:
        		System.out.println("Enter Student Roll No:");
                roll_no=sc.nextInt();
                student.setRoll_no(roll_no);
                int res3=studentDao.delete(student);
                if(res3!=0)System.out.println("Record Deleted Successfully!!");
                else System.out.println("Delete Failed!!");
        		break;
        	case 4:
        		List<Student> student3=studentDao.getAllStudent();
        		System.out.println("*******Students Data********");
        		
        		for(Student s:student3) {
        			System.out.print("Roll No :"+s.getRoll_no()+"\t");
                    System.out.print("Name :"+s.getName()+"\t");
                    System.out.print("DOB :"+s.getDOB()+"\t");
                    System.out.print("DOJ :"+s.getDOJ()+"\n");
        		}
        		break;
        	case 5:
        		System.out.println("Enter Student Roll No:");
                roll_no=sc.nextInt();
                student.setRoll_no(roll_no);
                Student student1=studentDao.getStudent(roll_no);
                System.out.println("*******Student Data********");
                System.out.print("Roll No :"+student1.getRoll_no()+"\t");
                System.out.print("Name :"+student1.getName()+"\t");
                System.out.print("DOB :"+student1.getDOB()+"\t");
                System.out.print("DOJ :"+student1.getDOJ()+"\n");
                
        		break;
        	case 6:
        		System.out.println("Thank You For visiting!!");
        		flag=false;
        		break;
        	default:
        		System.out.println("Enter valid choice!!");
        		break;
        	}
        }
        
    }
}
