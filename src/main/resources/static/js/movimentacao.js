document.addEventListener("DOMContentLoaded", function () {

    // ===== Modal =====
    const modal = document.getElementById('janela-modal');

    if (modal) {
        modal.addEventListener('show.bs.modal', function (event) {

            const button = event.relatedTarget;

            const id = button.getAttribute('data-id');
            const nome = button.getAttribute('data-nome');
            const quantidade = button.getAttribute('data-quantidade');

            document.getElementById('modalProdutoId').value = id;
            document.getElementById('modalProdutoNome').value = nome;
            document.getElementById('modalProdutoQuantidade').value = quantidade;
        });
    }

    // ===== Alerts automáticos =====
    setTimeout(function () {
        let alerts = document.querySelectorAll(".alert");
        alerts.forEach(alert => alert.remove());
    }, 2000);

});