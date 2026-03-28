function gerarPdf() {
    const lista = document.getElementById("lista-pdf");

    const edicao = {
        margin: 5,
        filename: "lista-compras.pdf",
        html2canvas: { scale: 1 },
        jsPDF: { unit: "mm", format: "a4", orientation: "portrait" }
    };

    html2pdf().set(edicao).from(lista).save();
}
