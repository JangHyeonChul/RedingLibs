package com.reading.lib.domain.login.api;


import com.reading.lib.global.naverapi.NaverLibraryAPI;
import com.reading.lib.global.naverapi.NaverLibraryDTO;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
public class Test {

    private NaverLibraryAPI naverLibraryAPI;

    public Test(NaverLibraryAPI naverLibraryAPI) {
        this.naverLibraryAPI = naverLibraryAPI;
    }

    @GetMapping("/test")
    public NaverLibraryDTO test() {
        return naverLibraryAPI.getSearchLibrarys("자바");

    }
}
