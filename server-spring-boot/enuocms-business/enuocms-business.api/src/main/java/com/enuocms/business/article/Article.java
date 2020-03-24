package com.enuocms.business.article;

import com.enuocms.core.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Table;

/**
 * Created by zhanxiaoping on 2017/8/28.
 * article
 * zhanxp@me.com
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
@Table(name = "article")
public class Article extends BaseEntity {
    private String title;

    private String intro;

    private String picUrl;

    private Integer categoryId;

    private String content;
}
