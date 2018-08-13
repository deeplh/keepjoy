<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/admin/common/head.inc"%>
<%@ include file="/admin/common/foot.inc"%>
<title>登录页面</title>	
<style type="text/css">

	.row-bg{
		height:600px;
		align-items: center;
	}
 	#login{		 
 		width: 500px; 
 	    height: 300px; 
 	    border:1px solid #D3DCE6; 
 	    background:#fff; 
 	    border-radius: 4px; 		
 	    box-shadow:0 2px 4px 0 rgba(0,0,0,.12), 0 0 6px 0 rgba(0,0,0,.04); 		
 		} 
	#login .login-button{
		height:80px;
		line-height: 80px;
		border-bottom:1px solid #D3DCE6;
		
	}
	.login-body{
		padding:30px 30px 0 30px;
		
	}
	.margin-left{
		margin-left:30px;
	}
	#login .login-button .logo-style{
 		height:50px; 
 		padding-top:15px; 
		padding-left:20px;  
	}
	
	@font-face {
	  font-family: 'iconfont';
	  src: url('resource/css/iconfont/iconfont.eot');
	  src: url('resource/css/iconfont/iconfont.eot?#iefix') format('embedded-opentype'),
	  url('resource/css/iconfont/iconfont.woff') format('woff'),
	  url('resource/css/iconfont/iconfont.ttf') format('truetype'),
	  url('resource/css/iconfont/iconfont.svg#iconfont') format('svg');
	}
	
	.iconfont{
	  font-family:"iconfont" !important;
	  font-size:32px;font-style:normal;
	  -webkit-font-smoothing: antialiased;
	  -webkit-text-stroke-width: 0.2px;
	  -moz-osx-font-smoothing: grayscale;
	}
</style>
</head>
<body>
	<div id="log">
	<el-row type="flex" class="row-bg" justify="center">
		<div id="login" v-if="willShow">		
				<el-col>
					<div>					
 						<div class="login-button">
	 						<el-row type="flex" >
							  <el-col :span="21"><img src="admin/imgs/logo.png" class="logo-style"/></el-col>
							  <el-col :span="3"><i class="iconfont">&#xe643;</i></el-col>
							</el-row>						
 						</div>
						<div class="login-body">
							<el-form :model="ruleForm2" :rules="rules2" ref="ruleForm2" label-width="100px" class="demo-ruleForm">
							  <el-form-item label="用户名称" prop="userName">
							    <el-input type="text" v-model="ruleForm2.userName"  auto-complete="off"></el-input>
							  </el-form-item>
							  <el-form-item label="用户密码" prop="passWord">
							    <el-input type="password" v-model="ruleForm2.passWord" auto-complete="off"></el-input>
							  </el-form-item>
							  <el-form-item>
							    <el-button type="primary" @click="submitForm('ruleForm2')">提交</el-button>
							    <el-button @click="resetForm('ruleForm2')">重置</el-button>
							  </el-form-item>
							</el-form>
						</div>						
					</div>
				</el-col>					  
		</div>
		
		
 </el-row>	
	</div>
</body>
	<script type="text/javascript">
	new Vue({
		//绑定dom的地方
		el:'#log',
		//数据
		 data:function(){			 
			       var validatePass = function(rule, value, callback){
			         if (value === '') {
			          callback(new Error('请输入密码'));
			        } else {
			          if (this.ruleForm2&&this.ruleForm2.checkPass !== '') {
			            this.$refs.ruleForm2.validateField('passWord');
			          }
			          callback();
			        } 
			      }; 
			      return {
			    	willShow:true,
			        ruleForm2: {
			          userName: '',
			          passWord: ''
			        },
			        rules2: {
			          pass: [
			            { validator: validatePass, trigger: 'blur' }
			          ]
			        }
			      };
			 },
		
		 
		//页面所有加载好以后,进入的方法
		mounted:function(){
			//对data赋值
			this.user={
						id:2,
						name:"谢谢2"
			}
		},
		//编写页面交互方法的地方
		methods:{
			submitForm:function(formName) {
				var user=this.$refs[formName].model
				var userName = this.$refs[formName].model.userName;
				window.sessionStorage.setItem("userName",userName);
		        this.$refs[formName].validate(function(valid){		        	
		          if (valid) {
		        	  	return true
		          } else {
		            console.log('error submit!!');
		            return false;
		          }
		          
		        });
		        
		        doAjax({url:"login.action",data:{"j_username":this.ruleForm2.userName,"j_password":this.ruleForm2.passWord},successCallback:function(data){
		        console.log(data)
		        window.sessionStorage.setItem("user",JSON.stringify(data));
		        	location.href = "admin/index.jsp";
		        }})
		      },
		      resetForm:function(formName) {
		        this.$refs[formName].resetFields();
		      }
		}
	});
	
</script>
</html>

