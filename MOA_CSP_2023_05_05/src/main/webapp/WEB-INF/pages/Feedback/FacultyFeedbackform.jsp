<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>

.feeeback_container{
right: 0;
position: fixed;
top: 50%;
transform: translateY(-50%);
}
.feedback__label {
    box-sizing: border-box !important;
    display: block;
    direction: ltr !important;
    border-radius: 3px 0 0 3px;
    transform: translateX(2px);
    font-size: 13px;
    border: none;
    outline: none;
    padding: 12px 14px 12px 12px;
    cursor: pointer;
    white-space: nowrap;
    background-color: #f4364c !important;
    background-color: var(--hjFeedbackAccentColor, #f4364c) !important;
    background-color: #4d5167 !important;
    transition: transform 0.1s ease-in-out, box-shadow 0.1s ease-in-out;
    opacity: 0.96;
    width: 40px;
    display: flex;
    flex-direction: column;
    align-items: flex-start;
}
.feedback__label .feedback__text{
    overflow-wrap: normal !important;
    word-break: normal !important;
    word-wrap: normal !important;
    white-space: nowrap !important;
    filter: progid:DXImageTransform.Microsoft.BasicImage(rotation=2);
    cursor: pointer;
    -ms-writing-mode: tb-rl;
    -webkit-writing-mode: vertical-lr;
    writing-mode: vertical-lr;
    transform: rotate(180deg);
    color: #ffffff !important;
    color: var(--hjFeedbackAccentTextColor, #fff) !important;
}


.feedback__label:hover{
    transform: translateX(0);
    border-radius: 3px 0 0 3px;
    box-shadow: 0 0 35px 2px rgb(0 0 0 / 24%); 
    opacity: 1;
}

.feeeback_content{

    width: 320px;
    display: flex;
    z-index: 10;
    background-color: #fff;
    box-shadow: rgb(0 0 0 / 35%) 0 6px 100px 0;
    height: auto;
    min-height: 200px;
    display: none;
    animation: content__slideInRight 300ms forwards;
    position:relative;
}

@keyframes content__slideInRight{
  from{right: -400px;}
  to {right: 32px;}
}

@keyframes content__slideInLeft{
  from{right: 32px;}
  to {right: -400px;}
}

.feeeback_content .closeButton {
    right: 20px;
    position: absolute;
    top: -13px;
    width: 27px;
    height: 27px;
    cursor: pointer;
    border-radius: 50%;
    border-bottom-style: none;
    border-width: 0;
    color: #ffffff !important;
    background-color: #4d5167 !important;
/*     animation: content__slideInLeft 300ms reverse; */
}
.feeeback_content .closeButton i{
font-size: 24px;
font-weight: bold;
display: flex;
justify-content: center;

}

.feeeback__Title {
    font-size: 17px !important;
    padding: 10px !important;
    text-align: center;
    border-bottom: 0 !important;
    font-family: monospace;
    -webkit-font-smoothing: auto;
    font-weight: bold;
    color:#333333;
}
</style>

<style>

.face-wrapper {
    position: relative;
    left: 0;
    right: 0;
    width: 48px;
    height: 48px;
    padding: 8px;
    box-sizing: content-box;
    transition: 0.25s ease-out;
}
 .face-wrapper .face-counter {
	 display: none;
}
 .face-wrapper .face-counter.invisible {
	 transform: rotate(150deg) scale(0);
	 opacity: 0;
	 display: none;
}
 .face-wrapper .rate-input {
	 display: none;
}
 .face-wrapper .face {
	 display: block;
	 position: relative;
	 background: #ffcd00;
     border-radius: 50%;
     width: 50px;
     height: 50px;
     box-shadow: 0 0.6px 1.2px #cc9117;
	 transition: 0.25s ease-out;
	 animation-name: mymove;
     animation-duration: 1.5s;
}

 .face-wrapper .face:not([disabled]) {
	 cursor: pointer;
}
 .face-wrapper .face:after {
	 content: '';
	 position: absolute;
	 top: 0;
	 left: 0;
	 right: 0;
	 bottom: 0;
	 border-radius: 50%;
	 box-shadow: inset 0px -3px 9px #eaa514;
	 z-index: 9;
}
 .face-wrapper .face .eye {
	 position: absolute;
	 width: 11.6px;
     height: 11.6px;
     margin-top: 30%;
	 left: 19%;
	 border-radius: 50%;
	 transition: 0.25s ease-out;
}
 .face-wrapper .face .eye:last-of-type {
	 right: 19%;
	 left: auto;
}
 .face-wrapper .face .eye .pupil {
	 position: absolute;
	 background: #000;
	 left: 0;
	 right: 0;
	 top: 0;
	 margin: auto;
	 width: 75%;
	 height: 75%;
	 border-radius: 50%;
	 background: #a64c06;
	 box-shadow: inset 0 -1.2px 0.6px 0px #ca7432;
	 transition: width 0.25s ease-out, height 0.25s ease-out;
}
 .face-wrapper .face .eye .eyelid {
	 position: absolute;
	 width: 100%;
	 height: 0%;
	 bottom: -5%;
	 border-radius: 50%;
	 background: #ffcd27;
	 transition: 0.25s ease-out;
}
 .face-wrapper .face .mouth-wrapper {
	 position: absolute;
	 top: 47%;
	 width: 100%;
}
 .face-wrapper .face .mouth-wrapper .mouth {
	 width: 38%;
	 height: 14.4px;
	 background: #ca7432;
	 left: 0;
	 right: 0;
	 margin: auto;
	 position: relative;
	 border-radius: 290%;
	 box-shadow: inset 0 -1.2px 0.6px 0px #ca7432;
	 transition: 0.25s ease-out;
}
 .face-wrapper .face .mouth-wrapper .mouth:before {
	 content: '';
	 position: absolute;
	 width: 120%;
	 height: 73%;
	 background: #ffcd27;
	 border-radius: 0 0 140% 140%;
	 top: 0;
	 left: -10%;
	 transition: 0.25s ease-out;
}
 .face-wrapper .face.grayscale {
	 transform: scale(0.9);
	 background: #f9ca07;
     box-shadow: 0 0.6px 1.2px #fad128;
}
 .face-wrapper .face.grayscale:after {
	 box-shadow: inset 0px -3px 9px #deb000;
}
 .face-wrapper .face.grayscale .pupil {
	 background: #794014;
    box-shadow: inset 0 -1.2px 0.6px 0px #8b5a36;
}
 .face-wrapper .face.grayscale .mouth {
	 background: #ce7f42;
    box-shadow: inset 0 -1.2px 0.6px 0px #cf813f;
}
 .face-wrapper .face.grayscale .mouth:before {

	     background: #f9d13d;
}
 .face-wrapper:hover .face {
	 transform: scale(1.1);
}
 .face-wrapper:hover .eyes-wrapper {
	 animation: shake infinite 0.15s;
	 transform: translate3d(0, 0, 0);
	 backface-visibility: hidden;
	 perspective: 1000px;
}
 .face-wrapper:hover .eyes-wrapper .eyelid {
	 height: 50%;
}
 .face-wrapper:hover .mouth-wrapper .mouth {
	 transform: scaleX(1.2);
}
 .face-wrapper:hover .mouth-wrapper .mouth:before {
	 transform: translateY(-20%) scaleY(0.75);
}
 .face-wrapper:active .face {
	 transform: scale(1.05);
}
 .face-wrapper:active .eyes-wrapper .eye .eyelid {
	 height: 75%;
}
 .face-wrapper .face-sad .mouth-wrapper .mouth {
	 height: 2.4px;
	 top: 8.4px;
	 border-radius: 4.2px;
	 width: 30%;
}
 .face-wrapper .face-sad .mouth-wrapper .mouth:before {
	 display: none;
}
 .face-wrapper:hover .face-sad .eyes-wrapper .eye {
	 transform: scale(0.9);
}
 .face-wrapper:hover .face-sad .eyes-wrapper .eyelid {
	 height: 0;
}
 .face-wrapper:hover .face-sad .mouth-wrapper .mouth {
	 transform: scaleX(0.9) rotateZ(-10deg);
}
 .face-wrapper:active .face-sad .eyes-wrapper .eye .pupil {
	 top: 0%;
	 height: 70%;
}
 .face-wrapper .face-disappointed .mouth-wrapper {
	 transform: rotateZ(-180deg);
}
 .face-wrapper .face-disappointed .mouth-wrapper .mouth {
	 top: -6px;
	 height: 9px;
}
 .face-wrapper .face-disappointed .mouth-wrapper .mouth:before {
	 transform: translateY(-30%);
	 width: 120%;
	 height: 120%;
}
 .face-wrapper:hover .face-disappointed .eyes-wrapper .eye {
	 transform: scale(0.9);
}
 .face-wrapper:hover .face-disappointed .eyes-wrapper .eyelid {
	 height: 0;
}
 .face-wrapper:hover .face-disappointed .mouth-wrapper .mouth {
	 transform: translateY(-5%) scale3d(0.8, 1, 1);
}
 .face-wrapper:hover .face-disappointed .mouth-wrapper .mouth:before {
	 transform: translateY(-30%);
	 width: 120%;
	 height: 120%;
}
 .face-wrapper:active .face-disappointed .eyes-wrapper .eye .pupil {
	 top: 0%;
	 height: 70%;
}
 .face-wrapper .face-wtf .mouth-wrapper {
	 transform: rotateZ(-180deg);
}
 .face-wrapper .face-wtf .mouth-wrapper .mouth {
	 top: -3.6px;
}
 .face-wrapper:hover .face-disappointed .mouth-wrapper .mouth {
	 top: -3.6px;
}
 .face-wrapper .face-love .eye .pupil {
	 background: none;
	 box-shadow: none;
}
 .face-wrapper .face-love .eye .eyelid {
	 position: absolute;
	 transform: rotate(-45deg);
	 width: 10px;
	 height: 10px;
	 top: 0;
	 left: 0;
	 right: 0;
	 bottom: 0;
	 margin: auto;
	 background: #dc0e0e;
	 border-radius: 0;
	 z-index: 999;
	 transition: 0.25s ease-out, background 0s;
}
 .face-wrapper .face-love .eye .eyelid:before, .face-wrapper .face-love .eye .eyelid:after {
	 content: '';
	 position: absolute;
	 width: 100%;
	 height: 100%;
	 background: #dc0e0e;
	 top: -50%;
	 border-radius: 50% 50% 0 0;
}
 .face-wrapper .face-love .eye .eyelid:after {
	 top: auto;
	 right: -50%;
	 border-radius: 0 50% 50% 0;
}
 .face-wrapper:hover .face-love .eyes-wrapper .eye .eyelid {
	 transform: translateY(-5px) rotate(-45deg) scale3d(1.1, 1.1, 1.1);
}
 .face-wrapper:active .face-love .eyes-wrapper .eye .pupil {
	 top: 0%;
	 height: 85%;
}
 .face-wrapper:active .face-love .eyes-wrapper .eye .eyelid {
	 height: 12px;
	 transform: translateY(-5px) rotate(-45deg) scale3d(1.3, 1.3, 1.3);
}
 @keyframes shake {
	 0%, 100% {
		 transform: translate3d(0, 0, 0);
	}
	 25% {
		 transform: translate3d(1%, 1%, 0);
	}
	 75% {
		 transform: translate3d(1%, 1%, 0);
	}
}

.send-btn {
    border-radius: 0px;
    background: #4d5167;
    color: white;
    box-shadow: rgb(0 0 0 / 35%) 0 6px 10px 0;
}
@keyframes mymove {
  from{opacity:0;transform: scale(1.1);}
  to {opacity:1;transform: scale(0.9);}
}

.feedback-faces {
    height: 100px;
    position: relative;
}
.face_text{
text-align:center;
font-size:16px;
color:inherit;
}
.face-wtf_text,.face-disappointed_text,.face-sad_text,.face-happy_text,.face-love_text{
display:none;
font-size: 16px;
text-align: center;
/* width: 100%; */
position: absolute;
top: 70px;
margin: 0px 12px;
}
.face-wrapper:hover + div{
display:block;
}
.feedback-que {
    text-align: center;
    font-family: sans-serif;
    /* font-size: 20px; */
}
.feedback-que  h5, .feedback-que  .h5 {
    font-size: 18px;
}
.region_input{
display:none;
}




.left, .hidden {
    float: left;
    height:350px;
}

.left {
    width: 50%;
    background: #fff;
    z-index:1;
}

.hidden {
    width:25%;
    z-index:2;
    position:absolute;
    left:-1000px;
    background:#f90;
    color:#000;
}

.right {
    width:50%;
    float: right;
    height:350px;
    background:#000;
    color:#fff;
}

.clear {
    clear:both;
}
</style>

<div class="feeeback_container">

	<button class="feedback__label">
		<div class="feedback__text">Feedback</div>
	</button>

	<div class="feeeback_content">
		<button type="button" aria-label="Close" class="closeButton">
			<i class="bi bi-x"></i>
		</button>
		<p class="feeeback__Title">Give me a Feedback.</p>
		<div class="row m-0">
			<div class="col-12">
				<div class="select-style-2">
					<label>Feedback For</label>
					<div class="select-position">
						<select onchange="jay(this.value);">
							<option value="0">Select option</option>
							<option value="1">Admission</option>
							<option value="2">Infrastructure of Institute</option>
							<option value="3">Curriculum</option>
							<option value="4">Faculty</option>
							<option value="5">Over All Post Graduation</option>
						</select>
					</div>
				</div>
			</div>
		</div>

		<div class="row m-0">
			<div class="col-12">

				<div class="" id="feedback_1">
					<div class="feedback-que">
						<h5>Q. How was the admission process?</h5>
					</div>
					<div class="feedback-faces">
						<div class="row">
							<div class="face-wrapper">
								<label class="face grayscale face-wtf" for="rate-0"
									data-hint="Oh God! Why?!">
									<div class="eyes-wrapper">
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
									</div>
									<div class="mouth-wrapper">
										<div class="mouth"></div>
									</div>
								</label>
							</div>
							<div class="face-wtf_text">Oh God! Why?!</div>

							<div class="face-wrapper ">
								<label class="face grayscale face-disappointed" for="rate-1"
									data-hint="It sucks...">
									<div class="eyes-wrapper">
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
									</div>
									<div class="mouth-wrapper">
										<div class="mouth"></div>
									</div>
								</label>
							</div>
							<div class="face-disappointed_text">It sucks...</div>

							<div class="face-wrapper">
								<label class="face grayscale face-sad" for="rate-2"
									data-hint="It's ok. I guess.">
									<div class="eyes-wrapper">
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
									</div>
									<div class="mouth-wrapper">
										<div class="mouth"></div>
									</div>
								</label>
							</div>
							<div class="face-sad_text">It's ok. I guess.</div>

							<div class="face-wrapper ">
								<label class="face grayscale face-happy" for="rate-3"
									data-hint="This is great!">
									<div class="eyes-wrapper">
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
									</div>
									<div class="mouth-wrapper">
										<div class="mouth"></div>
									</div>
								</label>
							</div>
							<div class="face-happy_text">This is great!</div>

							<div class="face-wrapper ">
								<label class="face grayscale face-love" for="rate-4"
									data-hint="OMG! I love it!">
									<div class="eyes-wrapper">
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
									</div>
									<div class="mouth-wrapper">
										<div class="mouth"></div>
									</div>
								</label>
							</div>
							<div class="face-love_text">OMG! I love it!</div>
						</div>
					</div>
					
					<div class="region_input">
					<div class="input-style-1">
							<label>Please put your specific region.</label>
							<textarea placeholder="Please enter specific region" rows="3"></textarea>
					</div>
					</div>

					<ul class="buttons-group m-0 d-flex justify-content-center">
						<li><a href="#"
							class="main-btn send-btn square-btn btn-hover">Send Message <i
								class="bi-cursor-fill"></i>
						</a></li>

					</ul>

				</div>
				
				<div class="" id="feedback_2">
					<div class="feedback-que">
						<h5>Q. How is the infrastructure of institute?</h5>
					</div>
					<div class="feedback-faces">
						<div class="row">
							<div class="face-wrapper ">
								<label class="face grayscale face-wtf" for="rate-0"
									data-hint="Oh God! Why?!">
									<div class="eyes-wrapper">
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
									</div>
									<div class="mouth-wrapper">
										<div class="mouth"></div>
									</div>
								</label>
							</div>
							<div class="face-wtf_text">Oh God! Why?!</div>

							<div class="face-wrapper ">
								<label class="face grayscale face-disappointed" for="rate-1"
									data-hint="It sucks...">
									<div class="eyes-wrapper">
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
									</div>
									<div class="mouth-wrapper">
										<div class="mouth"></div>
									</div>
								</label>
							</div>
							<div class="face-disappointed_text">It sucks...</div>

							<div class="face-wrapper">
								<label class="face grayscale face-sad" for="rate-2"
									data-hint="It's ok. I guess.">
									<div class="eyes-wrapper">
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
									</div>
									<div class="mouth-wrapper">
										<div class="mouth"></div>
									</div>
								</label>
							</div>
							<div class="face-sad_text">It's ok. I guess.</div>

							<div class="face-wrapper ">
								<label class="face grayscale face-happy" for="rate-3"
									data-hint="This is great!">
									<div class="eyes-wrapper">
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
									</div>
									<div class="mouth-wrapper">
										<div class="mouth"></div>
									</div>
								</label>
							</div>
							<div class="face-happy_text">This is great!</div>

							<div class="face-wrapper ">
								<label class="face grayscale face-love" for="rate-4"
									data-hint="OMG! I love it!">
									<div class="eyes-wrapper">
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
									</div>
									<div class="mouth-wrapper">
										<div class="mouth"></div>
									</div>
								</label>
							</div>
							<div class="face-love_text">OMG! I love it!</div>
						</div>
					</div>
					
                       
                       <div class="region_input">
					<div class="input-style-1">
							<label>Please put your specific region.</label>
							<textarea placeholder="Please enter specific region" rows="3"></textarea>
					</div>
					</div>
					
					<ul class="buttons-group m-0 d-flex justify-content-center">
						<li><a href="#"
							class="main-btn send-btn square-btn btn-hover">Send Message <i
								class="bi-cursor-fill"></i>
						</a></li>

					</ul>

				</div>
				
				<div class="" id="feedback_3">
					<div class="feedback-que">
						<h5>Q. How was the last 6 months curriculum ?</h5>
					</div>
					<div class="feedback-faces">
						<div class="row">
							<div class="face-wrapper ">
								<label class="face grayscale face-wtf" for="rate-0"
									data-hint="Oh God! Why?!">
									<div class="eyes-wrapper">
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
									</div>
									<div class="mouth-wrapper">
										<div class="mouth"></div>
									</div>
								</label>
							</div>
							<div class="face-wtf_text">Oh God! Why?!</div>

							<div class="face-wrapper ">
								<label class="face grayscale face-disappointed" for="rate-1"
									data-hint="It sucks...">
									<div class="eyes-wrapper">
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
									</div>
									<div class="mouth-wrapper">
										<div class="mouth"></div>
									</div>
								</label>
							</div>
							<div class="face-disappointed_text">It sucks...</div>

							<div class="face-wrapper">
								<label class="face grayscale face-sad" for="rate-2"
									data-hint="It's ok. I guess.">
									<div class="eyes-wrapper">
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
									</div>
									<div class="mouth-wrapper">
										<div class="mouth"></div>
									</div>
								</label>
							</div>
							<div class="face-sad_text">It's ok. I guess.</div>

							<div class="face-wrapper ">
								<label class="face grayscale face-happy" for="rate-3"
									data-hint="This is great!">
									<div class="eyes-wrapper">
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
									</div>
									<div class="mouth-wrapper">
										<div class="mouth"></div>
									</div>
								</label>
							</div>
							<div class="face-happy_text">This is great!</div>

							<div class="face-wrapper ">
								<label class="face grayscale face-love" for="rate-4"
									data-hint="OMG! I love it!">
									<div class="eyes-wrapper">
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
									</div>
									<div class="mouth-wrapper">
										<div class="mouth"></div>
									</div>
								</label>
							</div>
							<div class="face-love_text">OMG! I love it!</div>
						</div>
					</div>
					
					<div class="region_input">
					<div class="input-style-1">
							<label>Please put your specific region.</label>
							<textarea placeholder="Please enter specific region" rows="3"></textarea>
					</div>
					</div>
   
					<ul class="buttons-group m-0 d-flex justify-content-center">
						<li><a href="#"
							class="main-btn send-btn square-btn btn-hover">Send Message <i
								class="bi-cursor-fill"></i>
						</a></li>

					</ul>

				</div>
				
				<div class="" id="feedback_4">
					<div class="feedback-que">
						<h5>Q. How was the faculty teaching?</h5>
					</div>
					<div class="feedback-faces">
						<div class="row">
							<div class="face-wrapper ">
								<label class="face grayscale face-wtf" for="rate-0"
									data-hint="Oh God! Why?!">
									<div class="eyes-wrapper">
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
									</div>
									<div class="mouth-wrapper">
										<div class="mouth"></div>
									</div>
								</label>
							</div>
							<div class="face-wtf_text">Oh God! Why?!</div>

							<div class="face-wrapper ">
								<label class="face grayscale face-disappointed" for="rate-1"
									data-hint="It sucks...">
									<div class="eyes-wrapper">
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
									</div>
									<div class="mouth-wrapper">
										<div class="mouth"></div>
									</div>
								</label>
							</div>
							<div class="face-disappointed_text">It sucks...</div>

							<div class="face-wrapper">
								<label class="face grayscale face-sad" for="rate-2"
									data-hint="It's ok. I guess.">
									<div class="eyes-wrapper">
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
									</div>
									<div class="mouth-wrapper">
										<div class="mouth"></div>
									</div>
								</label>
							</div>
							<div class="face-sad_text">It's ok. I guess.</div>

							<div class="face-wrapper ">
								<label class="face grayscale face-happy" for="rate-3"
									data-hint="This is great!">
									<div class="eyes-wrapper">
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
									</div>
									<div class="mouth-wrapper">
										<div class="mouth"></div>
									</div>
								</label>
							</div>
							<div class="face-happy_text">This is great!</div>

							<div class="face-wrapper ">
								<label class="face grayscale face-love" for="rate-4"
									data-hint="OMG! I love it!">
									<div class="eyes-wrapper">
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
									</div>
									<div class="mouth-wrapper">
										<div class="mouth"></div>
									</div>
								</label>
							</div>
							<div class="face-love_text">OMG! I love it!</div>
						</div>
					</div>
					
					<div class="region_input">
					<div class="input-style-1">
							<label>Please put your specific region.</label>
							<textarea placeholder="Please enter specific region" rows="3"></textarea>
					</div>
					</div>

					<ul class="buttons-group m-0 d-flex justify-content-center">
						<li><a href="#"
							class="main-btn send-btn square-btn btn-hover">Send Message <i
								class="bi-cursor-fill"></i>
						</a></li>

					</ul>

				</div>
				
				<div class="" id="feedback_5">
					<div class="feedback-que">
						<h5>Q. How was the Over all post graduation experience?</h5>
					</div>
					<div class="feedback-faces">
						<div class="row">
							<div class="face-wrapper ">
								<label class="face grayscale face-wtf" for="rate-0"
									data-hint="Oh God! Why?!">
									<div class="eyes-wrapper">
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
									</div>
									<div class="mouth-wrapper">
										<div class="mouth"></div>
									</div>
								</label>
							</div>
							<div class="face-wtf_text">Oh God! Why?!</div>

							<div class="face-wrapper ">
								<label class="face grayscale face-disappointed" for="rate-1"
									data-hint="It sucks...">
									<div class="eyes-wrapper">
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
									</div>
									<div class="mouth-wrapper">
										<div class="mouth"></div>
									</div>
								</label>
							</div>
							<div class="face-disappointed_text">It sucks...</div>

							<div class="face-wrapper">
								<label class="face grayscale face-sad" for="rate-2"
									data-hint="It's ok. I guess.">
									<div class="eyes-wrapper">
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
									</div>
									<div class="mouth-wrapper">
										<div class="mouth"></div>
									</div>
								</label>
							</div>
							<div class="face-sad_text">It's ok. I guess.</div>

							<div class="face-wrapper ">
								<label class="face grayscale face-happy" for="rate-3"
									data-hint="This is great!">
									<div class="eyes-wrapper">
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
									</div>
									<div class="mouth-wrapper">
										<div class="mouth"></div>
									</div>
								</label>
							</div>
							<div class="face-happy_text">This is great!</div>

							<div class="face-wrapper ">
								<label class="face grayscale face-love" for="rate-4"
									data-hint="OMG! I love it!">
									<div class="eyes-wrapper">
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
										<div class="eye">
											<div class="pupil">
												<div class="eyelid"></div>
											</div>
										</div>
									</div>
									<div class="mouth-wrapper">
										<div class="mouth"></div>
									</div>
								</label>
							</div>
							<div class="face-love_text">OMG! I love it!</div>
						</div>
					</div>
					
					<div class="region_input">
					<div class="input-style-1">
							<label>Please put your specific region.</label>
							<textarea placeholder="Please enter specific region" rows="3"></textarea>
					</div>
					</div>

					<ul class="buttons-group m-0 d-flex justify-content-center">
						<li><a href="#"
							class="main-btn send-btn square-btn btn-hover">Send Message <i
								class="bi-cursor-fill"></i>
						</a></li>

					</ul>

				</div>
				
				
			</div>
		</div>
	</div>
</div>





<script>
	$(document).ready(function() {
		

		$(".feedback__label").click(function() {
			$(".feeeback_content").show();
			$(".feedback__label").hide();
		});

		$(".closeButton").click(function() {
			$(".feeeback_content").hide();
			$(".feedback__label").show();
				
		});
		
		$(".face-wtf").click(function() {
			$(".region_input").show();
		});
		
		$(".face-disappointed").click(function() {
			$(".region_input").hide();
		});
		
		$(".face-sad").click(function() {
			$(".region_input").hide();
		});
		
		$(".face-happy").click(function() {
			$(".region_input").hide();
		});
		
		$(".face-love").click(function() {
			$(".region_input").hide();
		});
		
	});
</script>

<script>
	$(document).ready(function() {

		$("#feedback_1").hide();
		$("#feedback_2").hide();
		$("#feedback_3").hide();
		$("#feedback_4").hide();
		$("#feedback_5").hide();

	});

	function Admission() {

		$("#feedback_1").show();
		$("#feedback_2").hide();
		$("#feedback_3").hide();
		$("#feedback_4").hide();
		$("#feedback_5").hide();

	}

	function Institute() {

		$("#feedback_1").hide();
		$("#feedback_2").show();
		$("#feedback_3").hide();
		$("#feedback_4").hide();
		$("#feedback_5").hide();
	}

	function Curriculum() {

		$("#feedback_1").hide();
		$("#feedback_2").hide();
		$("#feedback_3").show();
		$("#feedback_4").hide();
		$("#feedback_5").hide();
	}

	function Faculty() {

		$("#feedback_1").hide();
		$("#feedback_2").hide();
		$("#feedback_3").hide();
		$("#feedback_4").show();
		$("#feedback_5").hide();
	}

	function PostGraduation() {

		$("#feedback_1").hide();
		$("#feedback_2").hide();
		$("#feedback_3").hide();
		$("#feedback_4").hide();
		$("#feedback_5").show();
	}

	function jay(val) {
		if (val == "1") {
			Admission();
			$(".region_input").hide();
		}
		if (val == "2") {
			Institute();
			$(".region_input").hide();
		}
		if (val == "3") {
			Curriculum();
			$(".region_input").hide();
		}
		if (val == "4") {
			Faculty();
			$(".region_input").hide();
		}
		if (val == "5") {
			PostGraduation();
			$(".region_input").hide();
		}

	}
</script>




