package com.allcity.controller;

import com.allcity.entities.ContactUs;
import com.allcity.service.ContactUsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:59753","https://allcity-transport-erp.onrender.com"})
@RestController
@RequestMapping("/api/contact-us")
public class ContactUsController {

    @Autowired
    private final ContactUsService contactUsService;

    public ContactUsController(ContactUsService contactUsService) {
        this.contactUsService = contactUsService;
    }


    // Submit Contact Form
    @PostMapping
    public ContactUs submitMessage(@RequestBody ContactUs contactUs) {
        return contactUsService.saveMessage(contactUs);
    }

    // Get All Messages (Admin)
    @GetMapping
    public List<ContactUs> getMessages() {
        return contactUsService.getAllMessages();
    }
}
