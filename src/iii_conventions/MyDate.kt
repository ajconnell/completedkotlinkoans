package iii_conventions

import iii_conventions.multiAssignemnt.isLeapYear

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        if (this.year != other.year) return this.year.compareTo(other.year)
        if (this.month != other.month) return this.month.compareTo(other.month)
        if (this.dayOfMonth != other.dayOfMonth) return this.dayOfMonth.compareTo(other.dayOfMonth)
        return 0
    }

}

private fun daysInMonth(month: Int, year: Int): Int {
    return when (month) {
        3, 5, 8, 10 -> 30
        1 -> if (isLeapYear(year)) 29 else 28
        else -> 31
    }
}

operator fun MyDate.plus(timeInterval: TimeInterval): MyDate {
    return this.addTimeIntervals(timeInterval, 1)
}

operator fun MyDate.plus(repeatedTimeInterval: RepeatedTimeInterval): MyDate {
    return this.addTimeIntervals(repeatedTimeInterval.timeInterval, repeatedTimeInterval.number)
}

operator fun MyDate.rangeTo(other: MyDate): DateRange {
    return DateRange(this, other)
}

operator fun MyDate.inc(): MyDate {
    return when {
        dayOfMonth < daysInMonth(month, year) -> MyDate(year, month, dayOfMonth + 1)
        month < 11 -> MyDate(year, month + 1, 1)
        else -> MyDate(year + 1, 0, 1)
    }
}

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

operator fun TimeInterval.times(number: Int): RepeatedTimeInterval {
    return RepeatedTimeInterval(this, number)
}

class RepeatedTimeInterval(val timeInterval: TimeInterval, val number: Int = 1)

class DateRange(override val start: MyDate, override val endInclusive: MyDate) : ClosedRange<MyDate>, Iterable<MyDate> {
    override fun iterator(): Iterator<MyDate> {
        return MyDateIterator(start, endInclusive)
    }

}

class MyDateIterator(val start: MyDate, val endInclusive: MyDate) : Iterator<MyDate> {
    private var current = start

    override fun hasNext(): Boolean {
        return current <= endInclusive
    }

    override fun next(): MyDate {
        return current++
    }

}
