package com.example.demo.service;

import com.example.demo.model.Appointments;
import com.example.demo.repo.AppointmentsRepository;
import com.example.demo.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AppointmentsService {
    private final AppointmentsRepository appointmentsRepository;
    private final UserRepository userRepository;

    public AppointmentsService(AppointmentsRepository appointmentsRepository, UserRepository userRepository){
        this.appointmentsRepository = appointmentsRepository;
        this.userRepository = userRepository;
    }

//  create new appointment
    public Appointments createAppointment(Appointments appointment){
        if(!userRepository.existsByPhoneNumber(appointment.getPhoneNumber())){
            throw new RuntimeException("User not found with phone number: "+ appointment.getPhoneNumber());
        }

//       generate unique appointment id
        appointment.setAppointmentId(UUID.randomUUID().toString());
        return appointmentsRepository.save(appointment);
    }

//    get all appointments for a user
    public List<Appointments> getAppointmentsByPhoneNumber(String phoneNumber){
        return appointmentsRepository.findByPhoneNumber(phoneNumber);
    }

    public Optional<Appointments> findById(String phoneNumber, String appointmentId){
        return appointmentsRepository.findById(phoneNumber,appointmentId);
    }

//    update/reschedule and appointment
    public Appointments updateAppointment(String phoneNumber,String appointmentId, Appointments updatedAppointment){
        Optional<Appointments> existingAppointment = appointmentsRepository.findById(phoneNumber,appointmentId);
        if(existingAppointment.isPresent()){
            Appointments appointment = existingAppointment.get();
            appointment.setAppointmentDatetime(updatedAppointment.getAppointmentDateTime()  );
            appointment.setStatus(updatedAppointment.getStatus());
            return appointmentsRepository.save(appointment);
        }else{
            throw new RuntimeException("Appointment not found: " + appointmentId);
        }
    }

//    cancel an appointment
    public void cancelAppointment(String phoneNumber, String appointmentId){
        if(!appointmentsRepository.existsById(phoneNumber,appointmentId)){
            throw new RuntimeException("Appointment not found: " + appointmentId);
        }
        appointmentsRepository.deleteById(phoneNumber,appointmentId);
    }
}
