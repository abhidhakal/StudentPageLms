package com.example.lms.Pojo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter@Setter
@RequiredArgsConstructor

public class NoticesPojo {
    private Integer noticeId;
    private String title;
    private String description;
    private LocalDate uploadDate;
}
