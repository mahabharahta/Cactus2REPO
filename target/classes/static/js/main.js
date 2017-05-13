/**
 * Created by Rostik on 13.05.2017.
 */

var uuid = "fdgdyetwrfgnmkl7689sf";
function feed_click()
{
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