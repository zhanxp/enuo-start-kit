<?php
namespace app\index\controller;
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
        return view('index',['title'=>'Home']);
        //return $this->fetch('index',['name'=>'thinkphp']);
    }

    public function about()
    {
        return view('about',['title'=>'Aboout','message'=>'Your application description page.']);
        //return $this->fetch('index',['name'=>'thinkphp']);
    }
}
