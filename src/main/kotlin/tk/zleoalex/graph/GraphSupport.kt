package tk.zleoalex.graph


import guru.nidi.graphviz.*
import guru.nidi.graphviz.attribute.*
import guru.nidi.graphviz.engine.Format
import guru.nidi.graphviz.engine.Graphviz
import tk.zleoalex.automaton.Automaton
import java.io.File

fun <WordType: IWordGraphable> Automaton<WordType>.toGraphviz(): Graphviz {
    val s = mutableMapOf(Pair(this.startStatement,0))
    val g = graph(name = "Automaton",directed = true) {
        edge[Arrow.VEE]
        graph[Rank.dir(Rank.RankDir.LEFT_TO_RIGHT)]
        "s0"[Shape.DOUBLE_CIRCLE]
    }

    this.forEach { v ->
        s.putIfAbsent(v,s.size)
        val vAppend = if(v.acceptor != null) "\n(${v.acceptor!!.id})" else ""
        v.nextNode.forEach { (c, set) ->
            set.forEach { u ->
                val uAppend = if(u.acceptor != null) "\n(${u.acceptor!!.id})" else ""
                val uColor = u.acceptor?.color ?: Color.BLACK
                val label = if(c == null) Label.html("&epsilon;") else Label.of("'$c'")

                s.putIfAbsent(u,s.size)

                g.add(graph {
                    ("s${s[v]}$vAppend" - "s${s[u]}$uAppend"[uColor])[label]
                })
            }
        }
    }

    return g.toGraphviz()
}

fun Graphviz.saveSVG(filename:String) = this.render(Format.SVG).toFile(File(filename))
