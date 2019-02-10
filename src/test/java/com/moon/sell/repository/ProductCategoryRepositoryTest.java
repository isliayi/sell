package com.moon.sell.repository;

import com.moon.sell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository repository;

    @Test
    public void findOneTest() {
        ProductCategory productCategory=repository.findById(1).get();
        System.out.println(productCategory);
    }

    @Test
    public void saveTest(){
        ProductCategory productCategory=new ProductCategory("女生最爱",3);
        repository.save(productCategory);
    }

    @Test
    public void findByCategoryTypeInTest(){
        List<Integer> list= Arrays.asList(2,2,3);
        List<ProductCategory> result= repository.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0,result.size());
    }
}