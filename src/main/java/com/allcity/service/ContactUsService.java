package com.allcity.service;

import com.allcity.entities.ContactUs;

import java.util.List;

public interface ContactUsService {

    ContactUs saveMessage(ContactUs contactUs);

    List<ContactUs> getAllMessages();
}
