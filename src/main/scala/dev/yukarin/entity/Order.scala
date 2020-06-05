package dev.yukarin.entity

sealed trait Order
case object Order {
  case object ASC extends Order
  case object DESC extends Order
}
