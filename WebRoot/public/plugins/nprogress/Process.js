/**
 * 
 * 页面首部显示加载脉冲线
 * 
 */
	
	(function($){
		$.browser = function(){
			var browser = {},
				matched = function(ua){
					ua = ua.toLowerCase();
					var match = /(chrome)[ \/]([\w.]+)/.exec(ua) || 
								/(webkit)[ \/]([\w.]+)/.exec(ua) || 
								/(opera)(?:.*version|)[ \/]([\w.]+)/.exec(ua) || 
								/(msie) ([\w.]+)/.exec(ua) || 
								ua.indexOf("compatible") < 0 && /(mozilla)(?:.*? rv:([\w.]+)|)/.exec(ua) || 
								[];
					return {
						browser : match[1] || '',
						version : match[2] || '0'
					};
				}(navigator.userAgent);
				
			if(matched.browser){
				browser[matched.browser] = true;
				browser.version = matched.version;
			}
			if(browser.chrome){
				browser.webkit = true;
			}else if(browser.webkit){
				browser.safari = true;
			}
			
			return browser;
		}();
	})(jQuery);

	$(function(){
		var config = {
				minimum: 		0.35,
				ease:			'ease',
				speed:			500,
				trickleRate:	0.01,
				trickleSpeed:	150	
		};
		/**
		 * IE10以下，不加载进度环，因其不支持CSS3部分属性
		 */
		(function(ie,version){
			if(ie){
				if(parseInt(version) < 10){
					config.showSpinner = false;
				}
			}
		})($.browser.msie,$.browser.version);
		NProgress.configure(config);
		NProgress.start();
	});
	$(window).load(function(){
		//setTimeout(NProgress.done,150);
		NProgress.done();
		
	});