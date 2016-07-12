package com.vishal.Hibernate;


import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;

//import javax.transaction.Transaction;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;

public class Main {
	private static SessionFactory factory; 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try{
	         factory = new Configuration().configure().buildSessionFactory();
	      }catch (Throwable ex) { 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
	      }
	      Main ME = new Main();

	      /* Add few employee records in database */
	      Integer empID1 = ME.addEmployee("Zara", "Ali", 1000);
	      Integer empID2 = ME.addEmployee("Daisy", "Das", 5000);
	      Integer empID3 = ME.addEmployee("John", "Paul", 10000);
	      
	      Integer rollNumber = ME.addStudent("Vishal", "Male");
	      
	      /* List down all the employees */
	      ME.listEmployees();

	      /* Update employee's records */
	      ME.updateEmployee(empID1, 5000);

	      /* Delete an employee from the database */
	      ME.deleteEmployee(empID2);

	      /* List down new list of the employees */
	      ME.listEmployees();
	      
	      
	      ME.totalSalary();
		

	 }
	   /* Method to CREATE an employee in the database */
	   public Integer addEmployee(String fname, String lname, int salary){
	      Session session = factory.openSession();
	      //Transaction tx = null;
	      Integer employeeID = null;
	      try{
	         session.beginTransaction();
	         Employee employee = new Employee(fname, lname, salary);
	         employeeID = (Integer) session.save(employee); 
	         session.getTransaction().commit();
	      }catch (HibernateException e) {
	         if (session.getTransaction()!=null) session.getTransaction().rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	      return employeeID;
	   }
	   /* Method to  READ all the employees */
	   public void listEmployees( ){
	      Session session = factory.openSession();
	      //Transaction tx = null;
	      try{
	         session.beginTransaction();
	         List employees = (List) session.createQuery("FROM Employee").list(); 
	         for (Iterator iterator = 
	                           ((java.util.List) employees).iterator(); iterator.hasNext();){
	            Employee employee = (Employee) iterator.next(); 
	            System.out.print("First Name: " + employee.getFirstName()); 
	            System.out.print("  Last Name: " + employee.getLastName()); 
	            System.out.println("  Salary: " + employee.getSalary()); 
	         }
	         session.getTransaction().commit();
	      }catch (HibernateException e) {
	         if (session.getTransaction()!=null) session.getTransaction().rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	   }
	   /* Method to UPDATE salary for an employee */
	   public void updateEmployee(Integer EmployeeID, int salary ){
	      Session session = factory.openSession();
	     // Transaction tx = null;
	      try{
	         session.beginTransaction();
	         Employee employee = 
	                    (Employee)session.get(Employee.class, EmployeeID); 
	         employee.setSalary( salary );
			 session.update(employee); 
			 session.getTransaction().commit();
	      }catch (HibernateException e) {
	         if (session.getTransaction()!=null) session.getTransaction().rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	   }
	   /* Method to DELETE an employee from the records */
	   public void deleteEmployee(Integer EmployeeID){
	      Session session = factory.openSession();
	      //Transaction tx = null;
	      try{
	         session.beginTransaction();
	         Employee employee = 
	                   (Employee)session.get(Employee.class, EmployeeID); 
	         session.delete(employee); 
	         session.getTransaction().commit();
	      }catch (HibernateException e) {
	         if (session.getTransaction()!=null) session.getTransaction().rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	   }
	   
	   public Integer addStudent(String name,String gender){
		   Integer rollNumber = null;
		   Session session = factory.openSession();
		   try{
			   session.beginTransaction();
			   Student_Info student = new Student_Info(name,gender);
			   rollNumber = (Integer)session.save(student);
			   session.getTransaction().commit();
		   }catch (HibernateException e) {
		         if (session.getTransaction()!=null) session.getTransaction().rollback();
		         e.printStackTrace(); 
		   }finally {
		         session.close(); 
		   }
		   return rollNumber;
	   }
	   
	   public void totalSalary(){
		      Session session = factory.openSession();
		      
		      try{
		         session.beginTransaction();
		         Criteria cr = session.createCriteria(Employee.class);

		         // To get total salary.
		         cr.setProjection(Projections.sum("salary"));
		         List totalSalary = cr.list();

		         System.out.println("Total Salary: " + totalSalary.get(0) );
		         session.getTransaction().commit();
		      }catch (HibernateException e) {
		         if (session.getTransaction()!=null) session.getTransaction().rollback();
		         e.printStackTrace(); 
		      }finally {
		         session.close(); 
		      }
		   }
	

}
