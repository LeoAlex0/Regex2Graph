package tk.zleoalex

import guru.nidi.graphviz.attribute.Color
import tk.zleoalex.automaton.IWordRegex
import tk.zleoalex.graph.IWordGraphable

data class CustomLanguage(
    override val regex: String,
    override val prio: Int = 0,
    override val color: Color = Color.BLUE,
    override val id: Int = 0
) : IWordRegex, IWordGraphable