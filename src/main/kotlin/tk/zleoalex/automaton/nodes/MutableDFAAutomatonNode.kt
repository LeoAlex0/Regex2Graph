package tk.zleoalex.automaton.nodes

class MutableDFAAutomatonNode<WordType>(override val nextSet: MutableMap<Char, MutableDFAAutomatonNode<WordType>> = mutableMapOf(),
                                        override var acceptor: WordType? = null
) : DFAAutomatonNode<WordType>()