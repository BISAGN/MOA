<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://www.gstatic.com/firebasejs/4.12.0/firebase.js"></script>
<style>
 .hidden {
	 display: none;
}
 .row {
	 display: flex;
	 justify-content: center;
}
 .text-center {
	 text-align: center;
}
 .slide-out-x {
	 transform: translateX(-100%);
}
 .slide-out-x-alt {
	 transform: translateX(100%);
}
 .slide-out-y {
	 transform: translateY(-100%);
}
 .slide-out-y-alt {
	 transform: translateY(100%);
}
 .text-violet {
	 color: #7f28ff;
}
 .text-gray {
	 color: #4f4f4f;
}
 .feedback-wrapper {
	 position: fixed;
	 top: 0;
	 left: 0;
	 bottom: 0;
	 right: 0;
	 margin: auto;
	 background: #f59d69;
	 max-width: 480px;
	 height: fit-content;
/* 	 min-height: 150px; */
/* 	 border-radius: 10px; */
	 box-shadow: 0 10px 25px rgba(0, 0, 0, 0.2);
	 transition: 0.55s cubic-bezier(0.1, 1, 0.25, 1.15);
	 z-index: 1000;
	 
}

 .feedback-wrapper.title-hovered:active {
	 transform: translateY(-5%);
}
 .feedback-wrapper.at-bottom {
	 top: auto;
	 transform: translateY(100%);
	 bottom: 0px;
	 transition: 0.2s ease-out;
}
 .feedback-wrapper.at-bottom:active {
	 transform: translateY(105%);
}
 .feedback-wrapper .feedback-title {
	 padding: 8px;
	 color: #fff;
	 background: #ee4e34;
	 border-radius: 4px 4px 0 0;
	 height: 35px;
     width: 100px;
     margin: -35px auto 0;
}
.feedback-wrapper .feedback-title:hover{
    transform: translateX(0);
    box-shadow: 0 0 35px 2px rgb(0 0 0 / 24%);
    opacity: 1;
 }
 .feedback-wrapper .feedback-title h5{
     margin:0;
     color: white;
}
 .feedback-wrapper .feedback-title h1 {
	 margin: 0;
	 font-size: 1.4rem;
}
 .feedback-wrapper .feedback-content {
	 max-height: calc(100vh - 35px);
	 overflow-y: auto;
	 width: 100%;
    background-color: #fff;
    box-shadow: rgb(0 0 0 / 35%) 0 6px 100px 0;
    overflow: hidden;
}
 .feedback-wrapper .feedback-faces {
	 padding: 10px;
	 height: 110px;
	 overflow: hidden;
}
 .face-wrapper {
	 position: relative;
	 left: 0;
	 right: 0;
	 width: 60px;
	 height: 60px;
	 padding: 10px;
	 box-sizing: content-box;
	 transition: 0.25s ease-out;
}
 .face-wrapper .face-counter {
	 position: absolute;
	 right: 0;
	 background: #ee4e34;
	 width: 25%;
	 height: 25%;
	 text-align: center;
	 line-height: 170%;
	 font-size: 70%;
	 border-radius: 50%;
	 font-weight: 800;
	 color: #fff;
	 z-index: 99;
	 box-shadow: inset 0 -1.2px 1.8px #b92413;
	 transform: rotate(0deg) scale(1);
	 transition: 0.25s ease-out;
	 
}
 .face-wrapper .face-counter.invisible {
	 transform: rotate(150deg) scale(0);
	 opacity: 0;
}
 .face-wrapper .face {
	 display: block;
	 position: relative;
	 background: #ffcd00;
    border-radius: 50%;
    width: 60px;
    height: 60px;
    box-shadow: 0 0.6px 1.2px #cc9117;
	 transition: 0.25s ease-out;
	 animation-name: mymove;
     animation-duration: 1.5s;
}


/*  .face-wrapper .face { */
/* 	 display: block; */
/* 	 position: relative; */
/* 	 background: #ffcd00; */
/*     border-radius: 50%; */
/*     width: 60px; */
/*     height: 60px; */
/*     box-shadow: 0 0.6px 1.2px #cc9117; */
/* 	/*  transition: 0.25s ease-out; */
/* 	 animation-name: mymove; */
/*      animation-duration: 1.5s; */ */
/*      -webkit-animation: dissolveInBottom 0.7s ease-in-out; */
     
