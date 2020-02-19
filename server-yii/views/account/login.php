<?php

/* @var $this yii\web\View */
/* @var $form yii\bootstrap\ActiveForm */
/* @var $model app\models\LoginForm */

use yii\helpers\Html;
use yii\bootstrap\ActiveForm;

$this->title = 'Login';
$this->params['breadcrumbs'][] = $this->title;

$this->context->layout = "empty";

?>

<div class="row">
    <div class="col-md-4 col-md-offset-4">
        <div class="panel panel-default">
            <div class="panel-heading"><?= Html::encode($this->title) ?></div>
            <div class="panel-body">
                <?php $form = ActiveForm::begin([
                    'id' => 'login-form',
                    'layout' => 'default',
                    'fieldConfig' => [
                        'template' => "{label}\n{input}\n<span class=\"\">{error}</span>",
                        'labelOptions' => ['class' => ' control-label'],
                    ],
                ]); ?>
                     <?= $form->field($model, 'username')->textInput(['autofocus' => true,'placeholder'=>'username']) ?>
                     
                    <?= $form->field($model, 'password')->passwordInput(['placeholder'=>'password']) ?>
            
                    <?= $form->field($model, 'rememberMe')->checkbox([
                        'template' => "<div class=\"\">{input} {label}</div>\n<span class=\"\">{error}</span>",
                    ]) ?>
                     
                     <div class="form-group">
                        <div class="col-lg-offset-1 col-lg-11">
                            <?= Html::submitButton('Login', ['class' => 'btn btn-primary', 'name' => 'login-button']) ?>
                        </div>
                    </div>
                    
                <?php ActiveForm::end(); ?>
            </div>
        </div>
    </div>
</div>
