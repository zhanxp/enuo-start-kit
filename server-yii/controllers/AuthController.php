<?php

namespace app\controllers;

use Yii;
use yii\filters\AccessControl;
use yii\web\Controller;
use yii\web\Response;
use yii\filters\VerbFilter;
use app\models\Admin;

//
// Created by zhanxiaoping 
// zhanxp@me.com
//
class AuthController extends Controller
{
    // /**
    //  * @inheritdoc
    //  */
    // public function behaviors()
    // {
    //     return [
    //         'access' => [
    //             'class' => AccessControl::className(),
    //             'only' => ['logout'], 
    //             'rules' => [
    //                 [
    //                     'actions' => ['logout'],
    //                     'allow' => true,
    //                     'roles' => ['@'],
    //                 ],
    //             ],
    //         ],
    //         'verbs' => [
    //             'class' => VerbFilter::className(),
    //             'actions' => [
    //                 'logout' => ['post'],
    //             ],
    //         ],
    //     ];
    // }

    /**
     * 在程序执行之前，对访问的方法进行权限验证.
     * @param \yii\base\Action $action
     * @return bool
     * @throws ForbiddenHttpException
     */
     public function beforeAction($action)
     {
         //如果未登录，则直接返回
         if(Yii::$app->user->isGuest){
            // return $this->goHome();
            return $this->redirect('/account/login');
         }

         return true;

        //  //获取路径
        //  $path = Yii::$app->request->pathInfo;
 
        //  //忽略列表
        //  if (in_array($path, $this->ignoreList)) {
        //      return true;
        //  }
 
        //  if (Yii::$app->user->can($path)) {
        //      return true;
        //  } else {
        //      throw new ForbiddenHttpException(Yii::t('app', 'message 401'));
        //  }
     }
}