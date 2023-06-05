<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

<link rel="stylesheet" href="assets/vendor/common_custom_style.css">

<!-- ========== tab components start ========== -->
      <section class="dashboard-page">
        <div class="container-fluid">
          <!-- ========== title-wrapper start ========== -->
          <div class="title-wrapper pt-30">
            <div class="row align-items-center">
              <div class="col-md-6">
                <div class="title mb-30">
                  <h2>Event</h2>
                </div>
              </div>
              <!-- end col -->
              <div class="col-md-6">
                <div class="breadcrumb-wrapper mb-30">
                  <nav aria-label="breadcrumb">
                    <ol class="breadcrumb">
                      <li class="breadcrumb-item">
                        <a href="#">Dashboard </a>
                      </li>
                      <li class="breadcrumb-item active" aria-current="page">
                        Event
                      </li>
                    </ol>
                  </nav>
                </div>
              </div>
              <!-- end col -->
            </div>
            <!-- end row -->
          </div>
          <!-- ========== title-wrapper end ========== -->

          <div class="row" id="event_div">
          <input type="hidden" name="event_id" id="event_id" value="${event_id}">
             <!-- <div class="col-xxl-12 col-lg-12" id="event_div">
            <div class="custom-event">
              <div class="profile-wrapper feed-card-style mb-30">
              <div class="card-image">
										<a href="#0"> <img class="img-fluid"
											src="getEventImg?id=19" alt="" />
										</a>
									</div>
                <div class="profile-cover">
                  <img src="assets/db_img/allumini-banner.jpg" alt="cover-image"/>
                                  </div>
                <div class="d-md-flex">
                  <div class="profile-photo">
                                        <div class="profile-meta pt-20">
                      <h3 class="text-bold mb-1">Batch Reunion</h3>
                      <p class="text-sm">Hosted By <span class="text-heighlight"> Apurv</span></p>
                    </div>
                  </div>
                  <div class="profiles-activities w-100 pt-15 mb-20">
                    <ul class="buttons-group d-flex align-items-center trafic-count ms-auto">
                    <li class="count">
                                                <a href="#0" class="main-btn danger-btn btn-hover btn-iconic-icon"><i class="lni lni-star-filled"></i>  Interested <strong>234</strong></a>
                      </li> 
                     <li class="count">
                        <a href="#0" class="main-btn secondary-btn btn-hover btn-iconic-icon"><i class="lni lni-bookmark"></i>  Participants <strong>134</strong></a>
                      </li>
                   
                      <li><a href="#" class="main-btn dark-btn btn-hover btn-iconic-icon"><i class="lni lni-clipboard"></i>  Copy</a></li>
                     
                    </ul>
                                      </div>
                             
                </div>
                
                <div class="profile-info">               
                  <p>
                    Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.                    
                  </p>   
                   <hr>              
                </div>
               
                       <div class="custom-event-detail">
													<div class="col-md-12 col-lg-12 col-xl-12">
							<div class="d-flex">                                   
                    <div class="profile-meta">
                      <i class="lni lni-timer "></i>
                    </div>          
                  <div class="custom-event-data w-100">
                    <ul class="buttons-group d-flex align-items-center trafic-count ">
                    <li>                        
                        <h6 class="text-sm">Oct 11 at 2 PM - Oct 11 at 7 PM</h6>
                        <p class="text-sm post-time">10 Days</p>
                      </li>                                           
                    </ul>                                       
                  </div>
                </div>
                </div>                                
                <hr>
                <div class="col-md-12 col-lg-12 col-xl-12">
							<div class="d-flex">                                    
                    <div class="profile-meta">
                      <i class="lni lni-map-marker"></i>                    </div>                  
                  <div class="custom-event-data w-100">
                    <ul class="buttons-group d-flex align-items-center trafic-count ">
                    <li>                        
                       <h6 class="text-sm">Delhi University</h6>
										<p class="text-sm post-time">Benito Juarez Marg, South Campus, South Moti Bagh, New Delhi, Delhi 110021</p>
                      </li>                     
                      <li class="ms-auto">                     
                       <span><a href="#" class="text-heighlight mt-1">Show Map</a></span>
                      </li>
                    </ul>                                        
                  </div>
                </div>
                </div>
                <hr>
                <div class="col-md-12 col-lg-12 col-xl-12 pb-20">
							<div class="d-flex">                                     
                    <div class="profile-meta">
                      <i class="lni lni-wechat "></i>
                    </div>                  
                  <div class="custom-event-data w-100">
                    <ul class="buttons-group d-flex align-items-center trafic-count ">
                    <li>                        
                       <h6 class="text-sm">Hosted By</h6>
										<p class="text-sm post-time">Apurv</p>
                      </li>                     
                      <li class="ms-auto">
                      <span><a href="#" class="text-heighlight mt-1">Message Host</a></span>
                      </li>
                    </ul>                                        
                  </div>
                </div>
                </div>
              
						</div>                      
                
              </div>
                        
            </div>
            
            </div>  -->
          </div>
        </div>
        <!-- end container -->
      </section>
      <!-- ========== tab components end ========== -->

