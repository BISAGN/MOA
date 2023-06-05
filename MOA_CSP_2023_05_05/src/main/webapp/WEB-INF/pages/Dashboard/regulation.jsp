<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!-- tab components start -->
    <section class="tab-components">
      <div class="container-fluid">
        <!-- title-wrapper start -->
        <div class="title-wrapper pt-30">
          <div class="row align-items-center">
            <div class="col-md-6">
              <div class="title mb-30">
                <h2>Regulation</h2>
              </div>
            </div>
            <!-- end col -->
            <div class="col-md-6">
              <div class="breadcrumb-wrapper mb-30">
                <nav aria-label="breadcrumb">
                  <ol class="breadcrumb">
                    <li class="breadcrumb-item">
                      <a href="#0">Start View</a>
                    </li>
                    <li class="breadcrumb-item"><a href="#0">Regulation Forms</a></li>
                    <li class="breadcrumb-item active" aria-current="page">
                      Form A
                    </li>
                  </ol>
                </nav>
              </div>
            </div>
            <!-- end col -->
          </div>
          <!-- end row -->
        </div>
        <!-- title-wrapper end -->

        <!-- form-elements-wrapper start -->
        <div class="form-elements-wrapper">
          <div class="row">
            <div class="col-lg-6">
              <!-- input style start -->
              <div class="card-style mb-30">
                <h6 class="mb-25">Input Fields</h6>
                <div class="input-style-1">
                  <label>Full Name</label>
                  <input type="text" placeholder="Full Name" />
                </div>
                <!-- end input -->
                <div class="input-style-2">
                  <input type="text" placeholder="Full Name" />
                  <span class="icon"> <i class="lni lni-user"></i> </span>
                </div>
                <!-- end input -->
                <div class="input-style-3">
                  <input type="text" placeholder="Full Name" />
                  <span class="icon"><i class="lni lni-user"></i></span>
                </div>
                <!-- end input -->
              </div>
              <!-- end card -->
              <!-- ======= input style end ======= -->

              <!-- ======= select style start ======= -->
              <div class="card-style mb-30">
                <h6 class="mb-25">Selects</h6>
                <div class="select-style-1">
                  <label>Category</label>
                  <div class="select-position">
                    <select>
                      <option value="">Select category</option>
                      <option value="">Category one</option>
                      <option value="">Category two</option>
                      <option value="">Category three</option>
                    </select>
                  </div>
                </div>
                <!-- end select -->
                <div class="select-style-2">
                  <div class="select-position">
                    <select>
                      <option value="">Select category</option>
                      <option value="">Category one</option>
                      <option value="">Category two</option>
                      <option value="">Category three</option>
                    </select>
                  </div>
                </div>
                <!-- end select -->
              </div>
              <!-- end card -->
              <!-- ======= select style end ======= -->

              <!-- ======= select style start ======= -->
              <div class="card-style mb-30">
                <h6 class="mb-25">Time and Date</h6>
                <div class="input-style-1">
                  <label>Date</label>
                  <input type="date" />
                </div>
                <!-- end input -->
                <div class="input-style-2">
                  <input type="date" />
                  <span class="icon"><i class="lni lni-chevron-down"></i></span>
                </div>
                <!-- end input -->
                <div class="input-style-2">
                  <input type="time" />
                </div>
                <!-- end input -->
              </div>
              <!-- end card -->
              <!-- ======= input style end ======= -->

              <!-- ======= input switch style start ======= -->
              <div class="card-style mb-30">
                <h6 class="mb-25">Toggle switch input</h6>
                <div class="form-check form-switch toggle-switch mb-30">
                  <input class="form-check-input" type="checkbox" id="toggleSwitch1" />
                  <label class="form-check-label" for="toggleSwitch1">Default switch checkbox input</label>
                </div>
                <div class="form-check form-switch toggle-switch">
                  <input class="form-check-input" type="checkbox" id="toggleSwitch2" checked />
                  <label class="form-check-label" for="toggleSwitch2">Default switch checkbox input</label>
                </div>
              </div>
              <!-- ======= input switch style end ======= -->
            </div>
            <!-- end col -->
            <div class="col-lg-6">
              <!-- ======= textarea style start ======= -->
              <div class="card-style mb-30">
                <h6 class="mb-25">Textarea</h6>
                <div class="input-style-1">
                  <label>Message</label>
                  <textarea placeholder="Message" rows="5"></textarea>
                </div>
                <!-- end textarea -->
                <div class="input-style-3">
                  <textarea placeholder="Message" rows="5"></textarea>
                  <span class="icon"><i class="lni lni-text-format"></i></span>
                </div>
                <!-- end textarea -->
              </div>
              <!-- ======= textarea style end ======= -->

              <!-- ======= checkbox style start ======= -->
              <div class="card-style mb-30">
                <h6 class="mb-25">Checkbox</h6>
                <div class="form-check checkbox-style mb-20">
                  <input class="form-check-input" type="checkbox" value="" id="checkbox-1" />
                  <label class="form-check-label" for="checkbox-1">
                    Default Checkbox</label>
                </div>
                <!-- end checkbox -->
                <div class="form-check checkbox-style mb-20">
                  <input class="form-check-input" type="checkbox" value="" id="checkbox-2" disabled />
                  <label class="form-check-label" for="checkbox-2">
                    Disabled Checkbox</label>
                </div>
                <!-- end checkbox -->
                <div class="form-check checkbox-style checkbox-success mb-20">
                  <input class="form-check-input" type="checkbox" value="" id="checkbox-3" />
                  <label class="form-check-label" for="checkbox-3">
                    Success Checkbox</label>
                </div>
                <!-- end checkbox -->
                <div class="form-check checkbox-style checkbox-warning mb-20">
                  <input class="form-check-input" type="checkbox" value="" id="checkbox-4" />
                  <label class="form-check-label" for="checkbox-4">
                    Warning Checkbox</label>
                </div>
                <!-- end checkbox -->
                <div class="form-check checkbox-style checkbox-danger mb-20">
                  <input class="form-check-input" type="checkbox" value="" id="checkbox-5" />
                  <label class="form-check-label" for="checkbox-5">
                    Danger Checkbox</label>
                </div>
                <!-- end checkbox -->
              </div>
              <!-- ======= checkbox style end ======= -->

              <!-- ======= radio style start ======= -->
              <div class="card-style mb-30">
                <h6 class="mb-25">Radio</h6>
                <div class="form-check radio-style mb-20">
                  <input class="form-check-input" type="radio" value="" id="radio-1" />
                  <label class="form-check-label" for="radio-1">
                    Default Radio</label>
                </div>
                <!-- end radio -->
                <div class="form-check radio-style mb-20">
                  <input class="form-check-input" type="radio" value="" id="radio-2" disabled />
                  <label class="form-check-label" for="radio-2">
                    Disabled Radio</label>
                </div>
                <!-- end radio -->
                <div class="form-check radio-style radio-success mb-20">
                  <input class="form-check-input" type="radio" value="" id="radio-3" />
                  <label class="form-check-label" for="radio-3">
                    Success Radio</label>
                </div>
                <!-- end radio -->
                <div class="form-check radio-style radio-warning mb-20">
                  <input class="form-check-input" type="radio" value="" id="radio-4" />
                  <label class="form-check-label" for="radio-4">
                    Warning Radio</label>
                </div>
                <!-- end radio -->
                <div class="form-check radio-style radio-danger mb-20">
                  <input class="form-check-input" type="radio" value="" id="radio-5" />
                  <label class="form-check-label" for="radio-5">
                    Danger Radio</label>
                </div>
                <!-- end radio -->
              </div>
              <!-- ======= radio style end ======= -->
            </div>
            <!-- end col -->
          </div>
          <!-- end row -->
        </div>
        <!-- form-elements-wrapper end -->
        

      	

          	<!-- accordian-wrapper start -->
        <div class="title-wrapper pt-30">
          <div class="row align-items-center">
            <div class="col-md-12">
              <div class="title mb-30">
                <h2>Regulation</h2>
              </div>
            </div>            
          </div>          
        </div>
        <!-- accordian-wrapper end -->
        
        <div class="buttons-cards-wrapper">
          <div class="row">
         
               <div class="col-lg-12">
                    <div class="card-style mb-30">
                      
                      <div class="accordion" id="accordionPanelsStayOpenExample">
                        <div class="accordion-item accordion-itemstyle">
                          <h2 class="accordion-header" id="panelsStayOpen-headingOne">
                            <button class="accordion-button accordion-itemstylena accordion-primary-button " type="button" data-bs-toggle="collapse" data-bs-target="#panelsStayOpen-collapseOne" aria-expanded="true" aria-controls="panelsStayOpen-collapseOne">
                           PERSONAL DETAILS
                            </button>
                          </h2>
                          <div id="panelsStayOpen-collapseOne" class="accordion-collapse collapse show" aria-labelledby="panelsStayOpen-headingOne">
                            <div class="accordion-body">
                                <div class="card-style mb-30">
               
                <div class="row">
                
         <div class="col-lg-12">
                <div class="input-style-2">
                  <label>UPLOAD PHOTOGRAPH <strong class="mandatory">* </strong></label>
                 <input type="file" accept="image/*" id="photo_path" name="photo_path" class="form-control" onclick="">
                 
                </div>
                </div>
               <div class="col-lg-3">
                <div class="input-style-2">
                  <label>FIRST NAME <strong class="mandatory">* </strong></label>
                  <input type="text" placeholder="FIRST NAME " />
                  <span class="icon"> <i class="lni lni-user"></i> </span>
                </div>
                </div>
                <!-- end input -->
                 <div class="col-lg-6">
                 <div class="input-style-2">
                  <label>GENDER <strong class="mandatory">* </strong> </label>
                    </div>
               <form>
     
                  <label class="form-check-label radiostyle" for="Male">
                   <input class="form-check-input" type="radio" value="Male" id="Male" /> Male</label>
     
                  <label class="form-check-label radiostyle" for="Female">
                   <input class="form-check-input" type="radio" value="Female" id="Female" /> Female</label>
   
                  <label class="form-check-label radiostyle" for="Other">
                 <input class="form-check-input" type="radio" value="Other" id="Other" />   Other </label>
  </form>

                </div>
                <!-- end input -->
                
                 <div class="col-lg-6">
                <div class="input-style-2">
                  <label>FATHER'S NAME <strong class="mandatory">* </strong> </label>
                  <input type="text" placeholder="FIRST NAME " />
                  <span class="icon"> <i class="lni lni-user"></i> </span>
                </div>
                </div>
                
                 <div class="col-lg-6">
                <div class="input-style-2">
                  <label>AADHAAR NUMBER <strong class="mandatory">* </strong></label>
                  <input type="text" placeholder="AADHAAR NUMBER" />
                  <span class="icon"> <i class="bi bi-123"></i> </span>
                </div>
                </div>
                
                 <div class="col-lg-6">
                <div class="input-style-2">
                  <label>MOBILE NUMBER <strong class="mandatory">* </strong> </label>
                  <input type="text" placeholder="MOBILE NUMBER " />
                  <span class="icon"> <i class="bi bi-phone"></i></span>
                </div>
                </div>
                
                 <div class="col-lg-6">
                <div class="input-style-2">
                  <label>ALTERNATIVE MOBILE NUMBER <strong class="mandatory">* </strong></label>
                  <input type="text" placeholder="ALTERNATIVE MOBILE NUMBER  " />
                  <span class="icon"> <i class="bi bi-phone"></i> </span>
                </div>
                </div>
                
             
                
                <div class="col-lg-6">
                <div class="input-style-2">
                  <label>FAX NUMBER <strong class="mandatory">* </strong></label>
                  <input type="text" placeholder="FAX NUMBER " />
                  <span class="icon">  <i class="bi bi-123"></i> </span>
                </div>
                </div>
                
                 <div class="col-lg-6">
                <div class="input-style-2">
                  <label>EMAIL ID <strong class="mandatory">* </strong></label>
                  <input type="text" placeholder="EMAIL ID " />
                  <span class="icon"> <i class="bi bi-envelope-open-fill"></i> </span>
                </div>
                </div>
                 <div class="col-lg-6">
                <div class="input-style-2">
                  <label>Date <strong class="mandatory">* </strong></label>
                  <input type="date" />
                  <span class="icon"> <i class="bi bi-table"></i> </span>
                  
                </div>
                </div>
                <div class="col-lg-6">
                 <div class="select-style-1">
                  <label>NATIONALITY <strong class="mandatory">* </strong></label>
                  <div class="select-position">
                    <select>
                      <option value="">Select Country</option>
                      <option value="">INDIA</option>
                      <option value="">CANADA</option>
                      <option value="">RUSSIA</option>
                    </select>
                  </div>
                </div>
                </div>
                
               
                </div>
              </div>
                            </div>
                          </div>
                        </div>
                        <div class="accordion-item accordion-itemstyle">
                          <h2 class="accordion-header" id="panelsStayOpen-headingTwo">
                            <button class="accordion-button accordion-itemstylena accordion-primary-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#panelsStayOpen-collapseTwo" aria-expanded="false" aria-controls="panelsStayOpen-collapseTwo">
                             ADDRESS DETAILS
                            </button>
                          </h2>
                          <div id="panelsStayOpen-collapseTwo" class="accordion-collapse collapse" aria-labelledby="panelsStayOpen-headingTwo">
                            <div class="accordion-body">
                              <strong>This is the second item's accordion body.</strong> It is hidden by default, until the collapse plugin adds the appropriate classes that we use to style each element. These classes control the overall appearance, as well as the showing and hiding via CSS transitions. You can modify any of this with custom CSS or overriding our default variables. It's also worth noting that just about any HTML can go within the <code>.accordion-body</code>, though the transition does limit overflow.
                            </div>
                          </div>
                        </div>
                        <div class="accordion-item accordion-itemstyle">
                          <h2 class="accordion-header" id="panelsStayOpen-headingThree">
                            <button class="accordion-button accordion-itemstylena accordion-primary-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#panelsStayOpen-collapseThree" aria-expanded="false" aria-controls="panelsStayOpen-collapseThree">
                              Accordion Item #3
                            </button>
                          </h2>
                          <div id="panelsStayOpen-collapseThree" class="accordion-collapse collapse" aria-labelledby="panelsStayOpen-headingThree">
                            <div class="accordion-body">
                              <strong>This is the third item's accordion body.</strong> It is hidden by default, until the collapse plugin adds the appropriate classes that we use to style each element. These classes control the overall appearance, as well as the showing and hiding via CSS transitions. You can modify any of this with custom CSS or overriding our default variables. It's also worth noting that just about any HTML can go within the <code>.accordion-body</code>, though the transition does limit overflow.
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                    <!-- end card -->
                  </div>
            <!-- end col -->

          </div>
          <!-- end row -->
        </div>
          
          
      	
      	
      	      	     
      </div>
      <!-- end container -->
    </section>
    <!-- tab components end -->
          	
      </div>
    </section>
    <!-- button-cards-wrapper end -->
 
 
 