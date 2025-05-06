document.addEventListener("DOMContentLoaded", function () {
    const paidFromSelect = document.getElementById("paidFromSelect");
    const factureMontantInput = document.getElementById("factureMontantModal");
    const messageEl = document.getElementById('paymentResponseMessage');

    document.addEventListener("click", function (e) {
        if (e.target && e.target.classList.contains("payment-button")) {
            const button = e.target;
            const row = button.closest("tr");

            const id = row.querySelector("td:nth-child(1)").textContent.trim();
            const fournisseur = row.querySelector("td:nth-child(2)").textContent.trim();
            const montant = row.querySelector("td:nth-child(3)").textContent.trim();

            document.getElementById("factureIdModal").textContent = id;
            document.getElementById("factureFournisseurModal").textContent = fournisseur;
            factureMontantInput.value = montant; // Mettre à jour le champ montant avec la valeur de la facture

            document.getElementById("paymentModal").style.display = "block";
        }
    });

    document.querySelector("#paymentModal .close").addEventListener("click", function () {
        document.getElementById("paymentModal").style.display = "none";
    });

    window.addEventListener("click", function (event) {
        if (event.target === document.getElementById("paymentModal")) {
            document.getElementById("paymentModal").style.display = "none";
        }
    });

    document.getElementById("confirmPaymentBtn").addEventListener("click", function () {
        const paymentData = {
            doctype: "Payment Entry",
            payment_type: "Pay",
            party_type: "Supplier",
            party: document.getElementById("factureFournisseurModal").textContent.trim(),
            paid_from: paidFromSelect.value,
            paid_amount: parseFloat(factureMontantInput.value), // Utilisation de la valeur modifiée du montant
            received_amount: parseFloat(factureMontantInput.value), // Utilisation de la valeur modifiée du montant
            references: [
                {
                    reference_doctype: "Purchase Invoice",
                    reference_name: document.getElementById("factureIdModal").textContent.trim(),
                    allocated_amount: parseFloat(factureMontantInput.value) // Utilisation de la valeur modifiée du montant
                }
            ],
            docstatus: 1
        };

        console.log("Données du paiement à envoyer :", paymentData);

        fetch('/facture/paiement', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json', // Indiquer que nous envoyons des données en JSON
            },
            body: JSON.stringify(paymentData), // Convertir l'objet paymentData en JSON pour l'envoyer au serveur
        })
        .then(response => response.json())
        .then(data => {
            console.log("Réponse du serveur:", data); // Afficher la réponse entière pour debug

            if (data && data.data) {
                const paymentName = data.data.name;
                messageEl.style.color = 'green';
                messageEl.textContent = "Le paiement a été enregistré sous le nom : " + paymentName;
            } else {
                messageEl.style.color = 'red';
                messageEl.textContent = "Erreur de réponse du serveur : " + (data.message || "Réponse invalide");
            }

            setTimeout(() => {
                document.getElementById("paymentModal").style.display = "none";
            }, 3000);
        })
        .catch(error => {
            console.error("Erreur AJAX:", error);
            messageEl.style.color = 'red';
            messageEl.textContent = "Une erreur est survenue lors de l'envoi du paiement.";
        });
    });
});
