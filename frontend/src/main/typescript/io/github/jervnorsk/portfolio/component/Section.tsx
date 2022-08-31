import React, {ReactElement} from "react";
import {View} from "native-base";
import {ComponentProps} from "../util/ComponentUtil";
import {CSS} from "../index.css";

export default ({from}: SectionProps) => {
    return (
        <View style={CSS.section}>
            {from({})}
        </View>
    )
}

export interface SectionProps {
    from: (props?: ComponentProps) => JSX.Element
}
