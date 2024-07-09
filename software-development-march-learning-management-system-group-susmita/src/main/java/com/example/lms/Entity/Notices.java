package com.example.lms.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name="Notices")

public class Notices {
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="notices_seq_gen")
    @SequenceGenerator(name="notices_seq_gen",sequenceName="notices_seq",allocationSize=1)
    @Id
    private Integer noticeId;
    @Column(name="Title")
    private String title;
    @Column(name="Description")
    private String description;
    @Column(name="UploadDate")
    private LocalDate uploadedDate;



}
