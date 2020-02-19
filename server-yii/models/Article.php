<?php

namespace app\models;

use Yii;

//
// Created by zhanxiaoping 
// zhanxp@me.com
//
class Article extends Base 
{
    // public $title;
    // public $intro;
    // public $picUrl;
    // public $content;
    // public $categoryId;

    public static function tableName()
    {
        return 'article';
    }
}