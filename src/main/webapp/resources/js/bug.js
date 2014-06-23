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
        var add=document.getElementById("add");
        var del=document.getElementById("delete");
        var bugs=document.getElementById("bugs");
        add.disabled=true;
        del.disabled=false;
        bugs.selectedIndex=-1;
    });
    bugs.click(function () {
        var add=document.getElementById("add");
        var del=document.getElementById("delete");
        var deps=document.getElementById("deps");
        add.disabled=false;
        del.disabled=true;
        deps.selectedIndex=-1;
    });
});
function link(id) {
    location.href="/bug/"+id;
}
function link(id) {
    location.href="/bug/"+id;
}
function linkProj(id) {
    location.href="/bugs/?project_id="+id;
}