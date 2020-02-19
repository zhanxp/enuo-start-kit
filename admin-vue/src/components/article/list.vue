<template>
    <div>
        <h2 class="page-header">
            article
        </h2>

        <table class="table table-bordered">
            <thead>
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
            </thead>
            <tbody>
                <tr v-for="(item,index) in list.items" :key="index">
                    <td>
                    {{ item.title }}
                    </td>
                    <td>
                    {{item.ent_types}}
                    </td>
                    <td>
                    {{item.ent_status}}
                    </td>
                    <td>
                    {{item.creater}}
                    </td>
                    <td>
                    {{item.updater}}
                    </td>
                    <td >
                    {{ item.create_date | formatDate }}
                    </td>
                    <td>
                     {{ item.update_date | formatDate }}
                    </td>
                    <td>
                        <router-link :to="{ name: 'article_edit', params: { id: item.id }}">Edit</router-link>
                        <router-link :to="{ name: 'article_detail', params: { id: item.id }}">Details</router-link>
                    </td>
                </tr>
            </tbody>
        </table>
        <Pager :pageIndex="list.pageIndex" :pageSize="list.pageList" :total="list.total"  @go="loadData" ></Pager>
</div>
</template>

<script>
import {pageList} from './service'
import {formatDate} from '../../util';

import Pager from 'components/shared/pager'

export default {
    data(){
        return {
            list:{
                items:[],
                pageIndex:1,
                pageSize:10,
                total:10
            }
        }
    },
    mounted() {
        this.loadData();
    },
    components: {
        Pager, Pager
    },
    filters:{
        formatDate(time) {
            var date = new Date(time);
            return formatDate(date, "yyyy-MM-dd hh:mm");
        }
    },
    methods: {
       async loadData(pageIndex){
           pageIndex = pageIndex || 1;
           var res = await pageList(pageIndex,this.pageSize);
           this.list = res.data;
           //this.list.pageIndex = index;
       }
    }
}
</script>

<style lang="less" scoped>

</style>

