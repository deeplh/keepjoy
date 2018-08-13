<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="jt" uri="/jsontag"%>
<%@taglib prefix="jts" uri="/jtsecurity"%>
<style>
</style>


  <div id="content">		
	<el-card class="box-card">
	    <el-form :model="form" :rules="rules" ref="form" label-width="100px" class="demo-form">
		  
		  <el-form-item label="id" prop="id">
		  	
		    
		    	<el-input v-model="form.id"></el-input>
		    
		  </el-form-item>
		  
		  <el-form-item label="crewId" prop="crewId">
		  	
		    
		    	<el-input-number v-model="form.crewId"></el-input-number>
		    
		  </el-form-item>
		  
		  <el-form-item label="userId" prop="userId">
		  	
		    
		    	<el-input-number v-model="form.userId"></el-input-number>
		    
		  </el-form-item>
		  
  
		  <el-form-item>
		    <el-button type="primary" @click="submitForm()">确定</el-button>
		    <el-button @click="resetForm()">重置</el-button>
		  </el-form-item>
		</el-form>
	
	</el-card>
 </div>
<script type="text/javascript">
	new Vue({
		//绑定dom的地方
		el:'#content',
		
		//数据
		 data:function(){	
			 return {
				sourceForm:{
					
				},	 
				 form: {
						  id:'',
						  crewId:'',
						  userId:'',
			        },
			        rules: {
			          name: [
			          ]
			        }
			 	}
			 },
				 
		//页面所有加载好以后,进入的方法
		mounted:function(){
			this.get();
		},
		//编写页面交互方法的地方
		methods:{
			get:function(){
				var vueThis=this;
				var id=getUrlParams().id;
				doAjax(
						{
							url:"admin/usercrewrelation/get.action",
							data:{ id:id},
							successCallback:function(data){	
								vueThis.form=data;
								vueThis.sourceForm=JSON.parse(JSON.stringify(data));
			 				}
						}
					);
			},
			 submitForm:function() {
				 var vueThis=this;
				 	this.$refs["form"].validate(function(valid) {	   			        	
			          if (valid) {
					 	doAjax({url:"admin/usercrewrelation/update.action",data:vueThis.form,successCallback:function(data){	
					 		vueThis.$message({
					          message: '修改成功',
					          type: 'success'
					        }); 
					 	}});					        		            
			          } else {			            
			            return false;
			          }
			        });    
			      },
		      resetForm:function() {
		    	  this.form=JSON.parse(JSON.stringify(this.sourceForm));
		      }
		}
	});
	
</script>
