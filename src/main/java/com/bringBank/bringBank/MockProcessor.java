package com.bringBank.bringBank;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static java.lang.Long.parseLong;

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

        if (parseLong(value) == 1118880000){

            String response = getInfoSuccessResponse().toString();
            logger.info("Response sent: " + response);
            exchange.getOut().setBody(response);

        } else if (parseLong(value) == 1118880001) {

            String response1 = getInfoSuccessResponse1().toString();
            logger.info("Response sent: " + response1);
            exchange.getOut().setBody(response1);

        } else {

            String response4 = getInfoSuccessResponse4().toString();
            logger.info("Response sent: " + response4);
            exchange.getOut().setBody(response4);

        }



    }


    public StringBuilder getInfoSuccessResponse() {

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
                "\"currency\": \"KES\"," +
                "\"unclearedBalance\": \"-515.00\"," +
                "\"accountBalance\": \"639389.39\"," +
                "\"limitAmount\": \"0.00\"," +
                "\"isAccountDormant\": \" \"," +
                "\"isPostingRestricted\": \" \"," +
                "\"workingBalance\": \"639389.39\"," +
                "\"lockedAmount\": \"0.00\"," +
                "\"retentionAmount\": \"0.00\"" +
                "}" +
                "}" +
                "}";


        return new StringBuilder(jsonResponse);

    }

    public StringBuilder getInfoSuccessResponse1() {

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
                "\"companyCode\": \"KE0010009\"," +
                "\"currency\": \"KES\"," +
                "\"unclearedBalance\": \"-500.00\"," +
                "\"accountBalance\": \"790389.39\"," +
                "\"limitAmount\": \"0.00\"," +
                "\"isAccountDormant\": \" \"," +
                "\"isPostingRestricted\": \" \"," +
                "\"workingBalance\": \"790389.39\"," +
                "\"lockedAmount\": \"0.00\"," +
                "\"retentionAmount\": \"0.00\"" +
                "}" +
                "}" +
                "}";


        return new StringBuilder(jsonResponse);

    }


    private StringBuilder getInfoSuccessResponse4() {
        String jsonResponse = "{\"Account invalid\"}";


        return new StringBuilder(jsonResponse);
    }



}
