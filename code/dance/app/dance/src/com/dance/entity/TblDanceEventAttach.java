package com.dance.entity;

import com.dance.DanceConstant;
import com.dance.service.DanceCommonService;
import jsontag.JsonTagContext;
import jsontag.factory.JsonTagConfigFactory;
import jsontag.util.StringUtil;
import jsontag.util.UrlUtil;
import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.File;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class TblDanceEventAttach implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String img;
    private Integer eventId;
    private Long size;
    private Integer width;
    private Integer height;
    private String yearMonth;
    private String type;

    @GenericGenerator(name = "generator", strategy = "native")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "img", nullable = true, length = 100)
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Basic
    @Column(name = "eventId", nullable = true)
    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    @Basic
    @Column(name = "size", nullable = true)
    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    @Basic
    @Column(name = "width", nullable = true)
    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    @Basic
    @Column(name = "height", nullable = true)
    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    @Basic
    @Column(name = "yearMonth", nullable = true, length = 6)
    public String getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }


    @Basic
    @Column(name = "type", nullable = true, length = 10)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Transient
    public String getImgDis(){

        return DanceCommonService.getImgDis(this.img,this.type,this.yearMonth);
     }


    @Transient
    public String getImgDisMin() {
        return DanceCommonService.getImgDisMin(getImgDis());
    }
}
