package tk.zleoalex.automaton

import tk.zleoalex.automaton.nodes.*
import java.lang.Exception
import java.util.*

class DFAAutomaton<WordType>(
    override val startStatement: MutableDFAAutomatonNode<WordType>,
    override val allStatement: Set<MutableDFAAutomatonNode<WordType>>
) : Automaton<WordType>() {
    /**
     * 优化当前自动机，生成优化后新的自动机
     */
    fun optimize() : DFAAutomaton<WordType> {
        var set = allStatement.groupBy { it.acceptor }.values.map { it.toSet() }.toSet()

        while (true) {
            val newSet = mutableSetOf<Set<MutableDFAAutomatonNode<WordType>>>()

            set.forEach {
                /** 按照每个节点下一个节点的分类进行分类 **/
                val subset = it.groupBy { automaton -> automaton.nextSet.mapValues { (_, v) -> set.first { v in it } } }
                    .values.map { it.toSet() }

                newSet.addAll(subset)
            }
            if (newSet == set) break
            else set = newSet
        }

        /** 按照分类构造新的DFA **/

        val mapGroup = set.map { Pair(it,
            MutableDFAAutomatonNode(acceptor = it.first().acceptor)
        ) }.toMap()
        val mapSingle = set.map { s ->
            val new = mapGroup[s]
            s.map { Pair(it, new!!) }
        }.reduce { a, b -> a + b }.toMap()

        mapGroup.keys.map{ it.first() }.forEach { oldV ->
            oldV.nextSet.map { (c,oldU) -> Pair(c,mapSingle[oldU]!!) }.forEach { (c,u) ->
                mapSingle[oldV]!!.nextSet[c] = u
            }
        }

        return DFAAutomaton(
            mapSingle[startStatement]!!,
            mapSingle.values.toSet()
        )
    }
}

