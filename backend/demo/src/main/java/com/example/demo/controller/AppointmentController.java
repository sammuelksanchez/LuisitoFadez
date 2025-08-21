package com.example.demo.controller;

import com.example.demo.model.Appointments;
import com.example.demo.repo.AppointmentsRepository;
import com.example.demo.service.AppointmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.Map;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin(origins = "*")
public class AppointmentController {

    private final AppointmentsService appointmentsService;
    @Autowired
    public AppointmentController(AppointmentsService appointmentsService){
        this.appointmentsService = appointmentsService;
    }
    @PostMapping
    public ResponseEntity<Appointments> createAppointment(@RequestBody Appointments appointment){
        return ResponseEntity.ok(appointmentsService.createAppointment(appointment));
    }

    @GetMapping("/{phoneNumber}/{appointmentId}")                                 // there might be a typo here on appointMentID
    public ResponseEntity<Appointments> getAppointmentById(@PathVariable String phoneNumber, @PathVariable String appointMentId){
        return appointmentsService.findById(phoneNumber,appointMentId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{phoneNumber}/{appointmentId}")
    public ResponseEntity<Appointments> updateAppointment(
            @PathVariable String phoneNumber,
            @PathVariable String appointmentId,
            @RequestBody Appointments appointment){
        appointment.setAppointmentId(appointmentId);
        return ResponseEntity.ok(appointmentsService.updateAppointment(phoneNumber,appointmentId,appointment));
    }

    @DeleteMapping("/{phoneNumber}/{appointmentId}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable String phoneNumber,@PathVariable String appointmentId)
    {
        appointmentsService.cancelAppointment(phoneNumber,appointmentId);
        return ResponseEntity.ok().build();
    }

}
