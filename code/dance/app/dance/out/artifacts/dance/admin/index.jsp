<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/admin/common/head.inc"%>
<%@ include file="/admin/common/foot.inc"%>
<title>dance管理系统</title>
<style>
	body{
		margin:0;
		padding:0;
		background:#F7F7F7;
		font-family:"Open Sans","Helvetica Neue",Helvetica,Arial,sans-serif"; 
 		font-size:14px;
	}
	
	#right .bread-line{
		height: 40px;
	    line-height: 40px;
	    margin: 10px 10px 0 10px;
	}
	#loading-bar{
		width:200px;
		height:200px;
		margin:auto;
		margin-top:150px;
	}
</style>
</head>
<body>
	

	<div id="app"> 
	
	<!--head -->		
	   <jsp:include page="/admin/layout/head.jsp" ></jsp:include>			
	<!--left -->
       <jsp:include page="/admin/layout/left.jsp" ></jsp:include>     
    <!--right -->	
   		<el-col :span="20"  id="right">
   			<el-row>
	   			<el-breadcrumb separator="/" class="bread-line">					  
      				<el-breadcrumb-item  v-for="item in items">{{item}}</el-breadcrumb-item> 
				</el-breadcrumb>						
			</el-row>			
	<!--main -->	
			<el-row id="main">					
			</el-row>			
				<div id="loading-bar">
					<el-progress type="circle" :percentage="value" width="200"></el-progress>
				</div>	
   		</el-col>	  			
    </div>
</body>
	<script type="text/javascript">

	var indexVue=new Vue({
		//绑定dom的地方
		el:'#right',
		mounted:function(){			
			if(window.location.hash==''){
				openInnerPage('admin/modules/crew/search.jsp');
			}
			if(window.location.hash){				
	    		checkURL();	    		
	    		if(sessionStorage!=null){
	    			var value = sessionStorage.getItem("key");
	    			this.items=JSON.parse(value);
	    		}
	    	};	    	
		},
		data:function(){			
			return {
				items:[],
				value:80,
			};			
		},
		methods:{
		}
	});
	</script>
</html>