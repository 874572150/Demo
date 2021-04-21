<template>
    
    <ul class="jishu-nav">
        <li v-for="(value,key) in dataList" :key="key" :class="key==n?'active':''" @click="n=key"  >
          {{value.name}}
        </li>        
      </ul>  
</template>

<script>

import "@/assets/css/layout.css";

export default {
//   data() {
//     return {
//       tagsList: [],
//       collapse: false
//     };
//   },
//   components: {
//     // Header,
//     // Sidebar,
//     // Tags
//   }
// };
data() {
    return {
      msg:'我是技术战法中心s',
      flag:false,
      n:0,  //自定义，初始active位置
      dataList:[{
          name:'安全管理',
          id:0
        },
        {
          name:'员工管理',
          id:1
        }
        ,
        {
          name:'设备管理',
          id:2
        },
        {
          name:'设备参数',
          id:3
        },
        {
          name:'项目管理',
          id:4
        },
        {
          name:'系统参数',
          id:5
        },       
      ]
    }
  },
}
</script>
