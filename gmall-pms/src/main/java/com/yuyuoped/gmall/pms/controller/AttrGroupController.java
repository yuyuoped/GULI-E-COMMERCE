package com.yuyuoped.gmall.pms.controller;

import java.util.Collection;
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

import com.yuyuoped.gmall.pms.entity.AttrGroupEntity;
import com.yuyuoped.gmall.pms.service.AttrGroupService;
import com.yuyuoped.gmall.common.bean.PageResultVo;
import com.yuyuoped.gmall.common.bean.ResponseVo;
import com.yuyuoped.gmall.common.bean.PageParamVo;

/**
 * 属性分组
 *
 * @author yuyuoped
 * @email yuyuoped999@gmail.com
 * @date 2022-07-22 23:28:14
 */
@Api(tags = "属性分组 管理")
@RestController
@RequestMapping("pms/attrgroup")
public class AttrGroupController {

    @Autowired
    private AttrGroupService attrGroupService;

    /**
     * 列表
     */
    @GetMapping
    @ApiOperation("分页查询")
    public ResponseVo<PageResultVo> queryAttrGroupByPage(PageParamVo paramVo){
        PageResultVo pageResultVo = attrGroupService.queryPage(paramVo);

        return ResponseVo.ok(pageResultVo);
    }

    /**
     * 信息
     */
    @GetMapping("category/{cId}")
    @ApiOperation("详情查询")
    public ResponseVo<List<AttrGroupEntity>> queryAttrGroupByCategoryId(@PathVariable("cId") Long cId){
        List<AttrGroupEntity> attrGroups = attrGroupService.getByCategoryId(cId);

        return ResponseVo.ok(attrGroups);
    }

    /**
     * 信息
     */
    @GetMapping("withattrs/{categoryId}")
    @ApiOperation("查询对应spu的属性信息")
    public ResponseVo<Collection<AttrGroupEntity>> queryAttrsByCategoryId(@PathVariable("categoryId") Long categoryId){
        Collection<AttrGroupEntity> attrGroups = attrGroupService.queryAttrsByCategoryId(categoryId);

        return ResponseVo.ok(attrGroups);
    }

    /**
     * 信息
     */
    @GetMapping("{id}")
    @ApiOperation("详情查询")
    public ResponseVo<AttrGroupEntity> queryAttrGroupById(@PathVariable("id") Long id){
		AttrGroupEntity attrGroup = attrGroupService.getById(id);

        return ResponseVo.ok(attrGroup);
    }

    /**
     * 保存
     */
    @PostMapping
    @ApiOperation("保存")
    public ResponseVo<Object> save(@RequestBody AttrGroupEntity attrGroup){
		attrGroupService.save(attrGroup);

        return ResponseVo.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation("修改")
    public ResponseVo update(@RequestBody AttrGroupEntity attrGroup){
		attrGroupService.updateById(attrGroup);

        return ResponseVo.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation("删除")
    public ResponseVo delete(@RequestBody List<Long> ids){
		attrGroupService.removeByIds(ids);

        return ResponseVo.ok();
    }

}
