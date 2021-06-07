function start() {
  const button = document.getElementById("add-student");
  button.onclick = addStudent;
}

function addStudent() {
  const table = document.getElementById("student-table");

  const firstName = document.getElementById("first-name").value;
  const lastName = document.getElementById("last-name").value;
  const year = document.getElementById("year").value;

  if (firstName === "" || lastName === "" || year === "") {
    alert("Fields can't be empty!");
    return;
  }

  const row = table.insertRow(-1);
  const firstNameCell = row.insertCell(0);
  firstNameCell.innerHTML = firstName;
  const lastNameCell = row.insertCell(1);
  lastNameCell.innerHTML = lastName;
  const yearCell = row.insertCell(2);
  yearCell.innerHTML = year;
}

window.onload = start;
