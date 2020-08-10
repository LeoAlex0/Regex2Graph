package tk.zleoalex

import tk.zleoalex.automaton.toDFAAutomaton
import tk.zleoalex.automaton.toNFAAutomaton
import tk.zleoalex.graph.saveSVG
import tk.zleoalex.graph.toGraphviz

fun main(args: Array<String>) {
    try {
        val filename = try {
            val idx = args.indexOf("-o")
            if (idx == -1) "demo.svg"
            else args[idx+1]
        } catch (e: Exception) {
            "demo.svg"
        }
        readLine()!!.toNFAAutomaton<QuestLanguage>().also { it.endStatement.acceptor =
            QuestLanguage.Id1
        }
            .toDFAAutomaton()
            .optimize()
            .toGraphviz().saveSVG(filename)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}
