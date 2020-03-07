package com.enuocms.core.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Id;
import java.util.Date;

/**
 * Created by zhanxiaoping on 2020/3/7.
 * zhanxp@me.com
 */
@Data
@ToString
public abstract class BaseEntity {

    @Id
    protected Integer id;

    protected Integer entTypes;

    protected Integer entStatus;

    protected Integer creater;

    protected Integer updater;

    protected Date createDate;

    protected Date updateDate;
}
