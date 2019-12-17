package com.godrej.properties.webservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.godrej.properties.service.EOIPaymentDtlService;

@RestController
public class PaymentServiceController {

	@Autowired
	private EOIPaymentDtlService eoiPaymentService;
}
