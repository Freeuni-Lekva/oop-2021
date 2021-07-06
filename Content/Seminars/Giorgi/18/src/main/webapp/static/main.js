function filter() {
    var target = new URL("/api/students", "http://localhost:8080");
    for (var p of new FormData(document.getElementById("filter")).entries()) {
        target.searchParams.append(p[0], p[1]);
    }
    console.log(target.href);
    fetch(target.href)
    .then(response => response.json())
    .then(updateView)
    .catch(error => console.error(error));
    return false;
}

function setupWSFiltering() {
console.log("foooo");
    var ws = new WebSocket("ws://localhost:8080/api/students_ws");
    ws.onopen = function() {
    console.log(ws);
        ws.onmessage = function(e) {
            updateView(JSON.parse(e.data));
        }

        document.getElementById("filter").onsubmit = function() {
            return filterUsingWS(ws);
        }
    }

}

function filterUsingWS(ws) {
    var data = {}
    var first_name = document.getElementById("filter_first_name").value;
    var last_name = document.getElementById("filter_last_name").value;
    var enrollment_year = document.getElementById("filter_enrollment_year").value;
    if (first_name) {
        data.first_name = first_name;
    }
    if (last_name) {
        data.last_name = last_name;
    }
    if (enrollment_year) {
        data.enrollment_year = parseInt(enrollment_year, 10);
    }
    console.log(JSON.stringify(data));
    ws.send(JSON.stringify(data));
    return false;
}


function updateView(students) {
    document.querySelectorAll("#students tr.student").forEach(elem => {
        console.log(elem);
        elem.remove();
    });
    students.forEach(function(st) {
        var elem = document.createElement("tr");
        elem.classList.add("student");
        elem.innerHTML = `
            <td>${st.first_name}</td>
            <td>${st.last_name}</td>
            <td>${st.enrollment_year}</td>`;
        document.getElementById("students").appendChild(elem);
    });
}

function create() {
    var data = new URLSearchParams();
    for (var p of new FormData(document.getElementById("create")).entries()) {
        data.append(p[0], p[1]);
    }
    fetch("/api/students", {
        method: "POST",
        body: data
    })
    .then(filter)
    .catch(error => console.error(error));
    return false;
}

document.onload = setupWSFiltering;