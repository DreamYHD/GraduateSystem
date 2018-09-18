<template>
  <div>
    <el-container class="main">
      <el-container>
        <el-header class="main-header">
          <el-dropdown>
                        <span class="el-dropdown-link">
                            <img src="" alt="">
                        </span>
            <el-dropdown-menu slot="dropdown"  @click="finishLogin">
              <el-button style="height: 50px" @click="finishLogin">退出登录</el-button>
            </el-dropdown-menu>
          </el-dropdown>
        </el-header>
        <el-main style="background: white">
          <el-table
            :data="tableDataadmin"
            style="width: 100%"
            max-height="100%">
            <el-table-column
              style="align-content: center"
              fixed align="center"
              prop="id"
              label="毕业设计编号"
              width="200">
            </el-table-column>
            <el-table-column
              fixed align="center"
              prop="title"
              label="毕业设计名称"
              width="300">
            </el-table-column>
            <el-table-column
              fixed align="center"
              style="align-content: center"
              prop="startime"
              label="开始时间"
              width="200">
            </el-table-column>
            <el-table-column
              fixed align="center"
              style="align-content: center"
              prop="endtime"
              label="结束时间"
              width="200">
            </el-table-column>
            <el-table-column
              fixed align="center"
              prop="teachername"
              label="发布老师"
              width="300">
            </el-table-column>

            <el-table-column
              fixed align="center"
              prop="description"
              label="具体要求"
              width="680">
            </el-table-column>
            <el-table-column
              fixed align="center"
              label="操作"
              width="200">
              <template slot-scope="scope">
                <el-button
                  @click.native.prevent="pushRow(scope.$index, tableDataadmin)"
                  type="text"
                  size="small">
                  通过
                </el-button>
              </template>
            </el-table-column>
          </el-table>

        </el-main>
        <el-footer class="main-footer" height="50px">
          <p>页脚</p>
        </el-footer>
      </el-container>
    </el-container>
  </div>
</template>
<style>
  * {
    padding: 0;
    margin: 0;
  }

</style>
<style scoped lang="scss">
  $header-height: 60px;
  $background-color: #283239;
  $color: #FFF;

  .main {
    height: 100vh;
    min-width: 800px;
    min-height: 100px;
    overflow: hidden;

    aside {
      overflow: visible;
      height: 100%;
      background-color: $background-color;
      color: $color;

      .isClossTab {
        width: 100%;
        height: $header-height;
        cursor: pointer;
        font-size: 25px;
        text-align: center;
        line-height: $header-height;
        font-weight: bold;
        border-right: 1px solid #807c7c;
        box-sizing: border-box;
      }
      .menu {
        width: 100%;
        border-right: 0;

      }

    }

    .main-header {
      background-color: $background-color;
      color: $color;

      .el-dropdown {
        cursor: pointer;
        float: right;
      }
      .el-dropdown-link {

        img {
          $imgMargin: (($header-height - 50)/2);
          display: inline-block;
          width: 50px;
          height: 50px;
          border-radius: 25px;
          background-color: #FFF;
          margin-top: $imgMargin;
        }
      }
    }

    .crumbs {
      margin-bottom: 20px;
    }

    .main-footer {
      text-align: center;
      background-color: white;
      color: $color;
      line-height: 50px;
    }

  }

</style>

<script>
  import Axios from 'axios'
  export default {
    mounted(){
      const api1 = 'http://localhost:3000/gtitle/findbyis/'+'否';
      const self = this;
      Axios.get(api1, {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        }
      })
        .then(function (response) {
          self.tableDataadmin = response.data.data;
          console.log(self.tableDataadmin[0].title+self.tableDataadmin[0].teachername +"222222222222222222222")
        })
        .catch(function (error) {
        });
    },
    name: 'Administrator',
    data() {
      return {
        tableDataadmin: []
      };
    },
    methods: {
      finishLogin(){
        this.$router.push('login');
        this.$message.success('退出成功');
      },
      pushRow(index, rows) {
        const  pushId = this.tableDataadmin[index].id;
        console.log(pushId);
        const api = 'http://localhost:3000/gtitle/update/' + pushId;
        const params = new URLSearchParams();
        params.append('id', this.tableDataadmin[index].id);
        params.append('description', this.tableDataadmin[index].description);
        params.append('teachername', this.tableDataadmin[index].teachername);
        console.log(this.tableDataadmin[index].teachername);
        params.append('endtime', this.tableDataadmin[index].endtime);
        console.log(this.tableDataadmin[index].endtime);
        params.append('startime', this.tableDataadmin[index].startime);
        params.append('isselect', this.tableDataadmin[index].isselect);
        if (this.tableDataadmin[index].filepath == null){
          params.append('filepath', "");
        } else {
          params.append('filepath', this.tableDataadmin[index].filepath);
        }
        if (this.tableDataadmin[index].discuss == null){
          params.append('discuss', "");
        } else {
          params.append('discuss', this.tableDataadmin[index].discuss);
        }
        params.append('isselect', this.tableDataadmin[index].isselect);
        params.append('isthrough', '是');
        if (this.tableDataadmin[index].studentname === null){
          params.append('studentname', "");

        } else {
          params.append('studentname', this.tableDataadmin[index].studentname);
        }
        if (this.tableDataadmin[index].teacher1name === null){
          params.append('teacher1name',"");

        } else {
          params.append('teacher1name', this.tableDataadmin[index].teacher1name);
        }
        if (this.tableDataadmin[index].studentname === null){
          params.append('teacher2name', "");

        } else {
          params.append('teacher2name', this.tableDataadmin[index].teacher2name);

        }
        params.append('title', this.tableDataadmin[index].title);
        const self = this;
        console.log("------------------------------------------------------");
        Axios.post(api, params, {
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          }
        })
          .then(function (response) {
            console.log(response);
            self.$message({
              message: '审核成功',
              center: true,
              type: 'success'
            });
            rows.splice(index, 1);
          })
          .catch(function (error) {
            self.$message({
              message: '审核失败',
              center: true,
              type: 'warning'
            });
            console.log(error);
          });

      }
    }
  }
</script>

<style scoped>

</style>
