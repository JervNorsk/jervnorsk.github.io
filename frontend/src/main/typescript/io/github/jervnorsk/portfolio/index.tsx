import {Text} from "native-base";
import {createContext} from "./util/ContextUtil";
import {Outfit} from "./asset/Fonts";
import Header from "./component/Header";
import Root from "./component/Root";
import Container from "./component/Container";
import {CSS} from "./index.css";
import Brand from "./component/Brand";
import IndexScreen from "./screen/IndexScreen";


export default () => {
    const context = createContext({
        theme: {
            fonts: {
                Outfit
            },
            default: {
                fontFamily: "Outfit"
            }
        }
    })
    return (
        <Root context={context}>
            <IndexScreen />
        </Root>
    )
};
