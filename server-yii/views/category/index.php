<?php

/* @var $this yii\web\View */
use yii\helpers\Html;

$this->title = 'Category';
$this->params['breadcrumbs'][] = $this->title;
?>
<h2 class="page-header">
    <?= Html::encode($this->title) ?>
</h2>
<table class="table">
        <tr>
            <th>
                Title
            </th>
            <th>
                EntTypes
            </th>
            <th>
                EntStatus
            </th>
            <th>
                Creater
            </th>
            <th>
                Updater
            </th>
            <th>
                CreateDate
            </th>
            <th>
                UpdateDate
            </th>
            <th></th>
        </tr>
        <?php foreach($list as $vo):?>
        <tr>
            <td>
            <?=$vo['title']?>
            </td>
            <td>
            <?=$vo['ent_types']?>
            </td>
            <td>
            <?=$vo['ent_status']?>
            </td>
            <td>
            <?=$vo['creater']?>
            </td>
            <td>
            <?=$vo['updater']?>
            </td>
            <td>
            <?=$vo['create_date']?>
            </td>
            <td>
            <?=$vo['update_date']?>
            </td>
            <td>
                <a href="/category/edit/<?=$vo['id']?>">Edit</a> |
                <a href="/category/details/<?=$vo['id']?>">Details</a>
            </td>
        </tr>
        <?php endforeach;?>
    </table>
