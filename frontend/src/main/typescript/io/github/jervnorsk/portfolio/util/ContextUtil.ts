import {Theme, ThemeProps} from "./ThemeUtil";
import {AssetController} from "./AssetUtil";

export interface ContextProps {
    theme?: ThemeProps
}

export interface Context extends ContextProps {
    theme?: Theme,
    asset?: AssetController
}

export const createContext = (props: ContextProps): Context => {
    return {...props }
}
