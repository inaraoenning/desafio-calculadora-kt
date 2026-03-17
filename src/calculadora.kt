fun main() {

    // Calculadora inteligente capaz de
    // Reconhecer números e operadores matemáticos no console,
    // mínimo de 9 caracteres, e com parênteses.

    // Exemplo:
    // 5 + 7 * 9 / 2 (5 - 4) * 2

    println(" ==== 🧮 Super Calculadora === ")
    println(
        """
        |Digite uma operação
        |ex: 5 + 7 * 9 / 2 (5 - 4) * 2
    """.trimMargin()
    )
    val operacao: String = readln()

    // Tokenização: Separa a string em partes menores chamadas de token (numeros individuais, operadores e parenteses)
    // Avaliação: Calcular tokens na ordem correta, utilizando stacks

    // trim() no kotlin remove apenas o espaço do inicio e fim
    // replace() substitui

    // Verificar se tem 9 digitos
    if (operacao.length < 9) {
        println("❌ A operação deve ter o mínimo 9 caracteres.")
        return
    }

    // Remove espacos
    val semEspacos = operacao.replace(" ", "")

    //  Adiciona a multiplicação invisível
    var operacaoFormatada: String = ""


    for (i in 0 until semEspacos.length) {
        val caractereAtual = semEspacos[i]
        operacaoFormatada += caractereAtual

        // Se não é o ultimo caractere, olha o proximo
        if (i + 1 < semEspacos.length) {
            val proximoCaractere = semEspacos[i + 1]

            // se a letra atual for numero e a proxima for ( insere o *
            if (caractereAtual.isDigit() && proximoCaractere == '(') {
                operacaoFormatada += '*'
            }

            // se o caractere atual for ) e a proxima for numero insere *
            if (caractereAtual == ')' && proximoCaractere.isDigit()) {
                operacaoFormatada += '*'
            }

            // se o caractere for ) e o proximo (
            if (caractereAtual == ')' && proximoCaractere == '(') {
                operacaoFormatada += '*'
            }
        }
    }

    // Stringbuilder - mutable sequence of characters that can be used to efficiently perform multiple string manipulation operations.
    // val sb = StringBuilder()

}