

var templ = {
	tableTempl : `
		<table class = "table">
			<tbody>
			</tbody>
		</table>
		
	`,
		
	trTempl : function(image){
		return `
		<tr>
			<td style="width:100px">
				<a href="image/${image.imageId}" data-lightbox="roadtrip">
					<img src="thumb/${image.imageId}"
							width="100" 
							alt="${image.title}"
						 class="z-depth-2 rounded"/> 
				</a>
			</td>
			<td>		
				<p>
					<b>${image.title}</b>
					<a href="download/${image.imageId}">
						<i class="fa fa-download"></i>
					</a>
					(등록일 : <fmt:formatDate value="${image.regDate}" pattern="yyyy-MM-dd"/>)
				</p>
				<p>${image.description}</p>
				
			</td>
		</tr>
	`	
	}
			
}








$.fn.gallery = function(opt){
	var self = this;
	var api = new Rest(opt.url);
	
	self.append(templ.tableTempl);
	
	api.list('', function(images){
		images.forEach(function(image){
			var tr = templ.trTempl(image)
			self.find('tbody').append(tr);
			
		})
		
	});
	
	return self;
}