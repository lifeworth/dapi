package com.duzy.converter;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.duzy.dto.CustomerDto;
import com.duzy.model.CustomerModel;
import com.duzy.vo.CustomerVo;

import java.util.List;


/**
 * @author zhiyuandu
 */
public interface CustomerConverter<D extends CustomerDto, M extends CustomerModel, V extends CustomerVo> {
    /**
     * dto 转换成model
     * @param d dto
     * @return model
     */

    M dto2Model(D d);

    /**
     * model 转换成vo
     * @param m model
     * @return vo
     */
    V model2vo(M m);

    /**
     * model 转换成vo
     * @param mList modelList
     * @return voList
     */
    List<V> model2voList(List<M> mList);

    /**
     * model的分页转为vo的分页
     * @param modelPage model的分页
     * @return vo的分页
     */
    default Page<V> modelPage2ListPage(Page<M> modelPage) {
        Page<V> result = new Page<>();
        BeanUtil.copyProperties(modelPage, result, "record");
        List<M> records = modelPage.getRecords();
        List<V> voList = model2voList(records);
        result.setRecords(voList);
        return result;
    }
}
