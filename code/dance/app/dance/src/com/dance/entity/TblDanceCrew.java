package com.dance.entity;

import com.dance.DanceConstant;
import com.dance.service.DanceCommonService;
import jsontag.module.datadict.JtDataDictFactory;
import jsontag.util.DateUtil;
import jsontag.util.UrlUtil;
import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Entity
public class TblDanceCrew extends TblDanceCommonAttr implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private Date establishDate;
    private String founder;//中文



    public TblDanceCrew(){

    }

    public TblDanceCrew(Integer id, String name, Integer createUserId,
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



    @Transient
    public String getEstablishDateDis(){
        return DateUtil.dateToStringByDateFormat(this.establishDate,DateUtil.DATEFORMAT_YYYY_MM_DD);
    }


    @Transient
    public String getImgDis(){
        return DanceCommonService.getImgDis(this.img,DanceConstant.UPLOAD_IMG_TYPE_CREW,null);
    }


    @Transient
    public String getImgDisMin() {
        return DanceCommonService.getImgDisMin(getImgDis());
    }


}
