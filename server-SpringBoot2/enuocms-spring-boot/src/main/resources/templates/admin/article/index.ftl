<#assign htmlTitle>
<title>Article</title>
</#assign>

<#assign htmlHead>
</#assign>

<#assign contentBody>
<div class="container body-content">
    <h2  class="page-header">
        Article
    </h2>
    <div class="row">
        <div class="col-md-2">
            <#include "admin/shared/_menu.ftl" />
        </div>
        <div class="col-md-10">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <a onclick="fnAdd()"><i class="glyphicon glyphicon-plus"></i>添加</a>
                </div>
                <table class="table">
                    <tr>
                        <th>
                           Title
                        </th>
                        <th>
                            Intro
                        </th>
                        <th>
                            PicUrl
                        </th>
                        <th>
                            CategoryID
                        </th>
                        <th>
                            Content
                        </th>
                        <th>
                            Creater
                        </th>
                        <th>
                            CreateDate
                        </th>
                        <th>操作</th>
                    </tr>

                    <#list pageList.items as item>
                        <tr>
                            <td>
                            ${item.title}
                            </td>
                            <td>
                            ${item.intro}
                            </td>
                            <td>
                            ${item.picUrl}
                            </td>
                            <td>
                            ${item.categoryId}
                            </td>
                            <td>
                            ${item.content}
                            </td>
                            <td>
                            ${item.creater}
                            </td>
                            <td>
                            ${item.createDate?string('yyyy-MM-dd HH:mm:ss')}
                            </td>
                            <td>
                                <a href="${base}/article/edit/${item.id}" >Edit</a> |
                                <a href="${base}/article/details/${item.id}" >Details</a>
                            </td>
                        </tr>
                    </#list>
                </table>
            <div class="panel-footer">
                <#import "shared/_pager.ftl" as q>
                <@q.pager total=pageList.total pageIndex=pageList.pageIndex pageSize=pageList.pageSize  />
            </div>
        </div>
    </div>
</div>
</#assign>

<#assign htmlScript>
</#assign>

<#include "shared/_layout.ftl" />
