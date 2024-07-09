package com.example.productInfo.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 产品参数
 * @author 20891
 * @TableName parameter
 */
@TableName(value ="parameter")
@Data
public class Parameter implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer parameterId;

    /**
     * 电压
     */
    private String voltage;

    /**
     * 功率
     */
    private String power;

    /**
     * 调节器功能
     */
    private String functionInfo;

    /**
     * 尺寸A
     */
    private Integer sizeA;

    /**
     * 尺寸B
     */
    private Integer sizeB;

    /**
     * OD1（mm）
     */
    private Integer OD1;

    /**
     * D1 （mm）
     */
    private Integer D1;

    /**
     * D2（mm）
     */
    private Integer D2;

    /**
     * D3（mm）
     */
    private Integer D3;

    /**
     * D4（mm）
     */
    private Integer D4;

    /**
     * L1（mm）
     */
    private Integer L1;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 起动机id
     */
    private Integer productStarterId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}