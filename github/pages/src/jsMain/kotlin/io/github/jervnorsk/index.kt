package io.github.jervnorsk

//import mui.material.CssBaseline
import kotlinx.browser.document
import react.FC
import react.Props
import react.create
import react.dom.render

fun main() {
   render(Main.create(), document.getElementById("main")!!)
}

private val Main = FC<Props> {

}