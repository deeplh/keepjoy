<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="jts" uri="/jtsecurity"%>
<style>	  
 	  .bodrer-bottom{ 
 		border-bottom:1px solid #e5e5e5; 
 		} 
 	  .bodrer-right{ 
		border-right:2px solid #e5e5e5; 
 		} 
 	  .background-color{ 
 		height:650px;
 	  } 

</style>

	<el-col :span="4" class="bodrer-right background-color" id="left">
	    <el-menu class="background-color" :default-active="key" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose" @select="handleSelect">
	      <el-submenu :index="list1.id" v-for="list1 in navData" class="bodrer-bottom"  >
	        <template slot="title" >{{list1.menuName}}</template>
			    <el-menu-item :index="list2.id" :menu-url="list2.menuUrl" v-for="list2 in list1.sublists"  v-if="!list2.sublists || list2.sublists.length==0" >{{list2.menuName}}</el-menu-item>      				
      				<el-submenu :index="list2.id" v-for="list2 in list1.sublists" v-if="list2.sublists && list2.sublists.length>0">
			          <template slot="title">{{list2.menuName}}</template>
			          <el-menu-item :index="list3.id" :menu-url="list3.menuUrl" v-for="list3 in list2.sublists" v-if="!list3.sublists || list3.sublists.length==0">{{list3.menuName}}</el-menu-item>
			          	<el-submenu :index="list3.id" v-for="list3 in list2.sublists" v-if="list3.sublists && list3.sublists.length>0">
				          <template slot="title">{{list3.menuName}}</template>
				          <el-menu-item :index="list4.id" :menu-url="list4.menuUrl" v-for="list4 in list3.sublists" v-if="!list4.sublists || list4.sublists.length==0">{{list4.menuName}}</el-menu-item>
	      				</el-submenu>  
      				</el-submenu>       				
	      </el-submenu>
	    </el-menu>
  	</el-col>					

		
<script type="text/javascript">
		
