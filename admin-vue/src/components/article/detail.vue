<template>
    <div>
        <h2 class="page-header">
            article detail {{ id }}
        </h2>
    </div>
</template>

<script>
export default {
    data(){
        return {
            id:''
        }
    },
    mounted() {
        this.id = this.$route.params.id;
    },
  methods: {
    //   btnClick(){
    //       this.$emit("btnClick", "hello");
    //   }
  }
}
</script>

<style lang="less" scoped>

</style>

