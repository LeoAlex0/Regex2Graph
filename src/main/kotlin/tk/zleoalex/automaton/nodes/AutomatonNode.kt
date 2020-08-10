package tk.zleoalex.automaton.nodes

interface AutomatonNode<out WordType> {

    /** 可接受类型,空集表示无，当存在多个元素时有二义性 */
    val acceptor : WordType?

    /**
     * 当前字符是c时的下一个自动机
     */
    val nextNode : Map<Char?,Set<AutomatonNode<WordType>>>
}