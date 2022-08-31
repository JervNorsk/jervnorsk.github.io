import {loadFonts} from "./FontUtil";
import {Context} from "./ContextUtil";
import {ViewProps} from "react-native";

export interface ScreenProps extends ViewProps {
    context: Context
}
