(function () {
	  
  /* ========= e-form dashboard slider ======== */
  new Swiper('#db-default-carousel', {
    speed: 600,
    loop: true,    
    autoplay: {
      delay: 5000,      
      disableOnInteraction: false
    },    
    slidesPerView: 'auto',
    navigation: {
        nextEl: '.swiper-button-next',
        prevEl: '.swiper-button-prev',
    },
    pagination: {
      el: '.db-default-carousel-pagination',
      type: 'bullets',
      clickable: true
    },
    breakpoints: {
      320: {
        slidesPerView: 1,
        spaceBetween: 10
      },

      767: {
        slidesPerView: 2,
        spaceBetween: 10
      },

      1200: {
        slidesPerView: 4,
        spaceBetween: 10
      }
    }
  });
  
  
})();
