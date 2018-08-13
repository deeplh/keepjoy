package com.dance.entity;

import jsontag.module.datadict.JtDataDictFactory;
import jsontag.util.DateUtil;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
public class TblDanceEvent extends TblDanceCommonAttr implements Serializable {

    private static final long serialVersionUID = 1L;



    private String name;
    private Date stageDate;
    private Date beginTime;
    private Date endTime;
    private String eventType;
    private Integer maxPersonNum;


    public TblDanceEvent(){

    }

    public TblDanceEvent(Integer id, String name, Integer createUserId, Date createDateTime,
                         String address, Date stageDate,Date beginTime, Date endTime,
                         String eventType, Integer maxPersonNum,String status,
                         String nickName, String avatarUrl) {
        this.id = id;
        this.name = name;
        this.createUserId = createUserId;
        this.createDateTime = createDateTime;
        this.address = address;
        this.stageDate = stageDate;
        this.beginTime=beginTime;
        this.endTime=endTime;
        this.eventType = eventType;
        this.maxPersonNum = maxPersonNum;
        this.nickName = nickName;
        this.avatarUrl = avatarUrl;
        this.status=status;
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
    @Column(name = "beginTime", nullable = true)
    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    @Basic
    @Column(name = "eventType", nullable = true, length = 2)
    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    @Basic
    @Column(name = "endTime", nullable = true)
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Basic
    @Column(name = "stageDate", nullable = true,columnDefinition = "DATE")
    public Date getStageDate() {
        return stageDate;
    }

    public void setStageDate(Date stageDate) {
        this.stageDate = stageDate;
    }

    @Basic
    @Column(name = "maxPersonNum", nullable = true)
    public Integer getMaxPersonNum() {
        return maxPersonNum;
    }

    public void setMaxPersonNum(Integer maxPersonNum) {
        this.maxPersonNum = maxPersonNum;
    }



    @Transient
    public String getBeginTimeDis(){
        return DateUtil.dateToStringByDateFormat(this.beginTime,"HH:mm");
    }

    @Transient
    public String getEndTimeDis() {
        return DateUtil.dateToStringByDateFormat(this.endTime,"HH:mm");
    }

    @Transient
    public String getStageDateDis(){
        return DateUtil.dateToStringByDateFormat(this.stageDate,"yyyy-MM-dd");
    }


    @Transient
    public String getTimeBeforeDis() throws Exception {

        Long time = new Date().getTime() - this.createDateTime.getTime();
        Date date = new Date(time);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        Long milli=cal.getTimeInMillis();

        Integer millisecond=milli.intValue();

        if (millisecond != null) {
            Integer localSecond = millisecond / 1000;
            Integer hour = localSecond / 60 / 60;
            Integer day = hour/24;

            Integer minute = (localSecond - hour * 60 * 60) / 60;
            Integer second = localSecond - minute * 60 - hour * 60 * 60;
            if(day>0){
                return day+"天前";
            }
            if(hour>0 && minute>0){
                return hour+"小时"+minute+"分钟前";
            }
            else if(hour==0 && minute>0){
                return minute+"分钟前";
            }
            else{
                return "刚刚";
            }
        } else {
            return null;
        }



    }

    @Transient
    public String getEventTypeDis(){
        return JtDataDictFactory.getDataDictTypeKeyValue("eventType",this.eventType);
    }

    @Transient
    private List<TblDanceEventAttach> attachs;
    @Transient
    public List<TblDanceEventAttach> getAttachs() {
        return attachs;
    }
    @Transient
    public void setAttachs(List<TblDanceEventAttach> attachs) {
        this.attachs = attachs;
    }

    @Transient
    private String[] attachArr;

    @Transient
    public String[] getAttachArr() {
        return attachArr;
    }
    @Transient
    public void setAttachArr(String[] attachArr) {
        this.attachArr = attachArr;
    }

    @Transient
    private String[] attachArrMin;
    @Transient
    public String[] getAttachArrMin() {
        return attachArrMin;
    }
    @Transient
    public void setAttachArrMin(String[] attachArrMin) {
        this.attachArrMin = attachArrMin;
    }



//    @Transient
//    private List<TblDanceUser> actors;
//
//    @Transient
//    public List<TblDanceUser> getActors() {
//        return actors;
//    }
//    @Transient
//    public void setActors(List<TblDanceUser> actors) {
//        this.actors = actors;
//    }
}
