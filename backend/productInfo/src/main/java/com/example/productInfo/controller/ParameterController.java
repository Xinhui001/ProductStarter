package com.example.productInfo.controller;

import com.example.productInfo.common.BaseResponse;
import com.example.productInfo.common.ErrorCode;
import com.example.productInfo.common.ResultUtils;
import com.example.productInfo.exception.BusinessException;
import com.example.productInfo.model.request.ParamAddRequest;
import com.example.productInfo.service.ParameterService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 20891
 */
@RestController
@RequestMapping("/parameter")
@Slf4j
public class ParameterController {

    @Resource
    private ParameterService parameterService;

    @PostMapping("/addParam")
    public BaseResponse<Boolean> addParam(@RequestBody ParamAddRequest paramAddRequest) {
        if (paramAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if (StringUtils.isEmpty(paramAddRequest.getProductNum())) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        return ResultUtils.success(parameterService.addParam(paramAddRequest));
    }

}
