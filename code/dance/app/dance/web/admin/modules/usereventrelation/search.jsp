<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="jt" uri="/jsontag"%>
<%@taglib prefix="jts" uri="/jtsecurity"%>
<style>
</style>

  <div id="content">		
	 <el-card class="box-card">
	    <div class="post-filter">
			<el-row>
				<el-col>				    					  
					<el-form :inline="true" ref="searchForm"   >
		  <el-form-item label="id" prop="id">
		  	
					    
					    	<el-input v-model="form.id"></el-input>
					    
					    </el-form-item>
		  <el-form-item label="userId" prop="userId">
		  	
					    
					    	<el-input-number v-model="form.userId"></el-input-number>
					    
					    </el-form-item>
		  <el-form-item label="eventId" prop="eventId">
		  	
					    
					    	<el-input-number v-model="form.eventId"></el-input-number>
					    
					    </el-form-item>
					  <el-form-item>
					    <el-button type="primary" @click="handleSearch()">查询</el-button>
					    <el-button @click="resetForm('ruleForm')">重置</el-button>
					  </el-form-item>
					</el-form>
				</el-col>
			</el-row> 
	  	</div>
	
	</el-card> 
  
  	<el-card class="box-card">	
  	
		 <div class="post-table">
	  		<el-row>
	  			<el-col class="padding-left" :span="24">
	  			
	  			
		  		 <el-table
				    :data="list.rows"
				    style="width: 100%">
				     <el-table-column
				      label="操作"
				      >
				  <template scope="scope">
				        <el-button
				          @click.native.prevent="remove(scope.$index,scope.row.id)"
				          type="text"
				          size="small">
				          	删除
				        </el-button>
				        <el-button
				          @click.native.prevent="goToDetail(scope.row.id)"
				          type="text"
				          size="small">
				          	详情
				        </el-button>
				      </template> 
				    </el-table-column> 
				     <el-table-column
				      prop="id"
				      label="id" >
				    </el-table-column>
				     
				     <el-table-column
				      prop="userId"
				      label="userId" >
				    </el-table-column>
				     
				     <el-table-column
				      prop="eventId"
				      label="eventId" >
				    </el-table-column>
				     
				  </el-table> 
	  			</el-col>
	  		</el-row>
		</div>  
	</el-card>
  
  	<div class="block">
	  <el-pagination
	   	@size-change="handleSizeChange"
     	@current-change="handleCurrentChange"
	    layout="total, sizes,prev, pager, next, jumper"
	    :current-page="list.page"
	    :page-sizes="[10,20,50]"
	    :page-size="list.pageSize"
	    :total="list.total">
	  </el-pagination>		  
	</div> 
  </div>

<script type="text/javascript">
	new Vue({
		//绑定dom的地方
		el:'#content',
		//数据
		 data:function(){			 
		      return {	
		    	  form:{
		    		  pageSize:10,
		    		  currentPage:1,
		    		  pagination:"1"
		    	  },
		             list:{}
			      }
			 },
		
		 
		//页面所有加载好以后,进入的方法
		mounted:function(){
			//对data赋值
			this.search();
		},
		//编写页面交互方法的地方
		methods:{		     
		       handleSearch:function() {	
		    	  	this.search();	    	  
		        },
	          handleSizeChange:function(val) {
	            this.form.pageSize=val;
	            this.search();
		        },
	          handleCurrentChange:function(val) {
	            this.form.currentPage=val;
	            this.search();
	         }, 
		      search:function(){
	            var vueThis=this;	
			   	doAjax({url:"admin/usereventrelation/search.action",data:this.form,successCallback:function(data){	
					vueThis.list=data;
			 	}}); 
		      },
		      remove:function(index,id) {		    	  
		    	  var vueThis = this;
		    	  doAjax({url:'admin/usereventrelation/remove.action',data:{ id:id},
		    		  successCallback:function(data){			        	  		    		  
		    			 vueThis.list.rows.splice(index, 1); 
		        		 vueThis.$message({
		        		  type: 'success',
				          message: data.root,
			        	 });
		    		  }
		    		  });
		        },
	        resetForm:function(formName) {
		    	  this.$refs[formName].resetFields();		    	  
		      },
		      goToDetail:function(id){
		    	  openInnerPage("admin/modules/usereventrelation/update.jsp",{id:id});
		      }
		}
	});
	
</script>
