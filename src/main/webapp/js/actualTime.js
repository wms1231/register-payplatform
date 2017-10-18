		//显示24小时支付金额对比
 		function differenceMoney(){
 			var url = "../rest/common/select";
		     var param = { 
				"sqlKey": "payRecord.payContrast24"
			 };
		     var sData;
		     sData=loadData(url,param);
		     if(sData == ""){
		    	 return;
		     }
		     var totalMoney = 0;
		     var totalMoneyLastDay = 0;
		     for(var i = 0;i < sData.length; i ++){
		    	 totalMoney = add(totalMoney , sData[i].ZJE);
		    	 totalMoneyLastDay = add(totalMoneyLastDay , sData[i].ZJEL);
		     };
		     //alert(totalMoney)
		     
		     $("#totalMoney").html('<a>'+totalMoney.toFixed(2)+'</a>');
		     var differenceMoney = substract(totalMoney,totalMoneyLastDay);
		     var differenceMoneyPer = divide(differenceMoney,totalMoneyLastDay);
		     if(totalMoney > totalMoneyLastDay){
		    	 $("#span-24hJE").attr("class","glyphicon glyphicon-arrow-up");
		    	 $("#span-24hJE").css("color","green");
   	    	 $("#i-24hJE").html('<font size="3" color="green">'+(differenceMoneyPer*100).toFixed(2)+'%</font>');
		    	 	     
		     }else if (totalMoney == totalMoneyLastDay){
		    	 $("#span-24hJE").attr("class","");
		    	 $("#i-24hJE").html('<font size="3" color="black">0%</font>');	
		     }else{
		         $("#span-24hJE").attr("class","glyphicon glyphicon-arrow-down");
		    	 $("#span-24hJE").css("color","red");
		    	 $("#i-24hJE").html('<font size="3" color="red">'+(differenceMoneyPer*100).toFixed(2)+'%</font>');		    	
		     }
		   
		    // alert(totalMoney+","+totalMoneyLastDay+":"+differenceMoneyPer)
		     //$("#DifferenceMoney").html('<a>'+totalMoney+'</a>');
		     
		     showChart_mo(sData);
 			
 			
 		}
 		//显示24小时支付次数对比
 		function initDifferenceTimes(){
 			var url = "../rest/common/select";
		     var param = { 
				"sqlKey": "payRecord.payTimes24"
			 };
		     var sData;
		     sData=loadData(url,param);
		     if(sData == ""){
		    	 return;
		     }
		     var totalTimes = 0;
		     var totalTimesLastDay = 0;
		     for(var i = 0;i < sData.length; i ++){
		    	 totalTimes = add(totalTimes , sData[i].TIMES);
		    	 totalTimesLastDay = add(totalTimesLastDay , sData[i].TIMESL);
		     };
		     //alert(totalMoney)
		     
		     $("#totalTimes").html('<a>'+totalTimes+'</a>');
		     var differenceTimes = substract(totalTimes,totalTimesLastDay);
		     var differenceTimesPer = divide(differenceTimes,totalTimesLastDay);
		     if(totalTimes > totalTimesLastDay){
		    	 $("#span-24hXS").attr("class","glyphicon glyphicon-arrow-up");
		    	 $("#span-24hXS").css("color","green");
		    	 $("#i-24hXS").html('<font size="3" color="green">'+(differenceTimesPer*100).toFixed(2)+'%</font>');
		    	 	     
		     }else if (totalTimes == totalTimesLastDay){
		    	 $("#span-24hXS").attr("class","");
		    	 $("#i-24hXS").html('<font size="3" color="black">0%</font>');	
		     }else{
		         $("#span-24hXS").attr("class","glyphicon glyphicon-arrow-down");
		    	 $("#span-24hXS").css("color","red");
		    	 $("#i-24hXS").html('<font size="3" color="red">'+(differenceTimesPer*100).toFixed(2)+'%</font>');		    	
		     }
		   
		    // alert(totalMoney+","+totalMoneyLastDay+":"+differenceMoneyPer)
		     //$("#DifferenceMoney").html('<a>'+totalMoney+'</a>');
		     return sData;
//		     showChart_cou(sData);
 			
 			
 		}
 		
 		//显示24小时人均支付金额对比
 		function initDifferenceAvg(){
 			var url = "../rest/common/select";
		     var param = { 
				"sqlKey": "payRecord.payMoneyPer24"
			 };
		     var sData;
		     sData=loadData(url,param);
		     if(sData == ""){
		    	 return;
		     }
		     var totalMoney = 0;
		     var totalMoneyLastDay = 0;
		     var totalTimes = 0;
		     var totalTimesLastDay = 0;
		     var avg = 0;
		     var avgLastDay = 0;
		     for(var i = 0;i < sData.length; i ++){
		    	 totalTimes = add(totalTimes , sData[i].TIMES);
		    	 totalTimesLastDay = add(totalTimesLastDay , sData[i].TIMESL);
		    	 totalMoney = add(totalMoney , sData[i].ZJE);
		    	 totalMoneyLastDay = add(totalMoneyLastDay , sData[i].ZJEL);
		     };
		     var totalAvg = divide(totalMoney,totalTimes);//得到一天的人均支付费用
		     var totalAvgLastDay = divide(totalMoneyLastDay,totalTimesLastDay);//得到前一天的人均支付费用
		     
		     //alert(totalMoney)
		     
		     $("#avg").html('<a>'+totalAvg.toFixed(2)+'</a>');
		     var differenceAvg = substract(totalAvg,totalAvgLastDay);
		     var differenceAvgPer = divide(differenceAvg,totalAvgLastDay);
		     if(totalAvg > totalAvgLastDay){
		    	 $("#span-24hRJJE").attr("class","glyphicon glyphicon-arrow-up");
		    	 $("#span-24hRJJE").css("color","green");
		    	 $("#i-24hRJJE").html('<font size="3" color="green">'+(differenceAvgPer*100).toFixed(2)+'%</font>');
		    	 	     
		     }else if (totalAvg == totalAvgLastDay){
		    	 $("#span-24hRJJE").attr("class","");
		    	 $("#i-24hRJJE").html('<font size="3" color="black">0%</font>');	
		     }else{
		         $("#span-24hRJJE").attr("class","glyphicon glyphicon-arrow-down");
		    	 $("#span-24hRJJE").css("color","red");
		    	 $("#i-24hRJJE").html('<font size="3" color="red">'+(differenceAvgPer*100).toFixed(2)+'%</font>');		    	
		     }
		   
		    // alert(totalMoney+","+totalMoneyLastDay+":"+differenceMoneyPer)
		     //$("#DifferenceMoney").html('<a>'+totalMoney+'</a>');
		     return sData;
//		     showChart_cou(sData);
 		}
		function loadData(url,param){
			var data=null;
			   $.ajax(
				        {
				            type:"GET",
				           	url:url,
				            contentType: 'application/json',
				            dataType:"json",
				            data:param,
				            async:false,
				            success:function(json){
				            	data = json.content;
				            },
				            error:function(){
				            }
				        }
				    )
			return data;
		}
		//获取近24小时销售金额对比图表
		function showChart_mo(sData) {
		     var nowArr = new Array();
		     var lnowArr = new Array();
		     var timeArr = new Array();
			 var now = sData[0].N;
			 for(var i = 0;i < 24; i++) {
				 
				 if(now == 0){
					 now = "23";
					 timeArr.push(now+":00");
				 }else{
					 now = now-1;
					 timeArr.push(now + ":00");
				 };
				 nowArr[i]=0;
				 lnowArr[i]=0;
					 for(var j = 0;j < sData.length; j++) {
						 if(sData[j].HH+1==now){
							 	nowArr[i]=sData[j].ZJE.toFixed(2);
								lnowArr[i]=sData[j].ZJEL.toFixed(2);
					}
				}
		  }

		    var pieChart = echarts.init(document.getElementById('pie'));
		    pieOption = {
		    		 	title: {
		    		        text: '近24小时销售金额与上24小时对比'
		    		    },
		    		    color:['#00CCFF','#BEBEBE'],
		    		    tooltip: {
		    		        trigger: 'axis'
		    		    },
		    		    legend: {
		    		        data:['近24小时','上24小时']
		    		    },
		    		    grid: {
		    		        left: '3%',
		    		        right: '4%',
		    		        bottom: '3%',
		    		        containLabel: true
		    		    },
		    		    toolbox: {
		    		        feature: {
		    		            saveAsImage: {}
		    		        }
		    		    },
		    		    xAxis: {
		    		        type: 'category',
		    		        boundaryGap: false,
		    		        data: timeArr
		    		    },
		    		    yAxis: {
		    		        type: 'value'
		    		    },
		    		    series: [
		    		        {
		    		            name:'近24小时',
		    		            type:'line',
		    		            data:nowArr
		    		        },
		    		        {
		    		            name:'上24小时',
		    		            type:'line',
		    		            data:lnowArr
		    		        }
		    		    ]
		    	};
		    pieChart.setOption(pieOption);
				
	   }
		
		//获取近24小时销售笔数对比图表
		function showChart_cou(sData) {
		     var nowArr = new Array();
		     var lnowArr = new Array();
		     var timeArr = new Array();
			 var now = sData[0].N;
			 for(var i = 0;i < 24; i++) {
				 
				 if(now == 0){
					 now = "23";
					 timeArr.push(now+":00");
				 }else{
					 now = now-1;
					 timeArr.push(now + ":00");
				 };
				 nowArr[i]=0;
				 lnowArr[i]=0;
					 for(var j = 0;j < sData.length; j++) {
						 if(sData[j].HH+1==now){
							 	nowArr[i]=sData[j].TIMES;
								lnowArr[i]=sData[j].TIMESL;
					}
				}
		  }

		    var pieChart = echarts.init(document.getElementById('pie'));
		    pieOption = {
		    		 	title: {
		    		        text: '近24小时销售笔数与上24小时对比'
		    		    },
		    		    color:['#00CCFF','#BEBEBE'],
		    		    tooltip: {
		    		        trigger: 'axis'
		    		    },
		    		    legend: {
		    		        data:['近24小时','上24小时']
		    		    },
		    		    grid: {
		    		        left: '3%',
		    		        right: '4%',
		    		        bottom: '3%',
		    		        containLabel: true
		    		    },
		    		    toolbox: {
		    		        feature: {
		    		            saveAsImage: {}
		    		        }
		    		    },
		    		    xAxis: {
		    		        type: 'category',
		    		        boundaryGap: false,
		    		        data: timeArr
		    		    },
		    		    yAxis: {
		    		        type: 'value'
		    		    },
		    		    series: [
		    		        {
		    		            name:'近24小时',
		    		            type:'line',
		    		            data:nowArr
		    		        },
		    		        {
		    		            name:'上24小时',
		    		            type:'line',
		    		            data:lnowArr
		    		        }
		    		    ]
		    	};
		    pieChart.setOption(pieOption);
				
	   }
		
		//获取近24小时人均支付对比图表
		function showChart_avg(sData) {
		     var nowArr = new Array();
		     var lnowArr = new Array();
		     var timeArr = new Array();
			 var now = sData[0].N;
			 for(var i = 0;i < 24; i++) {
				 
				 if(now == 0){
					 now = "23";
					 timeArr.push(now+":00");
				 }else{
					 now = now-1;
					 timeArr.push(now + ":00");
				 };
				 nowArr[i]=0;
				 lnowArr[i]=0;
					 for(var j = 0;j < sData.length; j++) {
						 if(sData[j].HH+1==now){
							 	nowArr[i]=sData[j].AVG.toFixed(2);
								lnowArr[i]=sData[j].AVGL.toFixed(2);
					}
				}
		  }

		    var pieChart = echarts.init(document.getElementById('pie'));
		    pieOption = {
		    		 	title: {
		    		        text: '近24小时人均支付与上24小时对比'
		    		    },
		    		    color:['#00CCFF','#BEBEBE'],
		    		    tooltip: {
		    		        trigger: 'axis'
		    		    },
		    		    legend: {
		    		        data:['近24小时','上24小时']
		    		    },
		    		    grid: {
		    		        left: '3%',
		    		        right: '4%',
		    		        bottom: '3%',
		    		        containLabel: true
		    		    },
		    		    toolbox: {
		    		        feature: {
		    		            saveAsImage: {}
		    		        }
		    		    },
		    		    xAxis: {
		    		        type: 'category',
		    		        boundaryGap: false,
		    		        data: timeArr
		    		    },
		    		    yAxis: {
		    		        type: 'value'
		    		    },
		    		    series: [
		    		        {
		    		            name:'近24小时',
		    		            type:'line',
		    		            data:nowArr
		    		        },
		    		        {
		    		            name:'上24小时',
		    		            type:'line',
		    		            data:lnowArr
		    		        }
		    		    ]
		    	};
		    pieChart.setOption(pieOption);
				
	   }
		//除法
		function divide(arg1,arg2){  
			 var t1=0,t2=0,r1,r2;  
			 try{t1=arg1.toString().split(".")[1].length}catch(e){}  
			 try{t2=arg2.toString().split(".")[1].length}catch(e){}  
			 with(Math){  
			 r1=Number(arg1.toString().replace(".",""))  
			 r2=Number(arg2.toString().replace(".","")) 
			 return multiply((r1/r2),pow(10,t2-t1));  
			 }  
			 }  /* 何问起 hovertree.com */
			 //乘法 
		function multiply(arg1,arg2)  
			 {  
			 var m=0,s1=arg1.toString(),s2=arg2.toString();  
			 try{m+=s1.split(".")[1].length}catch(e){}  
			 try{m+=s2.split(".")[1].length}catch(e){}  
			 return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m)  
			 }  
			//加法  
		function add(arg1,arg2){  
			var r1,r2,m;  
			try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}  
			try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}  
			m=Math.pow(10,Math.max(r1,r2))  
			return (arg1*m+arg2*m)/m  
			}  
			//减法  
		function substract(arg1,arg2){ 
			  var r1,r2,m,n; 
			  try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0} 
			  try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0} 
			  m=Math.pow(10,Math.max(r1,r2)); 
			  n=(r1>=r2)?r1:r2; 
			  return ((arg1*m-arg2*m)/m).toFixed(n); 
			}