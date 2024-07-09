package com.example.productInfo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.productInfo.model.domain.Car;
import com.example.productInfo.service.CarService;
import com.example.productInfo.mapper.CarMapper;
import org.springframework.stereotype.Service;

/**
* @author 20891
* @description 针对表【car(车型信息)】的数据库操作Service实现
* @createDate 2024-07-08 15:51:17
*/
@Service
public class CarServiceImpl extends ServiceImpl<CarMapper, Car>
    implements CarService{

}




