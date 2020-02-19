<template>
    <div>
        <h2 class="page-header">
            category
        </h2>

        <table class="table">
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
                 <tr v-for="(item,index) in list" :key="index">
                    <td>
                    {{ item.title }}
                    </td>
                    <td>
                    {{ item.ent_types }}
                    </td>
                    <td>
                    {{ item.ent_status }}
                    </td>
                    <td>
                    {{ item.creater }}
                    </td>
                    <td>
                    {{ item.updater }}
                    </td>
                    <td>
                     {{ item.create_date | formatDate }}
                    </td>
                    <td>
                    {{ item.update_date | formatDate }}
                    </td>
                    <td>
                        <router-link :to="{ name: 'category_edit', params: { id: item.id }}">Edit</router-link>
                        <router-link :to="{ name: 'category_detail', params: { id: item.id }}">Details</router-link>
                    </td>
                </tr>
            </tbody>
    </table>

    </div>
</template>

<script>
import {list} from './service'
import {formatDate} from '../../util';
export default {
    data(){
        return {
            list:[]
        }
    },
    mounted() {
        this.loadData();
    },
    filters:{
        formatDate(time) {
            var date = new Date(time);
            return formatDate(date, "yyyy-MM-dd hh:mm");
        }
    },
    methods: {
        async loadData(){
           var res = await list();
           this.list = res.data;
       }
    }
}
</script>

<style lang="less" scoped>

</style>

