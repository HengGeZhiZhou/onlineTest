<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <script type="text/javascript" src="http://cache.amap.com/lbs/static/addToolbar.js"></script>
<style type="text/css">
#div{
width:600px;
height:300px;
}

</style>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
    <title>浏览器定位</title>
 <!--   <link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/> --> 
<link rel="stylesheet" href="css/map.css"/>
 
    <script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=b2c4eeb6ce640630a2cc6bc82399ac6c"></script>
    <script type="text/javascript" src="http://cache.amap.com/lbs/static/addToolbar.js"></script>
<body>
<!-- ${msg} -->
<div id="div" >

<div id='container'></div>
</div>
<div id="tip">
</div>
<form action="<%=request.getContextPath()%>/swagger/Test/getValue" method="post">
    <input type="hidden" id="Lng" name="Lng">
    <input type="hidden" id="Lat" name="Lat">
    <input type="submit" value="submit">
</form>
<div class="button-group">
<a><button id="setButton">设置为公司地址</button></a>

    <input type="button" class="button" value="开始编辑多边形" onClick="editor.startEditPolygon()"/>
    <input type="button" class="button" value="结束编辑多边形" onClick="editor.closeEditPolygon()"/>
</div>
<script type="text/javascript">
/***************************************
由于Chrome、IOS10等已不再支持非安全域的浏览器定位请求，为保证定位成功率和精度，请尽快升级您的站点到HTTPS。
***************************************/
    var editorTool,map, geolocation;
    //加载地图，调用浏览器定位服务
    map = new AMap.Map('container', {
        resizeEnable: true
    });
    map.plugin('AMap.Geolocation', function() {
        geolocation = new AMap.Geolocation({
            enableHighAccuracy: true,//是否使用高精度定位，默认:true
            timeout: 10000,          //超过10秒后停止定位，默认：无穷大
            buttonOffset: new AMap.Pixel(10, 20),//定位按钮与设置的停靠位置的偏移量，默认：Pixel(10, 20)
            zoomToAccuracy: true,      //定位成功后调整地图视野范围使定位位置及精度范围视野内可见，默认：false
            buttonPosition:'RB'
        });
        map.addControl(geolocation);
        geolocation.getCurrentPosition();
        AMap.event.addListener(geolocation, 'complete', onComplete);//返回定位信息
        
        AMap.event.addListener(geolocation, 'error', onError);      //返回定位出错信息
    });
    //解析定位结果
    function onComplete(data) {
        var str=['定位成功'];
        str.push('经度：' + data.position.getLng());
        str.push('纬度：' + data.position.getLat());
        if(data.accuracy){
             str.push('精度：' + data.accuracy + ' 米');
        }//如为IP精确定位结果则没有精度信息
        str.push('是否经过偏移：' + (data.isConverted ? '是' : '否'));
        document.getElementById('Lat').value=data.position.getLat();
        document.getElementById('Lng').value=data.position.getLng();
        document.getElementById('tip').innerHTML = str.join('<br>');


        AMap.event.addListener(geolocation, 'complete', onCom);//返回定位信息
        ediTool(data);
        
        /*
        ooonClick(data);
        alert(data.position.getLng()+"0000");
        window.location="{pageContext.request.contextPath}/setCoLoc.action?longitude="+data.position.getLng()+"&latitude="+data.position.getLat();
        */
    }
    //解析定位错误信息
    function onError(data) {
        document.getElementById('tip').innerHTML = '定位失败';
    }
    
    var contextMenu = new AMap.ContextMenu();  //创建右键菜单
    
    //右键添加Marker标记
    contextMenu.addItem("添加标记", function() {
    	
    	 var contextMenu = new AMap.ContextMenu();  //创建右键菜单
    	    
         
         //右键设置公司位置
    	    contextMenu.addItem("设置为公司地址", function() {
    	    
    	    	ediTo();

    	    	
    	      /*  map.zoomIn();*/
    	  /*  window.location="{pageContext.request.contextPath}/setCoLoc.action?longitude="+data.position.getLng()+"&latitude="+data.position.getLat();
    	  */  
    	 
    	  
    	  
    	        
    	    }, 0);
    	   
    	    
        var marker = new AMap.Marker({
            map: map,
            position: contextMenuPositon //基点位置
        });
        
        
        map.setCenter(marker.getPosition());
        //绑定鼠标右击事件——弹出右键菜单
        marker.on('rightclick', function(e) {
            contextMenu.open(map, e.lnglat);
            contextMenuPositon = e.lnglat;
        });
        
       
     }, 3);

    //地图绑定鼠标右击事件——弹出右键菜单
    map.on('rightclick', function(e) {
        contextMenu.open(map, e.lnglat);
        contextMenuPositon = e.lnglat;
        
    });
    /*
    map.on('click', function(e) {
        alert('您在[ '+e.lnglat.getLng()+','+e.lnglat.getLat()+' ]的位置点击了地图！');
    });
    */
    
    
    
   
    	
    	 
    	   
   
    
    	function ediTo(){
    	
    	
    	var editor={};
   	 editor._polygon=(function(){
   	        var arr = [ //构建多边形经纬度坐标数组
   	        
   	        [103.82822,30.67479+0.0001],
   	        [103.82821+0.0001,30.67479+0.0002],
   	        [103.82821+0.0002,30.67479+0.0003],
   	        
   	        [103.82822+0.0003,30.67479],
   	        
   	        ]
   	        return new AMap.Polygon({
   	            map: map,
   	            path: arr,
   	            strokeColor: "#0000ff",
   	            strokeOpacity: 1,
   	            strokeWeight: 3,
   	            fillColor: "#f5deb3",
   	            fillOpacity: 0.35
   	        });
   	    })();
   	  
   	    map.setFitView();

   	 
    }
    editor._polygonEditor= new AMap.PolyEditor(map, editor._polygon);
	   
	    editor.startEditPolygon=function(){
	        editor._polygonEditor.open();
	    }
	    editor.closeEditPolygon=function(){
	        editor._polygonEditor.close();
	    }
    
</script>





</body>
</html>