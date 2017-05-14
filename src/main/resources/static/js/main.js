/**
 * Created by Rostik on 13.05.2017.
 */

var uuid = "54947df8-0e9e-4471-a2f9-9af509fb5889";

var curr_name = "";

var info_active = false;

var table_active = false;

var feed_active = false;

var dataSourceTemperature = [{
    name: 'Current',
    mean: 35,
    min: 5,
    max: 22
}];

var dataSourceHumidity = [{
    name: 'Current',
    mean: 83,
    min: 70,
    max: 85
}];

var dataSourceIllumination = [{
    name: 'Current',
    mean: 1250,
    min: 500,
    max: 1860
}];

var dataSourceTemperatureChart = [{
    time: "00:00",
    tmp: 26
}, {
    time: "04:00",
    tmp: 12
}, {
    time: "08:00",
    tmp: 35
}, {
    time: "12:00",
    tmp: 38
}, {
    time: "16:00",
    tmp: 45
}, {
    time: "20:00",
    tmp: 15
}];

var dataSourceHumidityChart = [{
    time: "00:00",
    hmd: 40
}, {
    time: "04:00",
    hmd: 47
}, {
    time: "08:00",
    hmd: 52
}, {
    time: "12:00",
    hmd: 32
}, {
    time: "16:00",
    hmd: 40
}, {
    time: "20:00",
    hmd: 48
}];

var dataSourceIlluminationChart = [{
    time: "00:00",
    lmt: 2250
}, {
    time: "04:00",
    lmt: 1575
}, {
    time: "08:00",
    lmt: 1950
}, {
    time: "12:00",
    lmt: 760
}, {
    time: "16:00",
    lmt: 1560
}, {
    time: "20:00",
    lmt: 685
}];

