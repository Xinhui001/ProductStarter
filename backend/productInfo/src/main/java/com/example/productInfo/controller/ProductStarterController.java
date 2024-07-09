package com.example.productInfo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.productInfo.common.BaseResponse;
import com.example.productInfo.common.ErrorCode;
import com.example.productInfo.common.ResultUtils;
import com.example.productInfo.exception.BusinessException;
import com.example.productInfo.model.domain.Car;
import com.example.productInfo.model.domain.Parameter;
import com.example.productInfo.model.domain.ProductStarter;
import com.example.productInfo.model.vo.ProductStarterVO;
import com.example.productInfo.service.CarService;
import com.example.productInfo.service.ParameterService;
import com.example.productInfo.service.ProductStarterService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 20891
 */
@RestController
@RequestMapping("/product")
@Slf4j
public class ProductStarterController {

    @Resource
    private ProductStarterService productStarterService;

    @Resource
    private CarService carService;

    @Resource
    private ParameterService parameterService;

    @PostMapping("/add")
    public BaseResponse<Boolean> addProduct(@RequestBody ProductStarterVO productStarter) {
        if (productStarter == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean saveResult = productStarterService.saveProduct(productStarter);
        if (!saveResult) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        return ResultUtils.success(true);
    }

    @GetMapping("/recommend")
    public BaseResponse<List<ProductStarterVO>> recommend() {
        List<ProductStarter> productStarters = productStarterService.list();
        List<ProductStarterVO> productStarterVOList = new ArrayList<>();
        productStarters.stream().forEach(productStarter -> {
            ProductStarterVO productStarterVO = new ProductStarterVO();
            //产品信息
            BeanUtils.copyProperties(productStarter, productStarterVO);
            //车型信息
            Integer applicationId = productStarter.getApplicationId();
            if (applicationId != null && applicationId > 0) {
                Car car = carService.getById(applicationId);
                if (car != null) {
                    BeanUtils.copyProperties(car, productStarterVO);
                }
            }
            //参数信息
            Integer parameterId = productStarter.getParameterId();
            if (parameterId != null && parameterId > 0) {
                Parameter parameter = parameterService.getById(parameterId);
                if (parameter != null) {
                    BeanUtils.copyProperties(parameter, productStarterVO);
                }
            }
            productStarterVOList.add(productStarterVO);
        });
        if (CollectionUtils.isEmpty(productStarterVOList)) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        return ResultUtils.success(productStarterVOList);
    }

    @PostMapping("/update")
    public BaseResponse<Boolean> update(@RequestBody ProductStarterVO productStarterVO) {
        if (productStarterVO == null || productStarterVO.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean result = productStarterService.updateProduct(productStarterVO);
        return ResultUtils.success(result);
    }
}
