function calcularMargem() {

    const custo = limparValor(document.getElementById("precoCusto").value);
    const venda = limparValor(document.getElementById("precoVenda").value);

    if (!isNaN(custo) && !isNaN(venda) && custo > 0) {

        const lucro = venda - custo;
        const margem = (lucro / custo) * 100;

        document.getElementById("margemLucro").value = margem.toFixed(2) + "%";

    } else {

        document.getElementById("margemLucro").value = "";
    }
}

function limparValor(valor) {
    if (!valor) return NaN;

    return parseFloat(
        valor
            .replace(/\./g, '')
            .replace(',', '.')
    );
}

let timeout;

function calcularMargemDelay() {
    clearTimeout(timeout);
    timeout = setTimeout(calcularMargem, 200);
}

document.getElementById("precoCusto").addEventListener("input", calcularMargemDelay);
document.getElementById("precoVenda").addEventListener("input", calcularMargemDelay);