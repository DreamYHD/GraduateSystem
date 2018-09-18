<template>
  <div>
    <el-upload style="margin-right: 0"
               class="upload-demo"
               action="http://localhost:3000/upload"
               :on-preview="handlePreview"
               :on-remove="handleRemove"
               :before-remove="beforeRemove"
               :on-success="handleSuccess"
               multiple
               :limit="3"
               :on-exceed="handleExceed"
               :file-list="fileList">
      <el-button size="small" type="primary" round style="margin-top: 4px;margin-right: 0px">上传文件</el-button>
    </el-upload>
    <el-table
      :data="tableStudentComment"
      style="width: 100%"
      max-height="350">
      <el-table-column
        fixed align="center"
        prop="teachername1"
        label="教师1"
        width="350">
      </el-table-column>
      <el-table-column
        fixed align="center"
        prop="teachername2"
        label="教师2"
        width="350">
      </el-table-column>
      <el-table-column
        prop="score"
        fixed align="center"
        label="评分"
        width="350">
      </el-table-column>
      <el-table-column
        prop="comment"
        fixed align="center"
        label="点评"
        width="600">
      </el-table-column>

    </el-table>
  </div>
</template>
<script>
  import Axios from 'axios'
  import store from '../../vuex/store.js';

  export default {
    mounted() {
      this.inittable();
    },
    store,
    name: "StudentComment",
    data() {
      return {
        fileList: [],
        tableStudentComment: []
      }
    },
    methods: {
      handleSuccess(response, file, fileList) {
        {
          const api = 'http://localhost:3000/ts/update/' + this.$store.state.id;
          const params = new URLSearchParams();
          params.append('tsid', this.tableStudentComment[0].tsid);
          params.append('gtname', this.tableStudentComment[0].gtname);
          if (this.tableStudentComment[0].comment == null){
            params.append('comment', "");
          } else {
            params.append('comment', this.tableStudentComment[0].comment );
          }
          console.log(this.tableStudentComment[0].score+"score is");
          if (this.tableStudentComment[0].score == null ||this.tableStudentComment[0].score ===""){
            console.log(this.form.score+"score");
            params.append('score', "");
          }else {
            params.append('score', this.tableStudentComment[0].score);

          }
          params.append('filepath',fileList[0].url);
          params.append('studentname', this.tableStudentComment[0].studentname);
          params.append('teachername1', this.tableStudentComment[0].teachername1);
          params.append('teachername2', this.tableStudentComment[0].teachername2);
          const self = this;
          console.log("------------------------------------------------------");
          Axios.post(api, params, {
            headers: {
              'Content-Type': 'application/x-www-form-urlencoded'
            }
          })
            .then(function (response) {
              console.log(response.data.data.filepath);
              self.inittable();
              console.log(response);
              self.$message({
                message: '上传成功',
                center: true,
                type: 'success'
              });
            })
            .catch(function (error) {
              self.$message({
                message: '上传失败',
                center: true,
                type: 'warning'
              });
              console.log(error);
            });
        }
      },
      handleRemove(file, fileList) {
        console.log(file, fileList);
      },
      handlePreview(file) {
        console.log(file);
      },
      handleExceed(files, fileList) {
        this.$message.warning(`当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
      },
      beforeRemove(file, fileList) {
        return this.$confirm(`确定移除 ${ file.name }？`);
      },
      inittable() {
        const api1 = 'http://localhost:3000/ts/findbystudentname/' + this.$store.state.id;
        const self = this;
        Axios.get(api1, {
          headers: {
            'Content-Type': 'application/octet-stream'
          }
        })
          .then(function (response) {
            console.log(response.data.data[0].score + "111111111111111111111111111111111");
            self.tableStudentComment = response.data.data;
          })
          .catch(function (error) {
          });
      }
    }

  }
</script>

<style scoped>

</style>
