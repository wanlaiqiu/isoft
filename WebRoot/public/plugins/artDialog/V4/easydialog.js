/**
 * 自定义弹窗，简化前端的操作
 * 只适用于简单的提示
 * 2015-05-20
 * Van
 * */		

		/**
		 * 关闭界面所有对话框
		 */
		function closeDialog(){
			var list = art.dialog.list;
			for (var i in list) {
				list[i].close();
			};
		}
		/**
		 * 封装的alert
		 * @param {String} msg:弹出的消息，type:类型，用于显示对应图标，默认警告。s成功，e警告，q疑问，w警告 
		 * @param {Number} timedown：多少秒后关闭对话框，默认10秒[如果传递的不是数字默认也是10]，如果为0表示永不关闭 
		 * @param {boolean} lock：是否锁屏
		*/
		function artAlert(msg,callBack,type,timedown,lock){
			var icon='';
			if(type=='s'){
				icon='succeed';//成功
			}else if(type=='e'){
				icon='error';
			}else if(type=='q'){
				icon='question';
			}else{
				icon='warning';
			}
			if(!callBack){
				callBack=function(){};
			}
			var lo=true;
			if(lock==false){
				lo=false;
			}
			var r = /^\+?[0-9][0-9]*$/;//正整数 
			if(!r.test(timedown)){
				timedown=10;//10秒后关闭
			}
			var dialog=art.dialog.through({
				title: '提示信息',
				focus:true,//按钮聚焦
				content:msg,
				opacity: 0.2,	// 透明度
				top: '50%',				// Y轴坐标
				fixed: true,//静止定位，用户滚动窗体时依然居中显示
				lock:lo,//锁屏，关闭窗体前禁止执行其它操作
				icon:icon,
				time:timedown,//0表示不关闭
				zIndex: 10090,
				ok: function () {
					//仅仅显示确定对话框架，无任何操作
		    	},
		    	close:function(){
		    		callBack();
		    	}
			});
		}

		/**
		 * 仅仅显示对话框架，无提示图标
		 * @param {String} msg:消息
		 * @param {Boolean} lock:锁屏
		 */
		function artAlerts(msg,lock){
			var lo=true;
			if(lock!=true){
				lo=false;
			}
			dialog=art.dialog.through({
				//id:'only-one',//此属性会导致弹出窗口只显示一次！
				title: '提示信息',
				focus:true,//按钮聚焦
				content:msg,
				opacity: 0.2,	// 透明度
				top: '48%',				// Y轴坐标
				fixed: true,//静止定位，用户滚动窗体时依然居中显示
				lock:lo,//锁屏
				ok: function () {
		    	}
			});
		}
		
		/**
		 * 封装的confirm
		 * msg:弹出的消息
		 * callBack:回调函数，仅当点击OK时触发
		 */
		function artConfirm(msg,callBack){
			var dialog=art.dialog.through({
				title: '提示信息',
				focus:true,//按钮聚焦
				content:msg,
				opacity: 0.2,	// 透明度
				top: '40%',				// Y轴坐标【距离顶部】，默认38.2%
				fixed: true,//静止定位，用户滚动窗体时依然居中显示
				lock:true,//锁屏
				icon:'question',
				ok: function () {//确定，执行具体操作
					callBack();
		    	},
				cancel: function () {//关闭
					//art.dialog.tips('<div style="padding: 0 1em;margin:-1em 0;color:#FF0000;"><img src="/images/arrow_back.png" style="vertical-align:middle;margin-right:6px;"/>您取消了操作！</div>',1.5);
		    	}
			});
		}
		//显示图片框
		function artImage(pic,width,height,lock){
		}
		
		/**
		 * 短暂提示
		 * @param	{String}	content：提示内容
		 * @param	{Number}	time：显示时间 (默认1.5秒)
		 * @param	{boolean}	lock：锁屏 (默认true)
		 * 2015-05-20
		 * Add By Van
		 */
		function artTips(content,time,lock){
			var _lock = true;
			_lock = (lock == undefined) ? true : ((typeof lock == 'boolean') ? lock : true);
			var dialog = art.dialog.through({
				id: 'Tips',
		        title: false,
		        cancel: false,
		        fixed: true,
		        lock: _lock,
		        opacity: 0.3,
		        content:'<div style="padding: 0 1em;margin:-1em 0;">' + content + '</div>',
		        time:time || 1.5
			});
		}
		
		/**
		* 鼠标跟随提示，鼠标移置元素上是，在元素旁显示提示信息<br/>
		* refId：被跟随的元素<br/>
		* msg: 提示的消息(支持HTML代码、CSS)<br/>
		* Add At 2015-05-20<br/>
		* By Van
		*
		*/
		function smallTips(refId,msg){
			art.dialog.through({
				id: 'tips',
		        title: '<span style="color: #FF0080;font-size: 14px;">小提示</span>',
		        cancel: false,
		        fixed: true,
		        lock: false,
		        follow: document.getElementById(refId),
		        opacity: 0.2,
		        width: '300px',
		        //icon: 'face-smile',
		        content:'<div style="padding: 0px;">' + msg + '</div>'
			});
		}