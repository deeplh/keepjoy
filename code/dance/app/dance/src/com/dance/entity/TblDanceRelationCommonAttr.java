package com.dance.entity;

import jsontag.module.datadict.JtDataDictFactory;
import jsontag.util.DateUtil;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@MappedSuperclass
public class TblDanceRelationCommonAttr implements Serializable {

    @GenericGenerator(name = "generator", strategy = "native")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "id", nullable = false)
    public Integer id;

    @Column(name = "userId")
    public Integer userId;

    @Column(name = "status", nullable = true,length = 1,columnDefinition = "CHAR")
    public String status;

    @Column(name = "createUserId")
    public Integer createUserId;

    @Column(name = "createDateTime")
    public Date createDateTime;

    @Column(name = "updateUserId")
    public Integer updateUserId;

    @Column(name = "updateDateTime")
    public Date updateDateTime;

    @Column(name = "approveUserId")
    public Integer approveUserId;

    @Column(name = "approveDateTime")
    public Date approveDateTime;



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(Date updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public Integer getApproveUserId() {
        return approveUserId;
    }

    public void setApproveUserId(Integer approveUserId) {
        this.approveUserId = approveUserId;
    }

    public Date getApproveDateTime() {
        return approveDateTime;
    }

    public void setApproveDateTime(Date approveDateTime) {
        this.approveDateTime = approveDateTime;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Transient
    public String getCreateDateTimeDis(){
        return DateUtil.dateToStringByDateFormat(this.createDateTime,DateUtil.DATEFORMAT_YYYY_MM_DD_HH_MM_SS);
    }

    @Transient
    public String avatarUrl;

    @Transient
    public String aka;

    @Transient
    public String nickName;

    @Transient
    public String getAvatarUrl() {
        return avatarUrl;
    }

    @Transient
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @Transient
    public String getAka() {
        return aka;
    }

    @Transient
    public void setAka(String aka) {
        this.aka = aka;
    }

    @Transient
    public String getNickName() {
        return nickName;
    }

    @Transient
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Transient
    public String getStatusDis(){
        return JtDataDictFactory.getDataDictTypeKeyValue("status",this.status);
    }

    @Transient
    public List<TblDanceUser> members;

    @Transient
    public List<TblDanceUser> getMembers() {
        return members;
    }
    @Transient
    public void setMembers(List<TblDanceUser> members) {
        this.members = members;
    }

    @Transient
    public Boolean isMine;

    @Transient
    public Boolean getMine() {
        return isMine;
    }

    @Transient
    public void setMine(Boolean mine) {
        isMine = mine;
    }

}
