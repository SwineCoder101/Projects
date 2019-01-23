import HttpHelloWorld.BirthdayEntry;
import HttpHelloWorld.BirthdayEntryService;
import HttpHelloWorld.DateOfBirth;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class BirthdayEntryControllerTest {

    @Autowired
    private BirthdayEntryService birthdayEntryService;

    private final static String helloJohnReqStr = " /hello/john";
    private final static String birthdayJsonStr = " { 'dateOfBirth' : '2000-01-01' }";
    private final static String localHostURL = "http://localhost:8090";
    private RestTemplate restTemplate;
    private String requestURL;

    @Before
    public void init(){
        restTemplate = new RestTemplate();
        //SpringApplication.run(BirthdayEntryControllerTest.class);

        //log.info(quote.toString());
    }



    @Test
    public void shouldPutDateOfBirthWithNoContentResponse(){
//        Foo updatedInstance = new Foo("newName");
//        updatedInstance.setId(createResponse.getBody().getId());
//        String resourceUrl =
//                fooResourceUrl + '/' + createResponse.getBody().getId();
//        HttpEntity<Foo> requestUpdate = new HttpEntity<>(updatedInstance, headers);
//        template.exchange(resourceUrl, HttpMethod.PUT, requestUpdate, Void.class);

//        final String url = String.format("%s/api/shop/{id}", Global.webserviceUrl);
//
//        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("X-TP-DeviceID", Global.deviceID);
//        Shop shop= new Shop();
//        Map<String, String> param = new HashMap<String, String>();
//        param.put("id","10")
//        HttpEntity<Shop> requestEntity = new HttpEntity<Shop>(shop, headers);
//        HttpEntity<Shop[]> response = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Shop[].class, param);
//
//        shops = response.getBody();

//        requestURL = localHostURL + helloJohnReqStr + birthdayJsonStr;
//        HttpHeaders headers = new HttpHeaders();
//        HttpEntity<DateOfBirth> requestEntity = new HttpEntity<>(dateOfBirth)
//
//
//        restTemplate.exchange(requestURL,HttpMethod.PUT,)
//
//        ResponseEntity<String> response
//                = restTemplate.getForEntity(requestURL, String.class);

       // restTemplate.put(localHostURL + helloJohnReqStr,birthdayJsonStr);


        //assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

    @Test
    public void shouldGetDateOfBirthWithOkResponse(){
//        BirthdayGreeting birthdayGreeting =
//                restTemplate.getForObject(localHostURL + helloJohnReqStr + birthdayJsonStr, BirthdayGreeting.class);

//        birthdayEntryService.create(new BirthdayEntry("john","2000-01-01"));
//
//        requestURL = localHostURL + helloJohnReqStr;// + birthdayJsonStr;
//        ResponseEntity<String> response
//                = restTemplate.getForEntity(requestURL, String.class);
//        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

    }

}