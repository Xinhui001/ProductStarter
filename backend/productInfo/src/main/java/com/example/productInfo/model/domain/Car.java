package com.example.productInfo.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 车型信息
 * @author 20891
 * @TableName car
 */
@TableName(value ="car")
@Data
public class Car implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer applicationId;

    /**
     * 车型名称
     */
    private String carName;

    /**
     * 车型详情
     */
    private String carInfo;

    /**
     * 生产厂商（品牌）
     */
    private String manufacturer;

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