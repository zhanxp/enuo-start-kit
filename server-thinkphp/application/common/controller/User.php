<?php
namespace app\common\controller;

use app\common\model\Admin;

//
// Created by zhanxiaoping 
// zhanxp@me.com
//
class User extends Base {
    public function _initialize() {
		parent::_initialize();
        $uid = check_login();
        if (!$uid and $this->request->module() != 'account' ){
        //!in_array($this->url, array('account/index/login', 'account/index/verify'))) {
			return $this->redirect('/account/login');
		} elseif ($uid) {
			$user = Admin::get($uid);
			$this->assign('user', $user);
		}
    }
}
    