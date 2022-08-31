package io.github.jervnorsk.portfolio

import kotlinext.js.require

fun main() {
    require("expo").registerRootComponent {
        require("./io/github/jervnorsk/portfolio/index.tsx").default()
    };
}
