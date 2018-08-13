package com.dance.entity;

import com.dance.DanceConstant;
import com.dance.service.DanceCommonService;
import com.dance.vos.app.crew.SearchCrewApply;
import jsontag.module.datadict.JtDataDictFactory;
import jsontag.util.DateUtil;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


@Entity
public class TblDanceUserCrewRelation extends  TblDanceRelationCommonAttr implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer crewId;

    public TblDanceUserCrewRelation(){

    }


    @Basic
    @Column(name = "crewId", nullable = true)
    public Integer getCrewId() {
        return crewId;
    }

    public void setCrewId(Integer crewId) {
        this.crewId = crewId;
    }


    @Transient
    private String crewName;
    @Transient
    private String crewImg;
    @Transient
    public String getCrewName() {
        return crewName;
    }
    @Transient
    public void setCrewName(String crewName) {
        this.crewName = crewName;
    }
    @Transient
    public String getCrewImg() {
        return DanceCommonService.getImgDisMin(this.crewImg,DanceConstant.UPLOAD_IMG_TYPE_CREW,null);
    }
    @Transient
    public void setCrewImg(String crewImg) {
        this.crewImg = crewImg;
    }

    //DanceCommonService.java-setDanceUser
    public TblDanceUserCrewRelation(Integer id, Integer crewId, String crewName, String crewImg) {
        this.id = id;
        this.crewId = crewId;
        this.crewName = crewName;
        this.crewImg = crewImg;
    }




    //SearchCrewApply
    public TblDanceUserCrewRelation(Integer id, Integer crewId, String crewName, String crewImg,
                                    String aka, String avatarUrl,Integer userId,Date createDateTime) {
        this.id = id;
        this.crewId = crewId;
        this.crewName = crewName;
        this.crewImg = crewImg;
        this.aka=aka;
        this.avatarUrl=avatarUrl;
        this.userId=userId;
        this.createDateTime=createDateTime;
    }

    //SearchCrewApplyMe
    public TblDanceUserCrewRelation(Integer id, Integer crewId, String crewName, String crewImg,
                                    Integer userId,Date createDateTime,String status) {
        this.id = id;
        this.crewId = crewId;
        this.crewName = crewName;
        this.crewImg = crewImg;
        this.userId=userId;
        this.createDateTime=createDateTime;
        this.status=status;
    }




    @Transient
    public String getNameDis() {
        return this.crewName;
    }

    @Transient
    public String getImgDis() {
        return getCrewImg();
    }
}
