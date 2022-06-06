package kr.ac.kgu.app.trail.util.parser

import java.lang.Exception
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

const val DATE_FORMAT = "yyyy-MM-dd HH:mm:ss"



fun getCurrentDate(): Calendar = Calendar.getInstance()


fun parseDate(calendar: Calendar):String{
    val formatter: DateFormat = SimpleDateFormat(DATE_FORMAT, Locale.KOREA)
    return formatter.format(calendar.time)
}

fun addMinuteDate(calendar: Calendar,minute:Int)= calendar.add(Calendar.MINUTE,minute)



fun parseSelectedDate(date: Date): String {
    val formatter: DateFormat = SimpleDateFormat(DATE_FORMAT, Locale.KOREA)
    return formatter.format(date)
}

fun parseDate(selectedDate: String): Date? {
    val formatter: DateFormat = SimpleDateFormat(DATE_FORMAT, Locale.KOREA)
    return try {
        formatter.parse(selectedDate)
    } catch (e: ParseException) {
        Date()
    }
}

fun parseNoYearDate(selectedDate: String): String {
    return try {
        selectedDate.substring(0, selectedDate.length - 5)
    } catch (e: Exception) {
        ""
    }
}

