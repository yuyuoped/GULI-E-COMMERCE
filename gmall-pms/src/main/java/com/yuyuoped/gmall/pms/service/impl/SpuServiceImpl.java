package com.yuyuoped.gmall.pms.service.impl;

import com.yuyuoped.gmall.pms.entity.*;
import com.yuyuoped.gmall.pms.feign.GmallSmsClient;
import com.yuyuoped.gmall.pms.mapper.SkuMapper;
import com.yuyuoped.gmall.pms.mapper.SpuAttrValueMapper;
import com.yuyuoped.gmall.pms.mapper.SpuDescMapper;
import com.yuyuoped.gmall.pms.service.*;
import com.yuyuoped.gmall.pms.vo.SkuVo;
import com.yuyuoped.gmall.pms.vo.SpuAttrValueVo;
import com.yuyuoped.gmall.pms.vo.SpuVo;
import com.yuyuoped.gmall.sms.vo.SkuSalesVo;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuyuoped.gmall.common.bean.PageResultVo;
import com.yuyuoped.gmall.common.bean.PageParamVo;

import com.yuyuoped.gmall.pms.mapper.SpuMapper;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;


@Service("spuService")
public class SpuServiceImpl extends ServiceImpl<SpuMapper, SpuEntity> implements SpuService {

    @Autowired
    private SpuDescMapper spuDescMapper;
    @Autowired
    private SpuAttrValueService spuAttrValueService;
    @Autowired
    private SkuMapper skuMapper;
    @Autowired
    private SkuImagesService skuImagesService;
    @Autowired
    private SkuAttrValueService skuAttrValueService;
    @Autowired
    private GmallSmsClient gmallSmsClient;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public PageResultVo queryPage(PageParamVo paramVo) {
        IPage<SpuEntity> page = this.page(
                paramVo.getPage(),
                new QueryWrapper<SpuEntity>()
        );

        return new PageResultVo(page);
    }

    @Override
    public PageResultVo queryPageByCategoryId(Long cId, PageParamVo paramVo) {
        QueryWrapper<SpuEntity> wrapper = new QueryWrapper<>();
        if (cId != 0) {
            wrapper.eq("category_id", cId);
        }

        if (!StringUtils.isEmpty(paramVo.getKey())) {
            wrapper.and(w -> w.like("name", paramVo.getKey())
                    .or()
                    .like("id", paramVo.getKey()));
        }

        IPage<SpuEntity> page = this.page(
                paramVo.getPage(),
                wrapper
        );

        return new PageResultVo(page);
    }

    @GlobalTransactional(rollbackFor = Exception.class)
    @Override
    public void bigSave(SpuVo spuVo) {
        /// 1.保存spu相关
        // 1.1. 保存spu基本信息 spu
        spuVo.setCreateTime(new Date());
        spuVo.setUpdateTime(spuVo.getCreateTime());
        this.save(spuVo);

        Long spuId = spuVo.getId();

        // 1.2. 保存spu的描述信息 spu_desc
        // 注意：spu_desc表的主键是spu_id,需要在实体类中配置该主键不是自增主键
        SpuDescEntity spuDescEntity = new SpuDescEntity();
        spuDescEntity.setSpuId(spuId);
        // 把商品的图片描述，保存到spu详情中，图片地址以逗号进行分割
        spuDescEntity.setDecript(org.apache.commons.lang3.StringUtils.join(spuVo.getSpuImages(), ","));
        spuDescMapper.insert(spuDescEntity);

        // 1.3. 保存spu的规格参数信息
        List<SpuAttrValueVo> baseAttrs = spuVo.getBaseAttrs();
        if (!CollectionUtils.isEmpty(baseAttrs)) {
            List<SpuAttrValueEntity> spuAttrValueEntities = baseAttrs.stream().peek(baseAttr -> {
                baseAttr.setSort(0);
                baseAttr.setSpuId(spuId);
            }).collect(Collectors.toList());

            spuAttrValueService.saveBatch(spuAttrValueEntities);
        }


        /// 2. 保存sku相关信息
        List<SkuVo> skus = spuVo.getSkus();

        if (!CollectionUtils.isEmpty(skus)) {
            // 2.1. 保存sku基本信息
            skus.forEach(skuVo -> {
                skuVo.setSpuId(spuId);
                skuVo.setBrandId(spuVo.getBrandId());
                skuVo.setCategoryId(spuVo.getCategoryId());

                List<String> images = skuVo.getImages();
                if (!CollectionUtils.isEmpty(images)) {
                    skuVo.setDefaultImage(images.get(0));
                }
                skuMapper.insert(skuVo);

                Long skuId = skuVo.getId();

                // 2.2. 保存sku图片信息
                if (!CollectionUtils.isEmpty(images)) {
                    String defaultImage = images.get(0);
                    List<SkuImagesEntity> skuImagesEntityList = images.stream().map(image -> {
                        SkuImagesEntity skuImagesEntity = new SkuImagesEntity();
                        skuImagesEntity.setSkuId(skuId);
                        skuImagesEntity.setSort(0);
                        skuImagesEntity.setDefaultStatus(defaultImage.equals(image) ? 1 : 0);
                        skuImagesEntity.setUrl(image);
                        return skuImagesEntity;
                    }).collect(Collectors.toList());
                    skuImagesService.saveBatch(skuImagesEntityList);
                }

                // 2.3. 保存sku的规格参数（销售属性）
                List<SkuAttrValueEntity> saleAttrs = skuVo.getSaleAttrs();
                saleAttrs.forEach(skuAttrValueEntity -> {
                    skuAttrValueEntity.setSkuId(skuId);
                    skuAttrValueEntity.setSort(0);
                });
                skuAttrValueService.saveBatch(saleAttrs);

                // 3. 保存营销相关信息，需要远程调用gmall-sms
                // 3.1. 积分优惠
                // 3.2. 满减优惠
                // 3.3. 数量折扣
                SkuSalesVo skuSalesVo = new SkuSalesVo();
                BeanUtils.copyProperties(skuVo, skuSalesVo);
                skuSalesVo.setSkuId(skuId);
                gmallSmsClient.saveSales(skuSalesVo);
            });
        }

        rabbitTemplate.convertAndSend("PMS_SPU_EXCHANGE", "item.insert", spuId);
    }

}