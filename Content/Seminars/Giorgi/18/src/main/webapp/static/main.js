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