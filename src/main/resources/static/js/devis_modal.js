function openModal(quotationId) {
    fetch(`/demande/detail/${quotationId}`)
        .then(response => {
            if (!response.ok) {
                throw new Error("Erreur lors de la récupération de la demande");
            }
            return response.json();
        })
        .then(data => {
            console.log("Données reçues de l'API:", data);

            const modal = document.getElementById("myModal");
            const modalId = document.getElementById("modal-id");
            const modalFournisseur = document.getElementById("modal-fournisseur");
            const modalDocstatus = document.getElementById("modal-docstatus");

            if (modalId) {
                modalId.textContent = data.name;
            } else {
                console.error("L'élément avec l'ID 'modal-id' n'a pas été trouvé.");
            }

            if (modalFournisseur) {
                modalFournisseur.textContent = data.supplier;
            } else {
                console.error("L'élément avec l'ID 'modal-fournisseur' n'a pas été trouvé.");
            }

            if (modalDocstatus) {
                modalDocstatus.textContent = data.docstatus;
                modalDocstatus.className = "";
            } else {
                console.error("L'élément avec l'ID 'modal-docstatus' n'a pas été trouvé.");
            }
            if (modalDocstatus) {
                if (data.docstatus === 1) {
                    modalDocstatus.textContent = "Submitted";  // Si docstatus est 1
                    // Masquer le bouton "Enregistrer" si le statut est "Submitted"
                    modalDocstatus.classList.add("status-submitted");
                    if (saveButton) {
                        saveButton.style.display = "none";
                    }
                } else if (data.docstatus === 0) {
                    modalDocstatus.textContent = "Draft";  // Si docstatus est 0
                    // Afficher le bouton "Enregistrer" si le statut est "Draft"
                    modalDocstatus.classList.add("status-draft");
                    if (saveButton) {
                        saveButton.style.display = "inline-block";
                    }
                } else {
                    modalDocstatus.textContent = "Inconnu";  // Valeur par défaut si ce n'est ni 1 ni 0
                    if (saveButton) {
                        saveButton.style.display = "inline-block";
                    }
                }
            } else {
                console.error("L'élément avec l'ID 'modal-docstatus' n'a pas été trouvé.");
            }   
                                

            const itemsContainer = document.getElementById("modal-items");
            itemsContainer.innerHTML = ""; // Vider l'ancien contenu

            if (data.items && data.items.length > 0) {
                data.items.forEach(item => {
                    const itemHtml = `
                        <tr>
                            <td>${item.item_name}</td>
                            <td>${item.item_code}</td>
                             <td>
                                <input type="number" value="${item.qty}"  class="editable-rate" />
                            </td>
                            <td>
                                <input type="number" value="${item.rate}"  class="editable-rate" />
                            </td>
                        </tr>`;
                    itemsContainer.innerHTML += itemHtml;
                });
            } else {
                itemsContainer.innerHTML = `<tr><td colspan="3">Aucun item</td></tr>`;
            }

            if (modal) {
                modal.style.display = "block";
            } else {
                console.error("Le modal n'a pas été trouvé.");
            }

            const closeBtn = document.querySelector(".close");
            if (closeBtn) {
                closeBtn.onclick = closeModal;
            }

            window.onclick = function (event) {
                if (event.target == modal) {
                    closeModal();
                }
            };
        })
        .catch(error => {
            console.error("Erreur lors de l'ouverture du modal :", error);
            alert("Impossible de charger les détails de la demande.");
        });
}

function closeModal() {
    const modal = document.getElementById("myModal");
    if (modal) {
        modal.style.display = "none";
    } else {
        console.error("Le modal n'a pas été trouvé lors de la fermeture.");
    }
}