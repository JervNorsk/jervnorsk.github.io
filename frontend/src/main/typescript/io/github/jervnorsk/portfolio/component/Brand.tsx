import React from "react";
import {Text, View} from "native-base";
import {ComponentProps} from "../util/ComponentUtil";
import {CSS} from "../index.css";

export default (props?: ComponentProps) => {
    return (
        <View style={CSS.brand} {...props}>
            <Text style={CSS.brandText}>
                Jerv Norsk
            </Text>
        </View>
    )
}
