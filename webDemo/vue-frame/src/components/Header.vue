<template>
    <div id = "header">
        <div id="top">
          <ul>
            <li v-for="(item,key) in topList" :key="key" :class="key==n?'active':''" @click="n=key"  >
              {{value.name}}
            </li>
        </div>
    </div>
</template>

<script>
var _self;
export default {
  name: "Header",
  data() {
    return {
      topList: {},
    };
  },
  created() {
    _self = this;
  },
  mounted() {},

  methods: {},
};
</script>
<style scoped>
</style>



