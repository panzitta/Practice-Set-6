package base;

import static org.junit.Assert.*;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.PersonDomainModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person_Test {
		
	private static PersonDomainModel person1;
	private static UUID person1UUID = UUID.randomUUID();			
	
	@BeforeClass
	public static void personInstance() throws Exception{
		
		Date person1Birth = null;

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		 person1 = new PersonDomainModel();
		 
		try {
			person1Birth = dateFormat.parse("1994-11-27");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		person1.setPersonID(person1UUID);
		person1.setFirstName("Mingkun");
		person1.setMiddleName("a");
		person1.setLastName("Chen");
		person1.setBirthday(person1Birth);
		person1.setCity("Elkton");
		person1.setStreet("702 Stone Gate Blvd");
		person1.setPostalCode(21921);
		
	}
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	@Test
	public static void getAllPersonsTest() {
	ArrayList<PersonDomainModel> test;
	test.add(person1);
	assert(PersonDAL.getAllPersons() == test);
	}
	
	@Test
	public static void getPersonTest(){
	assert(PersonDAL.getPerson(person1UUID) == person1);
	}
	
	@Test
	public static void updatePersonTest(){
	person1.setMiddleName("Test");
	PersonDAL.updatePerson(person1);
	assert(person1.getMiddleName()=="Test");
	}
	
	@Test
	public static void addPersonTest(){
	PersonDomainModel person2 = new PersonDomainModel();
	PersonDAL.addPerson(person2);
	ArrayList<PersonDomainModel> test;
	test.add(person2);
	assert(PersonDAL.getAllPersons()==test);
	}
	
	@Test
	public static void deletePersonTest(){
	PersonDAL.deletePerson(person1UUID);
	assert(PersonDAL.getPerson(person1UUID)==null);
	}

}
