package com.example.lms.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name="transportation")
public class Transportation {
 @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator="transportation_seq_gen")
    @SequenceGenerator(name="transportation_seq_gen",sequenceName="transportation_seq_gen",allocationSize=1)

    private Integer busNo;

    @Column(name="Fees")
    private Integer busFee;

    @Column(name="Route")
    private String route;


}
