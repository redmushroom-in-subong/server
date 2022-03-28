package com.rms.drifeserver.domain.common.util.geolocation;

import lombok.RequiredArgsConstructor;
import org.hibernate.cfg.Environment;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;


@Component
public class KakaoGeoLocationApi {
    private static String restApiKey;
    private static String requestUrl;

    //TODO @Value 안에 있는 값 환경변수 저장하는 파일에 분리

    @Value("KakaoAK 16891297e4d22f1b77bfd94aff2f528e")
    public void setRestApiKey(String value){
        restApiKey=value;
    }

    @Value("http://dapi.kakao.com/v2/local/geo/coord2regioncode")
    public void setRequestUrl(String value) {
        requestUrl=value;
    }
    public static KakaoRegionResponse getRegionCodeByGeoLocation(String xCor,String yCor) throws ParseException, JSONException {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", restApiKey);
        headers.set("header2", "header2");
        Map<String, String> params = new HashMap<>();
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(requestUrl)
                .queryParam("x",xCor)
                .queryParam("y",yCor);

        System.out.println("requestUrl = " + uriBuilder.toUriString());
       
        HttpEntity request = new HttpEntity(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response=restTemplate.exchange(
                uriBuilder.toUriString(),
                HttpMethod.GET,
                request,
                String.class
        );

        JSONParser jsonParser=new JSONParser();
        JSONObject jsonObject=(JSONObject) jsonParser.parse(response.getBody());

        JSONArray jsonArray=(JSONArray) jsonObject.get("documents");
        JSONObject atFirst = (JSONObject) jsonArray.get(0);

        System.out.println("response = " + jsonObject.toJSONString());

        return new KakaoRegionResponse(
                atFirst.get("code").toString(),
                atFirst.get("address_name").toString()
        );
    }
}
