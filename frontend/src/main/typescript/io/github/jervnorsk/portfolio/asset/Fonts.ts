import {
    Outfit_100Thin,
    Outfit_200ExtraLight,
    Outfit_300Light,
    Outfit_400Regular,
    Outfit_500Medium,
    Outfit_600SemiBold,
    Outfit_700Bold,
    Outfit_800ExtraBold,
    Outfit_900Black
} from "@expo-google-fonts/outfit";
import {createFontWeight, Font} from "../util/FontUtil";

export const Outfit: Font = {
    100: createFontWeight({
        normal: Outfit_100Thin
    }),
    200: createFontWeight({
        normal: Outfit_200ExtraLight
    }),
    300: createFontWeight({
        normal: Outfit_300Light
    }),
    400: createFontWeight({
        normal: Outfit_400Regular
    }),
    500: createFontWeight({
        normal: Outfit_500Medium
    }),
    600: createFontWeight({
        normal: Outfit_600SemiBold
    }),
    700: createFontWeight({
        normal: Outfit_700Bold
    }),
    800: createFontWeight({
        normal: Outfit_800ExtraBold
    }),
    900:
        createFontWeight({
            normal: Outfit_900Black
        })
}
