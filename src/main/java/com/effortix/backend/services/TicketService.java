package com.effortix.backend.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.effortix.backend.models.Ticket;
import com.effortix.backend.repository.TicketRepository;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    // Create or update a ticket
    public Ticket saveOrUpdateTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    // Get a ticket by its ID
    public Optional<Ticket> getTicketById(Long tId) {
        return ticketRepository.findById(tId);
    }

    // Get all tickets
    public List<Ticket> getAllTickets() {
    	
        return ticketRepository.findAll();
    }

    // Delete a ticket by its ID
//    public void deleteTicketById(Long tId) {
//        ticketRepository.deleteById(tId);
//    }
    
    public boolean deleteTicketById(Long tId) {
        if (ticketRepository.existsById(tId)) {
            ticketRepository.deleteById(tId);
            return !ticketRepository.existsById(tId); // Confirm if the ticket is deleted
        } else {
            return false; // Return false if the ticket ID doesn't exist
        }
    }


  

    // Get all tickets assigned to a specific employee (From Employee)
    public List<Ticket> getTicketsByFromEmployeeId(Long employeeId) {
        return ticketRepository.findByFromEmployeeEId(employeeId);
    }

    // Get all tickets assigned to a specific employee (To Employee)
    public List<Ticket> getTicketsByToEmployeeId(Long employeeId) {
        return ticketRepository.findByFromEmployeeEId(employeeId);
    }

    // Get all tickets related to a specific project
    public List<Ticket> getTicketsByProjectId(Long projectId) {
        return ticketRepository.findByProjectPId(projectId);
    }

    // Get all tickets by status
    public List<Ticket> getTicketsByStatus(String status) {
        return ticketRepository.findBytStatus(status);
    }

    // Get all tickets by type
    public List<Ticket> getTicketsByType(String type) {
        return ticketRepository.findBytType(type);
    }

    // Update ticket status
    public Ticket updateTicketStatus(Long tId, String newStatus) {
        Optional<Ticket> ticketOptional = ticketRepository.findById(tId);
        if (ticketOptional.isPresent()) {
            Ticket ticket = ticketOptional.get();
            ticket.setTStatus(newStatus);
            return ticketRepository.save(ticket);
        }
        return null; // Or throw a custom exception
    }

    // Update ticket deadline
    public Ticket updateTicketDeadline(Long tId, Date newDeadline) {
        Optional<Ticket> ticketOptional = ticketRepository.findById(tId);
        if (ticketOptional.isPresent()) {
            Ticket ticket = ticketOptional.get();
            ticket.setDeadline(newDeadline);
            return ticketRepository.save(ticket);
        }
        return null; // Or throw a custom exception
    }

    // Get tickets by flag
    public List<Ticket> getTicketsByFlag(int tFlag) {
        return ticketRepository.findBytFlag(tFlag);
    }
}