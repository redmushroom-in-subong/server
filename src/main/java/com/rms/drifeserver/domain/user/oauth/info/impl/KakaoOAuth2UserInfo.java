package com.rms.drifeserver.domain.user.oauth.info.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rms.drifeserver.domain.user.oauth.info.OAuth2UserInfo;

import java.util.Map;

public class KakaoOAuth2UserInfo extends OAuth2UserInfo {

    public KakaoOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        return attributes.get("id").toString();
    }

    @Override
    public String getName() {
        Map<String, Object> properties = (Map<String, Object>) attributes.get("properties");
        if (properties == null) {
            return null;
        }

        return (String) properties.get("nickname");
    }

    @Override
    public String getEmail() {
        Map<String,String> kakaoAccount=(Map<String, String>)attributes.get("kakao_account");
        return (String) kakaoAccount.get("email");
    }

    @Override
    public String getImageUrl() {
        Map<String,Object> kakaoAccount=(Map<String,Object>)attributes.get("kakao_account");

        if (kakaoAccount == null) {
            return null;
        }
        Map<String,String> profile=(Map<String, String>)kakaoAccount.get("profile");
        if (profile == null) {
            return null;
        }

        return (String) profile.get("thumbnail_image_url");
    }
}
