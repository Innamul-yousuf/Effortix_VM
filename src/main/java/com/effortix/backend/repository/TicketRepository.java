package com.effortix.backend.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.effortix.backend.models.Employee;
import com.effortix.backend.models.Ticket;

@Repository
public
interface TicketRepository extends JpaRepository<Ticket, Long> {
	 
	List<Ticket> findByFromEmployeeEId(Long employeeId);
    List<Ticket> findByProjectPId(Long projectId);
    List<Ticket> findBytStatus(String status);
    List<Ticket> findBytType(String type);
    List<Ticket> findBytFlag(int tFlag);
}