function updateInfo() {
    var search = {}
    search["account"] = uuid;
    search["name"] = curr_name;
    if (info_active == true)
    {
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/api/navigation/inforeal",
            data: JSON.stringify(search),
            dataType: 'json',
            cache: false,
            timeout: 600000,
            success: function (data) {
                var json = JSON.parse(JSON.stringify(data));
                $('#greenhouseinfo').html(json["result"]);
                $(".gaugetemperature").dxCircularGauge({
                    value : json["temperature"]
                });
                $(".gaugehumidity").dxCircularGauge({
                    value : json["humidity"]
                });
                $(".gaugeillumination").dxCircularGauge({
                    value : json["light"]
                });

            },
            error: function (e) {

            }

        });
    }


}
function  click_info(identifier) {
    curr_name = identifier;
    info_active = true;
    table_active = false;
    feed_active = false;
    var search = {}
    search["account"] = uuid;
    search["name"] = curr_name;
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/navigation/info",
        data: JSON.stringify(search),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
                var json = JSON.parse(JSON.stringify(data));
                $('#main_content').html(json["result"]);


            var gaugetemperature = $(".gaugetemperature").dxCircularGauge({
                scale: {
                    startValue: 10,
                    endValue: 90,
                    tickInterval: 10,
                    label: {
                        customizeText: function (arg) {
                            return arg.valueText + " °C";
                        }
                    }
                },
                rangeContainer: {
                    ranges: [
                        { startValue: 10, endValue: 30, color: "#0077BE" },
                        { startValue: 30, endValue: 50, color: "#77DD77" },
                        { startValue: 50, endValue: 70, color: "#E6E200" },
                        { startValue: 70, endValue: 90, color: "#ff1900" }
                    ]
                },
                tooltip: { enabled: true },
                title: {
                    text: "Температура",
                    font: { size: 28 }
                },
                value : dataSourceTemperature[0].mean,
                subvalues : [dataSourceTemperature[0].min, dataSourceTemperature[0].max]
            }).dxCircularGauge("instance");

            var gaugehumidity = $(".gaugehumidity").dxCircularGauge({
                scale: {
                    startValue: 0,
                    endValue: 100,
                    tickInterval: 25,
                    label: {
                        customizeText: function (arg) {
                            return arg.valueText + " %";
                        }
                    }
                },
                rangeContainer: {
                    ranges: [
                        { startValue: 0, endValue: 25, color: "#ff1900" },
                        { startValue: 25, endValue: 50, color: "#E6E200" },
                        { startValue: 50, endValue: 75, color: "#468ffc" },
                        { startValue: 75, endValue: 100, color: "#0077BE" }
                    ]
                },
                tooltip: { enabled: true },
                title: {
                    text: "Влажность",
                    font: { size: 28 }
                },
                value : dataSourceHumidity[0].mean,
                subvalues : [dataSourceHumidity[0].min, dataSourceHumidity[0].max]
            }).dxCircularGauge("instance");

            var gauge = $(".gaugeillumination").dxCircularGauge({
                scale: {
                    startValue: 0,
                    endValue: 3000,
                    tickInterval: 500,
                    label: {
                        customizeText: function (arg) {
                            return arg.valueText + " лк";
                        }
                    }
                },
                rangeContainer: {
                    ranges: [
                        { startValue: 0, endValue: 1000, color: "#000000" },
                        { startValue: 1000, endValue: 2000, color: "#ff9400" },
                        { startValue: 2000, endValue: 3000, color: "#E6E200" }
                    ]
                },
                tooltip: { enabled: true },
                title: {
                    text: "Освещенность",
                    font: { size: 28 }
                },
                value : dataSourceIllumination[0].mean,
                subvalues : [dataSourceIllumination[0].min, dataSourceIllumination[0].max]
            }).dxCircularGauge("instance");
            var chart = $("#temperaturechart").dxChart({
                palette: "violet",
                dataSource: dataSourceTemperatureChart,
                commonSeriesSettings: {
                    type: types[0],
                    argumentField: "time"
                },
                commonAxisSettings: {
                    grid: {
                        visible: true
                    }
                },
                margin: {
                    bottom: 20
                },
                series: [
                    { valueField: "tmp", name: "Средняя температура" }
                ],
                tooltip:{
                    enabled: true
                },
                legend: {
                    verticalAlignment: "top",
                    horizontalAlignment: "right"
                },
                title: "Температура"
            }).dxChart("instance");

            var chart = $("#humiditychart").dxChart({
                palette: "violet",
                dataSource: dataSourceHumidityChart,
                commonSeriesSettings: {
                    type: types[0],
                    argumentField: "time"
                },
                commonAxisSettings: {
                    grid: {
                        visible: true
                    }
                },
                margin: {
                    bottom: 20
                },
                series: [
                    { valueField: "hmd", name: "Средняя влажность" }
                ],
                tooltip:{
                    enabled: true
                },
                legend: {
                    verticalAlignment: "top",
                    horizontalAlignment: "right"
                },
                title: "Влажность"
            }).dxChart("instance");

            var chart = $("#illuminationchart").dxChart({
                palette: "violet",
                dataSource: dataSourceIlluminationChart,
                commonSeriesSettings: {
                    type: types[0],
                    argumentField: "time"
                },
                commonAxisSettings: {
                    grid: {
                        visible: true
                    }
                },
                margin: {
                    bottom: 20
                },
                series: [
                    { valueField: "lmt", name: "Средняя освещенность" }
                ],
                tooltip:{
                    enabled: true
                },
                legend: {
                    verticalAlignment: "top",
                    horizontalAlignment: "right"
                },
                title: "Освещенность"
            }).dxChart("instance");
            var markerUrl = "https://js.devexpress.com/Demos/RealtorApp/images/map-marker.png",
                markersData = [{
                    location: [50.44763374716468, 30.423141159117222],
                    tooltip: {
                        text: "Температура 1"
                    }
                }, {
                    location: [50.44756073072327, 30.42320117354393],
                    tooltip: {
                        text: "Влажность 1"
                    }
                }, {
                    location: [50.44761666730541, 30.423228666186333],
                    tooltip: {
                        text: "Освещенность 1"
                    }
                }
                ];

            var mapWidget = $("#map").dxMap({
                zoom: 16,
                height: 550,
                width: "80%",
                controls: true,
                markerIconSrc: markerUrl,
                markers: markersData
            }).dxMap("instance");


            $("#show-tooltips").dxButton({
                text: "Показать все данные",
                onClick: function() {
                    var newMarkers = $.map(markersData, function(item) {
                        return $.extend(true, {}, item, { tooltip: { isShown: true }} );
                    });

                    mapWidget.option("markers", newMarkers);
                }
            });

            $("#make-pdf").dxButton({
                text: "Сформировать отчет",
                onClick: make_pdf()
            });

            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/api/navigation/charts",
                data: JSON.stringify(search),
                dataType: 'json',
                cache: false,
                timeout: 600000,
                success: function (data) {
                    var json = JSON.parse(JSON.stringify(data));
                    $("#illuminationchart").dxChart({
                        dataSource: json["lights"]
                    }).dxChart("instance");
                    $("#humiditychart").dxChart({
                        dataSource: json["humidities"]
                    }).dxChart("instance");
                    $("#temperaturechart").dxChart({
                        dataSource: json["temperatures"]
                    }).dxChart("instance");
                },
                error: function (e) {
                }
            });

        },
        error: function (e) {


        }
    });
}
function  feed_update() {
    var search = {}
    search["account"] = uuid;
    if (feed_active == true)
    {
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/api/navigation/feed",
            data: JSON.stringify(search),
            dataType: 'json',
            cache: false,
            timeout: 600000,
            success: function (data) {
                var json = JSON.parse(JSON.stringify(data));
                $('#main_content').html(json["result"]);

            },
            error: function (e) {
            }
        });
    }
}
function feed_click()
{
    info_active = false;
    table_active = false;
    feed_active = true;
    var search = {}
    search["account"] = uuid;

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/navigation/feed",
        data: JSON.stringify(search),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            var json = JSON.parse(JSON.stringify(data));
            $('#main_content').html(json["result"]);

        },
        error: function (e) {
        }
    });
}
function  update_table() {
    var search = {}
    search["account"] = uuid;
    if (table_active == true)
    {
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/api/navigation/all",
            data: JSON.stringify(search),
            dataType: 'json',
            cache: false,
            timeout: 600000,
            success: function (data) {

                var json = JSON.parse(JSON.stringify(data));
                $('#main_content').html(json["result"]);

            },
            error: function (e) {
            }
        });
    }


}

