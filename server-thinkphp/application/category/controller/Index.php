<?php
namespace app\category\controller;

use think\View;
use app\category\model\Category;
use app\common\controller\User;

//
// Created by zhanxiaoping 
// zhanxp@me.com
//
class Index  extends User
{
    public function index()
    {
        $list = Category::all();
        $view = new View;
        //设置变量输出
        $view->assign('list',$list);
        $view->assign('title','Category');
        return $view->fetch('index');
        
        //return $this->fetch('index',['name'=>'thinkphp']);
        //return $view->fetch(('index',['title'=>'Article']);
    }

    public function edit($id)
    {
        echo $id;
    }

    public function details($id)
    {
        echo $id;
    }

    public function add()
    {
        echo 'add';
    }
}
