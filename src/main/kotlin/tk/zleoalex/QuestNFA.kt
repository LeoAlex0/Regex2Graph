package tk.zleoalex

import tk.zleoalex.automaton.IWordRegex
import tk.zleoalex.automaton.NFAAutomaton
import tk.zleoalex.automaton.toNFAAutomaton

val letter = "(${(('a'..'z') + ('A'..'Z') + '_').joinToString("|")})"
val digit = "(${('0'..'9').joinToString("|")})"

interface WordType<T : IWordRegex> {
    val set : Set<T>
}

enum class QuestNFA: WordType<QuestLanguage> {
    Spaces {
        override val set: Set<QuestLanguage> = setOf(QuestLanguage.Id9)
    },ID {
        override val set: Set<QuestLanguage> = setOf(QuestLanguage.Id10)
    },NUM {
        override val set: Set<QuestLanguage> = setOf(QuestLanguage.Id11)
    },Operator {
        override val set: Set<QuestLanguage> = QuestLanguage.values().filter { it.id in 13..28 }.toSet()
    },Keyword {
        override val set: Set<QuestLanguage> = QuestLanguage.values().filter { it.id in 1..6 }.toSet()
    },Divider {
        override val set: Set<QuestLanguage> = setOf(QuestLanguage.Id0)
    }
}

val ndk
    get() = QuestNFA.values().filter { it !in setOf(
        QuestNFA.NUM,
        QuestNFA.ID
    ) }
        .map { it.set.toNFAAutomaton() }.reduce{a,b -> a or b}

val ids
    get() = QuestNFA.ID.set.toNFAAutomaton()

val nums
    get() = QuestNFA.NUM.set.toNFAAutomaton()

fun getNFA() : NFAAutomaton<QuestLanguage> {
    return ((nums and ndk) or (ids and ndk) or ndk).star()
}