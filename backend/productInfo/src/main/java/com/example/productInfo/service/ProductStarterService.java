package com.example.productInfo.service;

import com.example.productInfo.model.domain.ProductStarter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.productInfo.model.vo.ProductStarterVO;

import java.util.List;

/**
* @author 20891
* @description 针对表【productstarter(产品信息表-起动机)】的数据库操作Service
* @createDate 2024-07-08 15:51:02
*/
public interface ProductStarterService extends IService<ProductStarter> {

    /**
     * 增加产品
     *
     * @param productStarter
     * @return
     */
    boolean saveProduct(ProductStarterVO productStarter);

    /**
     * 更新产品（不包含参数表）
     *
     * @param productStarterVO
     * @return
     */
    boolean updateProduct(ProductStarterVO productStarterVO);
}
