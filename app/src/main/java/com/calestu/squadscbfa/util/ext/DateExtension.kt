package com.calestu.squadscbfa.util.ext

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


fun Date.dateNow(): Date {
    return calendar().time
}

fun Date.calendar(): Calendar {
    return Calendar.getInstance()
}

fun Date.formatToLocalDate(): String{
    val sdf= SimpleDateFormat("yyyMMdd", Locale.getDefault())
    return sdf.format(this)
}

/**
 * Pattern: yyyy-MM-dd HH:mm:ss
 */
fun Date.formatToServerDateTimeDefaults(): String{
    val sdf= SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    return sdf.format(this)
}

fun Date.formatToTruncatedDateTime(): String{
    val sdf= SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault())
    return sdf.format(this)
}

/**
 * Pattern: yyyy-MM-dd
 */
fun Date.formatToServerDateDefaults(): String{
    val sdf= SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return sdf.format(this)
}

/**
 * Pattern: HH:mm:ss
 */
fun Date.formatToServerTimeDefaults(): String{
    val sdf= SimpleDateFormat("HH:mm:ss", Locale.getDefault())
    return sdf.format(this)
}

/**
 * Pattern: dd/MM/yyyy HH:mm:ss
 */
fun Date.formatToViewDateTimeDefaults(): String{
    val sdf= SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
    return sdf.format(this)
}

/**
 * Pattern: dd/MM/yyyy
 */
fun Date.formatToViewDateDefaults(): String{
    val sdf= SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return sdf.format(this)
}

/**
 * Pattern: HH:mm:ss
 */
fun Date.formatToViewTimeDefaults(): String{
    val sdf= SimpleDateFormat("HH:mm:ss", Locale.getDefault())
    return sdf.format(this)
}

/**
 * Extension method to get Date for String with specified format.
 */
fun String.dateInFormat(format: String): Date? {
    val dateFormat = SimpleDateFormat(format, Locale.US)
    var parsedDate: Date? = null
    try {
        parsedDate = dateFormat.parse(this)
    } catch (ignored: ParseException) {
        ignored.printStackTrace()
    }
    return parsedDate
}

/**
 * Convert a given date to milliseconds
 */
fun Date.toMillis() : Long {
    val calendar = calendar()
    calendar.time = this
    return calendar.timeInMillis
}

/**
 * Checks if dates are same
 */
fun Date.isSame(to : Date) : Boolean {
    val sdf = SimpleDateFormat("yyyMMdd", Locale.getDefault())
    return sdf.format(this) == sdf.format(to)
}

fun Date.isSame(to : String) : Boolean {
    val sdf = SimpleDateFormat("yyyMMdd", Locale.getDefault())
    return sdf.format(this) == to
}

/**
 * Add field date to current date
 */
fun Date.add(field: Int, amount: Int): Date{
    val cal = calendar()
    cal.time=this
    cal.add(field, amount)

    this.time = cal.time.time

    cal.clear()

    return this
}

fun Date.set(field: Int, value: Int): Date{
    val calendar = calendar()
    calendar.time = this
    calendar.set(field, value)
    this.time = calendar.time.time
    calendar.clear()
    return this
}

fun Date.setYear(year: Int): Date{
    return set(Calendar.YEAR, year)
}
fun Date.setMonth(month: Int): Date {
    return set(Calendar.MONTH, month)
}
fun Date.setDay(day: Int): Date{
    return set(Calendar.DAY_OF_MONTH, day)
}
fun Date.setHour(hour: Int): Date{
    return set(Calendar.HOUR_OF_DAY, hour)
}
fun Date.setMinute(minute: Int): Date{
    return set(Calendar.MINUTE, minute)
}
fun Date.setSecond(second: Int): Date{
    return set(Calendar.SECOND, second)
}

fun Date.addYears(years: Int): Date{
    return add(Calendar.YEAR, years)
}
fun Date.addMonths(months: Int): Date {
    return add(Calendar.MONTH, months)
}
fun Date.addDays(days: Int): Date{
    return add(Calendar.DAY_OF_MONTH, days)
}
fun Date.addHours(hours: Int): Date{
    return add(Calendar.HOUR_OF_DAY, hours)
}
fun Date.addMinutes(minutes: Int): Date{
    return add(Calendar.MINUTE, minutes)
}
fun Date.addSeconds(seconds: Int): Date{
    return add(Calendar.SECOND, seconds)
}

fun Date.isWeekend(): Boolean {
    return isSaturday() || isSunday()
}

fun Date.isSunday(): Boolean {
    return calendar().get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY
}

fun Date.isMonday(): Boolean {
    return calendar().get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY
}

fun Date.isTuesday(): Boolean {
    return calendar().get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY
}

fun Date.isWednesday(): Boolean {
    return calendar().get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY
}

fun Date.isThursday(): Boolean {
    return calendar().get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY
}

fun Date.isFriday(): Boolean {
    return calendar().get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY
}

fun Date.isSaturday(): Boolean {
    return calendar().get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
}