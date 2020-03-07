var adminDao = require("./adminDao");

/**
 * Created by zhanxiaoping 
 * zhanxp@me.com
 */
var adminService = {
    login: async function(username, password) {
        var user = await adminDao.findByName(username);
        if (!user || !user.id) {
            return null;
        }

        if (user.password != password) {
            return null;
        }

        return user;
    },
    findById: async function(id) {
        return await adminDao.findById(id);
    }
};
module.exports = adminService;