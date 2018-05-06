
function lineChart(labels, values) {
	var ctxL = document.getElementById("lineChart").getContext('2d');
	var myLineChart = new Chart(ctxL, {
	    type: 'line',
	    data: {
	        labels: labels,
	        datasets: [
	            {
	                label: "온도 변화",
	                fillColor: "rgba(220,220,220,0.2)",
	                strokeColor: "rgba(220,220,220,1)",
	                pointColor: "rgba(220,220,220,1)",
	                pointStrokeColor: "#fff",
	                pointHighlightFill: "#fff",
	                pointHighlightStroke: "rgba(220,220,220,1)",
	                data: values
	            },
	        ]
	    },
	    options: {
	        responsive: true
	    }    
	});
}

function showLineChart(data) {
	var labels = data.map(item=>item.id + ' 시');
	var values = data.map(item=>item.value);

	lineChart(labels, values);	
}

