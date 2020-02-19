<?php

namespace app\controllers;

use Yii;
use yii\filters\AccessControl;
use yii\web\Controller;
use yii\web\Response;
use yii\filters\VerbFilter;
use app\models\LoginForm;
use app\models\ContactForm;

//
// Created by zhanxiaoping 
// zhanxp@me.com
//
class AccountController extends Controller
{
     /**
     * Login action.
     *
     * @return Response|string
     */
     public function actionLogin()
     {
         if (!Yii::$app->user->isGuest) {
             return $this->goHome();
         }
 
         $model = new LoginForm();
         if ($model->load(Yii::$app->request->post()) && $model->login()) {
             return $this->goBack();
         }
         return $this->render('login', [
             'model' => $model,
         ]);
     }
 
     /**
      * Logout action.
      *
      * @return Response
      */
     public function actionLogout()
     {
         Yii::$app->user->logout();
 
         return $this->goHome();
     }
}