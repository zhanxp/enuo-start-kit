<?php
namespace app\common\model;

use think\Model;

class Admin extends Model
{
    public function login($username = '', $password = '') {
        $map = array();
        $map['name'] = $username;
        $map['password'] = $password;
        $user = $this->where($map)->find();
        if(isset($user['id']) && $user['id']){
            $auth = array(
                'id'             => $user['id'],
                'name'        => $user['name']
            );
            session('user_auth', $auth);
            return $user['id'];
        }else{
            return 0;
        }
    }

    public function logout(){
		session('user_auth', null);
	}
}