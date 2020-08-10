package tk.zleoalex.automaton

import tk.zleoalex.automaton.nodes.AutomatonNode
import tk.zleoalex.automaton.nodes.MutableDFAAutomatonNode
import tk.zleoalex.automaton.nodes.MutableNFAAutomatonNode
import java.lang.Exception
import java.util.*

/**
 * 尝试合并MultiMap
 */
infix fun <K,V> Map<K,Set<V>>.union(other : Map<K,Set<V>>) =
    (this.keys union other.keys).map { k -> Pair(k,this[k].orEmpty() union other[k].orEmpty()) }.toMap()

/**
 * 子集构造法转换NFA为DFA,由[this]构造DFA
 * @return 构造完成的DFA
 */
fun <T: IWordRegex> NFAAutomaton<T>.toDFAAutomaton () : DFAAutomaton<T> {
    val openTable = LinkedList(listOf(this.startStatement.epsClosure))
    val st = MutableDFAAutomatonNode<T>()
    val findedMap = mutableMapOf(Pair(this.startStatement.epsClosure,st))

    while (openTable.isNotEmpty()){
        val s = openTable.pop()
        s.nextNode.forEach { (k,v) ->
            if (k!=null) {
                val vEps = v.epsClosure
                if (vEps !in findedMap) {
                    val acceptor = vEps.map { it.acceptor }.filter { it != null }.maxBy { it!!.prio }
                    findedMap[vEps] =
                        MutableDFAAutomatonNode(acceptor = acceptor)
                    openTable.push(vEps)
                }
                findedMap[s]!!.nextSet[k] = findedMap[vEps]!!
            }
        }
    }

    return DFAAutomaton(st, findedMap.values.toSet())
}

/**
 * 由DFS算法获取当前状态集合的eps闭包
 */
val <T> Set<MutableNFAAutomatonNode<T>>.epsClosure : Set<MutableNFAAutomatonNode<T>>
    get() = this.fold(setOf()){ set, automaton ->
        if (automaton in set) set else automaton.epsClosureDFS(set)
    }

/**
 * 一个集合NFA状态下一个字符对应的的NFA的集合
 */
val <T> Set<MutableNFAAutomatonNode<T>>.nextNode : Map<Char?,Set<MutableNFAAutomatonNode<T>>>
    get() = this.map { it.nextNode }.fold(mapOf()) { total,single -> total union single }

val <T> Set<MutableDFAAutomatonNode<T>>.nextSet : Map<Char,Set<MutableDFAAutomatonNode<T>>>
    get() = this.map { it.nextSet }.fold(mapOf()) { total,single -> total union single.mapValues { (_,v) -> setOf(v) } }