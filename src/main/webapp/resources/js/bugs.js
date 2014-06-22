$(document).ready(function(){
    var showclosed = $("#showclosed");
    var sortBy = $('ul.dropdown-menu li');
    var dropdown = $('#sortby');

    showclosed.prop("disabled", false);
    dropdown.prop("disabled", false);

    showclosed.click(function(){
        var checked = showclosed.prop("checked");
        showclosed.prop("disabled", true);
        location.href = getUrlWithParam("showclosed", checked);
    });

    sortBy.click(function(){
        var text = $(this).find("a").text();
        var prioritySort = text == "Priority";
        dropdown.prop("disabled", true);
        location.href = getUrlWithParam("priority", prioritySort);
    });
});

function getUrlWithParam(key, value) {
    var url = location.protocol + '//' + location.host + location.pathname + "?";
    var query = window.location.search.substring(1);
    if (query == "") {
        url = url + key + "=" + value;
    } else {
        var vars = query.split("&");
        for (var i = 0; i < vars.length; i++) {
            if (i != 0) {
                url = url + "&";
            }
            var pair = vars[i].split("=");
            if (pair[0] == key) {
                pair[1] = value;
            }
            url = url + pair[0] + "=" + pair[1];
        }
    }
    if (url.indexOf(key) == -1) {
        url = url + "&" + key + "=" + value;
    }
    return url;
}
