package com.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage(); 
        message.setTo(to); 
        message.setSubject(subject); 
        message.setText(text);
        System.out.print(message);
        emailSender.send(message);
    }

	public String sendOTP(String email, String msg, String subject) {
		SimpleMailMessage message = new SimpleMailMessage(); 
        message.setTo(email); 
        message.setSubject(subject); 
        message.setText(msg);
        System.out.print(message);
        emailSender.send(message);
		return "OTP sent succesfully!";
	}
    
//    public ResponseEntity<String> sendEmail(User user) {
//    	EmailService emailService = new EmailService();
//    	
//	 	System.out.println(user.getEmail()+"innside the senEmail method");
//        String to = user.getEmail() ;
//        String subject = "Succesfully registered"  ;
//        String text = "Hi "+user.getUsername() +", you have successfully registered to our service.";
//        emailService.sendSimpleMessage(to, subject, text);
//        return ResponseEntity.ok("Email sent successfully.");
// }
}
