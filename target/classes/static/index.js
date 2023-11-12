document.addEventListener("DOMContentLoaded", function () {
    fetch("http://localhost:8080/api/v1/card/getAll")
    .then((response) => { return response.json() })
    .then((parsedResponse) => {
        parsedResponse.forEach(element => {
            let newTr = document.createElement("tr");
            newTr.className = "table-primary";

            let newTh = document.createElement("th");
            newTh.scope = "row";

            newTh.textContent = element.id;

            newTr.appendChild(newTh);

            let extable = document.getElementById("table");
            extable.querySelector(".tableBody").appendChild(newTr);
        });
    })
    .catch((error) => {
        console.error("Error fetching data:", error);
    });
});
