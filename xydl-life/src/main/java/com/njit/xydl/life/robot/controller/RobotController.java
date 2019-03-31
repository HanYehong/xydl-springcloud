package com.njit.xydl.life.robot.controller;

import com.njit.xydl.life.robot.service.ChatService;
import com.njit.xydl.life.robot.service.RecognizeService;
import com.yehong.han.config.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @author HanYehong
 * @date 2019/3/31 17:38
 */
@RestController
@RequestMapping("robot")
public class RobotController {

    @Autowired
    private ChatService chatService;

    @Autowired
    private RecognizeService recognizeService;

    @PostMapping("/chat")
    public Response autoReplyRobot(String msg) {
        return Response.ok(chatService.chattingRobot(msg));
    }

    @PostMapping("/recognize")
    public Response recognizeCharacter(String imageUrl) throws IOException, URISyntaxException {
        return Response.ok(recognizeService.recognitionRobot(imageUrl));
    }
}