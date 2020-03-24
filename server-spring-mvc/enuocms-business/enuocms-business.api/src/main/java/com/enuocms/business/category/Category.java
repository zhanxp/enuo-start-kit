package com.enuocms.business.category;


import com.enuocms.core.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Table;

/**
 * Created by zhanxiaoping on 2017/8/28.
 * category
 * zhanxp@me.com
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
@Table(name = "category")
public class Category extends BaseEntity {
    private String title;
}