var leftVue=new Vue({
	//绑定dom的地方
	el:'#left',
	//数据
	 data:function(){
	    return {
	    	navData:[
					{id:"1" ,menuName: 'user',
			    	   sublists:[			    	            
								  {id:"1-1" ,menuName: 'dancer列表', menuUrl:"admin/modules/user/search.jsp" },
								  {id:"1-2" ,menuName: 'dancer添加',menuUrl:"admin/modules/user/create.jsp"},
			  					]  
					},		
					{id:"2" ,menuName: 'crew',
				    	   sublists:[			    	            
									  {id:"2-1" ,menuName: 'crew列表', menuUrl:"admin/modules/crew/search.jsp" },
									  {id:"2-2" ,menuName: 'crew添加',menuUrl:"admin/modules/crew/create.jsp"},
				  					]  
						},
						{id:"3" ,menuName: 'studio',
					    	   sublists:[			    	            
										  {id:"3-1" ,menuName: 'studio列表', menuUrl:"admin/modules/studio/search.jsp" },
										  {id:"3-2" ,menuName: 'studio添加',menuUrl:"admin/modules/studio/create.jsp"},
					  					]  
							},
							{id:"4" ,menuName: '活动',
						    	   sublists:[			    	            
											  {id:"4-1" ,menuName: 'event列表', menuUrl:"admin/modules/event/search.jsp" },
											  {id:"4-2" ,menuName: 'event添加',menuUrl:"admin/modules/event/create.jsp"},
						  					]  
								},
                {id:"5" ,menuName: '舞种',
                    sublists:[
                        {id:"5-1" ,menuName: '舞种列表', menuUrl:"admin/modules/style/search.jsp" },
                        {id:"5-2" ,menuName: '舞种添加',menuUrl:"admin/modules/style/create.jsp"},
                    ]
                }
            ],
				 getHash:'',
				 key:''
	    }		  
	},

	//页面所有加载好以后,进入的方法
	mounted:function(){
		//对data赋值
		this.user={
			id:'',
			name:''
		};
		
		this.handleHash();
		
		 // DO on hash change	
		 var vueThis= this;
	 	 $(window).on("hashchange", function() {	
	 		vueThis.handleHash();
	   		 checkURL();
	   	 });	 		 	
	},
	//编写页面交互方法的地方
	methods:{
		changeBread:function(params){
			var items=[];
			for(var i= 0; i < this.$data.navData.length;i++){
				var value =this.$data.navData[i];		
				if(params ==value.id || params.substr(0,1)==value.id){
					items.push(value.menuName);
					if(value && value.sublists){
						for(var j= 0; j <value.sublists.length;j++){
							var list = value.sublists[j];
							if(params.length>0 && (params==list.id || params.substr(0, 3)==list.id)){
								items.push(list.menuName);
								if(list && list.sublists){
									for(var k = 0; k < list.sublists.length;k++){
										var item = list.sublists[k];
										if(params.length>0 && (params==item.id ||params.substr(0, 5)==item.id)){
											items.push(item.menuName);	
											if(item && item.sublists){
												for(var n=0;n<item.sublists.length;n++){
													var four=item.sublists[n];
													if(params.length>0 && (params==four.id ||params.substr(0,7)==four.id)){
														items.push(four.menuName);
													}
												}
											}
										}
									}
								}					
							}
						}					
					}
				}			
			}
			return items;
		},
	 	handleHash:function(){ 	
		 	var url = window.location.hash.split('?')[0];	
	 		for(var i = 0 ; i < this.$data.navData.length;i++){
	 			var nav = this.$data.navData[i];	
	 			if(url && url!="#"+nav.menuUrl){		 				
	 				if(nav && nav.sublists)
	 					for(var j = 0 ; j < nav.sublists.length;j++){
	 						var sub= nav.sublists[j];	 						
	 						if(url && url!="#"+sub.menuUrl){	 							
	 							if(sub && sub.sublists){
		 							for(var k = 0; k < sub.sublists.length;k++){
		 								var list = sub.sublists[k];
		 								if(url && url!= "#"+list.menuUrl){
		 									if(list && list.sublists){
		 										for(var m = 0 ; m <list.sublists.length; m++){
		 											var fourth = list.sublists[m];
		 											if(url && url!= "#"+fourth.menuUrl){
		 												
		 											}else{
				 										this.key=fourth.id;
				 									}
		 											
		 										}
		 									}
		 									
		 								}else{
		 									this.key = list.id;	
		 									return;
		 								}
		 							}
	 							}
	 						}else{
	 							this.key = sub.id;
	 							return;
	 						}													
	 					}
	 			}else{
	 				this.key= nav.id;
	 				return;
	 			}
	 		}
        },
		
		handleOpen:function(key, keyPath) {
	        //console.log(key, keyPath);	       
	    },
        handleClose:function(key, keyPath) {
         //console.log(key, keyPath);
        }, 
        handleClick:function(tab, event) {
            //console.log(tab, event);           
          },
        handleSelect:function(key, keyPath,e){ 
        	console.log(key);
        	this.key = key;
        	if(window.oldUrl==undefined &&window.location.href==window.oldUrl){    
        		checkURL();
	 		}else{
	 			this.hashChange(this.key,e); 
	 		}        	
        	if(window.location.hash){
        		checkURL();
        	}
        },
       
        hashChange:function(key,e){
       	 var href=$(e.$el).attr("menu-url");
        	if(href){
        		window.oldUrl = window.location.href;
        		var items=this.changeBread(key);
        		window.sessionStorage.setItem("key",JSON.stringify(items))
        		if (window.location.search) {
    				window.location.href = window.location.href.replace(window.location.search, "")
    					.replace(window.location.hash, "") + "#" + href;
    	        	indexVue.$data.items=items; 	        	
    			}else{
    				window.location.hash = href;
    	        	indexVue.$data.items=items;
    			}          	
        	}; 
        },
       
       
	}
});
 
	
</script>
