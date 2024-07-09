package com.example.lms.Entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@RequiredArgsConstructor


@Table(name="StudentPayment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="payment_seq_gen")
    @SequenceGenerator(name="payment_seq_gen",sequenceName="payment_seq_gen",allocationSize=1)


    private Long paymentId;

    @Column(name="Date")
    private LocalDate date;
    @Column(name="Total")
    private Long Total;

    @Column(name="Paid")
    private Long paid;

    @Column(name="Due")
    private Long due;

    @Column(name="status")
    private boolean status;

    @ManyToOne
    private Student studentId;



}

