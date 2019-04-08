package com.njit.xydl.life.robot.service;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @author HanYehong
 * @date 2019/3/31 17:42
 */
public interface RecognizeService {

    String recognitionRobot(String imageUrl) throws IOException, URISyntaxException;
}
