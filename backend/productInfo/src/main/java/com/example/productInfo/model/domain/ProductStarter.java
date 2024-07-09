package com.example.productInfo.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 产品信息表-起动机
 * @author 20891
 * @TableName productstarter
 */
@TableName(value ="productstarter")
@Data
public class ProductStarter implements Serializable {
    /**
     * 产品id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 产品编号
     */
    private String productNum;

    /**
     * 产品展示图
     */
    private String showPic;

    /**
     * 全景展示
     */
    private String showAll;

    /**
     * 调节器插口图（三张以内）
     */
    private String socketPic;

    /**
     * 整机数据图（三张以内）
     */
    private String dataPic;

    /**
     * 产品性质  0-全新  1-再制造  2-未知
     */
    private Integer quality;

    /**
     * 适用车型
     */
    private Integer applicationId;

    /**
     * 系列号
     */
    private Integer serialNum;

    /**
     * 序列号
     */
    private Integer seriesNum;

    /**
     * 地区
     */
    private String area;

    /**
     * 产品参数id
     */
    private Integer parameterId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}