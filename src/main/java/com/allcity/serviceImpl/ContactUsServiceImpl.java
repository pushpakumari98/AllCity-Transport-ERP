package com.allcity.serviceImpl;

import com.allcity.entities.ContactUs;
import com.allcity.repositories.ContactUsRepository;
import com.allcity.service.ContactUsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactUsServiceImpl implements ContactUsService {

    @Autowired
    private final ContactUsRepository contactUsRepository;

    public ContactUsServiceImpl(ContactUsRepository contactUsRepository) {
        this.contactUsRepository = contactUsRepository;
    }

    @Override
    public ContactUs saveMessage(ContactUs contactUs) {
        return contactUsRepository.save(contactUs);
    }

    @Override
    public List<ContactUs> getAllMessages() {
        return contactUsRepository.findAll();
    }
}
