package com.cnh.dto;

import lombok.Data;

import java.util.List;

@Data
public class PhoneWithCommentDto {
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column phone.name
     *
     * @mbggenerated Tue Apr 14 23:40:33 CST 2020
     */
    private String phoneName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column phone.img_url
     *
     * @mbggenerated Tue Apr 14 23:40:33 CST 2020
     */
    private String imgUrl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column phone.price
     *
     * @mbggenerated Tue Apr 14 23:40:33 CST 2020
     */
    private Double price;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column phone.status
     *
     * @mbggenerated Tue Apr 14 23:40:33 CST 2020
     */
    private Integer status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column phone.store
     *
     * @mbggenerated Tue Apr 14 23:40:33 CST 2020
     */
    private String store;
    private String cont;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column phone.type
     *
     * @mbggenerated Tue Apr 14 23:40:33 CST 2020
     */
    private String types;

    private List<String> comments;  //很多评论

}
