package com.leonardo.controle_estoque.utils;

public class ConversorDecimal {

    public static Double StrToDoub(String valor) {
        if (valor == null) {
            return null;
        }
        try {
            valor = valor.trim().replace(",", ".");
            return Double.parseDouble(valor);

        } catch (NumberFormatException e){
            System.out.println("Erro ao coverter valor: " + valor);
            return null;
        }
    }


}
