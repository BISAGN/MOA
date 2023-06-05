<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<body>



<form action="feedbackform_dtlAction?${_csrf.parameterName}=${_csrf.token}" name="feedback_detail" id="step-form-horizontal" class="step-form-horizontal" 
                     method='POST' modelAttribute="feedback_dtl_CMD" enctype="multipart/form-data">
                     
                     
                     
           <div class="row justify-content-center">
          
          <div class="col-6">
          
          <div class="card-style mb-30">
                <h6 class="mb-25">Input Fields</h6>
                
                <div id="addtext">
                <div class="input-style-1" >
                  <label>Full Name<span class="mandatory">*</span></label>
                  <input type="text" placeholder="Full Name" />
                </div>
                </div>
                
                <li>			<a
										class="main-btn success-btn btn-hover btn-sm addminusbut"
										value="ADD" title="ADD"
										id="id_add_attNameMed1" onclick="addtextfunc();"><i
												class="lni lni-plus"></i></a></li>
                <!-- end input -->
                
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
                
                <div class="input-style-1">
                  <label>Message</label>
                  <textarea placeholder="Message" rows="5"></textarea>
                </div>
                
                
                <div class="input-style-2">
					<label>Date picker</label>
					<input type="text" name="date_of_reg" id="date_of_reg" maxlength="10" onclick="clickclear(this, 'DD/MM/YYYY')" 
					class="form-control-sm form-control effect-9 hasDatepicker" onfocus="this.style.color='#000000'"
					onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" 
					onkeyup="clickclear(this, 'DD/MM/YYYY')" onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this); validateUptoR();" aria-required="true" autocomplete="off" value="DD/MM/YYYY">
					<button type="button" class="ui-datepicker-trigger">
					<span class="icon"><i class="lni lni-calendar"></i></span></button>
				</div>
				
				<div class="input-style-form-check">					
					<div class="form-check checkbox-style">
					<input type="checkbox" id="male1" name="gender" class="form-check-input" value="0" required="">
					<label for="male1" class="form-check-label">Male</label>																													
					</div>
					<div class="form-check checkbox-style">															
					<input type="checkbox" id="female1" name="gender" class="form-check-input" value="1" required="">
					<label for="female1" class="form-check-label">Female</label> 														
					</div>
					<div class="form-check checkbox-style">
					<input type="checkbox" id="other1" name="gender" class="form-check-input" value="2" required="">
					<label for="other1" class="form-check-label">Other</label>															
					</div>															
				</div>
				
				<div class="input-style-form-check">					
					<div class="form-check radio-style">
					<input type="radio" id="male" name="gender" class="form-check-input" value="0" required="">
					<label for="male" class="form-check-label">Male</label>																													
					</div>
					<div class="form-check radio-style">															
					<input type="radio" id="female" name="gender" class="form-check-input" value="1" required="">
					<label for="female" class="form-check-label">Female</label> 														
					</div>
					<div class="form-check radio-style">
					<input type="radio" id="other" name="gender" class="form-check-input" value="2" required="">
					<label for="other" class="form-check-label">Other</label>															
					</div>															
				</div>
				
		<div class="table-wrapper table-responsive feedback-table">
		   <table class="table">
            <thead>
            <tr>
              <th  class="bg-none" style="border:none">
                &nbsp;
              </th>
              <th>
                <label> Excellent </label>
              </th>
              <th>
                <label> Very Good </label>
              </th>
              <th>
                <label> Good </label>
              </th>
              <th>
                <label> Fair </label>
              </th>
              <th>
                <label> Poor </label>
              </th>
              <th>
                <label> Very Poor </label>
              </th>
            </tr>
            </thead>
            <tbody>
            <tr>
              <th>
                <label> The course as a whole was: </label>
              </th>
              <td>
                  <div class="form-check radio-style">
					<input type="radio" id="demo1" name="gender" class="form-check-input" value="0" required="">
					<label for="demo1" class="form-check-label"></label>																													
				  </div>
               </td>
                <td>
                  <div class="form-check radio-style">
					<input type="radio" id="demo2" name="gender" class="form-check-input" value="0" required="">
					<label for="demo2" class="form-check-label"></label>																													
				  </div>
               </td>
               <td>
                  <div class="form-check radio-style">
					<input type="radio" id="demo3" name="gender" class="form-check-input" value="0" required="">
					<label for="demo3" class="form-check-label"></label>																													
				  </div>
               </td>
               <td>
                  <div class="form-check radio-style">
					<input type="radio" id="demo4" name="gender" class="form-check-input" value="0" required="">
					<label for="demo4" class="form-check-label"></label>																													
				  </div>
               </td>
                <td>
                  <div class="form-check radio-style">
					<input type="radio" id="demo5" name="gender" class="form-check-input" value="0" required="">
					<label for="demo5" class="form-check-label"></label>																													
				  </div>
               </td>
                <td>
                  <div class="form-check radio-style">
					<input type="radio" id="demo11" name="gender" class="form-check-input" value="0" required="">
					<label for="demo11" class="form-check-label"></label>																													
				  </div>
               </td>
            </tr>
            <tr>
              <th>
                <label> The course content was: </label>
              </th>
              <td>
                  <div class="form-check radio-style">
					<input type="radio" id="demo6" name="gender" class="form-check-input" value="0" required="">
					<label for="demo6" class="form-check-label"></label>																													
				  </div>
               </td>
                <td>
                  <div class="form-check radio-style">
					<input type="radio" id="demo27 name="gender" class="form-check-input" value="0" required="">
					<label for="demo7" class="form-check-label"></label>																													
				  </div>
               </td>
               <td>
                  <div class="form-check radio-style">
					<input type="radio" id="demo8" name="gender" class="form-check-input" value="0" required="">
					<label for="demo8" class="form-check-label"></label>																													
				  </div>
               </td>
               <td>
                  <div class="form-check radio-style">
					<input type="radio" id="demo9" name="gender" class="form-check-input" value="0" required="">
					<label for="demo9" class="form-check-label"></label>																													
				  </div>
               </td>
                <td>
                  <div class="form-check radio-style">
					<input type="radio" id="demo10" name="gender" class="form-check-input" value="0" required="">
					<label for="demo10" class="form-check-label"></label>																													
				  </div>
               </td>
                <td>
                  <div class="form-check radio-style">
					<input type="radio" id="demo12" name="gender" class="form-check-input" value="0" required="">
					<label for="demo12" class="form-check-label"></label>																													
				  </div>
               </td>
            </tr>
            
          </tbody></table>
             </div>   
          </div>
          
          
              <!-- end card -->
          </div>
          </div>
                     
                     
                     
                     <div class="card-style mb-30">
						<h6 class="mb-25">FeedbackForm</h6>
                     <table class="table" id="addoption">
                     <div class="input-style-form-check">'
												<p class="que"><span class="que-no">Q - Select your option
												</span> </p>
												<div class="q-mcq"><div class="row"><div class="col-12 col-sm-6 col-lg-3"><div class="form-check radio-style"><input value="1" id="op1" name="op2" type="radio" class="form-check-input" /> <label for="radio1" class="form-check-label">(A)  
												 op1
												</label></div></div>
												<div class="col-12 col-sm-6 col-lg-3"><div class="form-check radio-style"><input value="2" id="op2" name="op2" type="radio" class="form-check-input" /> <label  for="radio1" class="form-check-label">(B)
												 op2
												</label></div></div>
												<div class="col-12 col-sm-6 col-lg-3"><div class="form-check radio-style"><input value="3" id="op3" name="op3" type="radio" class="form-check-input" /> <label  for="radio1" class="form-check-label">(C)
												 op3
												</label></div></div>
												<div class="col-12 col-sm-6 col-lg-3"><div class="form-check radio-style"><input value="'4" id="op4" name="op4" type="radio" class="form-check-input" /> <label  for="radio1" class="form-check-label">(D)
												 op4
												 </label></div></div>
												 
												 
												 
							<div class="action">
						
								<ul class="buttons-group mainbtn">
						
						
									<li><a
										class="main-btn success-btn btn-hover btn-sm addminusbut"
										value="ADD" title="ADD"
										id="id_add_attNameMed1" onclick="addoptiondy();"><i
												class="lni lni-plus"></i></a></li>
									</ul>
								
							</div>
							</div></div>
                     </div>
                     
                     </table>
                     
                     <input type="hidden"
							id="count_hidden_add_option"
							name="count_hidden_add_option"
							class="form-control autocomplete" value="1">
                     
                     </div>
