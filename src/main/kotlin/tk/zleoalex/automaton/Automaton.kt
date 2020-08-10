package tk.zleoalex.automaton

import tk.zleoalex.automaton.nodes.AutomatonNode

abstract class Automaton<WordType> {
    /**
     * 起始状态
     */
    abstract val startStatement: AutomatonNode<WordType>

    abstract val allStatement: Set<AutomatonNode<WordType>>

    fun forEach(blk: (AutomatonNode<WordType>) -> Unit) = allStatement.forEach(blk)

}