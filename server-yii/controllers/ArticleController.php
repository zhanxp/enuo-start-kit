<?php

namespace app\controllers;


use Yii;
use yii\web\Response;
use app\models\Article;
use yii\data\Pagination;

//
// Created by zhanxiaoping 
// zhanxp@me.com
//
class ArticleController extends AuthController
{
     public function actionIndex()
     {
        $pageSize = Yii::$app->request->get('pageSize', 10);
        $pageIndex = Yii::$app->request->get('pageIndex', 1);
        $pageList = Article::pageList('',$pageIndex,$pageSize);

        $pages = new Pagination([
            'pageParam'=> 'pageIndex',
            'pageSizeParam' => 'pageSize',
            'pageSize' => $pageSize,
            'totalCount' => $pageList['total']
        ]);

        //  $pageSize = $pages->getPageSize(true);
        //  $pageIndex = $pages->getPage(true);
        //  $pageList = Article::pageList('',$pageIndex,$pageSize);
        //  $pages->totalCount =  $pageList['total'];
        
         return $this->render('index',[
                'pageList'=>$pageList,
                'pages'=>$pages
             ]);
     }
}