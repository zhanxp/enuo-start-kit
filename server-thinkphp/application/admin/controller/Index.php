<?php
namespace app\admin\controller;
use think\Controller;
use app\common\controller\User;

//
// Created by zhanxiaoping 
// zhanxp@me.com
//
class Index  extends User
{
    public function index()
    {
        return view('profile',['title'=>'Profile']);
    }
}
