import Foundation
import Capacitor

/**
 * Please read the Capacitor iOS Plugin Development Guide
 * here: https://capacitorjs.com/docs/plugins/ios
 */
@objc(MonetColorPlugin)
public class MonetColorPlugin: CAPPlugin, CAPBridgedPlugin {
    public let identifier = "MonetColorPlugin"
    public let jsName = "MonetColor"
    public let pluginMethods: [CAPPluginMethod] = [
        CAPPluginMethod(name: "echo", returnType: CAPPluginReturnPromise)
    ]
    private let implementation = MonetColor()

    @objc func echo(_ call: CAPPluginCall) {
        let value = call.getString("value") ?? ""
        call.resolve([
            "value": implementation.echo(value)
        ])
    }
}
