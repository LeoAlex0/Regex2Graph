package tk.zleoalex.automaton.nodes

interface NFAAutomatonNode<WordType> : AutomatonNode<WordType> {

    /**
     * 当前自动机的eps闭包集合
     */
    val epsClosure: Set<NFAAutomatonNode<WordType>>

    override val nextNode: Map<Char?,Set<NFAAutomatonNode<WordType>>>
}