package com.AN1D.an1d.DTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Sms {

    @NotBlank
    @Size(min = 3, max = 3, message = "Country code is not valid")
    private final String countryCode;

    @NotBlank
    @Size(min = 10, max = 10, message = "Mobile Number is not valid!")
    private final String mobileNumber;

    @NotBlank
    @Size(min = 10, message = "Message body must be greater than 10 character!")
    private final String message;
    
    public Sms(
        @JsonProperty("country_code")
        String countryCode,

        @JsonProperty("mobile_number") 
        String mobileNumber,

        @JsonProperty("message") 
        String message) 
    {
        this.countryCode = countryCode;
        this.mobileNumber = mobileNumber;
        this.message = message;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Sms{" +"mobileNumber= ..." + '\'' +", message='" + message + '\'' + '}';
    }
}