import {Context} from "./ContextUtil";
import {InterfaceViewProps} from "native-base/lib/typescript/components/basic/View/types";

export interface ComponentProps extends InterfaceViewProps {

}

export interface ComponentWithContextProps extends ComponentProps {
    context: Context
}
