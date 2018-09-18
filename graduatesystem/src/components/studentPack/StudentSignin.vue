<template>
  <div>

    <el-button type="success" icon="el-icon-check" style="width: 200px" @click="signinBtn">签到</el-button>
    <el-table
      :data="tableDataSigin"
      style="width: 100%">
      <el-table-column
        fixed align="center"
        prop="time"
        label="时间"
        width="580">
      </el-table-column>
      <el-table-column
        fixed align="center"
        prop="isstart"
        label="签到是否开始"
      >
      </el-table-column>
      <el-table-column
        fixed align="center"
        prop="studentarray"
        label="签到情况"
      >
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
  import store from '../../vuex/store.js';
  import Axios from 'axios'

  export default {
    store,
    mounted() {
      this.findTeacher();
    },
    name: "StudentSignin",
    data() {
      return {
        signStudentId: '',
        teachernameInStudentSigin: '',
        tableDataSigin: []
      }
    },
    methods: {
      findId(value) {
        const api1 = 'http://localhost:3000/signin/findbyti/'
          + this.teachernameInStudentSigin + '/'
          + '是';
        console.log(api1);
        const self2 = this;
        Axios.get(api1, {
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          }
        })
          .then(function (response) {
            if (value === response.data.data[0].keyword) {


              console.log(response.data.data[0].id + "dddddddddddddddddddddddddddddd");
              self2.signStudentId = response.data.data[0].id;
              console.log(self2.signStudentId);
              // test
              const api = 'http://localhost:3000/signin/update/' + self2.signStudentId;
              const params = new URLSearchParams();
              params.append('id', self2.signStudentId);
              params.append('isstart', '是');
              params.append('keyword', response.data.data[0].keyword);
              console.log(self2.$store.state.id + "updata");
              if (response.data.data[0].studentarray === null){

                params.append('studentarray',  self2.$store.state.id + "    ");
              } else {
                params.append('studentarray', response.data.data[0].studentarray + self2.$store.state.id + "             ");

              }
              params.append('teachername', response.data.data[0].teachername);
              params.append('time', response.data.data[0].time);
              const self = self2;
              console.log("------------------------------------------------------");
              Axios.post(api, params, {
                headers: {
                  'Content-Type': 'application/x-www-form-urlencoded'
                }
              })
                .then(function (response) {
                  console.log(response);
                  self.findTable();
                  self.$message({
                    message: '签到成功',
                    center: true,
                    type: 'success'
                  });
                })
                .catch(function (error) {
                  self.$message({
                    message: '签到失败',
                    center: true,
                    type: 'warning'
                  });
                  console.log(error);
                });
            }else {
              self2.$message({
                message: '签到码错误',
                center: true,
                type: 'warning'
              });
              console.log(error);
            }

          })
          .catch(function (error) {
            console.log(error);
          });
      },
      findTeacher() {
        console.log(this.$store.state.id + "initmyselect");
        const api1 = 'http://localhost:3000/gtitle/findbystudentname/' + this.$store.state.id;
        const self = this;
        Axios.get(api1, {
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          }
        })
          .then(function (response) {
            self.teachernameInStudentSigin = response.data.data[0].teachername;
            console.log(self.teachernameInStudentSigin);
            self.findTable();
          })
          .catch(function (error) {
            console.log(error)
          });
      },
      findTable() {
        const api1 = 'http://localhost:3000/signin/findbyt/' + this.teachernameInStudentSigin;
        console.log(api1);
        const self2 = this;
        Axios.get(api1, {
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          }
        })
          .then(function (response) {
            console.log(response.data.data[0].time);
            self2.tableDataSigin = response.data.data;
          })
          .catch(function (error) {
            console.log(error);
          });
      },
      signinBtn() {
        this.$prompt('请输入签到码', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
        }).then(({value}) => {
          this.findId(value);
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '取消输入'
          });
        });

      }
    }
  }
</script>

<style scoped>

</style>
