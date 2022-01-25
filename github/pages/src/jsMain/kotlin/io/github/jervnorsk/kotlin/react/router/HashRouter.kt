package io.github.jervnorsk.kotlin.react.router

import react.FC
import react.Props
import react.create
import react.dom.html.ReactHTML
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.h2
import react.dom.html.ReactHTML.header
import react.dom.html.ReactHTML.li
import react.dom.html.ReactHTML.nav
import react.dom.html.ReactHTML.ul
import react.key
import react.router.Route
import react.router.Routes
import react.router.dom.HashRouter
import react.router.dom.Link
import react.router.useParams

// ############################################################################
// ##### [JS Main]

private val App = FC<Props> {
   HashRouter {
      div {
         Header {}
         Main {}
      }
   }
}

private val Header = FC<Props> {
   header {
      nav {
         ul {
            li { Link { to = "/"; +"Home" } }
            li { Link { to = "/roster"; +"Roster" } }
            li { Link { to = "/schedule"; +"Schedule" } }
         }
      }
   }
}

private val Main = FC<Props> {
   ReactHTML.main {
      Routes {
         Route { path = "/"; element = Home.create() }
         Route { path = "/roster/*"; element = Roster.create() }
         Route { path = "/schedule"; element = Schedule.create() }
      }
   }
}

private val Home = FC<Props> {
   div {
      h1 { +"Welcome to the Tornadoes Website!" }
   }
}

private val Roster = FC<Props> {
   Routes {
      Route { path = "/"; element = FullRoster.create() }
      Route { path = "/:number"; element = Player.create() }
   }
}

private val FullRoster = FC<Props> {
   div {
      ul {
         PlayerAPI.map {
            li {
               key = it.number.toString()
               
               Link { to = "/roster/${it.number}"; +it.name }
            }
         }
      }
   }
}

private val Player = FC<Props> {
   val params = useParams()
   console.log(params)
   
   val player = PlayerAPI.find { it.number == params["number"]!!.toInt() }
   
   if (player == null) {
      div {
         +"Sorry, but the player was not found"
      }
      return@FC
   }
   
   div {
      h1 { +"${player.name} #${player.number}" }
      h2 { +"Position: ${player.position}" }
      Link { to = "/roster"; +"Back" }
   }
}

private data class PlayerData(
   val number: Int,
   val name: String,
   val position: String
)

private val PlayerAPI = arrayOf(
   PlayerData(1, "Ben Blocker", "G"),
   PlayerData(2, "Dave Defender", "D"),
   PlayerData(3, "Sam Sweeper", "D"),
   PlayerData(4, "Matt Midfielder", "M"),
   PlayerData(5, "William Winger", "M"),
   PlayerData(6, "Fillipe Forward", "F"),
)

private val Schedule = FC<Props> {
   div {
      ul {
         li { +"6/5 @ Evergreens" }
         li { +"6/8 vs Kickers" }
         li { +"6/14 @ United" }
      }
   }
}

// ############################################################################
// ##### [JS Main] React DSL

val ReactRouterExample.HashRouter: FC<Props>
   get() = App