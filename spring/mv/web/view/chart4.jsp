<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<style>
#main_center {
	margin: 0 20px;
	width: 760px;
	height: 480px;
	background: white;
}
</style>
<script>
function display(input, input2){	
	Highcharts.chart('container', {
	    chart: {
	        type: 'column'
	    },
	    title: {
	        text: '1인가구 7000명 이상인 동'
	    },
	    subtitle: {
	        text: '누르면 해당 동이 속한 구의 범죄 및 검거율이 나옵니다.'
	    },
	    xAxis: {
	        type: 'category'
	    },
	    yAxis: {
	        title: {
	            text: '(인구)명 / (검거율)%'
	        }

	    },
	    legend: {
	        enabled: false
	    },
	    plotOptions: {
	        series: {
	            borderWidth: 0,
	            dataLabels: {
	                enabled: true,
	                format: '{point.y}'
	            }
	        }
	    },
	    tooltip: {
	        headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
	        pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y}</b><br/>'
	    },

	    series: [{
	    	colorByPoint: true,	        
	        data:input
	    }],	    
	    drilldown: {
	    	 series: input2
	    
	    }
	});
}
$(document).ready(function(){
   
   $.ajax({
      url:'populationlist.do',
      success:function(data){
    	  $.ajax({
    		  url:'populationlist2.do',
    		 success:function(data2){
    		  	display(data, data2);  
    		  },
    	 	 error:function(){
    	 		alert('data2 fail');
    	 	 }    	  
    	  });
                  
      },
      error:function(){
         alert('data1 fail');
      }
	});
});   


</script>
<div id="main_center">
	<h1>Main Center</h1>
	<div id="container"
		style="min-width: 300px; height: 400px; margin: 0 auto"></div>
</div>

