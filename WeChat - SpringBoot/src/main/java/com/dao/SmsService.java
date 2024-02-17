package com.dao;

import com.twilio.Twilio;
import com.twilio.exception.ApiException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsService {
	private final String twilioAccountSid;
	private final String twilioAuthToken;
	private final String twilioPhoneNumber;

	public SmsService(@Value("${twilio.account.sid}") String twilioAccountSid,
			@Value("${twilio.auth.token}") String twilioAuthToken,
			@Value("${twilio.phone.number}") String twilioPhoneNumber) {
		this.twilioAccountSid = twilioAccountSid;
		this.twilioAuthToken = twilioAuthToken;
		this.twilioPhoneNumber = twilioPhoneNumber;

		Twilio.init(twilioAccountSid, twilioAuthToken);
	}

	public String sendSMS(String phoneNum, String message) {
		boolean flag = true;
		try {

			Message.creator(new PhoneNumber(phoneNum), new PhoneNumber(twilioPhoneNumber), message).create();
		} catch (ApiException e) {
			// Handle exception appropriately
			e.printStackTrace();
			flag = false;
		}

		if (flag) {
			return message;
		} else {
			return "0";
		}
	}

}
