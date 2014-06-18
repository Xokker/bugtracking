$(document).ready(function(){
    var showclosed = $("#showclosed");
    showclosed.prop("disabled", false);
    showclosed.click(function(){
        var checked = showclosed.prop("checked");
        var url = location.protocol + '//' + location.host + location.pathname + "?";
        var query = window.location.search.substring(1);
        if (query == "") {
            url = url + "showclosed=" + checked;
        } else {
            var vars = query.split("&");
            for (var i = 0; i < vars.length; i++) {
                if (i != 0) {
                    url = url + "&";
                }
                var pair = vars[i].split("=");
                if (pair[0] == "showclosed") {
                    pair[1] = checked;
                }
                url = url + pair[0] + "=" + pair[1];
            }
        }
        if (query.indexOf("showclosed") == -1) {
            url = url + "&showclosed=" + checked;
        }
        $("#showclosed").prop("disabled", true);
        location.href = url;
    });
});