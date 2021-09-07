package com.AN1D.an1d.config.Beans;
import com.twilio.http.TwilioRestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import com.twilio.http.TwilioRestClient.Builder;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(ignoreResourceNotFound = true, value = "classpath:application.properties")
public class TwilioInitializer {

    @Value("${twilio.account.sid}")
	private String account_sid;

    @Value("${twilio.auth.token}")
	private String auth_token;

    @Value("${twilio.sender.number}")
	private String fromPhoneNumber;

    @Bean(name = "twilioRestClient")
    public TwilioRestClient twilioRestClient() {
        return (new Builder(this.account_sid, this.auth_token)).build();
    }

    @Bean(name = "fromPhoneNumber")
    public String fromPhoneNumber() {
        return this.fromPhoneNumber;
    }
}