<!--                      <table summary="" aria-labelledby="label_9" cellpadding="4" cellspacing="0" class="form-matrix-table" data-component="matrix"><tr class="form-matrix-tr form-matrix-header-tr"><th class="form-matrix-th" style="border: none;">&nbsp;</th><th scope="col" class="form-matrix-headers form-matrix-column-headers form-matrix-column_0" style="position: relative;"><div class="editor-container editorHasText" style="display: inline-block;"><div class="inlineEditor" placeholder="Type Col Name" format="text" contenteditable="true" data-gramm="false">Excellent</div></div><button class="questionLine-editButton forRemove remove">×</button></th><th scope="col" class="form-matrix-headers form-matrix-column-headers form-matrix-column_1" style="position: relative;"><div class="editor-container editorHasText" style="display: inline-block;"><div class="inlineEditor" placeholder="Type Col Name" format="text" contenteditable="true" data-gramm="false">Very Good</div></div><button class="questionLine-editButton forRemove remove">×</button></th><th scope="col" class="form-matrix-headers form-matrix-column-headers form-matrix-column_2" style="position: relative;"><div class="editor-container editorHasText" style="display: inline-block;"><div class="inlineEditor" placeholder="Type Col Name" format="text" contenteditable="true" data-gramm="false">Good</div></div><button class="questionLine-editButton forRemove remove">×</button></th><th scope="col" class="form-matrix-headers form-matrix-column-headers form-matrix-column_3" style="position: relative;"><div class="editor-container editorHasText" style="display: inline-block;"><div class="inlineEditor" placeholder="Type Col Name" format="text" contenteditable="true" data-gramm="false">Fair</div></div><button class="questionLine-editButton forRemove remove">×</button></th><th scope="col" class="form-matrix-headers form-matrix-column-headers form-matrix-column_4" style="position: relative;"><div class="editor-container editorHasText" style="display: inline-block;"><div class="inlineEditor" placeholder="Type Col Name" format="text" contenteditable="true" data-gramm="false">Poor</div></div><button class="questionLine-editButton forRemove remove">×</button></th><th scope="col" class="form-matrix-headers form-matrix-column-headers form-matrix-column_5" style="position: relative;"><div class="editor-container editorHasText" style="display: inline-block;"><div class="inlineEditor" placeholder="Type Col Name" format="text" contenteditable="true" data-gramm="false">Very Poor</div></div><button class="questionLine-editButton forRemove remove">×</button></th><th style="border: none;"><button class="questionLine-editButton forAdd add">add column</button></th></tr><tr class="form-matrix-tr form-matrix-value-tr" aria-labelledby="label_9 label_9_row_0"><th scope="row" class="form-matrix-headers form-matrix-row-headers"><div class="editor-container editorHasText" style="display: inline-block;"><div class="inlineEditor" placeholder="Type Row Name" format="text" contenteditable="true" data-gramm="false">The course as a whole was:	</div></div><button class="questionLine-editButton forRemove remove">×</button></th><td class="form-matrix-values"><input readonly="" id="input_9_0_0" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[0]" tabindex="-1" required="" aria-labelledby="label_9_col_0 label_9_row_0" value="Excellent"><label for="input_9_0_0" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_0_1" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[0]" tabindex="-1" required="" aria-labelledby="label_9_col_1 label_9_row_0" value="Very Good"><label for="input_9_0_1" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_0_2" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[0]" tabindex="-1" required="" aria-labelledby="label_9_col_2 label_9_row_0" value="Good"><label for="input_9_0_2" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_0_3" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[0]" tabindex="-1" required="" aria-labelledby="label_9_col_3 label_9_row_0" value="Fair"><label for="input_9_0_3" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_0_4" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[0]" tabindex="-1" required="" aria-labelledby="label_9_col_4 label_9_row_0" value="Poor"><label for="input_9_0_4" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_0_5" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[0]" tabindex="-1" required="" aria-labelledby="label_9_col_5 label_9_row_0" value="Very Poor"><label for="input_9_0_5" class="matrix-choice-label matrix-radio-label"></label></td></tr><tr class="form-matrix-tr form-matrix-value-tr" aria-labelledby="label_9 label_9_row_1"><th scope="row" class="form-matrix-headers form-matrix-row-headers"><div class="editor-container editorHasText" style="display: inline-block;"><div class="inlineEditor" placeholder="Type Row Name" format="text" contenteditable="true" data-gramm="false">The course content was:	</div></div><button class="questionLine-editButton forRemove remove">×</button></th><td class="form-matrix-values"><input readonly="" id="input_9_1_0" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[1]" tabindex="-1" required="" aria-labelledby="label_9_col_0 label_9_row_1" value="Excellent"><label for="input_9_1_0" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_1_1" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[1]" tabindex="-1" required="" aria-labelledby="label_9_col_1 label_9_row_1" value="Very Good"><label for="input_9_1_1" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_1_2" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[1]" tabindex="-1" required="" aria-labelledby="label_9_col_2 label_9_row_1" value="Good"><label for="input_9_1_2" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_1_3" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[1]" tabindex="-1" required="" aria-labelledby="label_9_col_3 label_9_row_1" value="Fair"><label for="input_9_1_3" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_1_4" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[1]" tabindex="-1" required="" aria-labelledby="label_9_col_4 label_9_row_1" value="Poor"><label for="input_9_1_4" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_1_5" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[1]" tabindex="-1" required="" aria-labelledby="label_9_col_5 label_9_row_1" value="Very Poor"><label for="input_9_1_5" class="matrix-choice-label matrix-radio-label"></label></td></tr><tr class="form-matrix-tr form-matrix-value-tr" aria-labelledby="label_9 label_9_row_2"><th scope="row" class="form-matrix-headers form-matrix-row-headers"><div class="editor-container editorHasText" style="display: inline-block;"><div class="inlineEditor" placeholder="Type Row Name" format="text" contenteditable="true" data-gramm="false">The instructor's contribution to the course was:	</div></div><button class="questionLine-editButton forRemove remove">×</button></th><td class="form-matrix-values"><input readonly="" id="input_9_2_0" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[2]" tabindex="-1" required="" aria-labelledby="label_9_col_0 label_9_row_2" value="Excellent"><label for="input_9_2_0" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_2_1" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[2]" tabindex="-1" required="" aria-labelledby="label_9_col_1 label_9_row_2" value="Very Good"><label for="input_9_2_1" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_2_2" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[2]" tabindex="-1" required="" aria-labelledby="label_9_col_2 label_9_row_2" value="Good"><label for="input_9_2_2" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_2_3" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[2]" tabindex="-1" required="" aria-labelledby="label_9_col_3 label_9_row_2" value="Fair"><label for="input_9_2_3" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_2_4" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[2]" tabindex="-1" required="" aria-labelledby="label_9_col_4 label_9_row_2" value="Poor"><label for="input_9_2_4" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_2_5" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[2]" tabindex="-1" required="" aria-labelledby="label_9_col_5 label_9_row_2" value="Very Poor"><label for="input_9_2_5" class="matrix-choice-label matrix-radio-label"></label></td></tr><tr class="form-matrix-tr form-matrix-value-tr" aria-labelledby="label_9 label_9_row_3"><th scope="row" class="form-matrix-headers form-matrix-row-headers"><div class="editor-container editorHasText" style="display: inline-block;"><div class="inlineEditor" placeholder="Type Row Name" format="text" contenteditable="true" data-gramm="false">The instructor's effectiveness in teaching the subject matter was:	</div></div><button class="questionLine-editButton forRemove remove">×</button></th><td class="form-matrix-values"><input readonly="" id="input_9_3_0" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[3]" tabindex="-1" required="" aria-labelledby="label_9_col_0 label_9_row_3" value="Excellent"><label for="input_9_3_0" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_3_1" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[3]" tabindex="-1" required="" aria-labelledby="label_9_col_1 label_9_row_3" value="Very Good"><label for="input_9_3_1" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_3_2" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[3]" tabindex="-1" required="" aria-labelledby="label_9_col_2 label_9_row_3" value="Good"><label for="input_9_3_2" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_3_3" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[3]" tabindex="-1" required="" aria-labelledby="label_9_col_3 label_9_row_3" value="Fair"><label for="input_9_3_3" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_3_4" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[3]" tabindex="-1" required="" aria-labelledby="label_9_col_4 label_9_row_3" value="Poor"><label for="input_9_3_4" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_3_5" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[3]" tabindex="-1" required="" aria-labelledby="label_9_col_5 label_9_row_3" value="Very Poor"><label for="input_9_3_5" class="matrix-choice-label matrix-radio-label"></label></td></tr><tr class="form-matrix-tr form-matrix-value-tr" aria-labelledby="label_9 label_9_row_4"><th scope="row" class="form-matrix-headers form-matrix-row-headers"><div class="editor-container editorHasText" style="display: inline-block;"><div class="inlineEditor" placeholder="Type Row Name" format="text" contenteditable="true" data-gramm="false">Course Organization was:	</div></div><button class="questionLine-editButton forRemove remove">×</button></th><td class="form-matrix-values"><input readonly="" id="input_9_4_0" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[4]" tabindex="-1" required="" aria-labelledby="label_9_col_0 label_9_row_4" value="Excellent"><label for="input_9_4_0" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_4_1" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[4]" tabindex="-1" required="" aria-labelledby="label_9_col_1 label_9_row_4" value="Very Good"><label for="input_9_4_1" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_4_2" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[4]" tabindex="-1" required="" aria-labelledby="label_9_col_2 label_9_row_4" value="Good"><label for="input_9_4_2" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_4_3" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[4]" tabindex="-1" required="" aria-labelledby="label_9_col_3 label_9_row_4" value="Fair"><label for="input_9_4_3" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_4_4" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[4]" tabindex="-1" required="" aria-labelledby="label_9_col_4 label_9_row_4" value="Poor"><label for="input_9_4_4" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_4_5" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[4]" tabindex="-1" required="" aria-labelledby="label_9_col_5 label_9_row_4" value="Very Poor"><label for="input_9_4_5" class="matrix-choice-label matrix-radio-label"></label></td></tr><tr class="form-matrix-tr form-matrix-value-tr" aria-labelledby="label_9 label_9_row_5"><th scope="row" class="form-matrix-headers form-matrix-row-headers"><div class="editor-container editorHasText" style="display: inline-block;"><div class="inlineEditor" placeholder="Type Row Name" format="text" contenteditable="true" data-gramm="false">Clarity of instructor's voice was:	</div></div><button class="questionLine-editButton forRemove remove">×</button></th><td class="form-matrix-values"><input readonly="" id="input_9_5_0" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[5]" tabindex="-1" required="" aria-labelledby="label_9_col_0 label_9_row_5" value="Excellent"><label for="input_9_5_0" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_5_1" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[5]" tabindex="-1" required="" aria-labelledby="label_9_col_1 label_9_row_5" value="Very Good"><label for="input_9_5_1" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_5_2" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[5]" tabindex="-1" required="" aria-labelledby="label_9_col_2 label_9_row_5" value="Good"><label for="input_9_5_2" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_5_3" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[5]" tabindex="-1" required="" aria-labelledby="label_9_col_3 label_9_row_5" value="Fair"><label for="input_9_5_3" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_5_4" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[5]" tabindex="-1" required="" aria-labelledby="label_9_col_4 label_9_row_5" value="Poor"><label for="input_9_5_4" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_5_5" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[5]" tabindex="-1" required="" aria-labelledby="label_9_col_5 label_9_row_5" value="Very Poor"><label for="input_9_5_5" class="matrix-choice-label matrix-radio-label"></label></td></tr><tr class="form-matrix-tr form-matrix-value-tr" aria-labelledby="label_9 label_9_row_6"><th scope="row" class="form-matrix-headers form-matrix-row-headers"><div class="editor-container editorHasText" style="display: inline-block;"><div class="inlineEditor" placeholder="Type Row Name" format="text" contenteditable="true" data-gramm="false">Explanations by instructor were:	</div></div><button class="questionLine-editButton forRemove remove">×</button></th><td class="form-matrix-values"><input readonly="" id="input_9_6_0" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[6]" tabindex="-1" required="" aria-labelledby="label_9_col_0 label_9_row_6" value="Excellent"><label for="input_9_6_0" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_6_1" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[6]" tabindex="-1" required="" aria-labelledby="label_9_col_1 label_9_row_6" value="Very Good"><label for="input_9_6_1" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_6_2" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[6]" tabindex="-1" required="" aria-labelledby="label_9_col_2 label_9_row_6" value="Good"><label for="input_9_6_2" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_6_3" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[6]" tabindex="-1" required="" aria-labelledby="label_9_col_3 label_9_row_6" value="Fair"><label for="input_9_6_3" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_6_4" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[6]" tabindex="-1" required="" aria-labelledby="label_9_col_4 label_9_row_6" value="Poor"><label for="input_9_6_4" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_6_5" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[6]" tabindex="-1" required="" aria-labelledby="label_9_col_5 label_9_row_6" value="Very Poor"><label for="input_9_6_5" class="matrix-choice-label matrix-radio-label"></label></td></tr><tr class="form-matrix-tr form-matrix-value-tr" aria-labelledby="label_9 label_9_row_7"><th scope="row" class="form-matrix-headers form-matrix-row-headers"><div class="editor-container editorHasText" style="display: inline-block;"><div class="inlineEditor" placeholder="Type Row Name" format="text" contenteditable="true" data-gramm="false">Instructor's use of examples and illustrations was:	</div></div><button class="questionLine-editButton forRemove remove">×</button></th><td class="form-matrix-values"><input readonly="" id="input_9_7_0" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[7]" tabindex="-1" required="" aria-labelledby="label_9_col_0 label_9_row_7" value="Excellent"><label for="input_9_7_0" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_7_1" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[7]" tabindex="-1" required="" aria-labelledby="label_9_col_1 label_9_row_7" value="Very Good"><label for="input_9_7_1" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_7_2" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[7]" tabindex="-1" required="" aria-labelledby="label_9_col_2 label_9_row_7" value="Good"><label for="input_9_7_2" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_7_3" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[7]" tabindex="-1" required="" aria-labelledby="label_9_col_3 label_9_row_7" value="Fair"><label for="input_9_7_3" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_7_4" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[7]" tabindex="-1" required="" aria-labelledby="label_9_col_4 label_9_row_7" value="Poor"><label for="input_9_7_4" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_7_5" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[7]" tabindex="-1" required="" aria-labelledby="label_9_col_5 label_9_row_7" value="Very Poor"><label for="input_9_7_5" class="matrix-choice-label matrix-radio-label"></label></td></tr><tr class="form-matrix-tr form-matrix-value-tr" aria-labelledby="label_9 label_9_row_8"><th scope="row" class="form-matrix-headers form-matrix-row-headers"><div class="editor-container editorHasText" style="display: inline-block;"><div class="inlineEditor" placeholder="Type Row Name" format="text" contenteditable="true" data-gramm="false">Quality of questions or problems raised by the instructor was:	</div></div><button class="questionLine-editButton forRemove remove">×</button></th><td class="form-matrix-values"><input readonly="" id="input_9_8_0" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[8]" tabindex="-1" required="" aria-labelledby="label_9_col_0 label_9_row_8" value="Excellent"><label for="input_9_8_0" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_8_1" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[8]" tabindex="-1" required="" aria-labelledby="label_9_col_1 label_9_row_8" value="Very Good"><label for="input_9_8_1" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_8_2" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[8]" tabindex="-1" required="" aria-labelledby="label_9_col_2 label_9_row_8" value="Good"><label for="input_9_8_2" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_8_3" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[8]" tabindex="-1" required="" aria-labelledby="label_9_col_3 label_9_row_8" value="Fair"><label for="input_9_8_3" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_8_4" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[8]" tabindex="-1" required="" aria-labelledby="label_9_col_4 label_9_row_8" value="Poor"><label for="input_9_8_4" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_8_5" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[8]" tabindex="-1" required="" aria-labelledby="label_9_col_5 label_9_row_8" value="Very Poor"><label for="input_9_8_5" class="matrix-choice-label matrix-radio-label"></label></td></tr><tr class="form-matrix-tr form-matrix-value-tr" aria-labelledby="label_9 label_9_row_9"><th scope="row" class="form-matrix-headers form-matrix-row-headers"><div class="editor-container editorHasText" style="display: inline-block;"><div class="inlineEditor" placeholder="Type Row Name" format="text" contenteditable="true" data-gramm="false">Student's confidence in instructor's knowledge was:	</div></div><button class="questionLine-editButton forRemove remove">×</button></th><td class="form-matrix-values"><input readonly="" id="input_9_9_0" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[9]" tabindex="-1" required="" aria-labelledby="label_9_col_0 label_9_row_9" value="Excellent"><label for="input_9_9_0" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_9_1" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[9]" tabindex="-1" required="" aria-labelledby="label_9_col_1 label_9_row_9" value="Very Good"><label for="input_9_9_1" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_9_2" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[9]" tabindex="-1" required="" aria-labelledby="label_9_col_2 label_9_row_9" value="Good"><label for="input_9_9_2" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_9_3" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[9]" tabindex="-1" required="" aria-labelledby="label_9_col_3 label_9_row_9" value="Fair"><label for="input_9_9_3" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_9_4" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[9]" tabindex="-1" required="" aria-labelledby="label_9_col_4 label_9_row_9" value="Poor"><label for="input_9_9_4" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_9_5" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[9]" tabindex="-1" required="" aria-labelledby="label_9_col_5 label_9_row_9" value="Very Poor"><label for="input_9_9_5" class="matrix-choice-label matrix-radio-label"></label></td></tr><tr class="form-matrix-tr form-matrix-value-tr" aria-labelledby="label_9 label_9_row_10"><th scope="row" class="form-matrix-headers form-matrix-row-headers"><div class="editor-container editorHasText" style="display: inline-block;"><div class="inlineEditor" placeholder="Type Row Name" format="text" contenteditable="true" data-gramm="false">Instructor's enthusiasm was:	</div></div><button class="questionLine-editButton forRemove remove">×</button></th><td class="form-matrix-values"><input readonly="" id="input_9_10_0" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[10]" tabindex="-1" required="" aria-labelledby="label_9_col_0 label_9_row_10" value="Excellent"><label for="input_9_10_0" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_10_1" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[10]" tabindex="-1" required="" aria-labelledby="label_9_col_1 label_9_row_10" value="Very Good"><label for="input_9_10_1" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_10_2" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[10]" tabindex="-1" required="" aria-labelledby="label_9_col_2 label_9_row_10" value="Good"><label for="input_9_10_2" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_10_3" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[10]" tabindex="-1" required="" aria-labelledby="label_9_col_3 label_9_row_10" value="Fair"><label for="input_9_10_3" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_10_4" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[10]" tabindex="-1" required="" aria-labelledby="label_9_col_4 label_9_row_10" value="Poor"><label for="input_9_10_4" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_10_5" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[10]" tabindex="-1" required="" aria-labelledby="label_9_col_5 label_9_row_10" value="Very Poor"><label for="input_9_10_5" class="matrix-choice-label matrix-radio-label"></label></td></tr><tr class="form-matrix-tr form-matrix-value-tr" aria-labelledby="label_9 label_9_row_11"><th scope="row" class="form-matrix-headers form-matrix-row-headers"><div class="editor-container editorHasText" style="display: inline-block;"><div class="inlineEditor" placeholder="Type Row Name" format="text" contenteditable="true" data-gramm="false">Encouragement given to students to participate was:	</div></div><button class="questionLine-editButton forRemove remove">×</button></th><td class="form-matrix-values"><input readonly="" id="input_9_11_0" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[11]" tabindex="-1" required="" aria-labelledby="label_9_col_0 label_9_row_11" value="Excellent"><label for="input_9_11_0" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_11_1" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[11]" tabindex="-1" required="" aria-labelledby="label_9_col_1 label_9_row_11" value="Very Good"><label for="input_9_11_1" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_11_2" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[11]" tabindex="-1" required="" aria-labelledby="label_9_col_2 label_9_row_11" value="Good"><label for="input_9_11_2" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_11_3" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[11]" tabindex="-1" required="" aria-labelledby="label_9_col_3 label_9_row_11" value="Fair"><label for="input_9_11_3" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_11_4" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[11]" tabindex="-1" required="" aria-labelledby="label_9_col_4 label_9_row_11" value="Poor"><label for="input_9_11_4" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_11_5" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[11]" tabindex="-1" required="" aria-labelledby="label_9_col_5 label_9_row_11" value="Very Poor"><label for="input_9_11_5" class="matrix-choice-label matrix-radio-label"></label></td></tr><tr class="form-matrix-tr form-matrix-value-tr" aria-labelledby="label_9 label_9_row_12"><th scope="row" class="form-matrix-headers form-matrix-row-headers"><div class="editor-container editorHasText" style="display: inline-block;"><div class="inlineEditor" placeholder="Type Row Name" format="text" contenteditable="true" data-gramm="false">Answers to student questions were:	</div></div><button class="questionLine-editButton forRemove remove">×</button></th><td class="form-matrix-values"><input readonly="" id="input_9_12_0" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[12]" tabindex="-1" required="" aria-labelledby="label_9_col_0 label_9_row_12" value="Excellent"><label for="input_9_12_0" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_12_1" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[12]" tabindex="-1" required="" aria-labelledby="label_9_col_1 label_9_row_12" value="Very Good"><label for="input_9_12_1" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_12_2" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[12]" tabindex="-1" required="" aria-labelledby="label_9_col_2 label_9_row_12" value="Good"><label for="input_9_12_2" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_12_3" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[12]" tabindex="-1" required="" aria-labelledby="label_9_col_3 label_9_row_12" value="Fair"><label for="input_9_12_3" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_12_4" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[12]" tabindex="-1" required="" aria-labelledby="label_9_col_4 label_9_row_12" value="Poor"><label for="input_9_12_4" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_12_5" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[12]" tabindex="-1" required="" aria-labelledby="label_9_col_5 label_9_row_12" value="Very Poor"><label for="input_9_12_5" class="matrix-choice-label matrix-radio-label"></label></td></tr><tr class="form-matrix-tr form-matrix-value-tr" aria-labelledby="label_9 label_9_row_13"><th scope="row" class="form-matrix-headers form-matrix-row-headers"><div class="editor-container editorHasText" style="display: inline-block;"><div class="inlineEditor" placeholder="Type Row Name" format="text" contenteditable="true" data-gramm="false">Availability of extra help when needed was:	</div></div><button class="questionLine-editButton forRemove remove">×</button></th><td class="form-matrix-values"><input readonly="" id="input_9_13_0" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[13]" tabindex="-1" required="" aria-labelledby="label_9_col_0 label_9_row_13" value="Excellent"><label for="input_9_13_0" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_13_1" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[13]" tabindex="-1" required="" aria-labelledby="label_9_col_1 label_9_row_13" value="Very Good"><label for="input_9_13_1" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_13_2" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[13]" tabindex="-1" required="" aria-labelledby="label_9_col_2 label_9_row_13" value="Good"><label for="input_9_13_2" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_13_3" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[13]" tabindex="-1" required="" aria-labelledby="label_9_col_3 label_9_row_13" value="Fair"><label for="input_9_13_3" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_13_4" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[13]" tabindex="-1" required="" aria-labelledby="label_9_col_4 label_9_row_13" value="Poor"><label for="input_9_13_4" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_13_5" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[13]" tabindex="-1" required="" aria-labelledby="label_9_col_5 label_9_row_13" value="Very Poor"><label for="input_9_13_5" class="matrix-choice-label matrix-radio-label"></label></td></tr><tr class="form-matrix-tr form-matrix-value-tr" aria-labelledby="label_9 label_9_row_14"><th scope="row" class="form-matrix-headers form-matrix-row-headers"><div class="editor-container editorHasText" style="display: inline-block;"><div class="inlineEditor" placeholder="Type Row Name" format="text" contenteditable="true" data-gramm="false">Use of class time was:	</div></div><button class="questionLine-editButton forRemove remove">×</button></th><td class="form-matrix-values"><input readonly="" id="input_9_14_0" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[14]" tabindex="-1" required="" aria-labelledby="label_9_col_0 label_9_row_14" value="Excellent"><label for="input_9_14_0" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_14_1" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[14]" tabindex="-1" required="" aria-labelledby="label_9_col_1 label_9_row_14" value="Very Good"><label for="input_9_14_1" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_14_2" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[14]" tabindex="-1" required="" aria-labelledby="label_9_col_2 label_9_row_14" value="Good"><label for="input_9_14_2" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_14_3" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[14]" tabindex="-1" required="" aria-labelledby="label_9_col_3 label_9_row_14" value="Fair"><label for="input_9_14_3" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_14_4" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[14]" tabindex="-1" required="" aria-labelledby="label_9_col_4 label_9_row_14" value="Poor"><label for="input_9_14_4" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_14_5" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[14]" tabindex="-1" required="" aria-labelledby="label_9_col_5 label_9_row_14" value="Very Poor"><label for="input_9_14_5" class="matrix-choice-label matrix-radio-label"></label></td></tr><tr class="form-matrix-tr form-matrix-value-tr" aria-labelledby="label_9 label_9_row_15"><th scope="row" class="form-matrix-headers form-matrix-row-headers"><div class="editor-container editorHasText" style="display: inline-block;"><div class="inlineEditor" placeholder="Type Row Name" format="text" contenteditable="true" data-gramm="false">Instructor's interest in student's progress was:	</div></div><button class="questionLine-editButton forRemove remove">×</button></th><td class="form-matrix-values"><input readonly="" id="input_9_15_0" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[15]" tabindex="-1" required="" aria-labelledby="label_9_col_0 label_9_row_15" value="Excellent"><label for="input_9_15_0" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_15_1" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[15]" tabindex="-1" required="" aria-labelledby="label_9_col_1 label_9_row_15" value="Very Good"><label for="input_9_15_1" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_15_2" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[15]" tabindex="-1" required="" aria-labelledby="label_9_col_2 label_9_row_15" value="Good"><label for="input_9_15_2" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_15_3" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[15]" tabindex="-1" required="" aria-labelledby="label_9_col_3 label_9_row_15" value="Fair"><label for="input_9_15_3" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_15_4" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[15]" tabindex="-1" required="" aria-labelledby="label_9_col_4 label_9_row_15" value="Poor"><label for="input_9_15_4" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_15_5" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[15]" tabindex="-1" required="" aria-labelledby="label_9_col_5 label_9_row_15" value="Very Poor"><label for="input_9_15_5" class="matrix-choice-label matrix-radio-label"></label></td></tr><tr class="form-matrix-tr form-matrix-value-tr" aria-labelledby="label_9 label_9_row_16"><th scope="row" class="form-matrix-headers form-matrix-row-headers"><div class="editor-container editorHasText" style="display: inline-block;"><div class="inlineEditor" placeholder="Type Row Name" format="text" contenteditable="true" data-gramm="false">Amount you learned was:	</div></div><button class="questionLine-editButton forRemove remove">×</button></th><td class="form-matrix-values"><input readonly="" id="input_9_16_0" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[16]" tabindex="-1" required="" aria-labelledby="label_9_col_0 label_9_row_16" value="Excellent"><label for="input_9_16_0" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_16_1" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[16]" tabindex="-1" required="" aria-labelledby="label_9_col_1 label_9_row_16" value="Very Good"><label for="input_9_16_1" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_16_2" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[16]" tabindex="-1" required="" aria-labelledby="label_9_col_2 label_9_row_16" value="Good"><label for="input_9_16_2" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_16_3" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[16]" tabindex="-1" required="" aria-labelledby="label_9_col_3 label_9_row_16" value="Fair"><label for="input_9_16_3" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_16_4" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[16]" tabindex="-1" required="" aria-labelledby="label_9_col_4 label_9_row_16" value="Poor"><label for="input_9_16_4" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_16_5" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[16]" tabindex="-1" required="" aria-labelledby="label_9_col_5 label_9_row_16" value="Very Poor"><label for="input_9_16_5" class="matrix-choice-label matrix-radio-label"></label></td></tr><tr class="form-matrix-tr form-matrix-value-tr" aria-labelledby="label_9 label_9_row_17"><th scope="row" class="form-matrix-headers form-matrix-row-headers"><div class="editor-container editorHasText" style="display: inline-block;"><div class="inlineEditor" placeholder="Type Row Name" format="text" contenteditable="true" data-gramm="false">Relavence of course content was:	</div></div><button class="questionLine-editButton forRemove remove">×</button></th><td class="form-matrix-values"><input readonly="" id="input_9_17_0" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[17]" tabindex="-1" required="" aria-labelledby="label_9_col_0 label_9_row_17" value="Excellent"><label for="input_9_17_0" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_17_1" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[17]" tabindex="-1" required="" aria-labelledby="label_9_col_1 label_9_row_17" value="Very Good"><label for="input_9_17_1" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_17_2" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[17]" tabindex="-1" required="" aria-labelledby="label_9_col_2 label_9_row_17" value="Good"><label for="input_9_17_2" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_17_3" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[17]" tabindex="-1" required="" aria-labelledby="label_9_col_3 label_9_row_17" value="Fair"><label for="input_9_17_3" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_17_4" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[17]" tabindex="-1" required="" aria-labelledby="label_9_col_4 label_9_row_17" value="Poor"><label for="input_9_17_4" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_17_5" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[17]" tabindex="-1" required="" aria-labelledby="label_9_col_5 label_9_row_17" value="Very Poor"><label for="input_9_17_5" class="matrix-choice-label matrix-radio-label"></label></td></tr><tr class="form-matrix-tr form-matrix-value-tr" aria-labelledby="label_9 label_9_row_18"><th scope="row" class="form-matrix-headers form-matrix-row-headers"><div class="editor-container editorHasText" style="display: inline-block;"><div class="inlineEditor" placeholder="Type Row Name" format="text" contenteditable="true" data-gramm="false">Grading techniques were:	</div></div><button class="questionLine-editButton forRemove remove">×</button></th><td class="form-matrix-values"><input readonly="" id="input_9_18_0" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[18]" tabindex="-1" required="" aria-labelledby="label_9_col_0 label_9_row_18" value="Excellent"><label for="input_9_18_0" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_18_1" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[18]" tabindex="-1" required="" aria-labelledby="label_9_col_1 label_9_row_18" value="Very Good"><label for="input_9_18_1" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_18_2" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[18]" tabindex="-1" required="" aria-labelledby="label_9_col_2 label_9_row_18" value="Good"><label for="input_9_18_2" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_18_3" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[18]" tabindex="-1" required="" aria-labelledby="label_9_col_3 label_9_row_18" value="Fair"><label for="input_9_18_3" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_18_4" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[18]" tabindex="-1" required="" aria-labelledby="label_9_col_4 label_9_row_18" value="Poor"><label for="input_9_18_4" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_18_5" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[18]" tabindex="-1" required="" aria-labelledby="label_9_col_5 label_9_row_18" value="Very Poor"><label for="input_9_18_5" class="matrix-choice-label matrix-radio-label"></label></td></tr><tr class="form-matrix-tr form-matrix-value-tr" aria-labelledby="label_9 label_9_row_19"><th scope="row" class="form-matrix-headers form-matrix-row-headers"><div class="editor-container editorHasText" style="display: inline-block;"><div class="inlineEditor" placeholder="Type Row Name" format="text" contenteditable="true" data-gramm="false">Amount of assigned work was:	</div></div><button class="questionLine-editButton forRemove remove">×</button></th><td class="form-matrix-values"><input readonly="" id="input_9_19_0" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[19]" tabindex="-1" required="" aria-labelledby="label_9_col_0 label_9_row_19" value="Excellent"><label for="input_9_19_0" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_19_1" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[19]" tabindex="-1" required="" aria-labelledby="label_9_col_1 label_9_row_19" value="Very Good"><label for="input_9_19_1" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_19_2" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[19]" tabindex="-1" required="" aria-labelledby="label_9_col_2 label_9_row_19" value="Good"><label for="input_9_19_2" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_19_3" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[19]" tabindex="-1" required="" aria-labelledby="label_9_col_3 label_9_row_19" value="Fair"><label for="input_9_19_3" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_19_4" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[19]" tabindex="-1" required="" aria-labelledby="label_9_col_4 label_9_row_19" value="Poor"><label for="input_9_19_4" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_19_5" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[19]" tabindex="-1" required="" aria-labelledby="label_9_col_5 label_9_row_19" value="Very Poor"><label for="input_9_19_5" class="matrix-choice-label matrix-radio-label"></label></td></tr><tr class="form-matrix-tr form-matrix-value-tr" aria-labelledby="label_9 label_9_row_20"><th scope="row" class="form-matrix-headers form-matrix-row-headers"><div class="editor-container editorHasText" style="display: inline-block;"><div class="inlineEditor" placeholder="Type Row Name" format="text" contenteditable="true" data-gramm="false">Clarity of student requirements was:	</div></div><button class="questionLine-editButton forRemove remove">×</button></th><td class="form-matrix-values"><input readonly="" id="input_9_20_0" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[20]" tabindex="-1" required="" aria-labelledby="label_9_col_0 label_9_row_20" value="Excellent"><label for="input_9_20_0" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_20_1" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[20]" tabindex="-1" required="" aria-labelledby="label_9_col_1 label_9_row_20" value="Very Good"><label for="input_9_20_1" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_20_2" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[20]" tabindex="-1" required="" aria-labelledby="label_9_col_2 label_9_row_20" value="Good"><label for="input_9_20_2" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_20_3" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[20]" tabindex="-1" required="" aria-labelledby="label_9_col_3 label_9_row_20" value="Fair"><label for="input_9_20_3" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_20_4" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[20]" tabindex="-1" required="" aria-labelledby="label_9_col_4 label_9_row_20" value="Poor"><label for="input_9_20_4" class="matrix-choice-label matrix-radio-label"></label></td><td class="form-matrix-values"><input readonly="" id="input_9_20_5" class="form-radio validate[required]" type="radio" name="q9_pleaseEvaluate[20]" tabindex="-1" required="" aria-labelledby="label_9_col_5 label_9_row_20" value="Very Poor"><label for="input_9_20_5" class="matrix-choice-label matrix-radio-label"></label></td></tr><tr style="background: transparent;"><th style="border: none;"><button class="questionLine-editButton forAdd add">add row</button></th></tr></table> -->
                     </form>

