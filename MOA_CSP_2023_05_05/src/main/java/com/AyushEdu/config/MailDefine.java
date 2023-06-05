package com.AyushEdu.config;

public class MailDefine {
	
	private String registration_header_NCISM_institute;
	private String registration_body_NCISM_institute;
	public String getRegistration_header_NCISM_institute() {
		return registration_header_NCISM_institute;
	}
	public void setRegistration_header_NCISM_institute(String username,String password) {
		String data = "<p style=\"margin:0; font-size: 16px;\"><span style=\"color: #3cb371;font-weight: 600;\">Your Registration is Successful and approved by NCISM!</span><br>\n"
				+ "                                    The email includes your account details, so please keep it safe!<br>\n"
				+ "                                    Username: <span style=\"color: #3cb371;font-weight: 500;\">"+username+"</span> and\n"
				+ "                                    Password: <span style=\"color: #3cb371;font-weight: 500;\">"+password+"</span>\n"
				+ "                                  </p>";
		this.registration_header_NCISM_institute = data;
	}
	public String getRegistration_body_NCISM_institute() {
		registration_body_NCISM_institute = " <p style=\"margin:0\"><span style=\"color: #014a91;font-weight: 600;\">Welcome to National Commission for\n"
				+ "                                    Indian System of Medicine</span>, You have to log in to Ayush Education portal, then you have to follow the below instructions carefully.\n"
				+ "                                  </p>\n"
				+ "                                  <ul style=\"padding-left: 15px; list-style-position: outside; font-size: 15px; line-height: 1.5rem; margin-top: 5px; margin-bottom: 5px;\">\n"
				+ "                                    <li>Go to the official website of Ayush Educational Portal. The direct link is here: <a href=\"www.ayushedu.bisag-n.gov.in\" style=\"color: #014a91;font-weight: 400;\">www.ayushedu.bisag-n.gov.in</a></li>\n"
				+ "                                    <li>You will find a Sign In button on the top of the home page.</li>\n"
				+ "                                    <li>Click on the Sign In button.</li>\n"
				+ "                                    <li>Now you are redirected to the Sign In page of Ayush Educational Portal.</li>\n"
				+ "                                    <li>Select your category and enter the Email Id & Password.</li>\n"
				+ "                                    <!-- <li>Now, click on the Get-OTP button.</li>\n"
				+ "                                    <li>Now, an OTP is sent to your entered mobile number / Email ID. This will cause an email/SMS with an OTP to be sent to your registered email address/mobile..</li>\n"
				+ "                                    <li>Enter the OTP.</li> -->\n"
				+ "                                    <li>Enter the Captcha Code.</li>\n"
				+ "                                    <li>Now click on the Sign In button.</li>\n"
				+ "                                    <li>After Successful Sign In, you are redirected to the Dashboard.</li>                                    \n"
				+ "                                    <li>For any help, go through the <a href=\"www.ayushedu.bisag-n.gov.in\" style=\"color: #014a91;font-weight: 400;\">user manual</a>.</li>                                    \n"
				+ "                                  </ul>";
		
		return registration_body_NCISM_institute;
	}
	
	
	private String registration_header_NCH_institute;
	private String registration_body_NCH_institute;
	public String getRegistration_header_NCH_institute() {
		return registration_header_NCH_institute;
	}
	public void setRegistration_header_NCH_institute(String username,String password) {
		String data = " <p style=\"margin:0; font-size: 16px;\"><span style=\"color: #3cb371;font-weight: 600;\">Your Registration is Successful and approved by NCH!</span><br>\n"
				+ "                                    The email includes your account details, so please keep it safe!<br>\n"
				+ "                                    Username: <span style=\"color: #3cb371;font-weight: 500;\">"+username+"</span> and\n"
				+ "                                    Password: <span style=\"color: #3cb371;font-weight: 500;\">"+password+"</span>\n"
				+ "                                  </p>";
		this.registration_header_NCH_institute = data;
	}
	public String getRegistration_body_NCH_institute() {
		registration_body_NCH_institute = "<p style=\"margin:0\"><span style=\"color: #014a91;font-weight: 600;\">Welcome to National Commission for Homoeopathy</span>, You have to log in to Ayush Education portal, then you have to follow the below instructions carefully.\n"
				+ "                                  </p>\n"
				+ "                                  <ul style=\"padding-left: 15px; list-style-position: outside; font-size: 15px; line-height: 1.5rem; margin-top: 5px; margin-bottom: 5px;\">\n"
				+ "                                    <li>Go to the official website of Ayush Educational Portal. The direct link is here: <a href=\"www.ayushedu.bisag-n.gov.in\" style=\"color: #014a91;font-weight: 400;\">www.ayushedu.bisag-n.gov.in</a></li>\n"
				+ "                                    <li>You will find a Sign In button on the top of the home page.</li>\n"
				+ "                                    <li>Click on the Sign In button.</li>\n"
				+ "                                    <li>Now you are redirected to the Sign In page of Ayush Educational Portal.</li>\n"
				+ "                                    <li>Select your category and enter the Email Id & Password.</li>\n"
				+ "                                    <!-- <li>Now, click on the Get-OTP button.</li>\n"
				+ "                                    <li>Now, an OTP is sent to your entered mobile number / Email ID. This will cause an email/SMS with an OTP to be sent to your registered email address/mobile..</li>\n"
				+ "                                    <li>Enter the OTP.</li> -->\n"
				+ "                                    <li>Enter the Captcha Code.</li>\n"
				+ "                                    <li>Now click on the Sign In button.</li>\n"
				+ "                                    <li>After Successful Sign In, you are redirected to the Dashboard.</li>                                    \n"
				+ "                                    <li>For any help, go through the <a href=\"www.ayushedu.bisag-n.gov.in\" style=\"color: #014a91;font-weight: 400;\">user manual</a>.</li>                                    \n"
				+ "                                  </ul>";
		
		return registration_body_NCH_institute;
	}
//	public void setRegistration_body(String registration_body) {
//		this.registration_body = "";
//	}
	
	
	private String registration_header_NCISM_student;
	private String registration_body_NCISM_student;
	public String getRegistration_header_NCISM_student() {
		return registration_header_NCISM_student;
	}
	public void setRegistration_header_NCISM_student(String institute_name,String username) {
		String data = "<p style=\"margin:0; font-size: 16px;\"><span style=\"color: #3cb371;font-weight: 600;\">Your Registration is Successful with Institute : "+institute_name+".</span><br>\n"
				+ "                                    The email includes your account details, so please keep it safe!<br>\n"
				+ "                                    Email ID(Username): <span style=\"color: #3cb371;font-weight: 500;\">"+username+"</span>\n"
				+ "\n"
				+ "                                  </p>";
		this.registration_header_NCISM_student = data;
	}
	public String getRegistration_body_NCISM_student() {
		registration_body_NCISM_student = " <p style=\"margin:0\"><span style=\"color: #014a91;font-weight: 600;\">Welcome to National Commission for\n"
				+ "                                    Indian System of Medicine</span>, You have to log in to Ayush Education portal, then you have to follow the below instructions carefully.\n"
				+ "                                  </p>\n"
				+ "                                  <ul style=\"padding-left: 15px; list-style-position: outside; font-size: 15px; line-height: 1.5rem; margin-top: 5px; margin-bottom: 5px;\">\n"
				+ "                                    <li>Go to the official website of Ayush Educational Portal. The direct link is here: <a href=\"www.ayushedu.bisag-n.gov.in\" style=\"color: #014a91;font-weight: 400;\">www.ayushedu.bisag-n.gov.in</a></li>\n"
				+ "                                    <li>You will find a Sign In button on the top of the home page.</li>\n"
				+ "                                    <li>Click on the Sign In button.</li>\n"
				+ "                                    <li>Now you are redirected to the Sign In page of Ayush Educational Portal.</li>\n"
				+ "                                    <li>Select your category and enter the Email ID as Username.</li>\n"
				+ "                                    <li>Now, click on the Get-OTP button.</li>\n"
				+ "                                    <li>Now, an OTP is sent to your registered Email ID.</li>\n"
				+ "                                    <li>Enter the OTP.</li> \n"
				+ "                                    <li>Enter the Captcha Code.</li>\n"
				+ "                                    <li>Now click on the Sign In button.</li>\n"
				+ "                                    <li>After successful Sign In, you are redirected to the Dashboard.</li>                                    \n"
				+ "                                  </ul>";
		
		return registration_body_NCISM_student;
	}
	
	
	private String registration_header_NCH_student;
	private String registration_body_NCH_student;
	public String getRegistration_header_NCH_student() {
		return registration_header_NCH_student;
	}
	public void setRegistration_header_NCH_student(String institute_name,String username) {
		String data = " <p style=\"margin:0; font-size: 16px;\"><span style=\"color: #3cb371;font-weight: 600;\">Your Registration is Successful with Institute : "+institute_name+".</span><br>\n"
				+ "                                    The email includes your account details, so please keep it safe!<br>\n"
				+ "                                    Email ID(Username): <span style=\"color: #3cb371;font-weight: 500;\">"+username+"</span> \n"
				+ "                                  </p>";
		this.registration_header_NCH_student = data;
	}
	public String getRegistration_body_NCH_student() {
		registration_body_NCH_student = " <p style=\"margin:0\"><span style=\"color: #014a91;font-weight: 600;\">Welcome to National Commission for Homoeopathy</span>, You have to log in to Ayush Education portal, then you have to follow the below instructions carefully.\n"
				+ "                                  </p>\n"
				+ "                                  <ul style=\"padding-left: 15px; list-style-position: outside; font-size: 15px; line-height: 1.5rem; margin-top: 5px; margin-bottom: 5px;\">\n"
				+ "                                    <li>Go to the official website of Ayush Educational Portal. The direct link is here: <a href=\"www.ayushedu.bisag-n.gov.in\" style=\"color: #014a91;font-weight: 400;\">www.ayushedu.bisag-n.gov.in</a></li>\n"
				+ "                                    <li>You will find a Sign In button on the top of the home page.</li>\n"
				+ "                                    <li>Click on the Sign In button.</li>\n"
				+ "                                    <li>Now you are redirected to the Sign In page of Ayush Educational Portal.</li>\n"
				+ "                                    <li>Select your category and enter the Email ID as Username.</li>\n"
				+ "                                    <li>Now, click on the Get-OTP button.</li>\n"
				+ "                                    <li>Now, an OTP is sent to your registered Email ID.</li>\n"
				+ "                                    <li>Enter the OTP.</li> \n"
				+ "                                    <li>Enter the Captcha Code.</li>\n"
				+ "                                    <li>Now click on the Sign In button.</li>\n"
				+ "                                    <li>After successful Sign In, you are redirected to the Dashboard.</li>                                    \n"
				+ "                                  </ul>";
		
		return registration_body_NCH_student;
	}
	

	
//	16_02_2022 changes  practitioner
	private String registration_header_NCH_practitioner;
	private String registration_body_NCH_practitioner;
	public String getRegistration_header_NCH_practitioner() {
		return registration_header_NCH_practitioner;
	}
	public void setRegistration_header_NCH_practitioner(String username,String password) {
		String data = " <p style=\"margin:0; font-size: 16px;\"><span style=\"color: #3cb371;font-weight: 600;\">Your Registration is Successful and approved by State Council!</span><br>\n"
//				+ "                                    The email includes your account details, so please keep it safe!<br>\n"
//				+ "                                    Username: <span style=\"color: #3cb371;font-weight: 500;\">"+username+"</span> and\n"
//				+ "                                    Password: <span style=\"color: #3cb371;font-weight: 500;\">"+password+"</span>\n"
				+ "                                  </p>";
		this.registration_header_NCH_practitioner = data;
	}
	public String getRegistration_body_NCH_practitioner() {
		registration_body_NCH_practitioner 
				
				= " <p style=\"margin:0\"><span style=\"color: #014a91;font-weight: 600;\">Welcome to National Commission for Homoeopathy</span>, You have to Sign In to Ayush Education portal, then you have to follow the below instructions carefully.\n"
						+ "                                  </p>\n"
						+ "                                  <ul style=\"padding-left: 15px; list-style-position: outside; font-size: 15px; line-height: 1.5rem; margin-top: 5px; margin-bottom: 5px;\">\n"
						+ "                                    <li>Go to the official website of Ayush Educational Portal. The direct link is here: <a href=\"www.ayushedu.bisag-n.gov.in\" style=\"color: #014a91;font-weight: 400;\">www.ayushedu.bisag-n.gov.in</a></li>\n"
						+ "                                    <li>You will find a Sign In button on the top of the home page.</li>\n"
						+ "                                    <li>Click on the Sign In button.</li>\n"
						+ "                                    <li>Now you are redirected to the Sign In page of Ayush Educational Portal.</li>\n"
						+ "                                    <li>Select your category as a <b>Practitioner</b> and follow below instruction steps then, you need to click on the Aadhaar Verify button. \n"
						+ "                                    <ul style=\"padding-left: 0; list-style-position: outside; font-size: 15px; line-height: 1.5rem; margin-top: 5px; margin-bottom: 5px;\">\n"
						+ "												<li><b>Now, Follow below instruction steps:</b></p></li>\n"
						+ "												<li>You are redirect on <b>Meri Pehchaan</b> - \"Sign In to your account via DigiLocker\" form.</p></li>\n"
						+ "												<li>You can fillup your details though appropriate credentials and click on the Sign In button.</p></li>\n"
						+ "												<li>If you are new user? then you need to <b class=\"concat-string\"><a href=\"https://digilocker.meripehchaan.gov.in/signup/\" class=\"text-heighlight\" target=\"_blank\">Sign up for Meri Pehchaan</a></b>.</p></li>												\n"
						+ "												<li>DigiLocker has sent you an OTP to your registered mobile (xxxxxx1234) and email (dummy**@gmail.com).</p></li>\n"
						+ "												<li>Digilocker document with Single Window System needs to click on <b>Allow</b>.</p></li>\n"
						+ "												<li>Finally, You are <b>Sign In</b> as <b>Practitioner</b> category.</p></li>\n"
						+ "											</ul>\n"
						+ "                                    <li>After Successful Sign In, you will redirect to the Dashboard.</li>                                    \n"
						+ "                                    <li>For any help, go through the <a href=\"www.ayushedu.bisag-n.gov.in\" style=\"color: #014a91;font-weight: 400;\">User Manual</a></li>                                    \n"
						
						+ "                                  </ul>";
				
			
		return registration_body_NCH_practitioner;
	}
	private String registration_Reject_header_NCH_practitioner;
	private String registration_Reject_body_NCH_practitioner;
	public String getRegistration_Reject_header_NCH_practitioner() {
		String data = " <p style=\"margin:0; font-size: 16px;\"><span style=\"color: #ff2a1d;font-weight: 600;\">Your Registration is decline by State Council!</span><br>\n"
				+ "                                    Please, you need to contact your relavent state councile for further queries.<br>\n"
//				+ "                                    Username: <span style=\"color: #3cb371;font-weight: 500;\">"+username+"</span> and\n"
//				+ "                                    Password: <span style=\"color: #3cb371;font-weight: 500;\">"+password+"</span>\n"
				+ "                                  </p>";
		return data;
	}
//	private String registration_Reject_body_NCH_practitioner;

