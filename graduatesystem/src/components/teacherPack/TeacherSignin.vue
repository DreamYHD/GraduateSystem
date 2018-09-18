<template>

  <div>
    <el-row>
      <el-button @click="startSign" type="success" plain >开始签到</el-button>
      <el-button @click="endSign" type="danger" plain style="margin-left: 20px">结束签到</el-button>
    </el-row>
    <el-table
      style="margin-top: 20px"
      :data="tableDataSiginTeacher"
    >
      <el-table-column
        fixed align="center"
        prop="time"
        label="日期"
        width="200">
      </el-table-column>
      <el-table-column
        fixed align="center"
        prop="isstart"
        label="签到是否开始"
      >

      </el-table-column>
      <el-table-column
        fixed align="center"
        prop="keyword"
        label="签到码"
      >

      </el-table-column>
      <el-table-column
        fixed align="center"
        prop="studentarray"
        label="签到成功列表"
       >
      </el-table-column>

    </el-table>
  </div>

</template>

<script>
  import Axios from 'axios'
  import store from '../../vuex/store.js';
  export default {
    mounted(){
       this.findByt();
    },
    store,
    methods: {
      findByt(){
        console.log(this.$store.state.id+" ######");
        const api1 = 'http://localhost:3000/signin/findbyt/'+this.$store.state.id;
        const self2 = this;
        Axios.get(api1, {
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          }
        })
          .then(function (response) {
            self2.tableDataSiginTeacher = response.data.data;
          })
          .catch(function (error) {
            console.log(error);
          });
      },
      startSign(){
        this.$prompt('请输入签到码', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
        }).then(({ value }) => {
          const time = new Date();   // 程序计时的月从0开始取值后+1
          const m = time.getMonth() + 1;
          const t = time.getFullYear() + "-" + m + "-"
            + time.getDate() + " " + time.getHours() + ":"
            + time.getMinutes() + ":" + time.getSeconds();
          console.log(t);
          const api = 'http://localhost:3000/signin/add';
          const params = new URLSearchParams();
          params.append('time', t);
          params.append('isstart', '是');
          params.append('keyword', value);
          params.append('teachername',this.$store.state.id);
          console.log(this.$store.state.id+"+++++++++++++=");
          const self = this;
          Axios.post(api, params, {
            headers: {
              'Content-Type': 'application/x-www-form-urlencoded'
            }
          })
            .then(function (response) {
              self.findByt();
              self.$message({
                message: '发起签到成功',
                center: true,
                type: 'success'
              });
              self.siginid = response.data.data.id;
              console.log(response.data.data.time);
            })
            .catch(function (error) {
              self.$message({
                message: '发起签到失败',
                center: true,
                type: 'warning'
              });
              console.log(error);
            });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '取消输入'
          });
        });
      },
      endSign(){
        console.log(this.$store.state.id+" ######");
        const api1 = 'http://localhost:3000/signin/findbyt/'+this.$store.state.id;
        const self2 = this;
        Axios.get(api1, {
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          }
        })
          .then(function (response) {
            self2.tableDataSiginTeacher = response.data.data;
            const params = new URLSearchParams();
            params.append('time', response.data.data[0].time);
            params.append('keyword', response.data.data[0].keyword);
            params.append('isstart', '否');
            if (response.data.data[0].studentarray === null){
              params.append('studentarray',"");
            } else {
              params.append('studentarray', response.data.data[0].studentarray);
            }
            params.append('teachername',response.data.data[0].teachername);
            console.log(self2.$store.state.id+"+++++++++++++=");
            const self = self2;
            const api = 'http://localhost:3000/signin/update/'+self.siginid;
            console.log(api);

            Axios.post(api, params, {
              headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
              }
            })
              .then(function (response) {
                self.findByt();
                self.$message({
                  message: '结束签到成功',
                  center: true,
                  type: 'success'
                });
                console.log(response);
              })
              .catch(function (error) {
                self.$message({
                  message: '结束签到失败',
                  center: true,
                  type: 'warning'
                });
                console.log(error);
              });

          })
          .catch(function (error) {
            console.log(error);
          });

      }
    },
    data() {
      return {
        siginid:'',
        tableDataSiginTeacher: []
      }
    }
  }
</script>
