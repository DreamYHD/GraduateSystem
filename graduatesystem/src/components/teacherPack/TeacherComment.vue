<template>
  <div>
    <el-table
      :data="tebleComment"
      style="width: 100%">
      <el-table-column
        fixed align="center"
        label ="毕业设计题目"
        prop ='gtname'
      >
      </el-table-column>
      <el-table-column
        prop = 'studentname'
        label="学生姓名"
        fixed align="center">
      </el-table-column>
      <el-table-column
        prop = 'score'
        label="分数" fixed align="center">
      </el-table-column>
      <el-table-column
        prop = 'comment'
        fixed align="center"
        label="评阅">
      </el-table-column>
      <el-table-column
        label="查看论文"
        fixed align="center">
        <template slot-scope="scope">
          <el-button
            type="success"
            size="mini"
            @click="downloadFile(scope.$index, scope.row)">下载
          </el-button>
        </template>
      </el-table-column>
      <el-table-column label="操作" fixed align="center">
        <template slot-scope="scope">
          <el-button
            type="success"
            size="mini"
            @click="handleEdit(scope.$index, scope.row)">评阅打分
          </el-button>
        </template>
      </el-table-column>

    </el-table>
    <el-dialog title="评阅打分" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="评阅" :label-width="formLabelWidth">
          <el-input v-model="form.comment" auto-complete="off" type="textarea" rows="3"></el-input>
        </el-form-item>
        <el-form-item label="打分（0-10）" :label-width="formLabelWidth">
          <el-input v-model="form.score" auto-complete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="sendScore">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import Axios from 'axios'
  import store from '../../vuex/store.js';
  export default {
    store,
    name: 'TeacherComment',
    mounted(){
      this.inittable();
    },
    data() {
      return {
        selectrow:'',
        dialogFormVisible: false,
        form: {
          comment: '',
          score: ''
        },
        formLabelWidth: '120px',
        tebleComment: []
      }
    },
    methods: {
      inittable(){
        const api1 = 'http://localhost:3000/ts/findbyteachername/'+this.$store.state.id;
        const self = this;
        Axios.get(api1, {
          headers: {
            'Content-Type': 'application/octet-stream'
          }
        })
          .then(function (response) {
            console.log(response.data.data[0].score+ "111111111111111111111111111111111");
            self.tebleComment  = response.data.data;
          })
          .catch(function (error) {
          });
      },
      sendScore(){
        const api = 'http://localhost:3000/ts/update/' + this.tebleComment[this.selectrow].studentname;
        const params = new URLSearchParams();
        params.append('tsid', this.tebleComment[this.selectrow].tsid);
        params.append('gtname', this.tebleComment[this.selectrow].gtname);
        if (this.tebleComment[this.selectrow].comment == null){
          params.append('comment', this.form.comment);
        } else {
          params.append('comment', this.tebleComment[this.selectrow].comment +this.form.comment);
        }
        console.log(this.tebleComment[this.selectrow].score+"score is")
        if (this.tebleComment[this.selectrow].score == null ||this.tebleComment[this.selectrow].score ===""){
          console.log(this.form.score+"score");
          params.append('score', parseInt(this.form.score)/2+"");
        }else {
          params.append('score', parseInt(this.tebleComment[this.selectrow].score) + parseInt(this.form.score)/2+"" );

        }
        params.append('filepath', this.tebleComment[this.selectrow].filepath);
        params.append('studentname', this.tebleComment[this.selectrow].studentname);
        params.append('teachername1', this.tebleComment[this.selectrow].teachername1);
        params.append('teachername2', this.tebleComment[this.selectrow].teachername2);
        const self = this;
        console.log("------------------------------------------------------");
        Axios.post(api, params, {
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          }
        })
          .then(function (response) {
            self.inittable();
            self.dialogFormVisible = false;
            console.log(response);
            self.$message({
              message: '评论成功',
              center: true,
              type: 'success'
            });
          })
          .catch(function (error) {
            self.$message({
              message: '评论失败',
              center: true,
              type: 'warning'
            });
            console.log(error);
          });
      },
      downloadFile(inde,row){
        //this.tebleComment[this.selectrow].filepath
        console.log(this.tebleComment[inde].filepath);
        window.open(this.tebleComment[inde].filepath, '_blank');
      },
      handleEdit(index, row) {
        this.selectrow = index;
        this.dialogFormVisible = true;
        console.log(index, row);
      }
    }
  }
</script>
