package com.dance.entity;

import jsontag.util.DateUtil;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Entity
public class TblDanceUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer userId;
    private Integer jtWeixinSmallPlatformUserId;
    private String trueName;
    private String aka;
    private Integer age;
    private String sex;
    private Integer createUserId;
    private Date createDateTime;
    private Date beginDanceDate;
    private String img;
    private String info;
    private  String avatarUrl;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TblDanceUser that = (TblDanceUser) o;
        return
                Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId);
    }

    public TblDanceUser(){

    }

    public TblDanceUser(Integer userId, String aka, String avatarUrl) {
        this.userId = userId;
        this.aka = aka;
        this.avatarUrl = avatarUrl;
    }

    @Id
    @Column(name = "userId",nullable = false)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }




    @Basic
    @Column(name = "trueName", nullable = true, length = 20)
    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    @Basic
    @Column(name = "aka", nullable = true, length = 20)
    public String getAka() {
        return aka;
    }

    public void setAka(String aka) {
        this.aka = aka;
    }

    @Basic
    @Column(name = "age", nullable = true)
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Basic
    @Column(name = "sex", nullable = true, length = 1)
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "createUserId", nullable = true)
    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    @Basic
    @Column(name = "createDateTime", nullable = true)
    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }

    @Basic
    @Column(name = "beginDanceDate", nullable = true,columnDefinition = "DATE")
    public Date getBeginDanceDate() {
        return beginDanceDate;
    }

    public void setBeginDanceDate(Date beginDanceDate) {
        this.beginDanceDate = beginDanceDate;
    }

    @Basic
    @Column(name = "img", nullable = true, length = 50)
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }


    @Basic
    @Column(name = "info", nullable = true, length = 100)
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }


    @Basic
    @Column(name = "jtWeixinSmallPlatformUserId")
    public Integer getJtWeixinSmallPlatformUserId() {
        return jtWeixinSmallPlatformUserId;
    }

    public void setJtWeixinSmallPlatformUserId(Integer jtWeixinSmallPlatformUserId) {
        this.jtWeixinSmallPlatformUserId = jtWeixinSmallPlatformUserId;
    }


    @Basic
    @Column(name = "avatarUrl",length = 200)
    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }



    @Transient
    public String getBeginDanceDateDis(){
        return DateUtil.dateToStringByDateFormat(this.beginDanceDate,DateUtil.DATEFORMAT_YYYY_MM_DD);
    }

    @Transient
    public String getAkaDis(){
        return this.aka;
    }

    @Transient
    public String getTrueNameDis(){
        return this.trueName;
    }


    @Transient
    private List<TblDanceUserCrewRelation> crews;
    @Transient
    private List<TblDanceUserStudioRelation> studios;
    @Transient
    private List<TblDanceUserStyleRelation> styles;
    @Transient
    public List<TblDanceUserCrewRelation> getCrews() {
        return crews;
    }
    @Transient
    public void setCrews(List<TblDanceUserCrewRelation> crews) {
        this.crews = crews;
    }
    @Transient
    public List<TblDanceUserStudioRelation> getStudios() {
        return studios;
    }
    @Transient
    public void setStudios(List<TblDanceUserStudioRelation> studios) {
        this.studios = studios;
    }
    @Transient
    public List<TblDanceUserStyleRelation> getStyles() {
        return styles;
    }
    @Transient
    public void setStyles(List<TblDanceUserStyleRelation> styles) {
        this.styles = styles;
    }


}
