<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<style type="text/css">
	.bg-purple-dark {
		background: #99a9bf;
	}
	.header {
		display: flex;
		height:50px;
		line-height:50px;
	}	
	.left-header {
		flex: 1;
		color: #fff;
		font-size: 20px;
		line-height: 50px;
		padding-left: 20px;
	}	
	.right-header {
		flex: 1;
		text-align: right;
		padding-right:20px;
	}	
	
	#header .header-img{
		width:40px;
		height:40px;
		padding-top:5px;
		padding-left:30px;
		
	}
	</style>

<div id="header">
	<el-row> 
		<el-col :span="24">
			<div class="bg-purple-dark header">
				<img class="header-img" src="admin/imgs/logo.png"/>
				<div class="left-header">管理系统</div>
				<div class="right-header">
					<el-dropdown> 
						<el-button type="primary" trigger="click"
							class="fourth-button">{{user.userName}}&ensp;{{trueName}}&ensp;欢迎光临<i
							class="el-icon-caret-bottom el-icon--right"></i>
						</el-button> 
							<el-dropdown-menu slot="dropdown"> 
							<el-dropdown-item>设置</el-dropdown-item>
							<el-dropdown-item>个人资料</el-dropdown-item>
							<el-dropdown-item @click.native="loginHandle">退出</el-dropdown-item>
							</el-dropdown-menu> 
					</el-dropdown>
				</div>
			</div>
		</el-col> 
	</el-row>
		
</div>
<script type="text/javascript">
	new Vue({
		//绑定dom的地方
		el:'#header',
		//数据
		 data:function(){			 			 
			      return {
			    	 userName:'',
			    	 trueName:'',
			    	 user:''
			      };
			 },
			 
		//页面所有加载好以后,进入的方法
		mounted:function(){
			//对data赋值
			this.userName = sessionStorage.getItem("userName");
			this.user=JSON.parse(sessionStorage.getItem("user"))
			this.getUser();
		},
		//编写页面交互方法的地方
		methods:{
			loginHandle:function(){
				window.location.href="login.jsp";				 
			},
			getUser:function(){
				var vueThis=this;
				/*  doAjax({url:"get-current-user.action",data:{},successCallback:function(data){
			        	vueThis.trueName=data.trueName;
			        	vueThis.userName=data.userName;
			      }}) */
			},
		}
	});

	
</script>

