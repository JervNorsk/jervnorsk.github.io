import {Animated, Easing, StyleSheet, View} from "react-native";
import {ComponentWithContextProps} from "../util/ComponentUtil";
import {useAssets} from "../util/AssetUtil";
import {ScreenProps} from "../util/ScreenUtil";
import {extendTheme, NativeBaseProvider} from "native-base";
import {useEffect, useState} from "react";

export default (props: ComponentWithContextProps) => {
    useAssets(props.context)

    return (
        <View style={{flex: 1}}>
            <MainView {...props} />
            <LoadView {...props} />
        </View>
    )
}

const MainView = ({children, context}: ComponentWithContextProps) => {
    const theme = extendTheme({
        fontConfig: context.theme!.fonts,
        fonts: {
            heading: context.theme!.default.fontFamily,
            body: context.theme!.default.fontFamily,
            mono: context.theme!.default.fontFamily,
        }
    })
    return (
        <NativeBaseProvider theme={theme}>
            {children}
        </NativeBaseProvider>
    )
}

const LoadView = ({children, context}: ComponentWithContextProps) => {
    const position = {
        y: new Animated.Value(0)
    }

    useEffect(
        () => {
            if (context.asset!.success) {
                Animated.timing(position.y, {
                    useNativeDriver: false,
                    toValue: -window.innerHeight,
                    duration: 800,
                    easing: Easing.linear
                }).start()
            }
        },
        [context.asset!.success]
    )

    return (
        <Animated.View style={{
            position: "absolute",

            width: "100vw",
            height: "100vh",

            padding: "1rem",

            justifyContent: "flex-end",
            alignItems: "flex-end",

            backgroundColor: "#000",

            transform: [
                {translateY: position.y}
            ]
        }}>
            <ProgressBar context={context}/>
        </Animated.View>
    )
}

const ProgressBar = ({children, context}: ScreenProps) => {
    const [width, setWidth] = useState<number>(0)
    const [opacity, setOpacity] = useState<number>(1)

    useEffect(
        () => {
            setWidth(context.asset!.percentage)

            if (context.asset!.percentage >= 100) {
                setOpacity(0)
            }
        },
        [context.asset!.percentage]
    )

    return (
        <View style={{
            width: "10rem",
            height: ".5rem",
        }}>
            <View style={
                StyleSheet.flatten([
                    {
                        width: `${width}%`,
                        height: "100%",

                        backgroundColor: "#FFF",

                        opacity
                    }
                ])
            }>
            </View>
        </View>
    )
}
