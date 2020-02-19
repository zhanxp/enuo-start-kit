<?php

namespace app\models;

use Yii;
use yii\web\IdentityInterface;

//
// Created by zhanxiaoping 
// zhanxp@me.com
//
class User extends Base implements IdentityInterface
{
   

     /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'admin';
    }

    /**
    * @inheritdoc
    */
    public function attributeLabels()
    {
        return [
            'id' => 'ID',
            'name' => '用户名',
            'password' => '密码'
        ];
    }

    /**
     * @inheritdoc
     */
    public static function findIdentity($id)
    {
        return static::findOne(['id' => $id]);
    }

    /**
     * @inheritdoc
     */
    public static function findIdentityByAccessToken($token, $type = null)
    {
        return static::findOne(['auth_key' => $token]);
    }

    /**
     * Finds user by username
     *
     * @param string $username
     * @return static|null
     */
    public static function findByUsername($username)
    {
        return static::findOne(['name' => $username]);
        //return null;
    }

    /**
     * @inheritdoc
     */
    public function getId()
    {
        return $this['id'];
    }

     /**
     * @inheritdoc
     */
    public function getUsername(){
        return $this['name'];
    }

    /**
     * @inheritdoc
     */
    public function getAuthKey()
    {
        return $this['auth_key'];
    }

    /**
     * @inheritdoc
     */
    public function validateAuthKey($authKey)
    {
        return $this['auth_key'] === $authKey;
    }

     /**
     * Generates "remember me" authentication key
     */
    public function generateAuthKey()
    {
        $this->auth_key = Yii::$app->security->generateRandomString();
    }

    /**
     * Validates password
     *
     * @param string $password password to validate
     * @return bool if password provided is valid for current user
     */
    public function validatePassword($password)
    {
        $password_hash = Yii::$app->security->generatePasswordHash($this->password);
        return Yii::$app->security->validatePassword($password, $password_hash);
    }
}
