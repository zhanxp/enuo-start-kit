<?php
namespace app\account\controller;

use app\common\controller\Base;

//
// Created by zhanxiaoping 
// zhanxp@me.com
//
class Index  extends Base
{
    public function login()
    {
        //$view = new View;
        $this->assign('title','Login');

        if (IS_POST) {
            $username = '';
            $password = '';

            if (input('?post.username')) {
                $username = input('post.username');
            }
            if (input('?post.password')) {
                $password = input('post.password');
            }

            if (!$username || !$password) {
                return $this->error('用户名或者密码不能为空！','');
            }

            $user = model('Admin');
            $uid = $user->login($username,$password);
            if ($uid > 0) {
                $url = session('http_referer') ? session('http_referer') : url('/');
                return $this->success('登录成功！', $url);
            }else{
                return $this->error('登录失败！','');
            }
        }else{
            return  $this->fetch();
        }
    }

    public function logout()
    {
        model('Admin')->logout();
        return $this->redirect('/');
    }
}
