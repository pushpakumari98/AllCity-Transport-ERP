package com.allcity.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.aspectj.bridge.IMessage;

@Data
@Entity
@Table(name="contact-us")
public class ContactUs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String email;

    @Column(columnDefinition = "TEXT")
    private String message;
}
