document.getElementById("saveButton").addEventListener("click", function () {
    const docname = document.getElementById("modal-id").textContent;
    const docstatus = 1; // ou récupéré dynamiquement depuis un champ input ou autre
    console.log("ID de la demande:", docname);
    const rows = document.querySelectorAll("#modal-items tr");
    const updatedItems = [];

    rows.forEach(row => {
        const itemCode = row.cells[1].textContent;
        const itemName = row.cells[0].textContent;
        const qty = parseFloat(row.cells[2].querySelector("input").value);
        const rate = parseFloat(row.cells[3].querySelector("input").value);

        updatedItems.push({
            item_code: itemCode,
            item_name: itemName,
            qty: qty,
            rate: rate
        });
    });

    fetch(`/demande/details/${encodeURIComponent(docname)}`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            items: updatedItems,
            docstatus: docstatus // <-- C'est ici qu'on l'ajoute
        })
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("Erreur lors de la mise à jour");
        }
        return response.json();
    })
    .then(result => {
        console.log("Mise à jour réussie:", result);
        document.getElementById("saveMessage").style.display = "block";
        setTimeout(() => {
            document.getElementById("saveMessage").style.display = "none";
        }, 3000);
    })
    .catch(error => {
        console.error("Erreur lors de l'enregistrement :", error);
        alert("Échec de la mise à jour !");
    });
});
