package com.reading.lib.global.naverapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@Component
public class NaverLibraryAPI {

    @Value("${naver.client.id}")
    private String id;

    @Value("${naver.client.secret}")
    private String secret;


    private final String NAVER_API_URI = "https://openapi.naver.com";
    private final String NAVER_API_PATH = "/v1/search/book.json";
    private final String NAVER_API_QUERY = "query";

    private final String NAVER_API_DISPLAY = "display";
    private final Integer NAVER_API_DISPLAY_SIZE = 20;

    private ResponseEntity<NaverLibraryDTO> setRequestHeader(URI uri, RestTemplate restTemplate) {
        RequestEntity<Void> req = RequestEntity
                .get(uri)
                .header("X-Naver-Client-Id", id)
                .header("X-Naver-Client-Secret", secret)
                .build();

        return restTemplate.exchange(req, NaverLibraryDTO.class);
    }

    public NaverLibraryDTO getSearchLibrarys(String keyword) {
        RestTemplate restTemplate = new RestTemplate();
        URI uri = UriComponentsBuilder
                .fromUriString(NAVER_API_URI)
                .path(NAVER_API_PATH)
                .queryParam(NAVER_API_QUERY, keyword)
                .encode().build().toUri();

        return setRequestHeader(uri, restTemplate).getBody();

    }


}
