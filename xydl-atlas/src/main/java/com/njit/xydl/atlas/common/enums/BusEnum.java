package com.njit.xydl.atlas.common.enums;

/**
 * @author HanYehong
 * @date 2019/4/1 22:18
 */
public enum BusEnum {

    /**
     * 一号小公交
     */
    ONE_BUS(201901, "一号公交"),
    /**
     * 二号小公交
     */
    TWO_BUS(201902, "二号公交"),
    /**
     * 三号小公交
     */
    THREE_BUS(201903, "三号公交"),
    /**
     * 四号小公交
     */
    FOUR_BUS(201904, "四号公交"),
    /**
     * 五号小公交
     */
    FIVE_BUS(201905, "五号公交");

    BusEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    private Integer code;
    private String name;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
