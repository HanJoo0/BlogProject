
class Rest {
	constructor(url) {
		this.url = url;	// api URL
	}
	
	list(params, callback) {
		return $.get(this.url, params, callback);		
	}
	
	get(id, callback) {
		return $.get(this.url + '/' + id, callback);		
	}
	
	create(data, callback) {
		return $.ajax({
			url : this.url,
			type : 'post',
			contentType : 'application/json',
			data : JSON.stringify(data),
			success : callback
		});
	}

	update(data, callback) {
		return $.ajax({
			url : this.url,
			type : 'put',
			contentType : 'application/json',
			data : JSON.stringify(data),
			success : callback
		});
	}
	
	remove(id, callback) {
		return $.ajax({
			url : this.url +'/' + id,
			type : 'delete',
			success : callback			
		});
	}
}