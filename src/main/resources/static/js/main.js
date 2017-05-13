/**
 * Created by Rostik on 13.05.2017.
 */

var uuid = "54947df8-0e9e-4471-a2f9-9af509fb5889";

var info_active = false;

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