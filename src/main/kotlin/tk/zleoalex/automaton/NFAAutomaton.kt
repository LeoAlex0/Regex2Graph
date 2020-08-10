package tk.zleoalex.automaton

import tk.zleoalex.automaton.nodes.AutomatonNode
import tk.zleoalex.automaton.nodes.MutableNFAAutomatonNode

class NFAAutomaton<WordTypes>(override val startStatement: MutableNFAAutomatonNode<WordTypes> = MutableNFAAutomatonNode(),
                              val endStatement: MutableNFAAutomatonNode<WordTypes> = startStatement,
                              override val allStatement: Set<AutomatonNode<WordTypes>> = setOf(startStatement,endStatement)
) : Automaton<WordTypes>() {

    constructor(c: Char) : this(endStatement = MutableNFAAutomatonNode()) {
        startStatement.nextNode[c] = mutableSetOf(endStatement)
    }

    infix fun minOr(other: NFAAutomaton<WordTypes>): NFAAutomaton<WordTypes> {
        val ed = MutableNFAAutomatonNode<WordTypes>()
        other.startStatement.nextNode.forEach { (k, v) ->
            startStatement.nextNode[k] = (startStatement.nextNode[k].orEmpty() union v).toMutableSet()
        }

        listOf(this, other).map { it.endStatement.nextNode }.forEach {
            it[null] = mutableSetOf(ed)
        }

        return NFAAutomaton(
            startStatement, ed, allStatement union other.allStatement union setOf(ed)
                    subtract setOf(other.startStatement)
        )
    }

    infix fun or(other: NFAAutomaton<WordTypes>): NFAAutomaton<WordTypes> {
        val ed = MutableNFAAutomatonNode<WordTypes>()
        val st = MutableNFAAutomatonNode(
            mutableMapOf(
                Pair(
                    null as Char?,
                    mutableSetOf(this.startStatement, other.startStatement)
                )
            )
        )

        listOf(this, other).map { it.endStatement.nextNode }.forEach {
            it[null] = mutableSetOf(ed)
        }

        return NFAAutomaton(
            st,
            ed,
            allStatement union other.allStatement union setOf(st, ed)
        )
    }

    infix fun and(other: NFAAutomaton<WordTypes>): NFAAutomaton<WordTypes> {
        endStatement.nextNode.putAll(other.startStatement.nextNode)
        return NFAAutomaton(
            startStatement,
            other.endStatement,
            allStatement union other.allStatement subtract setOf(other.startStatement)
        )
    }

    fun star(): NFAAutomaton<WordTypes> {
        val ed = MutableNFAAutomatonNode<WordTypes>()
        val st = MutableNFAAutomatonNode(
            mutableMapOf(
                Pair(
                    null as Char?,
                    mutableSetOf(startStatement, ed)
                )
            )
        )
        endStatement.nextNode[null] = mutableSetOf(startStatement, ed)
        return NFAAutomaton(st, ed, allStatement union setOf(st, ed))
    }

    fun questionMark(): NFAAutomaton<WordTypes> {
        startStatement.nextNode.putIfAbsent(null, mutableSetOf())
        startStatement.nextNode[null]!!.add(endStatement)
        return this
    }
}