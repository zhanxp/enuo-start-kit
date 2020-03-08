<#macro pager total pageIndex=1 pageSize=10 url="?" >
    <#assign pageCount=((total + pageSize - 1) / pageSize)?int>
    <#if total==0><#return/></#if>
    <#if (pageIndex > pageCount)>
        <#assign pageIndex=pageCount>
    </#if>
    <#if (pageIndex < 1)>
        <#assign pageIndex=1>
    </#if>
<div class="page-mod">
    <div class="success">
        共${total}条数据,分${pageCount}页,每页显示${pageSize}条
    </div>
    <nav class="text-right" >
        <ul class="pagination">
        <#-- 上一页处理 -->
            <#if (pageIndex == 1)>
                <li class="prev disabled"><a >首页</a></li>
                <li class="prev disabled"><a >&nbsp;<</a></li>
            <#else>
                <li class="prev"><a href="${url}pageIndex=1" data-page="1" title="首页">首页</a></li>
                <li class="prev"><a href="${url}pageIndex=${ pageIndex - 1 }"  data-page="${pageIndex - 1}" title="上一页">&nbsp;<</a></li>
            </#if>
        <#-- 如果前面页数过多,显示... -->
            <#assign start=1>
            <#if (pageIndex > 4)>
                <#assign start=(pageIndex - 1)>
                <li><a href="${url}pageIndex=1"  data-page="1" >1</a></li>
                <li><a href="${url}pageIndex=2"  data-page="2" >2</a></li>
                <li class="disabled"><a>&hellip;</a></li>
            </#if>
        <#-- 显示当前页号和它附近的页号 -->
            <#assign end=(pageIndex + 1)>
            <#if (end > pageCount)>
                <#assign end=pageCount>
            </#if>
            <#list start..end as i>
                <#if (pageIndex==i)>
                    <li class="active" ><a >${i}</a></li>
                <#else>
                    <li><a href="${url}pageIndex=${i}"  data-page="${i}" >${i}</a></li>
                </#if>
            </#list>
        <#-- 如果后面页数过多,显示... -->
            <#if (end < pageCount - 2)>
                <li class="disabled"><a>&hellip;</a></li>
            </#if>
            <#if (end < pageCount - 1)>
                <li><a href="${url}pageIndex=${ pageCount - 1 }"  data-page="${ pageCount - 1 }" >${ pageCount-1 }</a></li>
            </#if>
            <#if (end < pageCount)>
                <li><a href="${url}pageIndex=${ pageCount }"  data-page="${ pageCount }"  >${ pageCount }</a></li>
            </#if>
        <#-- 下一页处理 -->
            <#if (pageIndex == pageCount)>
                <li class="next disabled" ><a >>&nbsp;</a></li>
                <li class="next disabled" ><a >末页</a></li>
            <#else>
                <li class="next" ><a href="${url}pageIndex=${ pageIndex + 1 }" data-page="${ pageIndex + 1}"  title="下一页">>&nbsp;</a></li>
                <li class="next" ><a href="${url}pageIndex=${  pageCount }" data-page="${ pageCount }" title="末页" >末页</a></li>
            </#if>
        </ul>
    </nav>
</div>
</#macro>  