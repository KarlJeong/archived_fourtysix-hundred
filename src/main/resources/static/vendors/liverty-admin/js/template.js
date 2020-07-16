(function($) {
  'use strict';
  $(function() {
    var body = $('body');
    var footer = $('.footer');

    //var current = location.pathname.split("/").slice(-1)[0].replace(/^\/|\/$/g, '');
    var current = location.pathname;
    $('.navbar.horizontal-layout .nav-bottom .page-navigation .nav-item').each(function() {
      var $this = $(this);
      if (current === "") {
        //for root url
        if ($this.find(".nav-link").attr('href').indexOf("index.html") !== -1) {
          $(this).find(".nav-link").parents('.nav-item').last().addClass('active');
          $(this).addClass("active");
        }
      } else {
        //for other url
        if ($this.find(".nav-link").attr('href').indexOf(current) !== -1) {
          $(this).find(".nav-link").parents('.nav-item').last().addClass('active');
          $(this).addClass("active");
        }
      }
    })

    $(window).scroll(function() {
      var headerBottom = '.navbar.horizontal-layout .nav-bottom';
      if ($(window).scrollTop() >= 70) {
        //$(headerBottom).addClass('fixed-top');
      } else {
        //$(headerBottom).removeClass('fixed-top');
      }
    });

    $(".navbar.horizontal-layout .navbar-menu-wrapper .navbar-toggler").on("click", function() {
      $(".navbar.horizontal-layout .nav-bottom").toggleClass("header-toggled");
    });

    //checkbox and radios
    $(".form-check .form-check-label,.form-radio .form-check-label").not(".todo-form-check .form-check-label").append('<i class="input-helper"></i>');

    // Isotope filters
    //-----------------------------------------------
    if ($('.isotope-container').length>0 || $('.masonry-grid').length>0 || $('.masonry-grid-fitrows').length>0 || $('.isotope-container-fitrows').length>0) {
      var $container = $('.masonry-grid').imagesLoaded( function() {
	      $('.masonry-grid').isotope({
	        itemSelector: '.masonry-grid-item',
	        layoutMode: 'masonry',
	        masonry: {
			    columnWidth: 100%,
			    horizontalOrder: true,
			  }
	      });
	      $('.masonry-grid-fitrows').isotope({
	        itemSelector: '.masonry-grid-item',
	        layoutMode: 'fitRows'
	      });
      });
      $('.isotope-container').fadeIn();
      var $container = $('.isotope-container').imagesLoaded( function() {
        $container.isotope({
          itemSelector: '.isotope-item',
          layoutMode: 'masonry',
          transitionDuration: '0.6s',
          filter: "*"
        });
      });
      $('.isotope-container-fitrows').fadeIn();
      var $container_fitrows = $('.isotope-container-fitrows').imagesLoaded( function() {
        $container_fitrows.isotope({
          itemSelector: '.isotope-item',
          layoutMode: 'fitRows',
          transitionDuration: '0.6s',
          filter: "*"
        });
      });
      // filter items on button click
      $('.filters').on( 'click', 'ul.nav li a', function() {
        var filterValue = $(this).attr('data-filter');
        $(".filters").find("li .active").removeClass("active");
        $(this).addClass("active");
        $container.isotope({ filter: filterValue });
        $container_fitrows.isotope({ filter: filterValue });
        return false;
      });
      $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
        $('.tab-pane .masonry-grid-fitrows').isotope({
          itemSelector: '.masonry-grid-item',
          layoutMode: 'fitRows'
        });
      });
    };

  });
})(jQuery);
