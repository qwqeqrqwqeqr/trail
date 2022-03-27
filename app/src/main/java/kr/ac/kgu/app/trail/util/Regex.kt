package kr.ac.kgu.app.trail.util

import java.util.regex.Pattern

class Regex {
    companion object {
        fun isEmailValid(email: String): Boolean {
            val expression = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$"
            val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
            val matcher = pattern.matcher(email)
            return matcher.matches()
        }

        fun isValidName(name: String): Boolean {
            val expression = "^[가-힣ㄱ-ㅎa-zA-Z0-9._ -]{2,}\$"
            val pattern = Pattern.compile(expression)
            return  pattern.matcher(name).matches()
        }
        //숫자, 문자, 특수문자 중 2가지 포함(8~15자)

        fun isPhoneNumberValid(phonenumber:String):Boolean{
            val expression = "^01(?:0|1|[6-9]) - (?:\\d{3}|\\d{4}) - \\d{4}\$\n"
            val pattern = Pattern.compile(expression)
            val matcher = pattern.matcher(phonenumber)
            return matcher.matches()
        }

        fun isPasswordValid(password:String):Boolean{
            val expression = "^(?=.*[a-zA-Z0-9])(?=.*[a-zA-Z!@#$%^&*])(?=.*[0-9!@#$%^&*]).{8,15}$"
            val pattern = Pattern.compile(expression)
            val matcher = pattern.matcher(password)
            return matcher.matches()
        }


    }
}