/* } */
/* @-webkit-keyframes dissolveInBottom{ */
/* 	0%{opacity: 0; transform:translate3d(0,100px,0)} */
/* 	100%{opacity: 1; transform:translate3d(0,0,0)} */
/* } */


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
	 width: 15.6px;
	 height: 15.6px;
	 margin-top: 32%;
	 left: 18%;
	 border-radius: 50%;
	 transition: 0.25s ease-out;
}
 .face-wrapper .face .eye:last-of-type {
	 right: 18%;
	 left: auto;
}
 .face-wrapper .face .eye .pupil {
	 position: absolute;
	 background: #000;
	 left: 0;
	 right: 0;
	 top: 0;
	 margin: auto;
	 width: 85%;
	 height: 85%;
	 border-radius: 50%;
	 background: #794014;
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
	 top: 60%;
	 width: 100%;
}
 .face-wrapper .face .mouth-wrapper .mouth {
	 width: 40%;
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
/* 	 background: #d3d3d3; */
/* 	 box-shadow: 0 0.6px 1.2px #ccc; */
	 background: #f9ca07;
     box-shadow: 0 0.6px 1.2px #fad128;
}
 .face-wrapper .face.grayscale:after {
/* 	 box-shadow: inset 0px -3px 9px #bbb; */
	 box-shadow: inset 0px -3px 9px #deb000;
}
 .face-wrapper .face.grayscale .pupil {
/* 	 background: #4f4f4f; */
/* 	 box-shadow: inset 0 -1.2px 0.6px 0px #949494; */
	 
	 background: #794014;
    box-shadow: inset 0 -1.2px 0.6px 0px #8b5a36;
}
 .face-wrapper .face.grayscale .eyelid {
/* 	 background: #d3d3d3; */
}
 .face-wrapper .face.grayscale.face-love .eyelid, .face-wrapper .face.grayscale.face-love .eyelid:before, .face-wrapper .face.grayscale.face-love .eyelid:after {
/* 	 background: #707070; */
}
 .face-wrapper .face.grayscale .mouth {
/* 	 background: #4f4f4f; */
/* 	 box-shadow: inset 0 -1.2px 0.6px 0px #949494; */
	 background: #ce7f42;
    box-shadow: inset 0 -1.2px 0.6px 0px #cf813f;
}
 .face-wrapper .face.grayscale .mouth:before {
/* 	 background: #d3d3d3; */
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
	 width: 12px;
	 height: 12px;
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


.feedback_lable_container{
    right: 0;
}
.feedback_lable_container{
    position: fixed;
    top: 50%;
    transform: translateY(-50%);
    box-sizing: border-box !important;
    display: block;
    direction: ltr !important;
}
.feedback_lable_container .feedback__label {
    border-radius: 3px 0 0 3px;
    transform: translateX(2px);
}
.feedback_lable_container .feedback__label {
    font-size: 13px;
    position: relative;
    border: none;
    outline: none;
    padding: 12px 14px 12px 12px;
    cursor: pointer;
    white-space: nowrap;
    background-color: #f4364c !important;
    background-color: var(--hjFeedbackAccentColor, #f4364c) !important;
    transition: transform 0.1s ease-in-out, box-shadow 0.1s ease-in-out;
    opacity: 0.96;
    width: 40px;
    display: flex;
    flex-direction: column;
    align-items: flex-start;
}
.feedback_lable_container .feedback__text {
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

.feedback_lable_container .feedback__label:hover {
    transform: translateX(0);
    box-shadow: 0 0 35px 2px rgb(0 0 0 / 24%);
    opacity: 1;
}
.feedback-que{
padding-top:10px;
}
.feedback-que h5 {
    font-size: 22px;
    text-align: center;
    color: #ffffff;
}
.send-btn {
    border-radius: 0px;
    background: #ee4e34;
    color: white;
}
.feedback-content{
   background: -moz-linear-gradient(45deg, #02e1ba 0%, #26c9f2 29%, #d911f2 66%, #ffa079 100%);
    background: -webkit-linear-gradient(45deg, #02e1ba 0%,#26c9f2 29%,#d911f2 66%,#ffa079 100%);
/*     background: linear-gradient(45deg, #02e1ba 0%,#26c9f273 29%,#26c9f273 66%,#02e1ba 100%); */
        background: linear-gradient(45deg, #f58f57c9 0%,#f7a26d 29%,#f27b2673 66%,#f4752b 100%);
    background-size: 400% 400%;
    -webkit-animation: Gradient 15s ease infinite;
    -moz-animation: Gradient 15s ease infinite;
    animation: Gradient 15s ease infinite;
    /* min-height: calc(100vh - 2rem); */
    display: flex;
    flex-direction: column;
    align-items: stretch;
    justify-content: space-evenly;
    overflow: hidden;
    position: relative;
  }  
    
.feedback-content::before, 
.feedback-content::after {
	content: "";
	width: 70vmax;
	height: 70vmax;
	position: absolute;
	background: rgba(255, 255, 255, 0.07);
	left: -20vmin;
	top: -20vmin;
	animation: morph 15s linear infinite alternate, spin 20s linear infinite;
	z-index: 1;
	will-change: border-radius, transform;
	transform-origin: 55% 55%;
	pointer-events: none; 
}
	
.feedback-content::after {
    width: 70vmin;
    height: 70vmin;
    left: auto;
    right: -10vmin;
    top: auto;
    bottom: 0;
    animation: morph 10s linear infinite alternate, spin 26s linear infinite reverse;
    transform-origin: 20% 20%; 
}

@-webkit-keyframes Gradient {
	0% {
		background-position: 0 50%
	}
	50% {
		background-position: 100% 50%
	}
	100% {
		background-position: 0 50%
	}
}

@-moz-keyframes Gradient {
	0% {
		background-position: 0 50%
	}
	50% {
		background-position: 100% 50%
	}
	100% {
		background-position: 0 50%
	}
}

@keyframes Gradient {
	0% {
		background-position: 0 50%
	}
	50% {
		background-position: 100% 50%
	}
	100% {
		background-position: 0 50%
	}
}

@keyframes morph {
  0% {
    border-radius: 40% 60% 60% 40% / 70% 30% 70% 30%; }
  100% {
    border-radius: 40% 60%; } 
}

@keyframes spin {
  to {
    transform: rotate(1turn); 
  } 
}
	.st0{display:none;}
	.st1{display:inline;}
	.st2{opacity:0.29;}
	.st3{fill:#FFFFFF;}
	.st4{clip-path:url(#SVGID_2_);fill:#FFFFFF;}
	.st5{clip-path:url(#SVGID_4_);}
	.st6{clip-path:url(#SVGID_6_);}
	.st7{clip-path:url(#SVGID_8_);}
	.st8{clip-path:url(#SVGID_10_);}
	.st9{fill:none;}
	.st10{clip-path:url(#SVGID_12_);}
	.st11{opacity:0.7;}
	.st12{clip-path:url(#SVGID_14_);}
	.st13{opacity:0.2;}
	.st14{clip-path:url(#SVGID_16_);}
	.st15{opacity:0.3;fill:#FFFFFF;enable-background:new;}
	
	.select_fildes , .select-style-2 {
    width: 100%;
    display: flex;
    align-items: center;
    margin: auto;
    justify-content: center;
}
.select_fildes {
    padding: 10px 0;
    background: #f59d69;
    position: relative;
    z-index: 1;
    color: white;
    font-family: monospace;
}
.select_fildes .select-style-2 .select-position select {
    width: 100%;
    background: transparent;
    border: 1px solid #ee4e34;
    color: #ffffff;
}
.select-style-2 .select-position::before {
    border-bottom: 1px solid #ffffff;
    border-right: 1px solid #ffffff;
}
.select-style-2 .select-position::after {
    border-top: 1px solid #ffffff;
    border-left: 1px solid #ffffff;
}
.select-style-2  option {
    color: #ee4e34;
}

@keyframes mymove {
  from{opacity:0;transform: scale(1.1);}
  to {opacity:1;transform: scale(0.9);}
}


@media (max-width: 991px){
.feedback-wrapper {
    max-width: 450px;
}
.select_fildes .select-style-2 .select-position select {
    width: 100%;
    padding: 12px 12px;
    padding-right: 28px
}
.select_fildes, .select-style-2 { 
 } 
.feedback-que h5 {
    font-size: 20px;
}
.face-wrapper {
    width: 55px;
    height: 55px;
    padding: 5px;
}
.feedback-wrapper .feedback-faces {
    height: 100px;
}
.face-wrapper .face {
    width: 55px;
    height: 55px;
}
.face-wrapper .face .eye {
    width: 13.6px;
    height: 13.6px;
}
.face-wrapper .face-love .eye .eyelid {
    width: 11px;
    height: 11px;
}
}

@media (max-width: 575px){
.feedback-wrapper {
    max-width: 350px;
}
.select_fildes, .select-style-2 {
    padding: 5px 10px;
    display: inline-block;
    text-align: center;
}
.feedback-que h5 {
    font-size: 18px;
}
.face-wrapper {
    width: 50px;
    height: 50px;
    padding: 5px;
}
.feedback-wrapper .feedback-faces {
    height: 92px;
}
.face-wrapper .face {
    width: 50px;
    height: 50px;
}
.face-wrapper .face .eye {
    width: 12.6px;
    height: 12.6px;
}
.face-wrapper .face-love .eye .eyelid {
    width: 10.5px;
    height: 10.5px;
}


}
/* .text-tooltip { */
/*     position: absolute; */
/*     visibility: hidden; */
/*     opacity: 0; */
/*     transition: opacity 1s ease; */
/*     bottom: 150%; */
/*     padding: 10px; */
/*     border-radius: 2px; */
/*     background: rgb(2, 2, 111); */
/*     color: rgb(255, 255, 255); */
/*     box-shadow: 0px 0px 12px rgba(2, 2, 111, 0.4); */
/*   } */
  
/*   .feedback-title:hover + .text-tooltip { */
/*     visibility: visible; */
/*     opacity: 1; */
/*     transition: opacity 0.5s ease-in-out; */
/*   } */
  
  
.feedback-wrapper .feedback-title {
    color: #fff;
    background: var(--bs-blue);
}
.select_fildes {
    padding: 10px 0;
    background: #2f71fd;
}
.feedback-content {
    background: linear-gradient(45deg, #2c6dfc 0%,#2c6dfc 29%,#2c6dfc 66%,#4e99fd 100%);
    }
    .send-btn {
    border-radius: 0px;
    background: #2c6dfc;
    color: white;
}
</style>
<div class="feedback-wrapper at-bottom">


  <div class="feedback-title">
    <h5 class="text-center m-0">Feedback
    </h5>
  </div>
  
<!--  <div class="text-tooltip"> -->
<!--  The text-overflow property may be specified using one or two values. -->
<!-- </div> -->
  
 <div class="select_fildes">
									<div class="select-style-2">
										<label>Feedback For<span class="mandatory">*</span></label>
									<div class="select-position">
										<select onchange="jay(this.value);">
											<option value="0">Select option</option>
											<option value="1" >Admission</option>
											<option value="2" >Infrastructure of Institute</option>
											<option value="3" >Curriculum</option>
											<option value="4" >Faculty</option>
											<option value="5" >Over All Post Graduation</option>
										</select>
									</div>
								</div>
									<!-- end select -->
</div>
  
  <div class="feedback-content" id="feedback_1">
  <div class="feedback-que">
 <h5>Q. How was the admission process?</h5> 
  </div>
    <div class="feedback-faces">
      <div class="row">
        <div class="face-wrapper slide-out-y-alt">
          <div class="face-counter invisible" data-title-none="No one rated it like this" data-title-one="One person rated it like this" data-title-many="other people rated it like this"></div>
          <input class="rate-input hidden" id="rate-0" type="radio" name="rating" value="0"/>
          <label class="face grayscale face-wtf" for="rate-0" data-hint="Oh God! Why?!">
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
        <div class="face-wrapper slide-out-y-alt">
          <div class="face-counter invisible" data-title-none="No one rated it like this" data-title-one="One person rated it like this" data-title-many="other people rated it like this"></div>
          <input class="rate-input hidden" id="rate-1" type="radio" name="rating" value="1"/>
          <label class="face grayscale face-disappointed" for="rate-1" data-hint="It sucks...">
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
        <div class="face-wrapper slide-out-y-alt">
          <div class="face-counter invisible" data-title-none="No one rated it like this" data-title-one="One person rated it like this" data-title-many="other people rated it like this"></div>
          <input class="rate-input hidden" id="rate-2" type="radio" name="rating" value="2"/>
          <label class="face grayscale face-sad" for="rate-2" data-hint="It's ok. I guess.">
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
        <div class="face-wrapper slide-out-y-alt">
          <div class="face-counter invisible" data-title-none="No one rated it like this" data-title-one="One person rated it like this" data-title-many="other people rated it like this"></div>
          <input class="rate-input hidden" id="rate-3" type="radio" name="rating" value="3"/>
          <label class="face grayscale face-happy" for="rate-3" data-hint="This is great!">
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
        <div class="face-wrapper slide-out-y-alt">
          <div class="face-counter invisible" data-title-none="No one rated it like this" data-title-one="One person rated it like this" data-title-many="other people rated it like this"></div>
          <input class="rate-input hidden" id="rate-4" type="radio" name="rating" value="4"/>
          <label class="face grayscale face-love" for="rate-4" data-hint="OMG! I love it!">
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
      </div>
      <div class="faces-hint text-center text-body" data-default-hint="">
      </div>
    </div>
    <ul class="buttons-group m-0 d-flex justify-content-center">
    <li><a href="#" class="main-btn send-btn square-btn btn-hover">Send Message <i class="bi-cursor-fill"></i></a></li>
    
    </ul>
  </div>
<div class="feedback-content" id="feedback_2">
  <div class="feedback-que">
 <h5>Q. How is the infrastructure of institute?</h5> 
  </div>
    <div class="feedback-faces">
      <div class="row">
        <div class="face-wrapper slide-out-y-alt">
          <div class="face-counter invisible" data-title-none="No one rated it like this" data-title-one="One person rated it like this" data-title-many="other people rated it like this"></div>
          <input class="rate-input hidden" id="rate-0" type="radio" name="rating" value="0"/>
          <label class="face grayscale face-wtf" for="rate-0" data-hint="Oh God! Why?!">
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
        <div class="face-wrapper slide-out-y-alt">
          <div class="face-counter invisible" data-title-none="No one rated it like this" data-title-one="One person rated it like this" data-title-many="other people rated it like this"></div>
          <input class="rate-input hidden" id="rate-1" type="radio" name="rating" value="1"/>
          <label class="face grayscale face-disappointed" for="rate-1" data-hint="It sucks...">
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
        <div class="face-wrapper slide-out-y-alt">
          <div class="face-counter invisible" data-title-none="No one rated it like this" data-title-one="One person rated it like this" data-title-many="other people rated it like this"></div>
          <input class="rate-input hidden" id="rate-2" type="radio" name="rating" value="2"/>
          <label class="face grayscale face-sad" for="rate-2" data-hint="It's ok. I guess.">
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
        <div class="face-wrapper slide-out-y-alt">
          <div class="face-counter invisible" data-title-none="No one rated it like this" data-title-one="One person rated it like this" data-title-many="other people rated it like this"></div>
          <input class="rate-input hidden" id="rate-3" type="radio" name="rating" value="3"/>
          <label class="face grayscale face-happy" for="rate-3" data-hint="This is great!">
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
        <div class="face-wrapper slide-out-y-alt">
          <div class="face-counter invisible" data-title-none="No one rated it like this" data-title-one="One person rated it like this" data-title-many="other people rated it like this"></div>
          <input class="rate-input hidden" id="rate-4" type="radio" name="rating" value="4"/>
          <label class="face grayscale face-love" for="rate-4" data-hint="OMG! I love it!">
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
      </div>
      <div class="faces-hint text-center text-body" data-default-hint="">
      </div>
    </div>
    <ul class="buttons-group m-0 d-flex justify-content-center">
    <li><a href="#" class="main-btn send-btn square-btn btn-hover">Send Message <i class="bi-cursor-fill"></i></a></li>
    
    </ul>
  </div>
<div class="feedback-content" id="feedback_3">
  <div class="feedback-que">
 <h5>Q. How was the last 6 months curriculum ?</h5> 
  </div>
    <div class="feedback-faces">
      <div class="row">
        <div class="face-wrapper slide-out-y-alt">
          <div class="face-counter invisible" data-title-none="No one rated it like this" data-title-one="One person rated it like this" data-title-many="other people rated it like this"></div>
          <input class="rate-input hidden" id="rate-0" type="radio" name="rating" value="0"/>
          <label class="face grayscale face-wtf" for="rate-0" data-hint="Oh God! Why?!">
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
        <div class="face-wrapper slide-out-y-alt">
          <div class="face-counter invisible" data-title-none="No one rated it like this" data-title-one="One person rated it like this" data-title-many="other people rated it like this"></div>
          <input class="rate-input hidden" id="rate-1" type="radio" name="rating" value="1"/>
          <label class="face grayscale face-disappointed" for="rate-1" data-hint="It sucks...">
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
        <div class="face-wrapper slide-out-y-alt">
          <div class="face-counter invisible" data-title-none="No one rated it like this" data-title-one="One person rated it like this" data-title-many="other people rated it like this"></div>
          <input class="rate-input hidden" id="rate-2" type="radio" name="rating" value="2"/>
          <label class="face grayscale face-sad" for="rate-2" data-hint="It's ok. I guess.">
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
        <div class="face-wrapper slide-out-y-alt">
          <div class="face-counter invisible" data-title-none="No one rated it like this" data-title-one="One person rated it like this" data-title-many="other people rated it like this"></div>
          <input class="rate-input hidden" id="rate-3" type="radio" name="rating" value="3"/>
          <label class="face grayscale face-happy" for="rate-3" data-hint="This is great!">
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
        <div class="face-wrapper slide-out-y-alt">
          <div class="face-counter invisible" data-title-none="No one rated it like this" data-title-one="One person rated it like this" data-title-many="other people rated it like this"></div>
          <input class="rate-input hidden" id="rate-4" type="radio" name="rating" value="4"/>
          <label class="face grayscale face-love" for="rate-4" data-hint="OMG! I love it!">
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
      </div>
      <div class="faces-hint text-center text-body" data-default-hint="">
      </div>
    </div>
    <ul class="buttons-group m-0 d-flex justify-content-center">
    <li><a href="#" class="main-btn send-btn square-btn btn-hover">Send Message <i class="bi-cursor-fill"></i></a></li>
    
    </ul>
  </div>
<div class="feedback-content" id="feedback_4">
  <div class="feedback-que">
 <h5>Q. How was the faculty teaching?</h5> 
  </div>
    <div class="feedback-faces">
      <div class="row">
        <div class="face-wrapper slide-out-y-alt">
          <div class="face-counter invisible" data-title-none="No one rated it like this" data-title-one="One person rated it like this" data-title-many="other people rated it like this"></div>
          <input class="rate-input hidden" id="rate-0" type="radio" name="rating" value="0"/>
          <label class="face grayscale face-wtf" for="rate-0" data-hint="Oh God! Why?!">
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
        <div class="face-wrapper slide-out-y-alt">
          <div class="face-counter invisible" data-title-none="No one rated it like this" data-title-one="One person rated it like this" data-title-many="other people rated it like this"></div>
          <input class="rate-input hidden" id="rate-1" type="radio" name="rating" value="1"/>
          <label class="face grayscale face-disappointed" for="rate-1" data-hint="It sucks...">
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
        <div class="face-wrapper slide-out-y-alt">
          <div class="face-counter invisible" data-title-none="No one rated it like this" data-title-one="One person rated it like this" data-title-many="other people rated it like this"></div>
          <input class="rate-input hidden" id="rate-2" type="radio" name="rating" value="2"/>
          <label class="face grayscale face-sad" for="rate-2" data-hint="It's ok. I guess.">
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
        <div class="face-wrapper slide-out-y-alt">
          <div class="face-counter invisible" data-title-none="No one rated it like this" data-title-one="One person rated it like this" data-title-many="other people rated it like this"></div>
          <input class="rate-input hidden" id="rate-3" type="radio" name="rating" value="3"/>
          <label class="face grayscale face-happy" for="rate-3" data-hint="This is great!">
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
        <div class="face-wrapper slide-out-y-alt">
          <div class="face-counter invisible" data-title-none="No one rated it like this" data-title-one="One person rated it like this" data-title-many="other people rated it like this"></div>
          <input class="rate-input hidden" id="rate-4" type="radio" name="rating" value="4"/>
          <label class="face grayscale face-love" for="rate-4" data-hint="OMG! I love it!">
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
      </div>
      <div class="faces-hint text-center text-body" data-default-hint="">
      </div>
    </div>
    <ul class="buttons-group m-0 d-flex justify-content-center">
    <li><a href="#" class="main-btn send-btn square-btn btn-hover">Send Message <i class="bi-cursor-fill"></i></a></li>
    
    </ul>
  </div>
<div class="feedback-content" id="feedback_5">
  <div class="feedback-que">
 <h5>Q. How was the Over all post graduation experience?</h5> 
  </div>
    <div class="feedback-faces">
      <div class="row">
        <div class="face-wrapper slide-out-y-alt">
          <div class="face-counter invisible" data-title-none="No one rated it like this" data-title-one="One person rated it like this" data-title-many="other people rated it like this"></div>
          <input class="rate-input hidden" id="rate-0" type="radio" name="rating" value="0"/>
          <label class="face grayscale face-wtf" for="rate-0" data-hint="Oh God! Why?!">
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
        <div class="face-wrapper slide-out-y-alt">
          <div class="face-counter invisible" data-title-none="No one rated it like this" data-title-one="One person rated it like this" data-title-many="other people rated it like this"></div>
          <input class="rate-input hidden" id="rate-1" type="radio" name="rating" value="1"/>
          <label class="face grayscale face-disappointed" for="rate-1" data-hint="It sucks...">
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
        <div class="face-wrapper slide-out-y-alt">
          <div class="face-counter invisible" data-title-none="No one rated it like this" data-title-one="One person rated it like this" data-title-many="other people rated it like this"></div>
          <input class="rate-input hidden" id="rate-2" type="radio" name="rating" value="2"/>
          <label class="face grayscale face-sad" for="rate-2" data-hint="It's ok. I guess.">
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
        <div class="face-wrapper slide-out-y-alt">
          <div class="face-counter invisible" data-title-none="No one rated it like this" data-title-one="One person rated it like this" data-title-many="other people rated it like this"></div>
          <input class="rate-input hidden" id="rate-3" type="radio" name="rating" value="3"/>
          <label class="face grayscale face-happy" for="rate-3" data-hint="This is great!">
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
        <div class="face-wrapper slide-out-y-alt">
          <div class="face-counter invisible" data-title-none="No one rated it like this" data-title-one="One person rated it like this" data-title-many="other people rated it like this"></div>
          <input class="rate-input hidden" id="rate-4" type="radio" name="rating" value="4"/>
          <label class="face grayscale face-love" for="rate-4" data-hint="OMG! I love it!">
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
      </div>
      <div class="faces-hint text-center text-body" data-default-hint="">
      </div>
    </div>
    <ul class="buttons-group m-0 d-flex justify-content-center">
    <li><a href="#" class="main-btn send-btn square-btn btn-hover">Send Message <i class="bi-cursor-fill"></i></a></li>
    
    </ul>
  </div>
</div>





<script>
"use strict";

const $canvas = $('body');
const $eyes = $('.eye');
const $rateInputs = $('.rate-input');

function vendorize(key, value) {
  const vendors = ['webkit', 'moz', 'ms', 'o', ''];
  var result = {};
  vendors.map(vendor => {
    const vKey = vendor ? '-' + vendor + '-' + key : key;
    result[vKey] = value;
  });
  return result;
} //https://github.com/jfmdev/jqEye/blob/master/Source/jqeye.js


function circle_position(x, y, r) {
  // Circle: x^2 + y^2 = r^2
  var res = {
    x: x,
    y: y
  };

  if (x * x + y * y > r * r) {
    if (x !== 0) {
      var m = y / x;
      res.x = Math.sqrt(r * r / (m * m + 1));
      res.x = x > 0 ? res.x : -res.x;
      res.y = Math.abs(m * res.x);
      res.y = y > 0 ? res.y : -res.y;
    } else {
      res.y = y > 0 ? r : -r;
    }
  }

  return res;
}

;

function findCenter(coords, sizeX, sizeY) {
  return {
    x: coords.left + sizeX / 2,
    y: coords.top + sizeY / 2
  };
}

function deltaVal(val, targetVal) {
  const delta = Math.min(100.0, ts - prevTs);
  const P = 0.001 * delta;
  return val + P * (targetVal - val);
}

function changeEyesPosition(px, py) {
  function changePosition() {
    const $t = $(this);
    const $pupil = $t.find('.pupil');
    const t_w = $t.width();
    const t_h = $t.height();
    const t_o = $t.offset();
    const t_p = $t.position();
    const abs_center = findCenter(t_o, t_w, t_h);
    const pos_x = px - abs_center.x + $(window).scrollLeft();
    const pos_y = py - abs_center.y + $(window).scrollTop();
    const cir = circle_position(pos_x, pos_y, t_w / 20);
    const styles = vendorize('transform', 'translateX(' + cir.x + 'px) translateY(' + cir.y + 'px)');
    $pupil.css(styles);
  }

  $eyes.each(changePosition);
}

function handleMouseMove(e) {
  const px = e.pageX,
        py = e.pageY;
  changeEyesPosition(px, py);
}

$canvas.on('mousemove', handleMouseMove);

function getFace($element) {
  return $element.parent('.face-wrapper').find('.face');
}

function handleFaceHover($face) {
  const $hint = $('.faces-hint');
  const hintText = $face.attr('data-hint') || $hint.attr('data-default-hint');
  $hint.text(hintText);
}

function handleFacesHover(e) {
  const $face = getFace($(e.target));
  handleFaceHover($face);
}

$('.feedback-faces').on('mousemove', handleFacesHover);

function handleFeedbackTitleHover(e) {
  const isHover = e.type === 'mouseenter';
  $(this).parent().toggleClass('title-hovered', isHover);
}

$('.feedback-title').on('mouseenter mouseleave', handleFeedbackTitleHover);

function handleFeedbackToggle() {
  const $this = $(this),
        $parent = $this.parent();
  $parent.toggleClass('at-bottom');
  $parent.find('.face-wrapper').each(function (index) {
    setTimeout(function (face) {
      face.toggleClass('slide-out-y-alt', $parent.hasClass('at-bottom'));
    }, (index - 1) * 40, $(this));
  });
}

$('.feedback-title').on('click', handleFeedbackToggle);



function handleRateInputChange() {
  const rating = parseInt($(this).val());
  getFace($rateInputs).addClass('grayscale');
  getFace($(this)).removeClass('grayscale');
  postRating(rating);
}

$rateInputs.on('change', handleRateInputChange); //Firebase stuff

function setCounter(stats) {
  const $counters = $('.face-counter');

  function setTitle($counter, size) {
    var titleType = '',
        titlePrefix = '';

    if (size === 0) {
      titleType = 'none';
    } else if (size === 1) {
      titleType = 'one';
    } else {
      titleType = 'many';
      titlePrefix = `${size} `;
    }

    $counter.attr({
      'title': titlePrefix + $counter.attr(`data-title-${titleType}`)
    });
  }

  $counters.each(index => {
    const $counter = $counters.eq(index),
          size = stats[index] || 0;
    $counter.text(size);
    setTitle($counter, size);
    $counter.removeClass('invisible');
  });
}

function getTotalRating() {
  var stats = {};
  firebase.database().ref('votes').limitToLast(1000).once('value', snapshot => {
    snapshot.forEach(snap => {
      const val = snap.val();
      var voteStat = stats[val.vote];
      voteStat = voteStat ? voteStat + 1 : 1;
      stats[val.vote] = voteStat;
    });
    setCounter(stats);
  });
}

function postRating(rating) {
  const currentUser = firebase.auth().currentUser;

  if (currentUser) {
    const uid = currentUser.uid;
    const data = {
      vote: rating,
      time: new Date().getTime()
    };
    firebase.database().ref(`votes/${uid}`).set(data).then(getTotalRating);
  }
}

function loginFB() {
  console.log('login');
  firebase.auth().signInAnonymously().then(user => {
    console.log(firebase.auth().currentUser.uid);
  }).catch(function (error) {
    // Handle Errors here.
    var errorCode = error.code;
    var errorMessage = error.message;

    if (errorCode === 'auth/operation-not-allowed') {
      alert('You must enable Anonymous auth in the Firebase Console.');
    } else {
      console.error(error);
    }
  });
}

function initFB() {
  var config = {
    apiKey: "AIzaSyA7-zbUFMXGItgDwVyfS0IVlqjCytQxQ8k",
    authDomain: "greatest-ever.firebaseapp.com",
    databaseURL: "https://greatest-ever.firebaseio.com",
    projectId: "greatest-ever",
    storageBucket: "greatest-ever.appspot.com",
    messagingSenderId: "784422044422"
  };
  firebase.initializeApp(config);

  if (!firebase.auth().currentUser) {
    loginFB();
  }
}

initFB();
</script>

<script>


$(document).ready(function(){
	
	$("#feedback_1").hide();
	$("#feedback_2").hide();
	$("#feedback_3").hide();
	$("#feedback_4").hide();
	$("#feedback_5").hide();
	
// 	$(".face-wrapper").css({"transition": "0.25s ease-out"});
// 	$(".face-wrapper .face").css({"transition": "0.25s ease-out"});
// 	$(".slide-out-y-alt").css({"transform": "translateY(100%)"});

});

function Admission(){
// 	alert("Admission");
		$("#feedback_1").show();
		$("#feedback_2").hide();
		$("#feedback_3").hide();
		$("#feedback_4").hide();
		$("#feedback_5").hide();
		
		/* $(".face").RemoveClass('slide-out-y-alt');
		$(".face-wrapper").removeClass("slide-out-y-alt");
		$(".face-wrapper").css({"transition": "0.25s ease-out"});
		$(".face-wrapper .face").css({"transition": "0.25s ease-out"}); */
		$(".face-wrapper").css({"animation-name": "topup"});
// 		$(".face-wrapper").css({"transition": "0.25s ease-out"});
}


function Institute(){
	
	$("#feedback_1").hide();
	$("#feedback_2").show();
	$("#feedback_3").hide();
	$("#feedback_4").hide();
	$("#feedback_5").hide();
}

function Curriculum(){
	
	$("#feedback_1").hide();
	$("#feedback_2").hide();
	$("#feedback_3").show();
	$("#feedback_4").hide();
	$("#feedback_5").hide();
}

function Faculty(){
	
	$("#feedback_1").hide();
	$("#feedback_2").hide();
	$("#feedback_3").hide();
	$("#feedback_4").show();
	$("#feedback_5").hide();
}

function PostGraduation(){
	
	$("#feedback_1").hide();
	$("#feedback_2").hide();
	$("#feedback_3").hide();
	$("#feedback_4").hide();
	$("#feedback_5").show();
}


function jay(val){
	if (val == "1") {
		 Admission();
	}
	if (val == "2") {
	   Institute();
	}
	if (val == "3") {
		Curriculum();
	}
	if (val == "4") {
		Faculty();
	}
	if (val == "5") {
		PostGraduation();
	}

}

</script>




