package com.dance.entity;

import com.dance.DanceConstant;
import com.dance.service.DanceCommonService;
import jsontag.module.datadict.JtDataDictFactory;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Entity
public class TblDanceStudio extends TblDanceCommonAttr implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private Date establishDate;
    private String founder;


    public TblDanceStudio(){

    }

    @Basic
    @Column(name = "name", nullable = true, length = 30)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    @Basic
    @Column(name = "establishDate", nullable = true,columnDefinition = "DATE")
    public Date getEstablishDate() {
        return establishDate;
    }

    public void setEstablishDate(Date establishDate) {
        this.establishDate = establishDate;
    }

    @Basic
    @Column(name = "founder", nullable = true, length = 30)
    public String getFounder() {
        return founder;
    }

    public void setFounder(String founder) {
        this.founder = founder;
    }





    public TblDanceStudio(Integer id, String name, Integer createUserId,
                          Date createDateTime, String status,
                            String aka, String avatarUrl) {
        this.id = id;
        this.name = name;
        this.createUserId = createUserId;
        this.createDateTime = createDateTime;
        this.status = status;
        this.aka=aka;
        this.avatarUrl=avatarUrl;
    }

    @Transient
    public String getImgDis(){
        return DanceCommonService.getImgDis(this.img,DanceConstant.UPLOAD_IMG_TYPE_STUDIO,null);
    }


    @Transient
    public String getImgDisMin() {
        return DanceCommonService.getImgDisMin(getImgDis());
    }



}
