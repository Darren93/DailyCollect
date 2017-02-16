package com.darren.spring.controller;

import javax.annotation.Resource;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SocketController {
	@RequestMapping("/ws")
	public String index() {
		return "/page/websocket";
	}
	@RequestMapping("/message")
	public String message() {
		return "/page/message";
	}
	@MessageMapping("/change-notice")
	@SendTo("/get/notice")
	public String greeting(String value) {
		return value;
	}
}
