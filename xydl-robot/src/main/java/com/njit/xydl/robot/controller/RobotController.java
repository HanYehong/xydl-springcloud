package com.njit.xydl.robot.controller;

import com.njit.xydl.robot.utils.JSONResult;
import com.njit.xydl.robot.utils.TulingRobot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/robot")
public class RobotController {
    @Autowired
    private TulingRobot tulingRobot;

    @RequestMapping("/message")
    public JSONResult replyMsg(@RequestParam String msg){
        try {
            return JSONResult.ok(tulingRobot.getMessage(msg));
        }catch (Exception e){
            return JSONResult.errorMsg(e.getMessage());
        }
    }
}
