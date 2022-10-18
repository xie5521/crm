<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <%--引入JQUERY--%>
    <script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
    <%--引入BOOTSTRAP框架--%>
    <link rel="stylesheet" href="jquery/bootstrap_3.3.0/css/bootstrap.min.css">
    <script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>

    <!--BOOTSTRAP_DATETIMEPICKER插件-->
    <link rel="stylesheet" href="jquery/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css">
    <script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js"></script>
    <script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/locale/bootstrap-datetimepicker.zh-CN.js"></script>

    <title>演示bs_datetimepicker</title>

    <script type="text/javascript">
        $(function (){
            $("#myDate").datetimepicker({
                language:'zh-CN',   //语言
                format:'yyyy-mm-dd',    //日期的格式
                minView:'month',    //可以选择的最小格式
                autoclose:true,      //设置选完之后是否自动关闭日历
                initialDate:new Date(),  //初始化显示的日期
                todayBtn:true ,      //设置显示“今天”按钮，显示今天的日期
                clearBtn:true       //设置是否显示“清空”按钮
            });
        })
    </script>
</head>
<body>
    <input type="text" id="myDate" readonly>

</body>
</html>