</body>



<script type="text/javascript">




	
	
	

	
function addtextfunc(){
	
	
	
for (var i = 0; i < 1; i++) {
		
		$("#addtext").append('<div class="input-style-1" contenteditable="true">'
                +'<label>Full Name<span class="mandatory">*</span></label>'
	            +'<input type="text" placeholder="Full Name" />'
              +'</div>');
	}
	
	
	
	
}
	
	
function addoptiondy(){
	
	debugger;
	
	
	
	for (var i = 0; i < 1; i++) {
		
		$("#addoption").append(' <div class="input-style-form-check" contenteditable="true">'
				+'<p class="que"><span class="que-no">Q - Select your option'
				+'</span> </p>'
				+'<div class="q-mcq"><div class="row"><div class="col-12 col-sm-6 col-lg-3"><div class="form-check radio-style"><input value="1" id="op1" name="op2" type="radio" class="form-check-input" /> <label for="radio1" class="form-check-label">(A)'
				+' op1'
				+'</label></div></div>'
				+'<div class="col-12 col-sm-6 col-lg-3"><div class="form-check radio-style"><input value="2" id="op2" name="op2" type="radio" class="form-check-input" /> <label  for="radio1" class="form-check-label">(B)'
				+' op2'
				+'</label></div></div>'
				+'<div class="col-12 col-sm-6 col-lg-3"><div class="form-check radio-style"><input value="3" id="op3" name="op3" type="radio" class="form-check-input" /> <label  for="radio1" class="form-check-label">(C)'
				+' op3'
				+'</label></div></div>'
				+'<div class="col-12 col-sm-6 col-lg-3"><div class="form-check radio-style"><input value="4" id="op4" name="op4" type="radio" class="form-check-input" /> <label  for="radio1" class="form-check-label">(D)'
				+' op4'
				+' </label></div></div>'
				+'<div class="action">'
				+'	<ul class="buttons-group mainbtn">'
				+'<li><a class="main-btn success-btn btn-hover btn-sm addminusbut" '
				+'value="ADD" title="ADD" '
				+'	id="id_add_attNameMed1"><i class="lni lni-plus"></i></a></li></ul></div></div></div></div>');
	}
	
	
	
}

function dynamicTable(R,index){
	
	var length = $("#count_hidden_att_name_med").val();
	for(var i = 1 ;i<=length;i++){
	$("#dynamicDataTable"+i).hide();
	}
	
	if($("#dynamicDataTable"+R).length){
		$("#dynamicDataTable"+R).show();

	}else{
	
	$("#dynamicDataTable").append("<div id='dynamicDataTable"+R+"'></div>");

	var seq = R+"_"+index;
//		var r1 = "'"+R+"_"+"'";
	$("div#dynamicDataTable"+R).append('');
	
		
				
			
	}
//		$('#modelWindow').modal('show');
	
}



</script>







