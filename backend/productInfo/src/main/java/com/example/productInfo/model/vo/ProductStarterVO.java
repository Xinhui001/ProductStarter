package com.example.productInfo.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 20891
 */
@Data
public class ProductStarterVO implements Serializable {

    private static final long serialVersionUID = -1803537471771735520L;

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
