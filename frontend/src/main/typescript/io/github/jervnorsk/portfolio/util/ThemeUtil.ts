import {FontMap, loadFonts} from "./FontUtil";

export interface ThemeProps {
    fonts: FontMap
    default: {
        fontFamily: string
    }
}

export interface Theme extends ThemeProps {

}

export function loadTheme(theme: Theme, onComplete?: () => void) {
    return loadFonts(theme.fonts, onComplete)
}
