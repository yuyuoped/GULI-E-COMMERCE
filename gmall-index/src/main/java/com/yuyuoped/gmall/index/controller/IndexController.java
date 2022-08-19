package com.yuyuoped.gmall.index.controller;

import com.yuyuoped.gmall.common.bean.ResponseVo;
import com.yuyuoped.gmall.index.feign.GmallPmsClient;
import com.yuyuoped.gmall.pms.entity.CategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private GmallPmsClient gmallPmsClient;

    @GetMapping("/**")
    public String index(Model model) {
        //pid为0表示为一级目录
        List<CategoryEntity> categoryEntities = gmallPmsClient.queryCategoriesByPid(0L).getData();
        model.addAttribute("categories", categoryEntities);

        //TODO：广告等业务

        return "index";
    }

    @GetMapping("/index/cates/{pid}")
    @ResponseBody
    public ResponseVo<List<CategoryEntity>> getLevel23ByPID(@PathVariable("pid") Long pid) {
        List<CategoryEntity> categoryEntities = gmallPmsClient.queryLevel23ByParentId(pid).getData();

        return ResponseVo.ok(categoryEntities);
    }

}