	public String getRegistration_Reject_body_NCH_practitioner() {
		registration_Reject_body_NCH_practitioner = "<p style=\"margin:0\"><span style=\"color: #014a91;font-weight: 600;\">Welcome to National Commission for Homoeopathy</span>,After carefully reviewing your application, we regret to inform you that we are decline your registration due to some inappropriate information. For further information, please contact to your State Council.\n"
				+ "                                  </p>\n";
				/*+ "                                  <ul style=\"padding-left: 15px; list-style-position: outside; font-size: 15px; line-height: 1.5rem; margin-top: 5px; margin-bottom: 5px;\">\n"
				+ "                                    <li>Go to the official website of Ayush Educational Portal. The direct link is here: <a href=\"www.ayushedu.bisag-n.gov.in\" style=\"color: #014a91;font-weight: 400;\">www.ayushedu.bisag-n.gov.in</a></li>\n"
				+ "                                    <li>You will find a Sign In button on the top of the home page.</li>\n"
				+ "                                    <li>Click on the Sign In button.</li>\n"
				+ "                                    <li>Now you are redirected to the Sign In page of Ayush Educational Portal.</li>\n"
				+ "                                    <li>Select your category and enter the Email Id & Password.</li>\n"
				+ "                                    <!-- <li>Now, click on the Get-OTP button.</li>\n"
				+ "                                    <li>Now, an OTP is sent to your entered mobile number / Email ID. This will cause an email/SMS with an OTP to be sent to your registered email address/mobile..</li>\n"
				+ "                                    <li>Enter the OTP.</li> -->\n"
				+ "                                    <li>Enter the Captcha Code.</li>\n"
				+ "                                    <li>Now click on the Sign In button.</li>\n"
				+ "                                    <li>After Successful Sign In, you are redirected to the Dashboard.</li>                                    \n"
				+ "                                    <li>For any help, go through the <a href=\"www.ayushedu.bisag-n.gov.in\" style=\"color: #014a91;font-weight: 400;\">user manual</a>.</li>                                    \n"
				+ "                                  </ul>";*/
		
		return registration_Reject_body_NCH_practitioner;
	}
//	END 16_02_2022 changes  practitioner


	
}
