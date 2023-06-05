<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- sidebar-nav start -->
  <aside class="sidebar-nav-wrapper">
 <div class="navbar-logo">
      <a href="commonDashboard">      
        <img src="assets/db_img/logo.png" class="img-fluid org-logo" alt="logo" />        
		<img src="assets/img/ayushlogo.png" class="img-fluid theme-logo">	
  <!-- 	<span class="logo-subtext"><img alt="moa-logo" src="assets/img/moa-icon.jpg" class="moa-logo">Ministry of AYUSH</span>		--> 
      </a>
    </div>
    <nav class="sidebar-nav">
      <ul>
<!--         <li class="nav-item nav-item-has-children"> -->
<!--           <a class="collapsed" href="#0" data-bs-toggle="collapse" data-bs-target="#ddmenu_1" aria-controls="ddmenu_1" -->
<!--             aria-expanded="false" aria-label="Toggle navigation"> -->

<!--             <i class="lni lni-grid-alt icon"></i> -->
<!--             <span class="text">Dashboard</span> -->
<!--           </a> -->
<!--           <ul id="ddmenu_1" class="dropdown-nav collapse"> -->
<!--             <li> -->
<!--               <a href="commonDashboard" class="">Default Dashboard</a> -->
<!--             </li> -->
<!--           </ul>  -->
<!--         </li> -->
<%--  ${menu2}  --%>

       
			 ${menu} 
        <span class="divider">
          <hr />
        </span>

      </ul>
    </nav>

  </aside>
  <div class="overlay active"></div>
  <!-- sidebar-nav end -->
  
  	<c:url value="/logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
