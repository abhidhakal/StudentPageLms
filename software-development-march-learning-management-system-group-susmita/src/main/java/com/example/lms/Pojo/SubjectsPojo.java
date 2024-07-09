package com.example.lms.Pojo;

import com.example.lms.Entity.ClassSchool;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class SubjectsPojo {
    private Integer subId;
    private String subjectName;
    private ClassSchool className;
    private String credits;

}
