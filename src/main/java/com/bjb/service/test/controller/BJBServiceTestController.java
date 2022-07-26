package com.bjb.service.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bjb.service.test.dto.BaseDTO;
import com.bjb.service.test.service.IBJBServiceTest;
import com.google.gson.Gson;

@RestController
@RequestMapping("/bjb")
public class BJBServiceTestController {

	@Autowired
	private IBJBServiceTest bjbservice;
	
	@RequestMapping(value = "/getSignature", method = RequestMethod.GET, produces = "application/json")
	public BaseDTO getHMACString() {
		BaseDTO dto = null;
		try {
			dto = bjbservice.getHMAC("POST:TEST:12345", "secretbanget");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	@RequestMapping(value = "/postSignature", method = RequestMethod.POST, produces = "application/json")
	public BaseDTO inputHMACString(@RequestBody String payload) {
		BaseDTO dto = null;
		try {
			Gson gsonReq = new Gson();
			dto = gsonReq.fromJson(payload, BaseDTO.class);
			dto = bjbservice.getHMAC(dto.getRequestPayload(), "secretpisan");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
}
