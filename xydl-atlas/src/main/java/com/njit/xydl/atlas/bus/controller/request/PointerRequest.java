package com.njit.xydl.atlas.bus.controller.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author HanYehong
 * @date 2019/4/1 22:31
 */
@Data
public class PointerRequest implements Serializable {

    private Long longitude;

    private Long latitude;

    private Integer busCode;

}
