package com.enuocms.account.service;

import com.enuocms.account.model.Admin;
import com.enuocms.core.service.BaseServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zhanxiaoping on 2020/3/7.
 * zhanxp@me.com
 */
@Component
@Transactional
public class AdminService extends BaseServiceImpl<Admin>  {

    public Admin getByName(String username) {
        Admin query = new Admin();
        query.setName(username);
        return selectOne(query);
    }
}
