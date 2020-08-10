package tk.zleoalex

import java.lang.Exception

class IlleagaleWord(val at : Int) : Exception("Illegal word @ $at")  {}