package com.bjb.service.test.service;

import com.bjb.service.test.dto.BaseDTO;

public interface IBJBServiceTest {

	public BaseDTO getHMAC(String stringToSign, String secretKey) throws Exception;
}
