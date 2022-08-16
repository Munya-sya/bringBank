package com.bringBank.bringBank;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static java.lang.Long.parseLong;
import static javax.xml.bind.DatatypeConverter.parseString;

@Component
public class MockProcessor implements Processor {

    private static final Logger logger = LoggerFactory.getLogger(MockProcessor.class);


    @Override
    public void process(Exchange exchange) throws Exception {

        String jsonInput = exchange.getIn().getBody(String.class);
        exchange.getIn().setHeader("accept", "application/json");
        logger.info("Received Request: " + jsonInput);

        exchange.getOut().setHeader(Exchange.CONTENT_TYPE, "application/json");
        exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, 200);



        String jsonString = jsonInput.toString();
        DocumentContext docCtx = JsonPath.parse(jsonString);
        JsonPath jsonPath = JsonPath.compile("$.requestPayload.primaryData.businessKey");
        String value=docCtx.read(jsonPath);
        System.out.println(value);


        if (parseLong(value) == 1118880000)  {

            String successResponse = SuccessResponse().toString();
            logger.info("Response sent: " + successResponse);
            exchange.getOut().setBody(successResponse);

        } else if (parseLong(value) == 1118880001) {

            String successResponse1 = SuccessResponse1().toString();
            logger.info("Response sent: " + successResponse1);
            exchange.getOut().setBody(successResponse1);

        }

         else if (parseLong(value) == 1118880003) {

            String successResponse2 = SuccessResponse2().toString();
            logger.info("Response sent: " + successResponse2);
            exchange.getOut().setBody(successResponse2);

         } else {

            String failureResponse = FailureResponse().toString();
            logger.info("Response sent: " + failureResponse);
            exchange.getOut().setBody(failureResponse);

        }


        String jsonString1 = jsonInput.toString();
        DocumentContext docCtx1 = JsonPath.parse(jsonString1);
        //JsonPath jsonPath1 = JsonPath.compile("$.requestPayload.additionalData.companyCode");
        JsonPath jsonPath1 = JsonPath.compile("$.requestPayload.additionalData.companyCode");
        String value1=docCtx1.read(jsonPath1);
        System.out.println(value1);

        if ((parseString(value1).startsWith("KE")) && parseLong(value) == 1118880000 )  {
            String CountryCodeResponse = SuccessResponse().toString();
            logger.info("Response sent: " + CountryCodeResponse);
            exchange.getOut().setBody(CountryCodeResponse);


        } else if ((parseString(value1).startsWith("TZ")) && parseLong(value) == 1118880001 ) {

            String CountryCodeResponse1 = SuccessResponse1().toString();
            logger.info("Response sent: " + CountryCodeResponse1);
            exchange.getOut().setBody(CountryCodeResponse1);


        }
         else if ((parseString(value1).startsWith("RW")) && parseLong(value) == 1118880003 ) {

            String CountryCodeResponse2 = SuccessResponse2().toString();
            logger.info("Response sent: " + CountryCodeResponse2);
            exchange.getOut().setBody(CountryCodeResponse2);


        }

