// Automatically generated - do not modify!

@file:JsModule("typescript")
@file:JsNonModule

package typescript

/**
 * Read tsconfig.json file
 * @param fileName The path to the config file
 */
external fun readJsonConfigFile(fileName: String, readFile: (path: String) -> String?): TsConfigSourceFile
