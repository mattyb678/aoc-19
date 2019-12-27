package xyz.mattyb.aoc

import org.clapper.util.classutil.*
import java.io.File
import kotlin.system.exitProcess


fun main(args: Array<String>) {
    if (args.size != 1) {
        System.err.println("Need a day in order to run")
        exitProcess(-1)
    }
    val day = args[0].toInt()

    val finder = ClassFinder()
    finder.addClassPath()
    val filter = AndClassFilter(
            NotClassFilter(InterfaceOnlyClassFilter()),
            SubclassClassFilter(DaySolver::class.java),
            NotClassFilter(AbstractClassFilter())
    )
    val foundClasses = ArrayList<ClassInfo>()
    finder.findClasses(foundClasses, filter)
    val daySolver = foundClasses.map { Class.forName(it.className) }
            .map { it.getDeclaredConstructor().newInstance() as DaySolver }
            .firstOrNull { it.day() == day }
    if (daySolver == null) {
        System.err.println("Could not find day: $day")
        exitProcess(-2)
    }

    val part1Resource = daySolver.inputResourcePart1()
    val part1Input = readFileAsLinesUsingUseLines("src/main/resources/$part1Resource")
    daySolver.part1(part1Input)

    val part2Resource = daySolver.inputResourcePart2()
    val part2Input = readFileAsLinesUsingUseLines("src/main/resources/$part2Resource")
    daySolver.part2(part2Input)
}

fun readFileAsLinesUsingUseLines(fileName: String): List<String>
        = File(fileName).useLines { it.toList() }