function make_pdf()
{

    var search = {}
    search["account"] = uuid;
    search["name"] = curr_name;

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "api/pdf/create",
        data: JSON.stringify(search),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
        },
        error: function (e) {
        }

    });
}

function download_pdf(data) {
    window.location = "/download/pdf/" + data;
}

function get_pdf_files()
{
    info_active = false;
    table_active = false;
    feed_active = false;
    var search = {}
    search["account"] = uuid;
    search["name"] = curr_name;

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "api/pdf/get",
        data: JSON.stringify(search),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            var json = JSON.parse(JSON.stringify(data));
            $('#main_content').html(json["result"]);
        },
        error: function (e) {
        }

    });
}
function all_click()
{
    info_active = false;
    table_active = true;
    feed_active = false;
    var search = {}
    search["account"] = uuid;

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/navigation/all",
        data: JSON.stringify(search),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {

            var json = JSON.parse(JSON.stringify(data));
            $('#main_content').html(json["result"]);

        },
        error: function (e) {
        }
    });
}

function report_click()
{
    info_active = false;
    table_active = false;
    feed_active = false;
    var search = {}
    search["account"] = uuid;

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/navigation/report",
        data: JSON.stringify(search),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            var json = JSON.parse(JSON.stringify(data));
            $('#main_content').html(json["result"]);

        },
        error: function (e) {
        }
    });
}


