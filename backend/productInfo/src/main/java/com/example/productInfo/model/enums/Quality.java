package com.example.productInfo.model.enums;

/**
 * 队伍状态枚举
 *
 * @author 20891
 */
public enum Quality {

    NEW(0, "全新"),
    RENEW(1, "再制造"),
    NONE(2, "未知");

    private int value;

    private String text;

    public static Quality getEnumByValue(Integer value) {
        if (value == null) {
            return null;
        }
        Quality[] values = Quality.values();
        for (Quality teamStatusEnum : values) {
            if (teamStatusEnum.getValue() == value) {
                return teamStatusEnum;
            }
        }
        return null;
    }

    Quality(int value, String text) {
        this.value = value;
        this.text = text;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}