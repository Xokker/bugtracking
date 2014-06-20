$(document).ready(function () {

    var add = $("#add");
    var del = $("#delete");
    var deps = $("#deps");
    var bugs = $("#bugs");
    add.click(function () {
        var is_add=document.getElementById("is_add");
        is_add.setAttribute("value", "True");
    });
    del.click(function () {
        var is_add=document.getElementById("is_add");
        is_add.setAttribute("value", "False");
    });
    deps.click(function () {

    });
    bugs.click(function () {

    });
});