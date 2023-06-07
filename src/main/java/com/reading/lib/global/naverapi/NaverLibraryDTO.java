package com.reading.lib.global.naverapi;

import lombok.Data;

import java.util.List;

@Data
public class NaverLibraryDTO {

    private Integer total;
    private Integer display;
    private List<LibraryItems> items;
}
