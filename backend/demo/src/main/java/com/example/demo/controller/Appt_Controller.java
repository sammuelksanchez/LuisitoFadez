package com.example.demo.controller;

import com.example.demo.model.Appointments;
import com.example.demo.repo.AppointmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.Map;

@RestController
@RequestMapping("/api/appointments")
public class Appt_Controller {
    @Autowired
    private AppointmentsRepository appointmentsRepository;

    @PostMapping
    public ResponseEntity<Appointments> createAppointment(@RequestBody Appointments appointment){
        return ResponseEntity.ok(appointmentsRepository.save(appointment));
    }

    @GetMapping("/{appointmentId}")
    public ResponseEntity<Appointments> getAppointmentById(@PathVariable String appointmentId){
        return appointmentsRepository.findById(appointmentId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{appointmentId}")
    public ResponseEntity<Appointments> updateAppointment(
            @PathVariable String appointmentId,
            @RequestBody Appointments appointment){
        appointment.setAppt_id(appointmentId);
        return ResponseEntity.ok(appointmentsRepository.save(appointment));
    }

    @DeleteMapping("/{apptId}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable String apptId)
    {
        appointmentsRepository.deleteById(apptId);
        return ResponseEntity.ok().build();
    }

}
