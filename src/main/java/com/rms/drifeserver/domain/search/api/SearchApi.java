package com.rms.drifeserver.domain.search.api;

import com.rms.drifeserver.domain.common.dto.ApiResponse;
import com.rms.drifeserver.domain.common.util.kakao.PlaceSearchRequest;
import com.rms.drifeserver.domain.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.rms.drifeserver.domain.common.dto.ApiResponse.success;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/local")
public class SearchApi {
    final private SearchService searchService;
    @GetMapping("/search")
    @ResponseBody
    public ApiResponse searchByKeyword(@RequestParam String query,
                                       @RequestParam(required = false) String category,
                                       @RequestParam(required = false) String x, @RequestParam(required = false) String y,
                                       @RequestParam(required = false) Integer radius,
                                       @RequestParam(required = false) String sort) throws Exception {
        return success(searchService.searchByKeyword(new PlaceSearchRequest (query,category,x,y,radius,sort)));
    }
/**
 * rivate String query;
 *     private String category;
 *     private String x;
 *     private String y;
 *     private Integer radius;
 *     private String sort;
 */
}
