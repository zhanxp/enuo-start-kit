<?php

namespace app\controllers;


use Yii;
use yii\web\Response;
use app\models\Category;

//
// Created by zhanxiaoping 
// zhanxp@me.com
//
class CategoryController extends AuthController
{
    public function actionIndex()
    {
        $list = Category::getList('');
        return $this->render('index',['list'=>$list]);
    }
}