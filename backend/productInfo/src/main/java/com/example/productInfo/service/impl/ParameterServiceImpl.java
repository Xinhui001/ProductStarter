package com.example.productInfo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.productInfo.common.ErrorCode;
import com.example.productInfo.exception.BusinessException;
import com.example.productInfo.model.domain.Parameter;
import com.example.productInfo.model.domain.ProductStarter;
import com.example.productInfo.model.request.ParamAddRequest;
import com.example.productInfo.service.ParameterService;
import com.example.productInfo.mapper.ParameterMapper;
import com.example.productInfo.service.ProductStarterService;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author 20891
* @description 针对表【parameter(产品参数)】的数据库操作Service实现
* @createDate 2024-07-08 15:51:12
*/
@Service
public class ParameterServiceImpl extends ServiceImpl<ParameterMapper, Parameter>
    implements ParameterService{

    @Resource
    private ProductStarterService productStarterService;

    /**
     * 增加参数
     *
     * @param paramAddRequest 产品编号
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addParam(ParamAddRequest paramAddRequest) {
        //产品编号校验
        if (StringUtils.isBlank(paramAddRequest.getProductNum())) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        //产品是否存在
        QueryWrapper<ProductStarter> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("productNum", paramAddRequest.getProductNum());
        ProductStarter productStarter = productStarterService.getOne(queryWrapper);
        if (productStarter == null || productStarter.getId() < 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"产品不存在");
        }
        //电压功率不能为空
        if (StringUtils.isAnyBlank(paramAddRequest.getPower(), paramAddRequest.getVoltage())) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"电压、功率不能为空");
        }
        //产品参数与产品表唯一对应  productStarterId在表内唯一
        QueryWrapper<Parameter> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("productStarterId", productStarter.getId());
        Parameter parameterHad = this.getOne(queryWrapper1);
        //参数表里有对应的productStarterId，那就执行更新操作
        if (parameterHad != null) {
            BeanUtils.copyProperties(paramAddRequest, parameterHad);
            this.updateById(parameterHad);
            //参数表id存入产品表
            productStarter.setParameterId(parameterHad.getParameterId());
            boolean updateResult = productStarterService.updateById(productStarter);
            if (!updateResult) {
                throw new BusinessException(ErrorCode.SYSTEM_ERROR);
            }
            return true;
        }
        //参数存入参数表
        parameterHad = new Parameter();
        BeanUtils.copyProperties(paramAddRequest, parameterHad);
        parameterHad.setProductStarterId(productStarter.getId());
        boolean saveParamResult = this.save(parameterHad);
        if (!saveParamResult) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        //参数表id存入产品表
        productStarter.setParameterId(parameterHad.getParameterId());
        boolean updateResult = productStarterService.updateById(productStarter);
        if (!updateResult) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        return true;
    }
}




