package com.reading.lib.kakaoapi;

import com.reading.lib.ReadingApplication;
import com.reading.lib.global.naverapi.NaverLibraryDTO;
import com.reading.lib.global.naverapi.SearchFilter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@SpringBootTest(classes = ReadingApplication.class)
@DisplayName("Naver ConnectionAPI Test")
@EnableConfigurationProperties
public class LibraryConnetionAPITest {

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

    @Test
    public void getClientId() {
        Assertions.assertThat(id).isNotNull();
    }

    @Test
    public void getClientSecretKey() {
        Assertions.assertThat(secret).isNotNull();
    }

    /*
    query String	Y	검색어. UTF-8로 인코딩되어야 합니다.
    display	Integer	N	한 번에 표시할 검색 결과 개수(기본값: 10, 최댓값: 100)
    start	Integer	N	검색 시작 위치(기본값: 1, 최댓값: 1000)
    sort	String	N	검색 결과 정렬 방법
    - sim: 정확도순으로 내림차순 정렬(기본값)
    - date: 출간일순으로 내림차순 정렬
     */

    @Test
    void getLibrarysConnection() {
            RestTemplate restTemplate = new RestTemplate();
            URI uri = UriComponentsBuilder
                    .fromUriString(NAVER_API_URI)
                    .path(NAVER_API_PATH)
                    .queryParam(NAVER_API_QUERY, "자바")
                    .encode().build().toUri();

            ResponseEntity.BodyBuilder responseOk = setRequestHeader(uri, restTemplate).ok();

            Assertions.assertThat(responseOk).isNotNull();

        }

    @Test
    void getSearchLibrarys() {
        RestTemplate restTemplate = new RestTemplate();
        URI uri = UriComponentsBuilder
                .fromUriString(NAVER_API_URI)
                .path(NAVER_API_PATH)
                .queryParam(NAVER_API_QUERY, "자바")
                .encode().build().toUri();

        ResponseEntity<NaverLibraryDTO> response = setRequestHeader(uri, restTemplate);
        NaverLibraryDTO body = response.getBody();


        Assertions.assertThat(response).isNotNull();
    }

    @Test
    void getdisplayLibrarys() {

        RestTemplate restTemplate = new RestTemplate();
        URI uri = UriComponentsBuilder
                .fromUriString(NAVER_API_URI)
                .path(NAVER_API_PATH)
                .queryParam(NAVER_API_QUERY, "자바")
                .queryParam(NAVER_API_DISPLAY, NAVER_API_DISPLAY_SIZE)
                .encode().build().toUri();

        ResponseEntity<NaverLibraryDTO> response = setRequestHeader(uri, restTemplate);

        Assertions.assertThat(response.getBody().getDisplay()).isNotZero()
                .isEqualTo(20);
    }

    @Test
    void getSortLibrarys() {
        RestTemplate restTemplate = new RestTemplate();
        URI uri = UriComponentsBuilder
                .fromUriString(NAVER_API_URI)
                .path(NAVER_API_PATH)
                .queryParam(NAVER_API_QUERY, "자바")
                .queryParam("sort", "sim")
                .encode().build().toUri();

        ResponseEntity<NaverLibraryDTO> response = setRequestHeader(uri, restTemplate);
        Assertions.assertThat(response).isNotNull();
    }

    @Test
    void getSearchTitleLibrarys() {
        RestTemplate restTemplate = new RestTemplate();
        URI uri = UriComponentsBuilder
                .fromUriString(NAVER_API_URI)
                .path(NAVER_API_PATH)
                .queryParam(NAVER_API_QUERY, "자바")
                .queryParam("d_titl", "젊은 근희의 행진")
                .encode().build().toUri();

        ResponseEntity<NaverLibraryDTO> response = setRequestHeader(uri, restTemplate);
        Assertions.assertThat(response).isNotNull();
    }

}