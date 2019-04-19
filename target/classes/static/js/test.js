$(function(){
	var testURL="/purchase";
	$("#test").click(function(){
		for(var i=0;i<5000;i++){
			var params = {
					userId:1,
					productId:2,
					quantity:1
			}
			$.ajax({
				url:testURL,
				type:'post',
				data:params,
				success:function(result){
					$.toast(result.message);
				}
			});
		}
		
	});
});
