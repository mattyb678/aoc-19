package xyz.mattyb.aoc

enum class OpCode(val code: Int) {
    ADD(1),
    MULTIPLY(2),
    HALT(99)
    ;

    companion object {
        fun fromCode(code: Int): OpCode? {
            return  values().firstOrNull { it.code == code }
        }
    }
}