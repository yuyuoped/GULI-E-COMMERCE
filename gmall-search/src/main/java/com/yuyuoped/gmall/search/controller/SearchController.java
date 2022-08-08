package com.yuyuoped.gmall.search.controller;

import com.yuyuoped.gmall.search.pojo.SearchParamVo;
import com.yuyuoped.gmall.search.pojo.SearchResponseVo;
import com.yuyuoped.gmall.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//@RestController
@Controller
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping("search")
    public String search(SearchParamVo paramVo, Model model){

        SearchResponseVo responseVo = this.searchService.search(paramVo);

        model.addAttribute("response", responseVo);
        model.addAttribute("searchParam", paramVo);
        return "search";
    }
}
