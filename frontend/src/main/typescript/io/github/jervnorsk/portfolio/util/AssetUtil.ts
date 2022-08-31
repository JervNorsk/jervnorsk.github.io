import {Context} from "./ContextUtil";
import {loadTheme} from "./ThemeUtil";
import {useEffect, useState} from "react";

export type Asset<P> =
    {
        type: "asset"
    }
    & P

export function createAsset<P>(props: P) {
    return {type: "asset", ...props} as Asset<P>
}

export function computeAssets(target: any, action: () => void) {
    if (!target) {
        return
    }

    if (typeof target == "object") {
        if (target.type == 'asset') {
            action()
        } else {
            Object.values(target).forEach((value) => {
                computeAssets(value, action)
            })
        }
    }
}

export type AssetController = {
    success: boolean,
    error: Error | null,
    percentage: number,
}

export function useAssets(context: Context) {
    const [success, setSuccess] = useState<boolean>(false)
    const [error, setError] = useState<Error | null>(null)
    const [percentage, setPercentage] = useState<number>(0)

    useEffect(() => {
        let assetCount = 0
        computeAssets(context, () => assetCount++)

        function onComplete() {
            setPercentage((prev) => prev + (100 / assetCount))
        }

        Promise.all([
            !context.theme ? null : loadTheme(context.theme, onComplete)
        ])
            .then(() => setSuccess(true))
            .catch((error) => setError(error))
    }, [])

    context.asset = {success, error, percentage}
}
