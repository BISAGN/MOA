<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<section class="dashboard-page">
<div class="container-fluid">

	<div class="title-wrapper pt-30">
		<div class="row align-items-center">
			<div class="col-md-6">
				<div class="title mb-30">
					<h2>My Profile</h2>
				</div>
			</div>
			<!-- end col -->
			<div class="col-md-6">
				<div class="breadcrumb-wrapper mb-30">
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="commonDashboard">Dashboard</a></li>
							<li class="breadcrumb-item active" aria-current="page">My Profile</li>
						</ol>
					</nav>
				</div>
			</div>
			<!-- end col -->
		</div>
		<!-- end row -->
	</div>

	<div class="form-elements-wrapper">
		<div class="row">
			<div class="col-12">
				<!-- input style start -->
                <form:form name="" id=""> 
					<div class="card-style mb-30">
					<h6 class="mb-25">My Profile</h6>

						<div class="row align-items-center">
								
							<div class="col-5 col-sm-4 col-md-4 col-lg-2">
								<div class="input-style-2">
									<label for="text-input">User Name</label>
								</div>
								<!-- end input -->
							</div>
							<div class="col-2 col-sm-4 col-md-4 col-lg-1">
								<div class="input-style-2">
									<label for="text-input">:</label>
								</div>
								<!-- end input -->
							</div>
							<div class="col-5 col-sm-4 col-md-4 col-lg-2">
								<div class="input-style-2">
									<span class="auto-input">${mp.login_name}</span>
								</div>
								<!-- end input -->
							</div>

						</div>		
						
						<div class="row align-items-center">
								
							<div class="col-5 col-sm-4 col-md-4 col-lg-2">
								<div class="input-style-2">
									<label for="text-input">User ID</label>
								</div>
								<!-- end input -->
							</div>
							<div class="col-2 col-sm-4 col-md-4 col-lg-1">
								<div class="input-style-2">
									<label for="text-input">:</label>
								</div>
								<!-- end input -->
							</div>
							<div class="col-5 col-sm-4 col-md-4 col-lg-2">
								<div class="input-style-2">
									<span class="auto-input">${mp.userName}</span>
								</div>
								<!-- end input -->
							</div>

						</div>		

				</div>
	

				</form:form>
			</div>
		</div>
	</div>

</div>
</section>

<!--  Old  -->

<%-- <div class="container">
	<div class="card">
		<div class="card-header">
			<h5><i class="menu-icon fa fa-user"></i> My Profile</h5>
		</div> <!-- end of card-header -->
		<div class="card-body card-block" style="font-weight: bold;font-size: 18px;">
			<div class="row mb-3">
						<div class="col-md-2">
							<label for="text-input">User Name</label>
						</div>
						<div class="col-md-1">
							:
						</div>
						<div class="col-md-6">
							<label for="text-input">${mp.login_name}</label>
						</div>
			</div>
			<div class="row mb-3">
						<div class="col-md-2">
							<label for="text-input">User ID</label>
						</div>
						<div class="col-md-1">
							:
						</div>
						<div class="col-md-6">
							<label for="text-input">${mp.userName}</label>
						</div>
			</div>
			<c:if test="${mp.army_no != null}">
<!-- 			<div class="row mb-3"> -->
<!-- 						<div class="col col-md-2"> -->
<!-- 							<label for="text-input">Army No</label> -->
<!-- 						</div> -->
<!-- 						<div class="col-md-1"> -->
<!-- 							: -->
<!-- 						</div> -->
<!-- 						<div class="col-12 col-md-6"> -->
							<label for="text-input">${mp.army_no}</label>
<!-- 						</div> -->
<!-- 			</div> -->
			</c:if>
		</div> <!-- end of card-body -->
	</div> <!-- end of card -->
</div> --%> <!-- end of container -->