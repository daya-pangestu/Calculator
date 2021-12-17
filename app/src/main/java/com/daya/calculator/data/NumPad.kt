package com.daya.calculator.data

data class NumPad(
    val char : Char,
)

val prederTeminateNumpad: List<NumPad> = listOf(
    NumPad('C'),
    NumPad('('),
    NumPad(')'),
    NumPad('/'),
    NumPad('7'),
    NumPad('8'),
    NumPad('9'),
    NumPad('*'),
    NumPad('4'),
    NumPad('5'),
    NumPad('6'),
    NumPad('-'),
    NumPad('1'),
    NumPad('2'),
    NumPad('3'),
    NumPad('+'),
    NumPad('.'),
    NumPad('0'),
    NumPad('%'),
    NumPad('=')
)

