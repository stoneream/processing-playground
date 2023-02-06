package app

import scala.util.Random

case class TikaTika(
    width: Int,
    height: Int,
    size: Int
) {
  private val random = new Random()

  def f: Seq[(Int, Int, (Int, Int, Int))] = {
    0.to(width, size).flatMap { x =>
      0.to(height, size).map { y =>
        (x, y, (random.nextInt(256), random.nextInt(256), random.nextInt(256)))
      }
    }
  }
}