<script nonce="${cspNonce}">
$(document).ready(function() {
	
		<c:forEach var="j" items="${event_div}" varStatus="num">
		
			var id = '${j.id}';
			var img = '${j.upload_img}';
			var title = '${j.title}';
			var hostedby = '${j.login_name}';
			var description = '${j.description}';
			var day = '${j.day}';
			var time = '${j.time}';
			var venue = '${j.venue}';
			
			var divapp ='<div id="event_div">'
			+'<div class="custom-event">'
			+'<div class="profile-wrapper feed-card-style mb-30">'
			+'<div class="row">'
			+'<div class="col-xl-12 col-lg-12"><div class="card-image"><img src="getEventImg?id='+id+'" class="img-fluid" alt="cover-image"/></div></div>'
			+'<div class="col-xl-6 col-lg-6 col-md-6 col-12"><div class="custom-event-title">'
			+'<h3 class="text-bold">'+title+'</h3><p class="text-sm">Hosted By <span class="text-heighlight text-sm">'+hostedby+'</span></p>'
			+'</div></div>'
			+'<div class="col-xl-6 col-lg-6 col-md-6 col-12"><div class="custom-event-btn">'
			+'<ul class="buttons-group">'
			+'<li class="count"><a href="#0" onclick="getevent_name(&apos;interested&apos;);" id="interested" class="main-btn danger-btn btn-hover btn-iconic-icon"><i class="lni lni-star-filled"></i>  Interested <strong><span id="interest1"></span></strong></a></li>' 
			+'<li class="count"><a href="#0" onclick="getevent_name(&apos;participate&apos;);" id="participate" class="main-btn secondary-btn btn-hover btn-iconic-icon"><i class="lni lni-bookmark"></i>  Participants <span id="participate1"></span><strong></strong></a></li>'
			+'<li><a href="#" class="main-btn dark-btn btn-hover btn-iconic-icon"><i class="lni lni-clipboard"></i>Copy Link</a></li>'
			+'</ul>'
			+'</div></div>'
			+'<div class="col-xl-12 col-lg-12"><div class="custom-event-info">'               
			+'<p>'+description+'</p>'   
			+'<hr>'              
			+'</div></div></div>'
			+'<div class="custom-main-info"><div class="row"><div class="col-md-6 col-lg-4 col-xl-4">'                                  
			+'<div class="custom-event-detail"><div class="d-flex"><div class="profile-meta"><i class="lni lni-timer "></i></div>'          
			+'<div class="custom-event-data">'
			+'<ul>'
			+'<li><h6 class="text-sm">'+day+' '+time+'</h6><p class="text-sm">10 Days</p></li>'                                           
			+'</ul>'                                       
			+'</div></div></div></div>'                                
			+'<div class="col-md-6 col-lg-4 col-xl-4">'
			+'<div class="custom-event-detail"><div class="d-flex"><div class="profile-meta"> <i class="lni lni-map-marker"></i></div>'                  
			+'<div class="custom-event-data">'
			+'<ul>'
			+'<li><h6 class="text-sm">Delhi University</h6><p class="text-sm">'+venue+'</p></li>'                     
			+'<li><span><a href="#" class="text-heighlight text-sm">Show Map</a></span></li>'
			+'</ul>'                                        
			+'</div></div></div></div>'
			+'<div class="col-md-6 col-lg-4 col-xl-4">'                                     
			+'<div class="custom-event-detail"><div class="d-flex"><div class="profile-meta"><i class="lni lni-wechat "></i></div>'                  
			+'<div class="custom-event-data">'
			+'<ul>'
			+'<li><h6 class="text-sm">Hosted By</h6><p class="text-sm">'+hostedby+'</p></li>'                     
			+'<li><span><a href="#" class="text-heighlight text-sm">Message Host</a></span></li>'
			+'</ul>'                                        
			+'<div></div></div></div></div></div></div></div>';
		$('#event_div').append(divapp);
		</c:forEach>
		
		count_of_IntParti();
	
});

function getevent_name(cat) {
	var event_name = $("#event_id").val();
	$.post('getevent_name?' + key + "=" + value, {
		event_id : event_name,cat:cat
	}).done(function(j) {
				alert(j);
	});
	
	count_of_IntParti();
	
}


function count_of_IntParti(){
	var event_id = $("#event_id").val();
	
	$.post("getTotaleventinterestedCount?"+key+"="+value,{id:event_id},function(j) {
		$("#interest1").text(j);
	 });
		
	$.post("getTotaleventparticipateCount?"+key+"="+value,{id:event_id},function(j) {
			$("#participate1").text(j);
	 });
}

</script>
 
 