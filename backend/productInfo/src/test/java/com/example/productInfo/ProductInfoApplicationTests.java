package com.example.productInfo;
import java.util.Date;

import com.example.productInfo.mapper.ParameterMapper;
import com.example.productInfo.mapper.ProductStarterMapper;
import com.example.productInfo.model.domain.Parameter;
import com.example.productInfo.model.domain.ProductStarter;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductInfoApplicationTests {

	@Resource
	private ProductStarterMapper productstarterMapper;

	@Resource
	private ParameterMapper parameterMapper;

	@Test
	void contextLoads() {
	}

	@Test
	void insert(){
		ProductStarter productstarter = new ProductStarter();
		productstarter.setProductNum("555555555");
		productstarter.setShowPic("66666666");
		productstarter.setShowAll("666666");
		productstarter.setSocketPic("6666");
		productstarter.setDataPic("6666666");
		productstarter.setQuality(0);
		productstarter.setSerialNum(66666666);
		productstarter.setSeriesNum(666666);
		productstarter.setArea("66666666");
		productstarter.setCreateTime(new Date());
		productstarter.setUpdateTime(new Date());



		productstarterMapper.insert(productstarter);
	}

	@Test
	void insertParam() {
		Parameter parameter = new Parameter();
		parameter.setVoltage("");
		parameter.setPower("");
		parameter.setFunctionInfo("");
		parameter.setSizeA(0);
		parameter.setSizeB(0);
		parameter.setOD1(0);
		parameter.setD1(0);
		parameter.setD2(0);
		parameter.setD3(0);
		parameter.setD4(0);
		parameter.setL1(0);
		parameter.setCreateTime(new Date());
		parameter.setUpdateTime(new Date());

		parameterMapper.insert(parameter);
	}
}
