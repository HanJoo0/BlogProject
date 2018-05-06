
class SensorApi{
	constructor(apiUrl) {
		this.apiUrl = apiUrl;
		this.values = null;
	}
	
	load(callback){
		$.get(this.apiUrl, function(data){
			this.values = data;
			if(callback)
				callback(this.values);
		});	
	}
	
	create(data, callback) {
		$.ajax({
			url : api,
			type : 'post',
			data : JSON.stringify(data),	// json 인코딩 
			contentType : 'application/json',
			success :  function(result){
				this.values.push(data);
				if(callback)
					callback(this.values);
				else
					alert('추가 실패');
			}				
		});		
	}

	update(data, callback) {
		$.ajax({
			url : api,
			type : 'put',
			data : JSON.stringify(data), 
			contentType : 'application/json',
			success :  function(result){
				if(result) {
					if(callback)
						callback(this.values);
				} else {
					alert('수정 실패');
				}
			}				
		});
	}

	remove(dataId, callback) {
		$.ajax({
			url : api + dataId,	//예: ~/api/sensor/5 구성 
			type : 'delete',
			success :  function(result){
				if(result) {
					sensor_values.splice(dataId, 1);	
				} else {
					alert('수정 실패');
				}
			}				
		});
	}
}