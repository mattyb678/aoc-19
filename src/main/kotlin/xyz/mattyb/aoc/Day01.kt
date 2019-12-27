package xyz.mattyb.aoc

class Day01 : DaySolver {

    override fun day(): Int = 1

    override fun inputResourcePart1() = "day01/input"

    override fun part1(input: List<String>) {
        println("Solving day 1, part 1")
        val sum = input.map { it.toInt() }
                .map { calculateFuel(it) }
                .sum()
        println("Fuel sum: $sum")
    }

    override fun inputResourcePart2() = "day01/input"

    override fun part2(input: List<String>) {
        println("Solving day 1, pat 2")
        val sum = input.map { it.toInt() }
                .map { calculateFuel(it) }
                .map {
                    var fuelNeeds = it
                    var total = it
                    while (calculateFuel(fuelNeeds) >= 0) {
                        val needs = calculateFuel(fuelNeeds)
                        total += needs
                        fuelNeeds = needs
                    }
                    return@map total
                }
                .sum()
        println("Fuel sum: $sum")
    }

    private fun calculateFuel(mass: Int): Int = mass.div(3).minus(2)
}