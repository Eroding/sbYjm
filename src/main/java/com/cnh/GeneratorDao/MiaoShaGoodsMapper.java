package com.cnh.GeneratorDao;

import com.cnh.dataobject.MiaoShaGoods;

public interface MiaoShaGoodsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table miaosha_goods
     *
     * @mbggenerated Sat Aug 03 09:01:16 CST 2019
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table miaosha_goods
     *
     * @mbggenerated Sat Aug 03 09:01:16 CST 2019
     */
    int insert(MiaoShaGoods record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table miaosha_goods
     *
     * @mbggenerated Sat Aug 03 09:01:16 CST 2019
     */
    int insertSelective(MiaoShaGoods record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table miaosha_goods
     *
     * @mbggenerated Sat Aug 03 09:01:16 CST 2019
     */
    MiaoShaGoods selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table miaosha_goods
     *
     * @mbggenerated Sat Aug 03 09:01:16 CST 2019
     */
    int updateByPrimaryKeySelective(MiaoShaGoods record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table miaosha_goods
     *
     * @mbggenerated Sat Aug 03 09:01:16 CST 2019
     */
    int updateByPrimaryKey(MiaoShaGoods record);
}