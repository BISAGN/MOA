<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>

<!doctype html>
<html>
<tiles:importAttribute name="title" />
<head>   
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="author" content="Ministry of Ayush">
<meta property="og:locale" content="en_US">
<meta property="og:type" content="website">
<meta property="og:title" content="Home - Ministry of Ayush, India">
<meta property="og:url" content="https://apps.bisag.co.in/MOA/landingpage">
<meta property="og:site_name" content="Ministry of Ayush, India">
<meta property="article:modified_time" content="2023-01-09T12:41:28+00:00">
<c:choose>
	<c:when test="${title == 'common'}">
		<meta name="description" content="Ayush Education (Ministry of Ayush - Ayush grid) has focused attention towards education and research in Ayurveda, Unani, Siddha, Sowa-Rigpa, Homoeopathy, Yoga and Naturopathy">	
		<meta name="keywords" content="Ayush Education, Ayush Education Portal, Ayush Grid, Ayush Grid Education, Ayush System Education, Ayush Education Life Cycle Management, Ayush Digital Learning Platform Ayush Grid, Ayush Collaboration System, Ministry of Ayush, Ayurveda, Yoga and Naturopathy, Unani, Siddha, Sowa-Rigpa, Homoeopathy">
	</c:when>
	<c:when test="${title == 'ncism'}">
		<meta name="description" content="Ayush Education (Ministry of Ayush - Ayush grid) has focused attention towards education and research in Ayurveda, Unani, Siddha, Sowa-Rigpa">	
		<meta name="keywords" content="Ayush Education, Ayush Education Portal, Ayush Grid, Ayush Grid Education, Ayush System Education, Ayush Education Life Cycle Management, Ayush Digital Learning Platform Ayush Grid, Ayush Collaboration System, Ministry of Ayush, Ayurveda, Unani, Siddha, Sowa-Rigpa">
	</c:when>
	<c:when test="${title == 'nch'}">
		<meta name="description" content="Ayush Education (Ministry of Ayush - Ayush grid) has focused attention towards education and research in Homoeopathy, Yoga and Naturopathy">
		<meta name="keywords" content="Ayush Education, Ayush Education Portal, Ayush Grid, Ayush Grid Education, Ayush System Education, Ayush Education Life Cycle Management, Ayush Digital Learning Platform Ayush Grid, Ayush Collaboration System, Ministry of Homoeopathy, Yoga and Naturopathy">
	</c:when>
</c:choose>
	 	
	<title>Ministry of Ayush, India</title>
	
	<!-- Favicons -->	
	<link rel="apple-touch-icon" sizes="180x180" href="admin/assets/img/favicon_io/apple-touch-icon.png">
	<link rel="icon" type="image/png" sizes="32x32" href="admin/assets/img/favicon_io/favicon-32x32.png">
	<link rel="icon" type="image/png" sizes="16x16" href="admin/assets/img/favicon_io/favicon-16x16.png">
	<!-- <link rel="manifest" href="admin/assets/img/favicon_io/site.webmanifest"> -->
	
	<!-- Initial CSS Files -->
	<link href="admin/assets/font/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">	
	
	<!-- Vendor CSS Files -->
	<link href="admin/assets/vendor/animate.css/animate.min.css" rel="stylesheet">
	<link href="admin/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="admin/assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">
	<link href="admin/assets/vendor/hover.css" rel="stylesheet" media="all">
	<!-- <link href="admin/assets/vendor/svg-animation.css" rel="stylesheet" media="all"> -->
	
	<!-- Main CSS Files -->
	<link href="admin/assets/css/style.css" rel="stylesheet">
	<link href="admin/assets/css/responsive.css" rel="stylesheet">
	<link href="admin/assets/css/custom_pages_style.css" rel="stylesheet">	
	
	<!-- Initial JS Files -->
	<script type="text/javascript" src="admin/assets/js/jquery-3.6.0.min.js"></script>
	
	<!-- Google Translate JS Files -->
<!-- 	<script type="text/javascript" src="admin/assets/js/g_translate.js"></script> -->
	
	<!-- Theme switch mode Files -->
	<link href="admin/assets/vendor/themeSwitchMode/switchmodestyle.css" rel="stylesheet">
	<link href="admin/assets/css/themeswitch-mode.css" rel="stylesheet">
	<script type="text/javascript" src="admin/assets/vendor/themeSwitchMode/themeswitchermode.js"></script>
	
	<!-- Other CSS Files -->
	<link href="admin/assets/vendor/page-fontsize/page_fontsize.css" rel="stylesheet">
	<script type="text/javascript" nonce="${cspNonce}" >	
		var $ = jQuery;	
		var key = "${_csrf.parameterName}";
		var value = "${_csrf.token}";
	
	</script>
</head>

	

<body class="<c:choose><c:when test="${title == 'common'}">theme-color-v3</c:when><c:when test="${title == 'nch'}">theme-color-v2</c:when><c:otherwise>theme-color-default</c:otherwise></c:choose> landing-main light-mode" id="body">

	<c:if test="${not empty msg}">
		<input type="hidden" name="msg" id="msg" value="${msg}"
			disabled="disabled" />
	</c:if>	

	<!-- header section start -->
	<c:choose>
		<c:when test="${title == 'ncism'}"><tiles:insertAttribute name="ncism_header" /></c:when>
		<c:when test="${title == 'nch'}"><tiles:insertAttribute name="nch_header" /></c:when>
		<c:when test="${title == 'common'}"><tiles:insertAttribute name="header" /></c:when>
		<c:otherwise><tiles:insertAttribute name="header" /></c:otherwise>
	</c:choose>	
		<%-- <c:if test="${title == 'common'}">		
			<tiles:insertAttribute name="header" />		
		</c:if>
		<c:if test="${title == 'ncism'}">		
			<tiles:insertAttribute name="ncism_header" />		
		</c:if>
		<c:if test="${title == 'nch'}">		
			<tiles:insertAttribute name="nch_header" />		
		</c:if>	 --%>
	<!-- header section end -->
	
	<!-- layout section start -->
	<section id="page-content">
		<tiles:insertAttribute name="body" />
		</section>
	<!-- layout section end -->
	
	<!-- footer section start -->
	<c:choose>
		<c:when test="${title == 'ncism'}"><tiles:insertAttribute name="ncism_footer" /></c:when>
		<c:when test="${title == 'nch'}"><tiles:insertAttribute name="nch_footer" /></c:when>
		<c:when test="${title == 'common'}"><tiles:insertAttribute name="footer" /></c:when>
		<c:otherwise><tiles:insertAttribute name="footer" /></c:otherwise>
	</c:choose>	
		
	<!-- footer section end -->		

	<!-- Other JS Files -->
	<script src="admin/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="admin/assets/vendor/swiper/swiper-bundle.min.js"></script>
	<script src="admin/assets/vendor/vanilla-tilt/vanilla-tilt.min.js"></script>
	<script src="admin/assets/vendor/vanilla-tilt/vanilla-tilt.js"></script>	
	<script src="admin/assets/js/parallax.min.js"></script>
	<script src="admin/assets/vendor/page-fontsize/page_fontsize.js"></script>
	<script src="admin/assets/vendor/lazy_load/jquery.lazy.min.js"></script>
	<script src="admin/assets/js/main.js"></script>	
	<script type="text/javascript" src="js/watermark/common.js"></script> <!-- 04_02_2023 -->
</body>

</html>
