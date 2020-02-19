<template>
    <div class="page-mod">
        <div class="result">
共{{total}}条数据,分{{pageCount}}页,每页显示{{pageSize}}条
        </div>
        <nav class="text-right">
            <ul class="pagination">
                <li v-if="pageIndex<=1" class="prev disabled"><a>首页</a></li>
                <li v-if="pageIndex<=1"  class="prev disabled"><a>&nbsp;&lt;</a></li>
                <li v-if="pageIndex>1"  class="prev"><a @click="first" data-page="1" title="首页">首页</a></li>
                <li v-if="pageIndex>1"  class="prev"><a @click="pre"  title="上一页">&nbsp;&lt;</a></li>
              
                <li v-if="pageIndex>4"  ><a @click="go(1)" data-page="1">1</a></li>
                <li v-if="pageIndex>4"  ><a @click="go(1)" data-page="2">2</a></li>
                <li v-if="pageIndex>4"   class="disabled"><a>&hellip;</a></li>

                <li v-for="(page,index) in pages" :key="index" :class="['pageNum',{active:page == pageIndex}] " >
                    <a v-if="page==pageIndex" >{{ page }}</a>
                    <a v-if="page!=pageIndex" @click="go(page)" >{{ page }}</a>
                </li>

                <li v-if="pageIndex>=pageCount" class="next disabled "><a>&gt;&nbsp;</a></li>
                <li v-if="pageIndex>=pageCount" class="next disabled "><a>末页</a></li>
                <li v-if="pageIndex<pageCount" class="next "><a @click="next()" title="下一页 ">&gt;&nbsp;</a></li>
                <li v-if="pageIndex<pageCount" class="next "><a @click="last()" title="末页 ">末页</a></li>
            </ul>
        </nav>
    </div>
</template>

<script>
export default {
    data(){
        return {
            pageCount:1,
            end:1,
            pages:[]
        }
    },
    props: {
        pageIndex:{
            type:Number,
            default:1
        },
        pageSize:{
            type:Number,
            default:10
        },
        total:{
            type:Number,
            default:10
        },
        url:{
            type:String,
            default:'?'
        },
    },
    created(){
     
    },
    mounted() {
       this.init();
    },
    computed: {
        
    },
     methods: {
        init(){
            this.pageCount = parseInt((this.total + this.pageSize - 1) / this.pageSize);
            this.end =  this.pageIndex + 1 > this.pageCount ? this.pageCount : this.pageIndex + 1;
            var start = this.pageIndex > 4 ? this.pageIndex -1 : 1;
            var end =  this.end; // this.pageIndex + 1 > this.pageCount ? this.pageCount : this.pageIndex + 1;
            var list = [];
            for(var i=1;i<=end;i++){
                list.push(i);
            }
            this.pages = list;
        },
        first(){
             this.go(1);
        },
        pre(){
            this.go(this.pageIndex - 1);
        },
        next(){
            this.go(this.pageIndex + 1);
        },
        last(){
            this.go(this.pageCount);
        },
        go(index){
            this.$emit('go',index);
        }
    },watch:{
        pageIndex:function(){
            this.init();
        },
        total:function(){
            this.init();
        },
        pageSize:function(){
            this.init();
        }
    },
}
</script>

<style lang="less" scoped>
.page-mod {
    position: relative;
}

.page-mod .result {
    position: absolute;
    top: 8px;
}

.page-mod nav ul.pagination {
    margin: 0;
}
</style>
