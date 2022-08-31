import React from "react";
import {View} from "native-base";
import {ComponentProps} from "../util/ComponentUtil";
import {CSS} from "../index.css";

export default (props: ComponentProps) => {
    return (
        <View style={CSS.container} {...props} />
    )
}
