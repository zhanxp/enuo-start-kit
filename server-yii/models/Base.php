<?php

namespace app\models;

use Yii;
use yii\db\ActiveRecord;

//
// Created by zhanxiaoping 
// zhanxp@me.com
//
class Base extends ActiveRecord 
{
    // public $id;
    // public $entTypes;
    // public $entStatus;
    // public $creater;
    // public $updater;
    // public $createDate;
    // public $updateDate;

    public static function getList($where)
    {
        $list =   static::find()
                            ->orderBy('id')
                            ->all();
        return $list;
    }

    public static function pageList($where,$pageIndex,$pageSize)
    {
        $skip = ($pageIndex - 1)*$pageSize;
        $list =   static::find()
                            ->offset($skip)
                            ->limit($pageSize)
                            ->orderBy('id')
                            ->all();
        $total =  static::find() -> count();

        return ['list'=>$list,'total'=>$total,'pageIndex'=>$pageIndex,'pageSize'=>$pageSize];
    }

    public static function findByID($id){
        return static::find()
                        ->where(['id' => $id])
                        ->one();
    }
}