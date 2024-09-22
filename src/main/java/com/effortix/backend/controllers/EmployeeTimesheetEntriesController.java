package com.effortix.backend.controllers;

import com.effortix.backend.models.EmployeeTimesheetEntries;
import com.effortix.backend.services.EmployeeTimesheetEntriesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/timesheet")
public class EmployeeTimesheetEntriesController {

    @Autowired
    private EmployeeTimesheetEntriesService timesheetService;

    // Get all timesheet entries for a specific employee by ID
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<EmployeeTimesheetEntries>> getTimesheetEntriesByEmployeeId(@PathVariable Long employeeId) {
        List<EmployeeTimesheetEntries> entries = timesheetService.getTimesheetEntriesByEmployeeId(employeeId);
        return ResponseEntity.ok(entries);
    }

    // Get all timesheet entries for a specific employee by ID and within a date range
    @GetMapping("/employee/{employeeId}/dates")
    public ResponseEntity<List<EmployeeTimesheetEntries>> getTimesheetEntriesByEmployeeIdAndDate(
            @PathVariable Long employeeId,
            @RequestParam("fromDate")@DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate,
            @RequestParam("toDate")@DateTimeFormat(pattern = "yyyy-MM-dd") Date toDate) {
        List<EmployeeTimesheetEntries> entries = timesheetService.getTimesheetEntriesByEmployeeIdAndDate(employeeId, fromDate, toDate);
        return ResponseEntity.ok(entries);
    }

    // Create a new timesheet entry
    @PostMapping
    public ResponseEntity<EmployeeTimesheetEntries> createTimesheetEntry(@RequestBody EmployeeTimesheetEntries timesheetEntry) {
        EmployeeTimesheetEntries createdEntry = timesheetService.createTimesheetEntry(timesheetEntry);
        return ResponseEntity.ok(createdEntry);
    }

    // Update an existing timesheet entry
    @PutMapping("/{etId}")
    public ResponseEntity<EmployeeTimesheetEntries> updateTimesheetEntry(
            @PathVariable Long etId,
            @RequestBody EmployeeTimesheetEntries updatedEntry) {
        Optional<EmployeeTimesheetEntries> updated = timesheetService.updateTimesheetEntry(etId, updatedEntry);
        return updated.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete a timesheet entry by ID
    @DeleteMapping("/{etId}")
    public ResponseEntity<Void> deleteTimesheetEntry(@PathVariable Long etId) {
        timesheetService.deleteTimesheetEntry(etId);
        return ResponseEntity.noContent().build();
    }
}