document.addEventListener("DOMContentLoaded", function () {
    const paidFromSelect = document.getElementById("paidFromSelect");
    const factureMontantInput = document.getElementById("factureMontantModal");

    // Écouteur pour l'ouverture du modal
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

    // Fermeture du modal
    document.querySelector("#paymentModal .close").addEventListener("click", function () {
        document.getElementById("paymentModal").style.display = "none";
    });

    // Fermeture du modal si on clique en dehors
    window.addEventListener("click", function (event) {
        if (event.target === document.getElementById("paymentModal")) {
            document.getElementById("paymentModal").style.display = "none";
        }
    });

    // Action du bouton "Confirmer le paiement"
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

        // Appel AJAX avec fetch pour envoyer la requête POST au backend
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

            // Vérifiez si la réponse contient la clé 'data' et si elle a la structure attendue
            if (data && data.data) {
                // Affichez des informations spécifiques de la réponse
                const paymentName = data.data.name;  // Exemple d'accès aux données spécifiques
                alert("Le paiement a été enregistré sous le nom : " + paymentName);
            } else {
                alert("Erreur de réponse du serveur : " + (data.message || "Réponse invalide"));
            }
        })
        .catch(error => {
            console.error("Erreur AJAX:", error);
            alert("Une erreur est survenue lors de l'envoi du paiement.");
        });

                // Fermer le modal après l'envoi
                document.getElementById("paymentModal").style.display = "none";
            });
        });
