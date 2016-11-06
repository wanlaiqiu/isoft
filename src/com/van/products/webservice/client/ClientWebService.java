package com.van.products.webservice.client;

import javax.jws.WebService;

/** 
 * @className: ClientWebService.java<br/>
 * @classDescription: Description here<br/>
 * @createTime: 2015年5月2日
 * @author Van
 */

@WebService
public interface ClientWebService {
	
	public String service(String param);

}
