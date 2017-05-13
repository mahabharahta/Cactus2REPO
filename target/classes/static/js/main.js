/**
 * Created by Rostik on 13.05.2017.
 */

var uuid = "54947df8-0e9e-4471-a2f9-9af509fb5889";

var info_active = false;

var dataSourceTemperature = [{
    name: 'Current',
    mean: 35,
    min: 28,
    max: 38
}];

var dataSourceHumidity = [{
    name: 'Current',
    mean: 83,
    min: 75,
    max: 95
}];

function updateInfo() {
    var search = {}
    search["account"] = uuid;
    if (info_active == true)
    {
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
            },
            error: function (e) {

                alert(e);

            }

        });
    }


}

function  click_info(identifier) {
    info_active = true;
    var search = {}
    search["account"] = uuid;
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
        },
        error: function (e) {

            alert(e);

        }
    });
}
function feed_click()
{
    info_active = false;
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

           alert(e);

        }
    });
}

function all_click()
{
    info_active = false;
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

            alert(e);

        }
    });
}


