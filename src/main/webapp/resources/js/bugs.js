$(document).ready(function(){
    $("#showclosed").prop("disabled", false);
    $("#showclosed").click(function(){
        var url = location.protocol + '//' + location.host + location.pathname + "?";
        var query = window.location.search.substring(1);
        if (query == "") {
            url = url + "showclosed=" + $("#showclosed").prop("checked");
        } else {
            var vars = query.split("&");
            for (var i = 0; i < vars.length; i++) {
                if (i != 0) {
                    url = url + "&";
                }
                var pair = vars[i].split("=");
                if (pair[0] == "showclosed") {
                    pair[1] = $("#showclosed").prop("checked");
                }
                url = url + pair[0] + "=" + pair[1];
            }
        }
        $("#showclosed").prop("disabled", true);
        location.href = url;
    });
});