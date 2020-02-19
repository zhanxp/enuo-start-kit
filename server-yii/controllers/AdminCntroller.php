<?php

namespace app\controllers;

use Yii;
use yii\web\Response;
use app\models\Admin;

//
// Created by zhanxiaoping 
// zhanxp@me.com
//
class AdminController extends AuthController
{
    public function actionIndex()
    {
        $profile = Admin::findByID(1);
        return $this->render('profile');
    }
}