package com.enuocms.account.admin.service;

import com.enuocms.account.admin.Admin;
import com.enuocms.account.admin.AdminService;
import com.enuocms.core.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * Created by zhanxiaoping on 2017/8/28.
 * zhanxp@me.com
 */
@Service
public class AdminServiceImpl extends BaseServiceImpl<Admin> implements AdminService {

    @Override
    public Admin getByName(String username) {
        Admin query = new Admin();
        query.setName(username);
        return selectOne(query);
    }
}
