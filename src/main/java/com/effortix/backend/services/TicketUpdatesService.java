
  package com.effortix.backend.services;
  
  import org.springframework.beans.factory.annotation.Autowired; import
  org.springframework.stereotype.Service;

import com.effortix.backend.models.Employee;
import com.effortix.backend.models.Project;
import com.effortix.backend.models.ProjectEmployee;
import com.effortix.backend.models.Ticket;
import com.effortix.backend.models.TicketUpdates;
import com.effortix.backend.repository.EmployeeRepository;
import com.effortix.backend.repository.ProjectEmployeeRepository;
import com.effortix.backend.repository.TicketRepository;
import com.effortix.backend.repository.TicketUpdatesRepository;

import java.util.Date;
import java.util.List; import java.util.Optional;
  
@Service
public class TicketUpdatesService{

    @Autowired
    private TicketUpdatesRepository ticketUpdatesRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TicketRepository ticketRepository;
    public List<TicketUpdates> getTicketUpdatesByEmployeeAndDateRange(
            Long employeeId, Date currentDate) {
        return ticketUpdatesRepository.findByEmployeeAndDateInRange(employeeId, currentDate);
    }
    
    public TicketUpdates saveOrUpdateTicketUpdates(TicketUpdates ticketUpdates) {
        // Validate input
     
     
       System.out.println("TU___---______:"+ticketUpdates.getTuId()+ ticketUpdates.gettUpdate());

        // Save or update TicketUpdates entity
        return ticketUpdatesRepository.save(ticketUpdates);
    }

    
    public Optional<TicketUpdates> getTicketUpdatesById(Long tuId) {
        return ticketUpdatesRepository.findById(tuId);
    }

 
    public List<TicketUpdates> getAllTicketUpdates() {
        return ticketUpdatesRepository.findAll();
    }

  
    public void deleteTicketUpdatesById(Long tuId) {
        ticketUpdatesRepository.deleteById(tuId);
    }
    
}