interp.configureCompiler(_.settings.Ydelambdafy.tryToSetColon(List("inline")))
interp.configureCompiler(_.settings.YpartialUnification.value = true)
repl.prompt() = "λ> "

import $ivy.`org.typelevel::cats-core:1.5.0`, cats._, cats.data._, cats.implicits._
import $ivy.`org.typelevel::cats-effect:1.2.0`, cats.effect._, cats.effect.implicits._
import $ivy.`org.typelevel::cats-mtl-core:0.4.0`, cats.mtl._, cats.mtl.implicits._
import $ivy.`co.fs2::fs2-core:1.0.3`, fs2._

import scala.concurrent.ExecutionContext.Implicits.global, scala.concurrent._, scala.concurrent.duration._

implicit val contextShiftIO: ContextShift[IO] = IO.contextShift(global)
implicit val timerIO: Timer[IO] = IO.timer(global)