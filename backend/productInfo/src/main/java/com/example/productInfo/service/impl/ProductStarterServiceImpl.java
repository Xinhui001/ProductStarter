package com.example.productInfo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.productInfo.common.ErrorCode;
import com.example.productInfo.exception.BusinessException;
import com.example.productInfo.mapper.CarMapper;
import com.example.productInfo.model.domain.Car;
import com.example.productInfo.model.domain.ProductStarter;
import com.example.productInfo.model.enums.Quality;
import com.example.productInfo.model.vo.ProductStarterVO;
import com.example.productInfo.service.CarService;
import com.example.productInfo.service.ProductStarterService;
import com.example.productInfo.mapper.ProductStarterMapper;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
* @author 20891
* @description 针对表【productstarter(产品信息表-起动机)】的数据库操作Service实现
* @createDate 2024-07-08 15:51:02
*/
@Service
public class ProductStarterServiceImpl extends ServiceImpl<ProductStarterMapper, ProductStarter>
    implements ProductStarterService {

    @Resource
    private ProductStarterMapper productStarterMapper;

    @Resource
    private CarMapper carMapper;

    @Resource
    private CarService carService;

    /**
     * 增加产品
     *
     * @param productStarter
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveProduct(ProductStarterVO productStarter) {
        //参数不为空
        if (productStarter == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        //产品编号不为空
        if (productStarter.getProductNum() == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        //产品编号不能重复
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("productNum", productStarter.getProductNum());
        long count1 = this.count(queryWrapper);
        if (count1 > 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"产品编号重复");
        }
        //产品性质
        Integer qualityEnum = Optional.ofNullable(productStarter.getQuality()).orElse(2);
        Quality enumByValue = Quality.getEnumByValue(qualityEnum);
        if (enumByValue == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        ProductStarter productStarter1 = new ProductStarter();
        //车型名称不为空的话
        if (StringUtils.isNotBlank(productStarter.getCarName())) {
            //查看数据库车型表中是否存在该车型名称（存在的话直接把数据库的赋给）
            QueryWrapper queryWrapperCar = new QueryWrapper();
            queryWrapperCar.eq("carName", productStarter.getCarName());
            Car car = carService.getOne(queryWrapperCar);
            if (car != null) {
                BeanUtils.copyProperties(car, productStarter);
                //存入起动机表
                BeanUtils.copyProperties(productStarter, productStarter1);
                boolean saveResult = this.save(productStarter1);
                if (!saveResult) {
                    throw new BusinessException(ErrorCode.SYSTEM_ERROR);
                }
                return true;
            }
            //不存在的话要在表里新建该车型
            Car car1 = new Car();
            BeanUtils.copyProperties(productStarter, car1);
            //车型名称不为空
            if (car1 != null) {
                //车型名称不能重复
                queryWrapperCar.eq("carName", car1.getCarName());
                long count = carService.count(queryWrapperCar);
                if (count > 0) {
                    throw new BusinessException(ErrorCode.PARAMS_ERROR,"车型名称重复");
                }
                carMapper.insert(car1);
            }
        }
        //存入起动机表
        BeanUtils.copyProperties(productStarter, productStarter1);
        boolean saveResult = this.save(productStarter1);
        if (!saveResult) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        return true;
    }

    /**
     * 更新产品（不包含参数表）
     *
     * @param productStarterVO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateProduct(ProductStarterVO productStarterVO) {
        if (StringUtils.isBlank(productStarterVO.getProductNum())) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper<ProductStarter> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("productNum", productStarterVO.getProductNum());
        long count = this.count(queryWrapper);
        //要更新的产品要存在
        if (count < 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        //更新car表
        //car表内是否存在对应信息
        String carName = productStarterVO.getCarName();
        Car car = new Car();
        if (StringUtils.isNotBlank(carName)) {
            QueryWrapper<Car> queryWrapperCar = new QueryWrapper<>();
            queryWrapperCar.eq("carName", carName);
            car = carService.getOne(queryWrapperCar);
            //car表内没有对应信息，新建信息
            if (car == null || car.getApplicationId() <= 0) {
                car = new Car();
                BeanUtils.copyProperties(productStarterVO, car);
                carService.save(car);
            }
            BeanUtils.copyProperties(productStarterVO, car);
            carService.updateById(car);
        }
        ProductStarter productStarter = new ProductStarter();
        BeanUtils.copyProperties(productStarterVO, productStarter);
        //applicationId同步到产品表
        Integer applicationId = car.getApplicationId();
        productStarter.setApplicationId(applicationId);
        boolean updateResult = this.updateById(productStarter);
        if (!updateResult) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        return true;
    }

}




