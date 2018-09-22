
google.charts.load('current', {'packages':['corechart']});

google.charts.setOnLoadCallback(drawChart);

function drawChart() {
    /*
    var data = new google.visualization.DataTable();
    
    data.addColumn('string', 'Date');
    data.addColumn('number', 'Valence');
    
    data.addRows([
        ['1.1.2018', 4],
        ['2.1.2018', 3],
        ['3.1.2018', 2]
    ]);
    */
   
    var data = new google.visualization.DataTable(moodData);
   
    var options = {
        'title': 'Valence Trend',
        'width': 1000,
        'height': 400,
        'curveType': 'function'
    };
    
    var chart = new google.visualization.LineChart(document.getElementById('chart'));
    
    chart.draw(data, options);
}