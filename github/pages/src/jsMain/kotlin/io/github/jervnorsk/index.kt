package io.github.jervnorsk

import io.github.jervnorsk.kotlin.react.router.HashRouterExample
import kotlinx.browser.document
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
import react.dom.render
import react.key
import react.router.Outlet
import react.router.Route
import react.router.Routes
import react.router.dom.HashRouter
import react.router.dom.Link
import react.router.useParams

fun main() {
   render(App.create(), document.getElementById("main")!!)
}

private val App = FC<Props> {
   HashRouterExample.App {}
}