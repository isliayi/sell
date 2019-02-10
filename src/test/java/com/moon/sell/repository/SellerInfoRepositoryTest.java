package com.moon.sell.repository;

import com.moon.sell.dataobject.SellerInfo;
import com.moon.sell.utils.KeyUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoRepositoryTest {

    @Autowired
    private SellerInfoRepository repository;

    @Test
    public void save(){
        SellerInfo sellerInfo=new SellerInfo();
        sellerInfo.setSellerId(KeyUtil.genUniqueKey());
        sellerInfo.setUsername("admin");
        sellerInfo.setPassword("admin");
        sellerInfo.setOpenid("abc");

        SellerInfo result=repository.save(sellerInfo);
        Assert.assertNotNull(result);
    }

    @Test
    public void findBuOpenid(){
            SellerInfo sellerInfo=repository.findByOpenid("abc");
            Assert.assertEquals("abc",sellerInfo.getOpenid());
    }
}