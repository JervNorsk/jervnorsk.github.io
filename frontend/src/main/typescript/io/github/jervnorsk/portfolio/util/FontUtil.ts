import {loadAsync} from "expo-font";
import {createAsset} from "./AssetUtil";

export interface FontMap {
    [p: string]: Font
}

export interface Font {
    100?: FontWeight,
    200?: FontWeight,
    300?: FontWeight,
    400?: FontWeight,
    500?: FontWeight,
    600?: FontWeight,
    700?: FontWeight,
    800?: FontWeight,
    900?: FontWeight
}

export interface FontWeight {
    normal: any
}

export function createFontWeight(props: FontWeight): FontWeight {
    return createAsset(props)
}

export function loadFonts(fonts: FontMap, onComplete?: () => void) {
    const expoFonts = toExpoFontMap(fonts);
    return loadAsync(expoFonts)
        .finally(() => {
            Object.keys(expoFonts).forEach((it, index) => onComplete ? onComplete() : null)
        })
}

function toExpoFontMap(fonts: FontMap): { [p: string]: any } {
    const result: { [p: string]: any } = {}

    Object.entries(fonts).forEach(([fontId, font]) => {
        Object.entries(font).forEach(([fontWeightId, fontWeight]) => {
            Object.entries(fontWeight).forEach(([fontTypeId, fontType]) => {
                let loadId = `${fontId}-${fontWeightId}`

                switch (fontTypeId) {
                    case "type":
                    case "normal":
                        break
                    default:
                        loadId += fontTypeId.charAt(0) + fontTypeId.substring(1)
                        break
                }

                result[loadId] = fontType
                switch (fontTypeId) {
                    case "normal":
                        fontWeight["normal"] = loadId
                        break
                }
            })
        })
    })

    return result
}
