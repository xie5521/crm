<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>
<head>
    <base href="<%=basePath%>">

    <title>演示文件上传</title>
</head>
<body>
<%--
    文件上传的表单必须满足三个条件
    1，表单组件标签只能用:<input type="file">
        表单组件标签:     <input type="text|password|radio|checkbox|hidden|button|submit|reset|file">
                      <select>,<textarea>等
     2,请求方式只能用：post
        get:参数通过请求头提交到后台，参数放在URL后边；只能向后台提交文本数据;对参数长度有限制;参数在地址栏，可以看见，相对不安全
            效率相对较高;可以使用缓存，静态资源可以缓存加载，
        post:参数通过请求体提交到后台;既能提交文本数据，也能提交二进制数据;理论上对参数长度无限制;参数在请求体，看不见，相对安全
            效率相对较低;不可以使用缓存，每次都要重新加载
     3,表单的编码格式只能用:multipart/form-data
        根据HTTP协议规定，浏览器每次向后台提交数据，都会对参数进行统一编码；默认采用的编码格式是urlencodeed，这种编码格式只能对文本数据进行编码；
        浏览器每次向后台提交数据，都会首先把所有的参数转换为字符串，然后对这些数据统一进行urlencoded编码；
        文件上传到表单编码格式只能用multipart/form-data
--%>
<form action="workbench/activity/fileUpLoad.do" method="post" enctype="multipart/form-data">
    <input type="file" name="myFile"><br>
    <input type="text" name="userName"><br>
    <input type="submit" value="提交">

</form>
</body>
</html>
