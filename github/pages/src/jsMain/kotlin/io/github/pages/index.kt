package io.github.pages

import io.github.pages.kotlin.react.KotlinReactApp
import kotlinx.browser.document
import react.FC
import react.Props
import react.create
import react.dom.render
import react.router.Route
import react.router.Routes
import react.router.dom.BrowserRouter

fun main() {
   render(App.create(), document.getElementById("main")!!)
}

val App = FC<Props> {
   BrowserRouter {
      Routes {
         Route { path ="/"; element = KotlinReactApp.create() }
      }
   }
}