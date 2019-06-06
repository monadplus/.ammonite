interp.configureCompiler(_.settings.Ydelambdafy.tryToSetColon(List("inline")))
interp.configureCompiler(_.settings.YpartialUnification.value = true)
repl.prompt() = ">>> "

// Compiler plugins
import $plugin.$ivy.`org.spire-math::kind-projector:0.9.9`

// Dependencies
import $ivy.`org.typelevel::cats-core:1.5.0`, cats._, cats.data._, cats.implicits._
import $ivy.`org.typelevel::cats-effect:1.2.0`, cats.effect._, cats.effect.implicits._, cats.effect.concurrent._
import $ivy.`org.typelevel::cats-mtl-core:0.4.0`, cats.mtl._, cats.mtl.implicits._
import $ivy.`co.fs2::fs2-core:1.0.3`, fs2._
import $ivy.`eu.timepit::refined:0.9.4`, eu.timepit.refined._, eu.timepit.refined.api.Refined, eu.timepit.refined.auto._, eu.timepit.refined.numeric._
// Clashes cats.effect.concurrent.Ref with scala.meta.Ref
// import $ivy.`ch.epfl.scala::scalafix-core:0.9.4`, scalafix.v1._, scala.meta._
import $ivy.`com.chuusai::shapeless:2.3.3`, shapeless._
import $ivy.`io.circe::circe-core:0.10.0`, _root_.io.circe._, _root_.io.circe.syntax._
import $ivy.`io.circe::circe-generic:0.10.0`, _root_.io.circe.generic.semiauto._
import $ivy.`io.circe::circe-generic-extras:0.10.0`
import $ivy.`io.circe::circe-parser:0.10.0`
import $ivy.`org.tpolecat::doobie-core:0.6.0`, doobie._, doobie.implicits._
import $ivy.`org.tpolecat::doobie-h2:0.6.0`
import $ivy.`org.tpolecat::doobie-hikari:0.6.0`
import $ivy.`org.postgresql:postgresql:42.1.1.jre7`
import $ivy.`io.github.jmcardon::tsec-common:0.1.0-M3`
import $ivy.`io.github.jmcardon::tsec-common:0.1.0-M3`
import $ivy.`io.github.jmcardon::tsec-password:0.1.0-M3`
import $ivy.`io.github.jmcardon::tsec-mac:0.1.0-M3`
import $ivy.`io.github.jmcardon::tsec-signatures:0.1.0-M3`
import $ivy.`io.github.jmcardon::tsec-jwt-mac:0.1.0-M3`
import $ivy.`io.github.jmcardon::tsec-jwt-sig:0.1.0-M3`
import $ivy.`io.github.jmcardon::tsec-http4s:0.1.0-M3`
import tsec._, tsec.authentication._
import $ivy.`org.http4s::http4s-dsl:0.20.0-M7`
import $ivy.`org.http4s::http4s-blaze-server:0.20.0-M7`
import $ivy.`org.http4s::http4s-blaze-client:0.20.0-M7`
import $ivy.`org.http4s::http4s-circe:0.20.0-M7`
import org.http4s._, org.http4s.dsl.io._, org.http4s.server.blaze._, org.http4s.implicits._
import $ivy.`joda-time:joda-time:2.9.7`, org.joda.time._
import $ivy.`org.scalacheck::scalacheck:1.14.0`, org.scalacheck._, org.scalacheck.Prop.{forAll}, Gen._, Arbitrary._

// Implicits
import scala.concurrent.ExecutionContext.Implicits.global, scala.concurrent._, scala.concurrent.duration._
import java.util.concurrent.{Executors, ScheduledExecutorService}
import scala.util._

implicit val scheduledExecutorService: ScheduledExecutorService = Executors.newScheduledThreadPool(4)
implicit val contextShiftIO: ContextShift[IO] = IO.contextShift(global)
implicit val timerIO: Timer[IO] = IO.timer(global)

def putStrLn(s: String): IO[Unit] = IO { println(s) }
//def putStrLn[F[_]: Sync](s: String): F[Unit] = Sync[F].delay(println(s))
implicit class RunSyntax[F[_]: Effect, A](self: F[A]) {
  def run(): A = self.toIO.unsafeRunSync()
}

def boom[A] = IO.raiseError[A](new Exception("Boom !"))
