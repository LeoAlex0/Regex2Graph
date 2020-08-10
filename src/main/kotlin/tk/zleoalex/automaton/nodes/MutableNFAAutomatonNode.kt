package tk.zleoalex.automaton.nodes

class MutableNFAAutomatonNode<WordType>(override val nextNode: MutableMap<Char?, MutableSet<MutableNFAAutomatonNode<WordType>>> = mutableMapOf(),
                                        override var acceptor: WordType? = null
) : NFAAutomatonNode<WordType> {

    /**
     * 由DFS算法获取当前节点的epsClosure闭包
     * @param closure 当前已经/正在搜索的节点
     * @return 当前节点的eps闭包
     */
    fun epsClosureDFS(closure: Set<MutableNFAAutomatonNode<WordType>>): Set<MutableNFAAutomatonNode<WordType>> =
        nextNode[null].orEmpty()
            .fold(closure union setOf(this)) { set, automaton -> if (automaton in set) set else automaton.epsClosureDFS(set) }

    override val epsClosure: Set<MutableNFAAutomatonNode<WordType>>
        get() = epsClosureDFS(setOf(this))
}

