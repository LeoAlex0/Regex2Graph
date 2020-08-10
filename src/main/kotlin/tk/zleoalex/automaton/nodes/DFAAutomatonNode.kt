package tk.zleoalex.automaton.nodes

abstract class DFAAutomatonNode<WordType> : AutomatonNode<WordType> {

    /**
     * 下一步为c时的可能状态集合
     */
    abstract val nextSet: Map<Char, DFAAutomatonNode<WordType>>

    /**
     * 兼容性接口
     */
    override val nextNode: Map<Char?,Set<DFAAutomatonNode<WordType>>>
        get() = nextSet.map { (k,v) -> Pair(k, setOf(v)) }.toMap()
}