package com.mmall.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mmall.common.ServerResponse;
import com.mmall.dao.CategoryMapper;
import com.mmall.pojo.Category;
import com.mmall.service.ICategoryService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.ListIterator;
import java.util.Set;

/**
 * Created by yimingfan on 9/9/18
 */

@Service("iCategoryService")
public class CategoryServiceImpl implements ICategoryService {


    private Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);
    @Autowired
    private CategoryMapper categoryMapper;

    public ServerResponse addCategory(String categoryName, Integer parentId){

        if(parentId == null || StringUtils.isBlank(categoryName)){
            return ServerResponse.createByErrorMessage("add category value error");
        }

        Category category = new Category();
        category.setName(categoryName);
        category.setParentId(parentId);
        category.setStatus(true); // if true, this category is being used.

        int rowCount = categoryMapper.insert(category);
        if(rowCount>0){
            return ServerResponse.createBySuccessMessage("add category success");
        }
        return ServerResponse.createByErrorMessage("add category error");
    }

    public ServerResponse updateCategoryName(String categoryName, Integer categoryId){
        if(StringUtils.isBlank(categoryName) || categoryId == null){
            return ServerResponse.createByErrorMessage("update category name failed");
        }
        Category category = new Category();
        category.setId(categoryId);
        category.setName(categoryName);
        int rowCount = categoryMapper.updateByPrimaryKeySelective(category);
        if(rowCount > 0 ){
            return ServerResponse.createBySuccessMessage("update category name success.");
        }
        return ServerResponse.createByErrorMessage("update category name failed.");
    }

    /**
     * 指查询当前节点下的子节点
     * @param categoryId
     * @return
     */
    public ServerResponse<List<Category>> checkChildrenParallelCategory(Integer categoryId){

        List<Category> categories = categoryMapper.selectChildrenCategoryByParentId(categoryId);
        if(CollectionUtils.isEmpty(categories)){
            logger.info("No subcategory result for current Category");
        }
        return  ServerResponse.createBySuccess(categories);
    }

    /**
     * 查询当前节点id
     * 以及所有子节点 包括子节点的子节点 的 id
     * @param categoryId
     * @return
     */
    public ServerResponse<List<Integer>> selectCategoryAndChildrenById(Integer categoryId){
        Set<Category> categorySet = Sets.newHashSet();
        findChildCategory(categorySet,categoryId);
        List<Integer> categoryIdList = Lists.newArrayList();
        if(categoryId!=null){
            for(Category categoryItem : categorySet){
                categoryIdList.add(categoryItem.getId());
            }
        }
        return ServerResponse.createBySuccess(categoryIdList);
    }

    //递归算法 算出子节点
    private Set<Category> findChildCategory(Set<Category> categorySet, Integer categoryId){
        Category category = categoryMapper.selectByPrimaryKey(categoryId);
        if(category != null){
            categorySet.add(category);
        }
        //查找子节点 递归算法必须要有一个退出条件
        List<Category> categoryList =categoryMapper.selectChildrenCategoryByParentId(categoryId);
        //这里无需判断是否为空 mybatis自动返回非空对象
        for(Category categoryItem : categoryList){
            findChildCategory(categorySet,categoryItem.getId());
        }
        return categorySet;
    }
}
