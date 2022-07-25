package com.yuyuoped.gmall.pms.controller;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yuyuoped.gmall.pms.entity.CategoryBrandEntity;
import com.yuyuoped.gmall.pms.service.CategoryBrandService;
import com.yuyuoped.gmall.common.bean.PageResultVo;
import com.yuyuoped.gmall.common.bean.ResponseVo;
import com.yuyuoped.gmall.common.bean.PageParamVo;

/**
 * 品牌分类关联
 *
 * @author yuyuoped
 * @email yuyuoped999@gmail.com
 * @date 2022-07-22 23:28:14
 */
@Api(tags = "品牌分类关联 管理")
@RestController
@RequestMapping("pms/categorybrand")
public class CategoryBrandController {

    @Autowired
    private CategoryBrandService categoryBrandService;

    /**
     * 列表
     */
    @GetMapping
    @ApiOperation("分页查询")
    public ResponseVo<PageResultVo> queryCategoryBrandByPage(PageParamVo paramVo){
        PageResultVo pageResultVo = categoryBrandService.queryPage(paramVo);

        return ResponseVo.ok(pageResultVo);
    }


    /**
     * 信息
     */
    @GetMapping("{id}")
    @ApiOperation("详情查询")
    public ResponseVo<CategoryBrandEntity> queryCategoryBrandById(@PathVariable("id") Long id){
		CategoryBrandEntity categoryBrand = categoryBrandService.getById(id);

        return ResponseVo.ok(categoryBrand);
    }

    /**
     * 保存
     */
    @PostMapping
    @ApiOperation("保存")
    public ResponseVo<Object> save(@RequestBody CategoryBrandEntity categoryBrand){
		categoryBrandService.save(categoryBrand);

        return ResponseVo.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation("修改")
    public ResponseVo update(@RequestBody CategoryBrandEntity categoryBrand){
		categoryBrandService.updateById(categoryBrand);

        return ResponseVo.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation("删除")
    public ResponseVo delete(@RequestBody List<Long> ids){
		categoryBrandService.removeByIds(ids);

        return ResponseVo.ok();
    }

}