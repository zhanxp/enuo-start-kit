<#assign htmlTitle>
<title>Category</title>
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

    <#list list as item>
        <tr>
            <td>
            ${item.title}
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
                <a href="${base}/category/edit/${item.id}" >Edit</a> |
                <a href="${base}/category/details/${item.id}" >Details</a>
            </td>
        </tr>
    </#list>
</table>
</#assign>

<#assign htmlScript>
</#assign>

<#include "shared/_layout.ftl" />
