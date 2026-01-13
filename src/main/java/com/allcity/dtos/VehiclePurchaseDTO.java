package com.allcity.dtos;

import lombok.Data;
import java.time.LocalDate;

@Data
public class VehiclePurchaseDTO {

    // sl.no
    private Long SlNo;

    // date
    private LocalDate date;

    // vehicle no
    private String vehicleNo;

    // booking hire
    private Double bookingHire;

    // booking receiving balance date
    private LocalDate bookingReceivingBalanceDate;

    // from & to
    private String fromLocation;
    private String toLocation;

    // transport
    private String transportName;

    // detain
    private String detain;

    // POD received date
    private LocalDate podReceivedDate;

    // lorry balance paid date
    private LocalDate lorryBalancePaidDate;
}
