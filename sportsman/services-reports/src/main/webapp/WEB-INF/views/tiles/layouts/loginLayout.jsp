<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
 
<html>
 
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<title><tiles:getAsString name="title" /></title>
	<link rel="icon" href="favicon.ico" />
	<link rel="stylesheet" href="<c:url value='assets/lib/bootstrap-3.3.6/css/bootstrap.min.css?v=${v}' />" />
	<link rel="stylesheet" href="<c:url value='assets/css/styles.css?v=${v}' />" />
			
	<!--[if lt IE 9]>
	  <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
    
</head>
  
<body class="login-bg">

        <header id="header">
            <tiles:insertAttribute name="header" />
        </header>
     
        <section id="site-content">
            <tiles:insertAttribute name="body" />
        </section>
        
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script type="text/javascript" src="<c:url value='assets/lib/jquery-1.12.0/jquery.min.js?v=${v}' />"></script>
        <script type="text/javascript" src="<c:url value='assets/lib/bootstrap-3.3.6/js/bootstrap.min.js?v=${v}' />"></script>
        <script type="text/javascript" src="<c:url value='assets/js/custom.js?v=${v}' />"></script>
        
</body>
</html>