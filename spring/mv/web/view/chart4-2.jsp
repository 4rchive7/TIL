<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<style>
#main_center{
   margin:0 20px;
   width:760px;
   height:480px;
   background:white;
}
</style>    
<script>
function display(input){
   Highcharts.chart('container', {
       chart: {
           type: 'variablepie'
       },
       title: {
           text: '2016, 2015 범죄 소분류에 의한 총합'
       },
       tooltip: {
           headerFormat: '',
           pointFormat: '<span style="color:{point.color}">\u25CF</span> <b> {point.name}</b><br/>' +
               'Area (square km): <b>{point.y}</b><br/>' +
               'Population density (people per square km): <b>'+
               
               '</b><br/>'
       },
       series: [{
           minPointSize: 50,
           innerSize: '80%',
           zMin: 0,
           name: 'countries',
           data : input
           
       }]
   });
}
$(document).ready(function(){
   // Server에 데이터를 요청한다.
   // AJAX로
   $.ajax({
      url:'populationlist.do',
      //url:'crimelist.do',
      success:function(data){
         alert(data);
         display(data);         
      },
      error:function(){
         alert('fail');
      }
   });
});
</script>
<div id="main_center">
<h1>Main Center</h1>
<div id="container" style="min-width: 300px; height: 400px; margin: 0 auto"></div>
</div>

