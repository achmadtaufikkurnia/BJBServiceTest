package com.bjb.service.test.service.impl;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

import com.bjb.service.test.dto.BaseDTO;
import com.bjb.service.test.service.IBJBServiceTest;

@Service
public class BJBServiceTestImpl implements IBJBServiceTest {

	@Override
	public BaseDTO getHMAC(String stringToSign, String secretKey) throws Exception {
		BaseDTO dto = null;
		try {
			dto = new BaseDTO();
			SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
			Mac mac = Mac.getInstance("HmacSHA256");
			mac.init(secretKeySpec);
			dto.setSignature(bytesToHex(mac.doFinal(stringToSign.getBytes())).toLowerCase());
			dto.setResponseCode("00");
			dto.setResponseMessage("SUCCESS");
		} catch (Exception e) {
			throw e;
		}
		return dto;
	}

	private String bytesToHex(byte[] hashInBytes) {

        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
