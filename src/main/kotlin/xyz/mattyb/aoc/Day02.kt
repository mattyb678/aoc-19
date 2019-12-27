package xyz.mattyb.aoc

import java.lang.IllegalStateException
import java.util.List.copyOf

class Day02 : DaySolver {
    override fun day() = 2

    override fun part1(input: List<String>) {
        println("Solving day 2, part 1")
        val program = input[0].split(",").map { it.toInt() }.toMutableList()
        program[1] = 12
        program[2] = 2
        run(program)
        println(program)
    }

    override fun inputResourcePart1() = "day02/input"

    override fun part2(input: List<String>) {
        println("Solving day 2, part 2")
        val initialProgram = input[0].split(",").map { it.toInt() }.toMutableList()
        for (i in 0..99) {
            for (j in 0..99) {
                val program = copyOf(initialProgram).toMutableList()
                program[1] = i
                program[2] = j
                run(program)
                if (program[0] == 19690720) {
                    val answer = 100 * i + j
                    println("Answer: $answer")
                    break
                }
            }
        }
    }

    override fun inputResourcePart2() = "day02/input"

    private fun run(program: MutableList<Int>): MutableList<Int> {
        var position = 0
        var opCode = OpCode.fromCode(program[position])
        while (opCode != OpCode.HALT) {
            val input1 = program[program[position + 1]]
            val input2 = program[program[position + 2]]
            val outputPosition = program[position + 3]
            val result = when(opCode) {
                OpCode.ADD -> input1 + input2
                OpCode.MULTIPLY -> input1 * input2
                else -> throw IllegalStateException("OpCode not ADD or MULTIPLY")
            }
            program[outputPosition] = result

            // Setup for next loop
            position += 4
            opCode = OpCode.fromCode(program[position])
        }
        return program
    }
}

fun main() {
    val solver = Day02()
//    solver.part1(listOf("1,0,0,0,99"))
//    solver.part1(listOf("2,3,0,3,99"))
//    solver.part1(listOf("2,4,4,5,99,0"))
//    solver.part1(listOf("1,1,1,4,99,5,6,0,99"))
    solver.part2(listOf("99"))
}