        else {

            String CountryCodeFailureResponse = FailureResponse().toString();
            logger.info("Response sent: " + CountryCodeFailureResponse);
            exchange.getOut().setBody(CountryCodeFailureResponse);

        }






    }


    public StringBuilder SuccessResponse() {
            int balance = 70000;
            int unclearedBalance = 3000;
            int workingBalance = 70000;
            int lockedAmount = 0;
            int limitAmount = 0;


        String jsonResponse = "{\" header\": {" +
                " \"messageID\": \"12424253738\"," +
                " \"conversationID\": \"a4439be6-24fd-46d1-ad72-709de1af8ffc\"," +
                " \"routeCode\": \"001\"," +
                "\"targetSystemID\": \"Not Available\"," +
                "\"statusCode\": \"0\"," +
                "\"statusDescription\": \"Success\"," +
                "\"statusMessage\": \"Success\"," +
                "\"messageCode\": \"00\"," +
                "\"ehfInfo\": {" +
                "\"ehfRef\": \"OSP-1002\"," +
                "\"ehfDesc\": \"Processed Successfully\"" +
                "}" +
                "}," +
                "\"responsePayload\": {" +
                " \"primaryData\": {" +
                "\"businessKey\": \"1118880000\"," +
                "\"businessKeyType\": \"Account Number\"" +
                "}," +
                "\"additionalData\": {" +
                "\"companyCode\": \"KE0010009\"," +
                "\"currency\": \"KENYA Currency KES\"," +
                "\"unclearedBalance\": \""+unclearedBalance+"\"," +
                "\"accountBalance\": \""+balance+"\"," +
                "\"limitAmount\": \""+limitAmount+"\"," +
                "\"isAccountDormant\": \" \"," +
                "\"isPostingRestricted\": \" \"," +
                "\"workingBalance\": \""+workingBalance+"\"," +
                "\"lockedAmount\": \""+lockedAmount+"\"," +
                "\"retentionAmount\": \"0.00\"" +
                "}" +
                "}" +
                "}";


        return new StringBuilder(jsonResponse);

    }

    public StringBuilder SuccessResponse1() {

        int balance = 100000;
        int unclearedBalance = -43990;
        int workingBalance = 100000;
        int lockedAmount = 0;
        int limitAmount = 0;

        String jsonResponse = "{\" header\": {" +
                " \"messageID\": \"12424253738\"," +
                " \"conversationID\": \"a4439be6-24fd-46d1-ad72-709de1af8ffc\"," +
                " \"routeCode\": \"001\"," +
                "\"targetSystemID\": \"Not Available\"," +
                "\"statusCode\": \"0\"," +
                "\"statusDescription\": \"Success\"," +
                "\"statusMessage\": \"Success\"," +
                "\"messageCode\": \"00\"," +
                "\"ehfInfo\": {" +
                "\"ehfRef\": \"OSP-1002\"," +
                "\"ehfDesc\": \"Processed Successfully\"" +
                "}" +
                "}," +
                "\"responsePayload\": {" +
                " \"primaryData\": {" +
                "\"businessKey\": \"1118880001\"," +
                "\"businessKeyType\": \"Account Number\"" +
                "}," +
                "\"additionalData\": {" +
                "\"companyCode\": \"TZ0010009\"," +
                "\"currency\": \"TANZANIAS Currency Tzs\"," +
                "\"unclearedBalance\": \""+unclearedBalance+"\"," +
                "\"accountBalance\": \""+balance+"\"," +
                "\"limitAmount\": \""+limitAmount+"\"," +
                "\"isAccountDormant\": \" \"," +
                "\"isPostingRestricted\": \" \"," +
                "\"workingBalance\": \""+workingBalance+"\"," +
                "\"lockedAmount\": \""+lockedAmount+"\"," +
                "\"retentionAmount\": \"0.00\"" +
                "}" +
                "}" +
                "}";




        return new StringBuilder(jsonResponse);

    }

    public StringBuilder SuccessResponse2() {

        int balance = 100000;
        int unclearedBalance = -43990;
        int workingBalance = 100000;
        int lockedAmount = 0;
        int limitAmount = 0;

        String jsonResponse = "{\" header\": {" +
                " \"messageID\": \"12424253738\"," +
                " \"conversationID\": \"a4439be6-24fd-46d1-ad72-709de1af8ffc\"," +
                " \"routeCode\": \"001\"," +
                "\"targetSystemID\": \"Not Available\"," +
                "\"statusCode\": \"0\"," +
                "\"statusDescription\": \"Success\"," +
                "\"statusMessage\": \"Success\"," +
                "\"messageCode\": \"00\"," +
                "\"ehfInfo\": {" +
                "\"ehfRef\": \"OSP-1002\"," +
                "\"ehfDesc\": \"Processed Successfully\"" +
                "}" +
                "}," +
                "\"responsePayload\": {" +
                " \"primaryData\": {" +
                "\"businessKey\": \"1118880003\"," +
                "\"businessKeyType\": \"Account Number\"" +
                "}," +
                "\"additionalData\": {" +
                "\"companyCode\": \"RW0010009\"," +
                "\"currency\": \"RWANDA RWS\"," +
                "\"unclearedBalance\": \""+unclearedBalance+"\"," +
                "\"accountBalance\": \""+balance+"\"," +
                "\"limitAmount\": \""+limitAmount+"\"," +
                "\"isAccountDormant\": \" \"," +
                "\"isPostingRestricted\": \" \"," +
                "\"workingBalance\": \""+workingBalance+"\"," +
                "\"lockedAmount\": \""+lockedAmount+"\"," +
                "\"retentionAmount\": \"0.00\"" +
                "}" +
                "}" +
                "}";




        return new StringBuilder(jsonResponse);

    }


    private StringBuilder FailureResponse() {
        String jsonResponse = "{\" header\": {" +
                " \"messageID\": \"12424253738\"," +
                " \"conversationID\": \"a4439be6-24fd-46d1-ad72-709de1af8ffc\"," +
                " \"routeCode\": \"001\"," +
                "\"targetSystemID\": \"Not Available\"," +
                "\"statusCode\": \"0\"," +
                "\"statusDescription\": \"Declined\"," +
                "\"statusMessage\": \"Declined\"," +
                "\"messageCode\": \"00\"," +
                "\"ehfInfo\": {" +
                "\"ehfRef\": \"OSP-1002\"," +
                "\"ehfDesc\": \"Access Denied\"" +
                "}" +
                "}," +
                "\"responsePayload\": {" +
                " \"primaryData\": {" +
                "\"businessKey\": \"NOT FOUND\"," +
                "\"businessKeyType\": \"Account Number\"" +
                "}," +
                "\"additionalData\": {" +
                "\"companyCode\": \"Unregistered\"," +
                "\"currency\": \"NULL\"," +
                "\"unclearedBalance\": \"NULL\"," +
                "\"accountBalance\": \"NULL\"," +
                "\"limitAmount\": \"NULL\"," +
                "\"isAccountDormant\": \" \"," +
                "\"isPostingRestricted\": \" \"," +
                "\"workingBalance\": \"NULL\"," +
                "\"lockedAmount\": \"NULL\"," +
                "\"retentionAmount\": \"NULL\"" +
                "}" +
                "}" +
                "}";


        return new StringBuilder(jsonResponse);
    }



}
