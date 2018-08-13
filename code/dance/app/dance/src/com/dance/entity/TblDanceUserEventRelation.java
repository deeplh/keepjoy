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
public class TblDanceUserEventRelation extends  TblDanceRelationCommonAttr implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer eventId;


    public  TblDanceUserEventRelation(){

    }



    @Basic
    @Column(name = "eventId", nullable = true)
    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }




    @Transient
    private String eventName;
    @Transient
    private String eventImg;
    @Transient
    public String getEventName() {
        return eventName;
    }
    @Transient
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
    @Transient
    public String getEventImg() {
        return DanceCommonService.getImgDisMin(this.eventImg,DanceConstant.UPLOAD_IMG_TYPE_EVENT,null);
    }
    @Transient
    public void setEventImg(String eventImg) {
        this.eventImg = eventImg;
    }





    //SearchEventApply
    public TblDanceUserEventRelation(Integer id, Integer eventId, String eventName, String eventImg,
                                    String aka, String avatarUrl,Integer userId,Date createDateTime) {
        this.id = id;
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventImg = eventImg;
        this.aka=aka;
        this.avatarUrl=avatarUrl;
        this.userId=userId;
        this.createDateTime=createDateTime;
    }

    //SearchEventApplyMe
    public TblDanceUserEventRelation(Integer id, Integer eventId, String eventName, String eventImg,
                                    Integer userId,Date createDateTime,String status) {
        this.id = id;
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventImg = eventImg;
        this.userId=userId;
        this.createDateTime=createDateTime;
        this.status=status;
    }

    //GetEvent
    public TblDanceUserEventRelation(Integer userId,String aka, String avatarUrl,String status){
        this.userId=userId;
        this.aka=aka;
        this.avatarUrl=avatarUrl;
        this.status=status;
    }

    @Transient
    public String getNameDis() {
        return this.eventName;
    }

    @Transient
    public String getImgDis() {
        return getEventImg();
    }
}
