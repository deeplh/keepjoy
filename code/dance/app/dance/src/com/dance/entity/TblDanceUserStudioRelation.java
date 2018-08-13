package com.dance.entity;

import com.dance.DanceConstant;
import com.dance.service.DanceCommonService;
import jsontag.module.datadict.JtDataDictFactory;
import jsontag.util.DateUtil;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


@Entity
public class TblDanceUserStudioRelation  extends  TblDanceRelationCommonAttr implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer studioId;


    public TblDanceUserStudioRelation(){

    }



    @Basic
    @Column(name = "studioId", nullable = true)
    public Integer getStudioId() {
        return studioId;
    }

    public void setStudioId(Integer studioId) {
        this.studioId = studioId;
    }



    @Transient
    private String studioName;
    @Transient
    private String studioImg;
    @Transient
    public String getStudioName() {
        return studioName;
    }
    @Transient
    public void setStudioName(String studioName) {
        this.studioName = studioName;
    }
    @Transient
    public String getStudioImg() {
        return DanceCommonService.getImgDisMin(this.studioImg,DanceConstant.UPLOAD_IMG_TYPE_STUDIO,null);
    }
    @Transient
    public void setStudioImg(String studioImg) {
        this.studioImg = studioImg;
    }

    //DanceCommonService.java-setDanceUser
    public TblDanceUserStudioRelation(Integer id, Integer studioId, String studioName, String studioImg) {
        this.id = id;
        this.studioId = studioId;
        this.studioName = studioName;
        this.studioImg = studioImg;
    }





    //SearchStudioApply
    public TblDanceUserStudioRelation(Integer id, Integer studioId, String studioName, String studioImg,
                                    String aka, String avatarUrl,Integer userId,Date createDateTime) {
        this.id = id;
        this.studioId = studioId;
        this.studioName = studioName;
        this.studioImg = studioImg;
        this.aka=aka;
        this.avatarUrl=avatarUrl;
        this.userId=userId;
        this.createDateTime=createDateTime;
    }

    //SearchStudioApplyMe
    public TblDanceUserStudioRelation(Integer id, Integer studioId, String studioName, String studioImg,
                                    Integer userId,Date createDateTime,String status) {
        this.id = id;
        this.studioId = studioId;
        this.studioName = studioName;
        this.studioImg = studioImg;
        this.userId=userId;
        this.createDateTime=createDateTime;
        this.status=status;
    }


    @Transient
    public String getNameDis() {
        return this.studioName;
    }

    @Transient
    public String getImgDis() {
        return getStudioImg();
    }
}
