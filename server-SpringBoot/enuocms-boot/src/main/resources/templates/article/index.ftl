<#assign htmlTitle>
<title>Article</title>
</#assign>

<#assign htmlHead>
</#assign>

<#assign contentBody>
<h2  class="page-header">
    Article
    <small>
        <a href="${base}/article/add" >Create</a>
    </small>
</h2>
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
            ${item.entTypes}
            </td>
            <td>
            ${item.entStatus}
            </td>
            <td>
            ${item.creater}
            </td>
            <td>
            ${item.updater}
            </td>
            <td>
            ${item.createDate?string('dd.MM.yyyy HH:mm:ss')}
            </td>
            <td>
            ${item.updateDate?string('dd.MM.yyyy HH:mm:ss')}
            </td>
            <td>
                <a href="${base}/article/edit/${item.id}" >Edit</a> |
                <a href="${base}/article/details/${item.id}" >Details</a>
            </td>
        </tr>
    </#list>
</table>


    <#import "shared/pager.ftl" as q>
    <@q.pager total=pageList.total pageIndex=pageList.pageIndex pageSize=pageList.pageSize  />

</#assign>

<#assign htmlScript>
</#assign>

<#include "shared/_layout.ftl" />
