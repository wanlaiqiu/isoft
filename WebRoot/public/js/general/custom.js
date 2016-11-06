	
	
	/**
	 * 匹配特定的URL，以便自动高亮当前所在页面导航
	 */
	(function($){
		$.currentHighLight = function(key){
			var url = window.location.toString(),
				regex = new RegExp('\/' + key);
			if(url.match(regex)){
				var selector = '._' + key;
				$(selector).addClass('active').siblings().removeClass('active');
				return true;
			}
			return false;
		}
	})(jQuery);

	$(function(){
		$.currentHighLight('about') ||
		$.currentHighLight('article') ||
		$.currentHighLight('category') ||
		$.currentHighLight('function') ||
		$.currentHighLight('contact');
		
		//pajax
		$(document).pjax('a', '#banner');
		
	});