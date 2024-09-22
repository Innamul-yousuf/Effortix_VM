package com.effortix.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.effortix.backend.testers.GeminiTesterClass;
import com.effortix.backend.testers.ProjectEmployeeTester;
import com.effortix.backend.testers.TicketTester;

@SpringBootApplication
public class EffortixSprint2Application {
	 @Autowired
	    private ProjectEmployeeTester projectEmployeeTester;
	 
	public static void main(String[] args) {
		SpringApplication.run(EffortixSprint2Application.class, args);
		System.out.println("		Hello, World!     ");
	}
	@Bean
    public CommandLineRunner demo() {
        return (args) -> {
            // Print all ProjectEmployee records to the console
            projectEmployeeTester.printAllProjectEmployees();
            
            // You can also print a single ProjectEmployee by ID
            projectEmployeeTester.printProjectEmployeeById(1L); // Replace with actual ID
        };
    }
	
	@Autowired
	private TicketTester ticketTester;
	 @Bean
	    public CommandLineRunner demo1() {
	        return (args) -> {
	            System.out.println("________STARTED TICKETTESTER________");
	        	// Test all service methods in ProjectEmployeeTester
	        	ticketTester.testAllMethods();
	        };
	    }
	 
	 @Autowired
	private GeminiTesterClass geminiTesterClass ;
	 @Bean
	    public CommandLineRunner callGemini() {
	        return (args) -> {
	            System.out.println("________STARTED Gemini API________");
	        	// Test all service methods in ProjectEmployeeTester
	           // geminiTesterClass.runAPI();
	        };
	    }
	 
}
