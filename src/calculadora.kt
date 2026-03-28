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
    """.trimMargin() // remove as margens antes de cada linha
    )
    val operacao: String = readln()

    // Verifica se tem 9 digitos
    if (operacao.length < 9) {
        println("❌ A operação deve ter o mínimo 9 caracteres.")
        return
    }

    // Remover espacos
    val semEspacos = operacao.replace(" ", "")

    // Regex coloca o '*' onde ele foi ocultado
    val regexMultiplicacao = Regex("(?<=\\d)(?=\\()|(?<=\\))(?=\\d)|(?<=\\))(?=\\()")
    val operacaoFormatada = semEspacos.replace(regexMultiplicacao, "*")

    // Separar a string em Tokens
    // "5+7" vira lista ["5", "+", "7"]
    val regexToken = Regex("\\d+|[+\\-*/()]")
    val tokens = regexToken.findAll(operacaoFormatada).map { it.value }.toList()

    // Organixa a fila (Shunting Yard)
    // MutableList - fazer o papel de Fila e Pilha
    val filaSaida = mutableListOf<String>()
    val pilhaOperadores = mutableListOf<String>()

    for (token in tokens) {
        // Se for um número, vai direto para a fila de saída
        if (token[0].isDigit()) {
            filaSaida.add(token)
        }
        // Se for um parêntese abrindo, entra na pilha de espera
        else if (token == "(") {
            pilhaOperadores.add(token)
        }
        // Se for um parêntese fechando, tira o que está na pilha até achar o '('
        else if (token == ")") {
            while (pilhaOperadores.isNotEmpty() && pilhaOperadores.last() != "(") {
                filaSaida.add(pilhaOperadores.removeLast())
            }
            // Tira o '(' da pilha e joga fora, não precisamos mais dele
            if (pilhaOperadores.isNotEmpty() && pilhaOperadores.last() == "(") {
                pilhaOperadores.removeLast()
            }
        }
        // Se for um operador matemático (+, -, *, /)
        else {
            while (pilhaOperadores.isNotEmpty()) {
                val topoDaPilha = pilhaOperadores.last()

                // Descobre quem é mais forte
                var forcaToken = 0
                if (token == "*" || token == "/") forcaToken = 2
                if (token == "+" || token == "-") forcaToken = 1

                var forcaTopo = 0
                if (topoDaPilha == "*" || topoDaPilha == "/") forcaTopo = 2
                if (topoDaPilha == "+" || topoDaPilha == "-") forcaTopo = 1

                // Se quem está na pilha for mais forte ou igual, ele sai primeiro e vai pra fila
                if (topoDaPilha != "(" && forcaTopo >= forcaToken) {
                    filaSaida.add(pilhaOperadores.removeLast())
                } else {
                    break // Para o while
                }
            }
            // Depois que organizou os mais fortes, o operador atual entra na pilha
            pilhaOperadores.add(token)
        }
    }

    // Se sobrou algum operador na pilha de espera no final, manda pra fila
    while (pilhaOperadores.isNotEmpty()) {
        filaSaida.add(pilhaOperadores.removeLast())
    }

    //  Calcular resultado
    // A fila  na ordem certa - fazer conta
    val pilhaCalculo = mutableListOf<Double>()

    for (token in filaSaida) {
        if (token[0].isDigit()) {
            // Se número, transforma em Double e coloca na pilha de cálculo
            pilhaCalculo.add(token.toDouble())
        } else {
            // Se operador, pega dois últimos números da pilha
            val numero2 = pilhaCalculo.removeLast()
            val numero1 = pilhaCalculo.removeLast()
            var resultado = 0.0

            // Faz a conta dependendo do sinal
            if (token == "+") resultado = numero1 + numero2
            if (token == "-") resultado = numero1 - numero2
            if (token == "*") resultado = numero1 * numero2
            if (token == "/") resultado = numero1 / numero2

            // Guarda o resultado de volta na pilha
            pilhaCalculo.add(resultado)
        }
    }

    // último número que sobrar é a resposta
    println("✅ Resultado final: ${pilhaCalculo.last()}")

}

//  O padrão \d+|[+\-*/()] faz exatamente aquela divisão: o \d+ garante que números de múltiplos
//  dígitos (como 144) não sejam separados, enquanto o [+\-*/()] (onde o - é escapado com \ para
//  não ser confundido com um intervalo) captura os símbolos matemáticos individualmente.
//  A função findAll do Kotlin percorre a string formatada e extrai essas partes limpas
//  para uma lista.


// Substituido por regex
/*for (i in 0 until semEspacos.length) {
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
}*/