	$(document).ready(function() {

		$('.verticalnav > li > a').click(function() {
			$(this).parent().toggleClass('open')
			$(this).siblings().toggle();
		});

		$('.verticalnav > li:first-child  ul').show();
		$(".navi-tabs__content").hide();
		$(".navi-tabs__content:first").show();

		$('.verticalnav .dropdown li a').click(function() {
			$(".navi-tabs__content").hide();
			var activeTab = $(this).attr("href");
			$(activeTab).fadeIn();
			$('.verticalnav .dropdown a').removeClass('active');
			$(this).addClass('active');
		});
	});