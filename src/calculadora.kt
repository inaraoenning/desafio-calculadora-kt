fun main() {

    // Calculadora inteligente capaz de
    // Reconhecer números e operadores matemáticos no console,
    // mínimo de 9 caracteres, e com parênteses.

    // Exemplo:
    // 5 + 7 * 9 / 2 (5 - 4) * 2

    print("Informe a operação ex:5 + 7 * 9 / 2 (5 - 4) * 2 :")
    val operacao: String = readln()

    if (operacao.length < 9) {
        println("A operação deve conter no mínimo 9 caracteres")
        return
    }

    // Verifica se é digito
    val isDigit = operacao.isDigit()

    println(operacao)
    println("Tamanho da operação ${operacao.length}")


}