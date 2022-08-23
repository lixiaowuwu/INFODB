package com.cy.store.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActNotice implements Serializable {
    private int nid;
    private int aid;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date startTime;
    private String title;
    private String cover;
    private String text;
    private int isDelete;
    private String reserve1;
    private String reserve2;
}
