package com.example.productInfo.service;

import com.example.productInfo.model.domain.Parameter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.productInfo.model.request.ParamAddRequest;

/**
* @author 20891
* @description 针对表【parameter(产品参数)】的数据库操作Service
* @createDate 2024-07-08 15:51:12
*/
public interface ParameterService extends IService<Parameter> {

    /**
     * 增加参数
     *
     * @param paramAddRequest 产品编号
     * @return
     */
    boolean addParam(ParamAddRequest paramAddRequest);

}
