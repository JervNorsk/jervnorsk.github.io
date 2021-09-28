package com.github.turansky.csstype

internal const val NAMED_COLOR = "NamedColor"

private val VALUE_MAP = mapOf(
    "transparent" to "rgba(0,0,0,0)",

    "aliceblue" to "#F0F8FF",
    "antiquewhite" to "#FAEBD7",
    "aqua" to "#00FFFF",
    "aquamarine" to "#7FFFD4",
    "azure" to "#F0FFFF",
    "beige" to "#F5F5DC",
    "bisque" to "#FFE4C4",
    "black" to "#000000",
    "blanchedalmond" to "#FFEBCD",
    "blue" to "#0000FF",
    "blueviolet" to "#8A2BE2",
    "brown" to "#A52A2A",
    "burlywood" to "#DEB887",
    "cadetblue" to "#5F9EA0",
    "chartreuse" to "#7FFF00",
    "chocolate" to "#D2691E",
    "coral" to "#FF7F50",
    "cornflowerblue" to "#6495ED",
    "cornsilk" to "#FFF8DC",
    "crimson" to "#DC143C",
    "cyan" to "#00FFFF",
    "darkblue" to "#00008B",
    "darkcyan" to "#008B8B",
    "darkgoldenrod" to "#B8860B",
    "darkgray" to "#A9A9A9",
    "darkgreen" to "#006400",
    "darkgrey" to "#A9A9A9",
    "darkkhaki" to "#BDB76B",
    "darkmagenta" to "#8B008B",
    "darkolivegreen" to "#556B2F",
    "darkorange" to "#FF8C00",
    "darkorchid" to "#9932CC",
    "darkred" to "#8B0000",
    "darksalmon" to "#E9967A",
    "darkseagreen" to "#8FBC8F",
    "darkslateblue" to "#483D8B",
    "darkslategray" to "#2F4F4F",
    "darkslategrey" to "#2F4F4F",
    "darkturquoise" to "#00CED1",
    "darkviolet" to "#9400D3",
    "deeppink" to "#FF1493",
    "deepskyblue" to "#00BFFF",
    "dimgray" to "#696969",
    "dimgrey" to "#696969",
    "dodgerblue" to "#1E90FF",
    "firebrick" to "#B22222",
    "floralwhite" to "#FFFAF0",
    "forestgreen" to "#228B22",
    "fuchsia" to "#FF00FF",
    "gainsboro" to "#DCDCDC",
    "ghostwhite" to "#F8F8FF",
    "gold" to "#FFD700",
    "goldenrod" to "#DAA520",
    "gray" to "#808080",
    "green" to "#008000",
    "greenyellow" to "#ADFF2F",
    "grey" to "#808080",
    "honeydew" to "#F0FFF0",
    "hotpink" to "#FF69B4",
    "indianred" to "#CD5C5C",
    "indigo" to "#4B0082",
    "ivory" to "#FFFFF0",
    "khaki" to "#F0E68C",
    "lavender" to "#E6E6FA",
    "lavenderblush" to "#FFF0F5",
    "lawngreen" to "#7CFC00",
    "lemonchiffon" to "#FFFACD",
    "lightblue" to "#ADD8E6",
    "lightcoral" to "#F08080",
    "lightcyan" to "#E0FFFF",
    "lightgoldenrodyellow" to "#FAFAD2",
    "lightgray" to "#D3D3D3",
    "lightgreen" to "#90EE90",
    "lightgrey" to "#D3D3D3",
    "lightpink" to "#FFB6C1",
    "lightsalmon" to "#FFA07A",
    "lightseagreen" to "#20B2AA",
    "lightskyblue" to "#87CEFA",
    "lightslategray" to "#778899",
    "lightslategrey" to "#778899",
    "lightsteelblue" to "#B0C4DE",
    "lightyellow" to "#FFFFE0",
    "lime" to "#00FF00",
    "limegreen" to "#32CD32",
    "linen" to "#FAF0E6",
    "magenta" to "#FF00FF",
    "maroon" to "#800000",
    "mediumaquamarine" to "#66CDAA",
    "mediumblue" to "#0000CD",
    "mediumorchid" to "#BA55D3",
    "mediumpurple" to "#9370D8",
    "mediumseagreen" to "#3CB371",
    "mediumslateblue" to "#7B68EE",
    "mediumspringgreen" to "#00FA9A",
    "mediumturquoise" to "#48D1CC",
    "mediumvioletred" to "#C71585",
    "midnightblue" to "#191970",
    "mintcream" to "#F5FFFA",
    "mistyrose" to "#FFE4E1",
    "moccasin" to "#FFE4B5",
    "navajowhite" to "#FFDEAD",
    "navy" to "#000080",
    "oldlace" to "#FDF5E6",
    "olive" to "#808000",
    "olivedrab" to "#6B8E23",
    "orange" to "#FFA500",
    "orangered" to "#FF4500",
    "orchid" to "#DA70D6",
    "palegoldenrod" to "#EEE8AA",
    "palegreen" to "#98FB98",
    "paleturquoise" to "#AFEEEE",
    "palevioletred" to "#DB7093",
    "papayawhip" to "#FFEFD5",
    "peachpuff" to "#FFDAB9",
    "peru" to "#CD853F",
    "pink" to "#FFC0CB",
    "plum" to "#DDA0DD",
    "powderblue" to "#B0E0E6",
    "purple" to "#800080",
    "red" to "#FF0000",
    "rosybrown" to "#BC8F8F",
    "royalblue" to "#4169E1",
    "saddlebrown" to "#8B4513",
    "salmon" to "#FA8072",
    "sandybrown" to "#F4A460",
    "seagreen" to "#2E8B57",
    "seashell" to "#FFF5EE",
    "sienna" to "#A0522D",
    "silver" to "#C0C0C0",
    "skyblue" to "#87CEEB",
    "slateblue" to "#6A5ACD",
    "slategray" to "#708090",
    "slategrey" to "#708090",
    "snow" to "#FFFAFA",
    "springgreen" to "#00FF7F",
    "steelblue" to "#4682B4",
    "tan" to "#D2B48C",
    "teal" to "#008080",
    "thistle" to "#D8BFD8",
    "tomato" to "#FF6347",
    "turquoise" to "#40E0D0",
    "violet" to "#EE82EE",
    "wheat" to "#F5DEB3",
    "white" to "#FFFFFF",
    "whitesmoke" to "#F5F5F5",
    "yellow" to "#FFFF00",
    "yellowgreen" to "#9ACD32",
)
