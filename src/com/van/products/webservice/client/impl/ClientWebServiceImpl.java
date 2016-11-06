package com.van.products.webservice.client.impl;

import java.util.Date;

import javax.jws.WebService;

import com.van.products.webservice.client.ClientWebService;

/** 
 * @className: ClientWebServiceImpl.java<br/>
 * @classDescription: Description here<br/>
 * @createTime: 2015年5月2日
 * @author Van
 */

@WebService(endpointInterface = "com.van.products.webservice.client.ClientWebService"
			,serviceName = "clientService")
public class ClientWebServiceImpl implements ClientWebService {

	@Override
	public String service(String param) {

		System.out.println("+++++++++++" + new Date());
		return "访问时间：" + new Date() + "携带参数:" + param;
	}

}
