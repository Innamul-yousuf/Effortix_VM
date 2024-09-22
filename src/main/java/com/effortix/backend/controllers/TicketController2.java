package com.effortix.backend.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.effortix.backend.models.Employee;
import com.effortix.backend.models.Project;
import com.effortix.backend.models.Ticket;
import com.effortix.backend.repository.TicketRepository;
import com.effortix.backend.services.EmployeeService;
import com.effortix.backend.services.ProjectService;
import com.effortix.backend.services.TicketService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/tickets")
public class TicketController2 {

    @Autowired
    private TicketService ticketService;
    
    @Autowired
    private EmployeeService employeeService;
    
    @Autowired
    private ProjectService projectService;
    
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    
    // List all tickets
    @GetMapping
    public String listTickets(Model model) {
        List<Ticket> tickets = ticketService.getAllTickets();
        model.addAttribute("tickets", tickets);
        return "ticketUI/ticket_list"; // Returns 'ticket_list.html'
    }

    // Show form to create a new ticket
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("ticket", new Ticket());
        return "ticketUI/ticket_form"; // Returns 'ticket_form.html'
    }

    // Save a new ticket
    @PostMapping("/save")
    public String saveTicket2(@ModelAttribute("ticket") Ticket ticket) {
        ticketService.saveOrUpdateTicket(ticket);
        return "redirect:/tickets"; // Redirects to the ticket list
    }

    // View details of a single ticket
    @GetMapping("/{id}")
    public String viewTicket(@PathVariable("id") Long id, Model model) {
        Optional<Ticket> ticket = ticketService.getTicketById(id);
        if (ticket.isPresent()) {
            model.addAttribute("ticket", ticket.get());
            return "ticketUI/ticket_detail"; // Returns 'ticket_detail.html'
        } else {
            model.addAttribute("errorMessage", "Ticket not found");
            return "error"; // Returns 'error.html'
        }
    }

    // Show form to edit an existing ticket
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Optional<Ticket> ticket = ticketService.getTicketById(id);
        if (ticket.isPresent()) {
            model.addAttribute("ticket", ticket.get());
            return "ticketUI/ticket_form"; // Returns 'ticket_form.html'
        } else {
            model.addAttribute("errorMessage", "Ticket not found");
            return "error"; // Returns 'error.html'
        }
    }

    // Delete a ticket
    @PostMapping("/{id}/delete")
    public String deleteTicket(@PathVariable("id") Long id) {
        boolean deleted = ticketService.deleteTicketById(id);
        return "redirect:/tickets"; // Redirects to the ticket list
    }
    
    
    @GetMapping("/tickets/edit/{tId}")
    public String editTicket(@PathVariable("tId") Long tId, Model model) {
        Optional<Ticket> ticket = ticketService.getTicketById(tId);
        List<Employee> employees = employeeService.getAllEmployees(); // Fetch all employees
        List<Project> projects = projectService.getAllProjects(); // Fetch all projects

        model.addAttribute("ticket", ticket);
        model.addAttribute("employees", employees);
        model.addAttribute("projects", projects);
        
        return "ticket-form"; // Thymeleaf template name
    }

    
    @Autowired
    ProjectService projectService2;
    @GetMapping("/tickets/new")
    public String createNewTicket(Model model) {
        Ticket ticket=new Ticket();
        List<Employee> employees = employeeService.getAllEmployees();
        System.out.println("Employees For Form:"+employees.size()+employees+employees.get(0).geteId());
        for(int i=0; i <employees.size();i++) {
        	System.out.println("Employee name: "+employees.get(i).geteName());
        }
        List<Project> projects = projectService2.getAllProjects();
        for(int i=0; i <projects.size();i++) {
        	System.out.println("projects name: "+projects.get(i).getpName());
        }
   
        
        model.addAttribute("ticket", ticket);
        model.addAttribute("employees", employees);
        model.addAttribute("projects", projects);
        
        return "ticketUI/ticket_form";
    }

    @PostMapping("/tickets/save")
    public String saveTicket(Ticket ticket) {
        ticketService.saveOrUpdateTicket(ticket);
        return "redirect:/tickets";
    }
    
    
}