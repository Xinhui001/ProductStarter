package com.example.productInfo.model.request;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class ParamAddRequest implements Serializable {

    private static final long serialVersionUID = 1864055886681105465L;

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
}
