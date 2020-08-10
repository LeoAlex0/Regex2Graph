package tk.zleoalex

import guru.nidi.graphviz.attribute.Color
import tk.zleoalex.automaton.IWordRegex
import tk.zleoalex.graph.IWordGraphable

enum class QuestLanguage : IWordGraphable,
    IWordRegex {
    Id1 {
        override val prio: Int = 0
        override val id: Int = 1
        override val regex: String = "begin"
        override val color: Color = Color.ALICEBLUE
    },Id2 {
        override val prio: Int = 0
        override val id: Int = 2
        override val regex: String = "if"
        override val color: Color = Color.ANTIQUEWHITE
    },Id3 {
        override val prio: Int = 0
        override val id: Int = 3
        override val regex: String = "then"
        override val color: Color = Color.AQUAMARINE
    },Id4 {
        override val prio: Int = 0
        override val id: Int = 4
        override val regex: String = "while"
        override val color: Color = Color.AZURE
    },Id5 {
        override val prio: Int = 0
        override val id: Int = 5
        override val regex: String = "do"
        override val color: Color = Color.BEIGE
    },Id6 {
        override val prio: Int = 0
        override val id: Int = 6
        override val regex: String = "end"
        override val color: Color = Color.BISQUE
    },Id10 {
        override val prio: Int = -1
        override val id: Int = 10
        override val regex: String = "$letter($letter|$digit)*"
        override val color: Color = Color.BLANCHEDALMOND
    },Id11 {
        override val prio: Int = 0
        override val id: Int = 11
        override val regex: String = "$digit$digit*"
        override val color: Color = Color.BLUE
    },Id13 {
        override val prio: Int = 0
        override val id: Int = 13
        override val regex: String = "+"
        override val color: Color = Color.BROWN
    },Id14 {
        override val prio: Int = 0
        override val id: Int = 14
        override val regex: String = "-"
        override val color: Color = Color.BURLYWOOD
    },Id15 {
        override val prio: Int = 0
        override val id: Int = 15
        override val regex: String = "\\*"
        override val color: Color = Color.CADETBLUE
    },Id16 {
        override val prio: Int = 0
        override val color: Color = Color.CHARTREUSE
        override val id: Int = 16
        override val regex: String = "/"
    },Id17 {
        override val prio: Int = 0
        override val color: Color = Color.CHOCOLATE
        override val id: Int = 17
        override val regex: String = ":"
    },Id18 {
        override val prio: Int = 1
        override val color: Color = Color.CORAL
        override val id: Int = 18
        override val regex: String = ":="
    },Id20 {
        override val prio: Int = 0
        override val color: Color = Color.CRIMSON
        override val id: Int = 20
        override val regex: String = "<"
    },Id21 {
        override val prio: Int = 1
        override val color: Color = Color.CORNFLOWERBLUE
        override val id: Int = 21
        override val regex: String = "<>"
    },Id22 {
        override val prio: Int = 1
        override val color: Color = Color.CORNSILK
        override val id: Int = 22
        override val regex: String = "<="
    },Id23 {
        override val prio: Int = 0
        override val color: Color = Color.CYAN
        override val id: Int = 23
        override val regex: String = ">"
    },Id24 {
        override val prio: Int = 1
        override val color: Color = Color.DARKGOLDENROD
        override val id: Int = 24
        override val regex: String = ">="
    },Id25 {
        override val prio: Int = 0
        override val color: Color = Color.DARKGREEN
        override val id: Int = 25
        override val regex: String = "="
    },Id26 {
        override val prio: Int = 0
        override val color: Color = Color.DARKKHAKI
        override val id: Int = 26
        override val regex: String = ";"
    },Id27 {
        override val prio: Int = 0
        override val color: Color = Color.DARKOLIVEGREEN
        override val id: Int = 27
        override val regex: String = "\\("
    },Id28 {
        override val prio: Int = 0
        override val color: Color = Color.DARKORANGE
        override val id: Int = 28
        override val regex: String = "\\)"
    },Id0 {
        override val prio: Int = 0
        override val color: Color = Color.DARKSALMON
        override val id: Int = 0
        override val regex: String = "#"
    },Id9 {
        override val color: Color = Color.BLACK
        override val prio: Int = -1
        override val regex: String = "( |\t|\n)( |\t|\n)*"
        override val id: Int = 9
    }
}