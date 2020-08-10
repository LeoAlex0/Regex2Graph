package tk.zleoalex.automaton

import java.util.*

fun <WordType> String.toNFAAutomaton(): NFAAutomaton<WordType> {
    val stackNumber = Stack<NFAAutomaton<WordType>>()
    val stackOperator = Stack<Char>()

    val prio = "|&(*?"
    var canConnect = false
    var inffi = false

    fun calc() {
        when (stackOperator.pop()) {
            '*' -> {
                stackNumber.push(stackNumber.pop().star())
            }
            '?' -> {
                stackNumber.push(stackNumber.pop().questionMark())
            }
            '&' -> {
                val r = stackNumber.pop()
                val l = stackNumber.pop()
                stackNumber.push(l and r)
            }
            '|' -> {
                val r = stackNumber.pop()
                val l = stackNumber.pop()
                stackNumber.push(l or r)
            }
        }
    }
    this.forEach { c ->
        when {
            c !in "?*|()\\" || inffi -> {
                if (canConnect) {
                    while (stackOperator.isNotEmpty() && prio.indexOf(stackOperator.peek()) >= prio.indexOf('&'))
                        calc()
                    stackOperator.push('&')
                }
                stackNumber.push(NFAAutomaton(c))
                canConnect = true
                inffi = false
            }
            c == '\\' -> inffi = true
            c != ')' && c != '*' -> {
                canConnect = c != '(' && c != '|'
                while (stackOperator.isNotEmpty() && stackOperator.peek() != '(' && prio.indexOf(stackOperator.peek()) >= prio.indexOf(
                        c
                    )
                )
                    calc()
                stackOperator.push(c)
            }
            c == ')' -> {
                canConnect = true
                while (stackOperator.isNotEmpty() && stackOperator.peek() != '(')
                    calc()
                if (stackOperator.isNotEmpty()) stackOperator.pop()
            }
            else -> {
                canConnect = true
                stackNumber.push(stackNumber.pop().star())
            }
        }
    }

    while (stackOperator.isNotEmpty()) calc()
    while (stackNumber.size > 1) {
        val r = stackNumber.pop()
        val l = stackNumber.pop()
        stackNumber.push(l and r)
    }

    return stackNumber.pop()
}

fun <WordType : IWordRegex> WordType.toNFAAutomaton(): NFAAutomaton<WordType> =
    this.regex.toNFAAutomaton<WordType>().also { it.endStatement.acceptor = this }

fun <WordType : IWordRegex> Iterable<WordType>.toNFAAutomaton(): NFAAutomaton<WordType> =
    this.map { wd -> wd.toNFAAutomaton() }.reduce { a, b -> a minOr b }