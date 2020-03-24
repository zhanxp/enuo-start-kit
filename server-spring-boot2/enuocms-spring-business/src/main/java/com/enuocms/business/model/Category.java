package com.enuocms.business.model;


import com.enuocms.core.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Table;

/**
 * Created by zhanxiaoping on 2017/8/28.
 * zhanxp@me.com
 * category
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
@Table(name = "category")
public class Category extends BaseEntity {
    private String title;
}
