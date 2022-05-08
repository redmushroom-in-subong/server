package com.rms.drifeserver.domain.common.util.kakao;

import com.rms.drifeserver.domain.common.exception.BaseException;
import com.rms.drifeserver.domain.common.exception.type.ErrorCode;
import com.rms.drifeserver.domain.common.util.geolocation.KakaoRegionResponse;
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
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.rms.drifeserver.domain.common.exception.type.ErrorCode.*;
import static java.lang.Long.parseLong;
@Component
public class SearchPlaceByKey {
    private static String restApiKey="KakaoAK 16891297e4d22f1b77bfd94aff2f528e";
    private static String requestUrl="http://dapi.kakao.com/v2/local/search/keyword.json";

    //TODO @Value 안에 있는 값 환경변수 저장하는 파일에 분리
    @Value("KakaoAK 16891297e4d22f1b77bfd94aff2f528e")
    public void setRestApiKey(String value){
        restApiKey=value;
    }

    @Value("http://dapi.kakao.com/v2/local/search/keyword.json")
    public void setRequestUrl(String value) {
        requestUrl=value;
    }
    public static List<Long> searchPlaceByKeyWord(PlaceSearchRequest sReq) throws ParseException,Exception, JSONException {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", restApiKey);
        headers.set("header2", "header2");
        headers.set("charset", "utf-8");

        if(sReq.getQuery().isEmpty()){
            throw new BaseException(INVALID);
        }
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(requestUrl)
                .queryParam("query",sReq.getQuery());

        if(sReq.getCategory()!=null){
            uriBuilder.queryParam("category",sReq.getCategory());
        }

        if(sReq.getSort()!=null){
            uriBuilder.queryParam("sort",sReq.getSort());
        }

        if((sReq.getX()!=null)&&(sReq.getY()!=null)&&(sReq.getRadius()!=null)) {
            uriBuilder.queryParam("x", sReq.getX())
                    .queryParam("y", sReq.getY())
                    .queryParam("radius", sReq.getRadius());
        }
        else if(!(sReq.getX()==null&&sReq.getY()==null&&(sReq.getRadius()==null))){
                throw new BaseException(INVALID);
        }else if(sReq.getSort()!=null&&sReq.getSort().equals("distance")){
                throw new BaseException(INVALID);
        }
        System.out.println(sReq.toString()+uriBuilder.toUriString());



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

        System.out.println("response = " + jsonObject.toJSONString());
        List<Long> ret = new ArrayList<>();
        for (Object jObj : jsonArray) {
            JSONObject temp = (JSONObject) jObj;
            ret.add(parseLong(temp.get("id").toString()));
        }
        System.out.println("ret.toString() = " + ret.toString());
        return ret;
    }

    public static void main(String[] args) {
        PlaceSearchRequest ps=new PlaceSearchRequest("인하칼국수","FD6");
        try{
            searchPlaceByKeyWord(ps);
        }catch (BaseException | ParseException | JSONException  e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
