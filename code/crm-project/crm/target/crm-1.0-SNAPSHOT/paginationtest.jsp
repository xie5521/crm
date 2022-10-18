<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <%--引入JQUERY--%>
    <script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>

    <%--BOOTSTRAP--%>
    <link rel="stylesheet" type="text/css" href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" >
    <script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
    <%--PAGINATION plugin--%>
    <link rel="stylesheet" type="text/css" href="jquery/bs_pagination-master/css/jquery.bs_pagination.min.css">
    <script type="text/javascript" src="jquery/bs_pagination-master/js/jquery.bs_pagination.min.js"></script>
    <script type="text/javascript" src="jquery/bs_pagination-master/localization/en.js"></script>

    <title>演示bs_pagination插件的使用</title>

    <script type="text/javascript">
        $(function (){
            $("#demo_pag1").bs_pagination({
                totalPages:100,      // 总页数
                currentPage:1,        //当前页号，默认1
                rowsPerPage:10,        //每页显示的行数,默认10
                totalRows:1000,         //总页数，默认1000
                visiblePageLinks: 10,   //最多显示的卡片数
                showGoToPage: true,     //是否显示"跳转到“部分，默认为true
                showRowsPerPage: true,  //是否显示“每页显示条数“部分，默认为true
                showRowsInfo: true,     //是否显示记录的信息，默认为true
                onChangePage:function (event, pageObj){       //当用户切换页号是执行这个代码

                }
            });
        });
    </script>
</head>
<body>
<div id="demo_pag1"></div>
</body>
</html>
