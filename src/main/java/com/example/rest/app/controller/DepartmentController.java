package com.example.rest.app.controller;

import com.example.rest.app.enums.TicketStatus;
import com.example.rest.app.enums.TicketType;
import com.example.rest.app.enums.UserType;
import com.example.rest.app.model.Department;
import com.example.rest.app.model.Ticket;
import com.example.rest.app.model.User;
import com.example.rest.app.service.DepartmentService;
import com.example.rest.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department/")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<Department>> allDepartments() {
        List<Department> departments = this.departmentService.findAll();
        if (departments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }


    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Department> saveDepartment(@RequestBody Department department) {
        this.departmentService.saveDepartment(department);
        return new ResponseEntity<>(department, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Department> deleteDepartment(@PathVariable("id") Long id) {
        Department department = this.departmentService.findById(id);
        if (department == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.departmentService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public ResponseEntity<Department> update(@RequestBody Department department) {
        this.departmentService.saveDepartment(department);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    @RequestMapping(value = "{ticket_id}", method = RequestMethod.POST)
    public ResponseEntity<?> signTicket(@PathVariable("ticket_id") Ticket ticket) {

        if(ticket.getTicketType() == TicketType.T_A){
            User minTicketUserA = userService.minTicketCount(UserType.A);
            ticket.setTicketStatus(TicketStatus.PENDING);
            minTicketUserA.getListTicket().add(ticket);
            userService.saveUser(minTicketUserA);

        }else  if(ticket.getTicketType() == TicketType.T_B){
            User minTicketUserB = userService.minTicketCount(UserType.B);
            ticket.setTicketStatus(TicketStatus.PENDING);
            minTicketUserB.getListTicket().add(ticket);
            userService.saveUser(minTicketUserB);
        }else{
            User minTicketUserC = userService.minTicketCount(UserType.C);
            ticket.setTicketStatus(TicketStatus.PENDING);
            minTicketUserC.getListTicket().add(ticket);
            userService.saveUser(minTicketUserC);
        }
        return ResponseEntity.ok(ticket);

    }

}
