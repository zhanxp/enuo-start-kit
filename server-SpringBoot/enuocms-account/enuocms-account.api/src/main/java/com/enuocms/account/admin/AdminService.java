
package com.enuocms.account.admin;
import com.enuocms.core.service.BaseService;

/**
 * Created by zhanxiaoping on 2017/8/28.
 * zhanxp@me.com
 */
public interface AdminService extends BaseService<Admin> {
    Admin getByName(String username);
}
