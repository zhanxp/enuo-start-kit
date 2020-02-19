<?php
namespace app\article\controller;

use think\View;
use app\article\model\Article;
use app\common\controller\User;

//
// Created by zhanxiaoping 
// zhanxp@me.com
//
class Index  extends User
{
    public function index()
    {
        $pageIndex = 1;
        $pageSize = 10;
        if (input('?get.pageIndex')) {
            $pageIndex = input('get.pageIndex/d');
        }
        if (input('?get.pageSize')) {
            $pageSize = input('get.pageSize/d');
        }
       
        $skip = ($pageIndex - 1) * $pageSize;
        if($skip<0){
            $skip = 0;
        }
        
        $article = new Article();
        $list = $article->where('id','>',0)
                        ->limit($skip,$pageSize)
                        ->order('id', 'desc')
                        ->select();
                       
        $total = $article ->where('id','>',0) ->count();
        $pageCount =  (int)(($total + $pageSize - 1) / $pageSize);

        $view = new View;
        //设置变量输出
        $view->assign('list',$list);
        $view->assign('title','Article');

        //pager
        $pager = $view->fetch('common@control/pager',['pageIndex'=>$pageIndex,'pageSize'=>$pageSize,'total'=>$total,'pageCount'=> $pageCount,'url'=>'?']);
        $view->assign('pager',$pager);

        return $view->fetch('index');
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
