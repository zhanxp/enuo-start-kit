<#assign htmlTitle>
<title>Category</title>
</#assign>

<#assign htmlHead>
</#assign>

<#assign contentBody>
<div class="container body-content">
    <h2  class="page-header">
        Category
    </h2>
    <div class="row">
        <div class="col-md-2">
            <#include "admin/shared/_menu.ftl" />
        </div>
        <div class="col-md-10">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <a  onclick="fnAdd()"><i class="glyphicon glyphicon-plus"></i>添加</a>
                </div>
                    <table class="table table-bordered">
                    <tr>
                        <th>
                            Title
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
                        <th>
                            操作
                        </th>
                    </tr>

                    <#list list as item>
                        <tr>
                            <td>
                            ${item.title}
                            </td>
                            <td>
                            ${item.creater}
                            </td>
                            <td>
                            ${item.updater}
                            </td>
                            <td>
                            ${item.createDate?string('yyyy-MM-dd HH:mm:ss')}
                            </td>
                            <td>
                            ${item.updateDate?string('yyyy-MM-dd HH:mm:ss')}
                            </td>
                            <td>
                                <a onclick="fnEdit(${item.id})" class="text-primary">修改</a>
                                <a onclick="fnDel(${item.id})" class="text-danger">删除</a>
                            </td>
                        </tr>
                    </#list>
                </table>
            </div>
        </div>
    </div>
</div>
</#assign>

<#assign htmlScript>
</#assign>

<#include "shared/_layout.ftl" />
